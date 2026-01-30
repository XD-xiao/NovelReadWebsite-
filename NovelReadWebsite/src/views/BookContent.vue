<script setup>
import { ref, onMounted, computed, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getBookContentService, getBookChapterService } from '@/api/book.js'

const route = useRoute()
const router = useRouter()

// 从路由中获取参数（字符串类型）
const bookId = computed(() => route.params.bookId)
const currentChapterId = computed(() => {
  const id = route.params.chapterId
  return id ? Number(id) : null
})

// 响应式数据
const loading = ref(true)
const error = ref(null)
const chapterList = ref([]) // ← 改为响应式 ref

const chapterContent = ref('')
const bookTitle = ref('')

// 计算当前章节索引
const currentChapterIndex = computed(() => {
  return chapterList.value.findIndex(ch => ch.id === currentChapterId.value)
})

const prevChapter = computed(() => {
  const idx = currentChapterIndex.value
  return idx > 0 ? chapterList.value[idx - 1] : null
})

const nextChapter = computed(() => {
  const idx = currentChapterIndex.value
  return idx >= 0 && idx < chapterList.value.length - 1
      ? chapterList.value[idx + 1]
      : null
})

// 目录抽屉控制
const showTocDrawer = ref(false)

// 🌟 加载章节列表（目录）
const loadChapterList = async () => {
  if (!bookId.value) return

  try {
    const res = await getBookChapterService(bookId.value)
    // 假设返回格式：{ data: { chapters: [ { id, title }, ... ] } }
    // 或直接返回数组：{ data: [ { id, title }, ... ] }

    // 根据你的后端调整以下一行 👇
    chapterList.value = Array.isArray(res.data) ? res.data : (res.data.data || [])
  } catch (err) {
    console.error('加载目录失败:', err)
    chapterList.value = []
    // 不阻断内容加载，只影响目录
  }
}

import { useUserStore } from '@/stores'
const userStore = useUserStore()
// 🌟 加载章节内容
const loadChapterContent = async () => {
  const id = currentChapterId.value
  const bid = bookId.value

  if (!id || !bid) {
    error.value = '缺少书籍或章节 ID'
    loading.value = false
    return
  }

  try {
    loading.value = true
    error.value = null

    const res = await getBookContentService(userStore.token, id)
    const data = res.data.data || res.data.data

    bookTitle.value = data.title || `第${id}章`
    chapterContent.value = data.content || '<p>暂无内容</p>'
  } catch (err) {
    console.error('加载章节内容失败:', err)
    error.value = '章节加载失败，请稍后重试'
    chapterContent.value = ''
  } finally {
    loading.value = false
  }
}

// 导航方法（保持不变）
const goBack = () => {
  router.push(`/book/${bookId.value}`)
}

const openToc = () => {
  showTocDrawer.value = true
}

const closeToc = () => {
  showTocDrawer.value = false
}

const goToChapter = (chapterId) => {
  if (chapterId !== currentChapterId.value) {
    router.push(`/book/${bookId.value}/${chapterId}`)
  }
  closeToc()
}

const goToPrevChapter = () => {
  if (prevChapter.value) {
    goToChapter(prevChapter.value.id)
  }
}

const goToNextChapter = () => {
  if (nextChapter.value) {
    goToChapter(nextChapter.value.id)
  }
}

// 初始化：先加载目录，再加载内容（顺序不强制，可并行）
onMounted(async () => {
  await loadChapterList() // 先获取目录
  await loadChapterContent() // 再加载当前章节
})

// 监听 bookId 变化（理论上不会变，但更健壮）
watch(
    () => route.params.bookId,
    async (newBookId, oldBookId) => {
      if (newBookId !== oldBookId) {
        await loadChapterList()
        await loadChapterContent()
      }
    }
)

// 监听 chapterId 变化（切换章节）
watch(
    () => route.params.chapterId,
    (newId, oldId) => {
      if (newId !== oldId) {
        loadChapterContent()
      }
    }
)
</script>

<template>
  <div class="book-reader">
    <!-- 顶部导航 -->
    <header class="book-header">
      <button class="nav-btn back-btn" @click="goBack">← 返回</button>
      <h1 class="book-title">{{ bookTitle || '加载中...' }}</h1>
      <button class="nav-btn toc-btn" @click="openToc">目录</button>
    </header>

    <!-- 主内容区 -->
    <main class="book-content">
      <div v-if="loading">正在加载章节...</div>
      <div v-else-if="error" style="color: red; padding: 20px;">{{ error }}</div>
      <div v-else v-html="chapterContent"></div>
    </main>

    <!-- 底部翻章 -->
    <footer class="book-footer">
      <button
          class="page-btn prev-btn"
          :disabled="!prevChapter"
          @click="goToPrevChapter"
      >
        上一章
      </button>
      <span class="page-indicator">
        {{ currentChapterIndex >= 0 ? currentChapterIndex + 1 : '?' }} / {{ chapterList.length }}
      </span>
      <button
          class="page-btn next-btn"
          :disabled="!nextChapter"
          @click="goToNextChapter"
      >
        下一章
      </button>
    </footer>

    <!-- 目录抽屉 -->
    <Teleport to="body">
      <div
          v-if="showTocDrawer"
          class="drawer-overlay"
          @click="closeToc"
      ></div>
      <div v-if="showTocDrawer" class="toc-drawer">
        <div class="toc-header">
          <h2>目录</h2>
          <button class="close-btn" @click="closeToc">×</button>
        </div>
        <ul class="toc-list">
          <li
              v-for="item in chapterList"
              :key="item.id"
              class="toc-item"
              :class="{ active: item.id === currentChapterId }"
              @click="goToChapter(item.id)"
          >
            {{ item.title }}
          </li>
        </ul>
      </div>
    </Teleport>
  </div>
</template>

<style scoped>
/* 复用之前的样式，仅新增 .active */
.book-reader {
  display: flex;
  flex-direction: column;
  height: 100vh;
  max-width: 800px;
  margin: 0 auto;
  font-family: 'Segoe UI', sans-serif;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.book-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 16px 24px;
  background-color: #f8f9fa;
  border-bottom: 1px solid #e0e0e0;
  position: relative;
  z-index: 10;
}

.nav-btn {
  background: none;
  border: 1px solid #ccc;
  padding: 6px 12px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  color: #333;
}

.nav-btn:hover {
  background-color: #e9e9e9;
}

.book-title {
  margin: 0;
  font-size: 18px;
  font-weight: 600;
  color: #222;
  text-align: center;
  flex: 1;
  padding: 0 16px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.book-content {
  flex: 1;
  padding: 24px;
  overflow-y: auto;
  line-height: 1.7;
  font-size: 16px;
  color: #333;
  background-color: #fdfdfd;
}

.book-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 24px;
  background-color: #f8f9fa;
  border-top: 1px solid #e0e0e0;
}

.page-btn {
  padding: 10px 20px;
  font-size: 16px;
  border: none;
  border-radius: 6px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

.page-btn:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.page-btn:hover:not(:disabled) {
  background-color: #0056b3;
}

.page-indicator {
  font-size: 14px;
  color: #666;
}

.drawer-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.4);
  z-index: 999;
}

.toc-drawer {
  position: fixed;
  top: 0;
  right: 0;
  width: 280px;
  height: 100vh;
  background: white;
  box-shadow: -2px 0 10px rgba(0, 0, 0, 0.1);
  z-index: 1000;
  display: flex;
  flex-direction: column;
  animation: slideIn 0.3s ease-out;
}

@keyframes slideIn {
  from { transform: translateX(100%); }
  to { transform: translateX(0); }
}

.toc-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;
  border-bottom: 1px solid #eee;
}

.toc-header h2 {
  margin: 0;
  font-size: 18px;
  color: #333;
}

.close-btn {
  background: none;
  border: none;
  font-size: 24px;
  cursor: pointer;
  color: #888;
}

.close-btn:hover {
  color: #333;
}

.toc-list {
  list-style: none;
  padding: 0;
  margin: 0;
  overflow-y: auto;
  flex: 1;
}

.toc-item {
  padding: 12px 16px;
  border-bottom: 1px solid #f0f0f0;
  cursor: pointer;
  font-size: 15px;
  color: #444;
  transition: background 0.2s;
}

.toc-item:hover,
.toc-item.active {
  background-color: #e6f0ff;
  color: #007bff;
  font-weight: 500;
}

.toc-item.active {
  border-left: 3px solid #007bff;
}
</style>