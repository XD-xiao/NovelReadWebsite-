<script setup>
import { ref, watch } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ArrowLeftBold, Search } from '@element-plus/icons-vue'
import { getSearchBooksService } from '@/api/book.js' // ← 引入你的 service

const route = useRoute()
const router = useRouter()

const searchQuery = ref(route.query.q || '')
const sortBy = ref('default')
const rawBooks = ref([])
const books = ref([])

// 获取并缓存原始数据
const getSearchBooks = async () => {
  const q = searchQuery.value.trim()
  if (!q) {
    rawBooks.value = []
    books.value = []
    return
  }

  try {
    const res = await getSearchBooksService(q)
    rawBooks.value = res.data?.data || []
    applySorting()
  } catch (error) {
    console.error('搜索失败:', error)
    rawBooks.value = []
    books.value = []
  }
}

// 本地排序
const applySorting = () => {
  let data = [...rawBooks.value]
  if (sortBy.value === 'favorites') {
    data.sort((a, b) => b.favorites - a.favorites)
  } else if (sortBy.value === 'rating') {
    data.sort((a, b) => b.rating - a.rating)
  }
  books.value = data
}

// 搜索并更新 URL
const handleSearch = () => {
  const q = searchQuery.value.trim()
  if (q !== (route.query.q || '')) {
    router.push({ path: '/search', query: { q } })
  }
  // 注意：路由变化会触发 route.query.q 的 watch，进而更新 searchQuery，再触发 getSearchBooks
}

// 监听路由变化（例如从外部链接进入）
watch(
    () => route.query.q,
    (newQ) => {
      searchQuery.value = newQ || ''
    },
    { immediate: true }
)

// 监听搜索词变化 → 重新请求
watch(searchQuery, () => {
  getSearchBooks()
}, { immediate: true })

// 监听排序变化 → 本地重排
watch(sortBy, () => {
  applySorting()
})

// 其他方法...
const goBackToHome = () => router.push('/')
const goBookDetail = (id) =>
    router.push({ path: `/book/${id}` })
</script>

<template>
  <div class="search-page">
    <!-- 顶部搜索栏 -->
    <header class="top-bar">
      <div class="back-button" @click="goBackToHome">
        <ArrowLeftBold style="width: 20px" />
      </div>
      <input
          v-model="searchQuery"
          @keyup.enter="handleSearch"
          type="text"
          placeholder="请输入小说名称或作者"
          class="search-input"
      />
      <Search @click="handleSearch" style="width: 25px; height: 25px; margin-left: 10px" color="gray" />
    </header>

    <!-- 返回按钮 -->


    <!-- 排序选项 -->
    <div class="sort-options">
      <button
          :class="{ active: sortBy === 'default' }"
          @click="sortBy = 'default'"
      >
        默认
      </button>
      <button
          :class="{ active: sortBy === 'favorites' }"
          @click="sortBy = 'favorites'"
      >
        最多收藏
      </button>
      <button
          :class="{ active: sortBy === 'rating' }"
          @click="sortBy = 'rating'"
      >
        评分
      </button>
    </div>

    <!-- 搜索结果 -->
    <main class="results-section">
      <div v-if="books.length === 0" class="no-results">
        未找到相关小说 😢
      </div>

      <div v-for="novel in books" :key="novel.id" class="novel-item" @click="goBookDetail(novel.id)" >
        <img :src="novel.cover" alt="封面" class="cover" />
        <div class="info">
          <div class="title">{{ novel.title }}</div>
          <div class="author">{{ novel.author }}</div>
        </div>
        <div class="metrics">
          <div>⭐ {{ novel.rating }}</div>
          <div>❤️ {{ novel.collect }}</div>
        </div>
      </div>
    </main>
  </div>
</template>

<style scoped>
.search-page {
  font-family: Arial, sans-serif;
  width: 100%;
  height: 100vh; /* 占满视口高度 */
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  overflow: hidden; /* 防止 body 出现双滚动条 */
}

.top-bar {
  flex-shrink: 0; /* 不被压缩 */
  background-color: #91d0fb;
  padding: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 10px;
}

.sort-options {
  flex-shrink: 0; /* 固定高度，不参与滚动 */
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-bottom: 16px;
}

/* 关键：让结果区域可滚动 */
.results-section {
  flex: 1; /* 占据剩余空间 */
  overflow-y: auto; /* 允许垂直滚动 */
  display: flex;
  flex-direction: column;
  gap: 16px;
  padding: 0 16px; /* 可选：增加左右内边距 */
}

.search-input {
  width: 300px;
  padding: 8px 12px;
  border: none;
  border-radius: 20px;
  outline: none;
  font-size: 14px;
}

/* 返回按钮 */
.back-button {
  padding-left: 0;
  padding-right: 15px;
  color: #1e90ff;
  cursor: pointer;
  font-weight: bold;
  text-align: left;
  font-size: 16px;
}

/* 排序选项 */
.sort-options {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-bottom: 16px;
}

.sort-options button {
  padding: 8px 16px;
  border: 1px solid #ccc;
  background: #f5f5f5;
  border-radius: 20px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.2s;
}

.sort-options button.active {
  background: #1e90ff;
  color: white;
  border-color: #1e90ff;
}

/* 结果区域 */
.results-section {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.no-results {
  text-align: center;
  color: #888;
  font-size: 18px;
  padding: 40px 0;
}

.novel-item {
  display: flex;
  align-items: center;
  padding: 12px 12px;
  border-bottom: 1px solid #eee;
}

.cover {
  width: 80px;
  height: 100px;
  object-fit: cover;
  margin-right: 16px;
  border-radius: 4px;
}

.info {
  flex: 1;
  text-align: left;
}

.title {
  font-weight: bold;
  font-size: 16px;
  margin-bottom: 8px;
}

.author {
  color: #666;
  font-size: 14px;
}

.metrics {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  min-width: 100px;
  font-size: 14px;
  color: #1e90ff;
  font-weight: bold;
}
</style>
