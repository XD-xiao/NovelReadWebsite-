<script setup>
import {onMounted, ref} from 'vue'
import { Search } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router' // 👈 新增
const router = useRouter() // 👈 获取 router 实例
import {
  getHomePicService, getNovelsByFavoritesService, getNovelsByRatingService, getNovelsByViewsService,
} from '@/api/book.js'
onMounted(() => {
  getHomePic();
  getNovelsByViews();
  getNovelsByRating();
  getNovelsByFavorites();
})

// 模拟滚动图片（轮播）
const homePic = ref( [])
const getHomePic = async () => {
  const res = await getHomePicService()
  homePic.value = res.data.data
  console.log(res.data.message)
}

// 模拟小说数据
const novelsByViews =ref([])
const getNovelsByViews = async () => {
  const res = await getNovelsByViewsService(10)
  novelsByViews.value = res.data.data
  console.log(res.data.data)
}

const novelsByRating = ref([])
const getNovelsByRating = async () => {
  const res = await getNovelsByRatingService(10)
  novelsByRating.value = res.data.data
  console.log(res.data.data)
}

const novelsByFavorites = ref([])
const getNovelsByFavorites = async () => {
  const res = await getNovelsByFavoritesService(10)
  novelsByFavorites.value = res.data.data
  console.log(res.data.data)
}

// 搜索功能（可扩展）
const searchQuery = ref('')
const handleSearch = () => {
  if (searchQuery.value.trim()) {
    router.push({ path: '/search', query: { q: searchQuery.value } })
  }
}

const goBookDetail = (id) => {
  // 示例：从首页跳转
  router.push({ path: `/book/${id}` })
}

</script>

<template>
  <div class="home-page">
    <!-- 顶部蓝色区域 + 搜索栏 -->
    <header class="top-bar">
      <input
          v-model="searchQuery"
          @keyup.enter="handleSearch"
          type="text"
          placeholder="请输入小说名称或作者"
          class="search-input"
      />
      <Search @click="handleSearch" style="width: 25px; height: 25px; margin-left: 10px" color="gray" />
    </header>
    <div style="width: 100%;text-align: center">
      ！欢迎您的到来 ！
    </div>

    <!-- 滚动播放的图片（简易轮播） -->
    <div class="banner-slider">
      <div class="banner-images">
        <img
            v-for="(src, index) in homePic"
            :key="index"
            :src="src"
            alt="Banner"
            class="banner-img"
        />
      </div>
    </div>

    <!-- 小说列表 -->
    <main class="novel-sections">
      <!-- 观看排行 -->
      <section class="novel-list">
        <h2>观看排行</h2>
        <hr>
        <div v-for="novel in novelsByViews" :key="novel.id" class="novel-item" @click="goBookDetail(novel.id)" >
          <img :src="novel.cover" alt="封面" class="cover" />
          <div class="info">
            <div class="title">{{ novel.title }}</div>
            <div class="author">{{ novel.author }}</div>
          </div>
          <div class="metric">{{ novel.watch }}次观看</div>
        </div>
      </section>

      <!-- 评价排行 -->
      <section class="novel-list">
        <h2>评价排行</h2>
        <hr>
        <div v-for="novel in novelsByRating" :key="novel.id" class="novel-item" @click="goBookDetail(novel.id)">
          <img :src="novel.cover" alt="封面" class="cover" />
          <div class="info">
            <div class="title">{{ novel.title }}</div>
            <div class="author">{{ novel.author }}</div>
          </div>
          <div class="metric">{{ novel.rating }}分</div>
        </div>
      </section>

      <!-- 收藏排行 -->
      <section class="novel-list">
        <h2>收藏排行</h2>
        <hr>
        <div v-for="novel in novelsByFavorites" :key="novel.id" class="novel-item" @click="goBookDetail(novel.id)">
          <img :src="novel.cover" alt="封面" class="cover" />
          <div class="info">
            <div class="title">{{ novel.title }}</div>
            <div class="author">{{ novel.author }}</div>
          </div>
          <div class="metric">{{ novel.collect }}人收藏</div>
        </div>
      </section>
    </main>
  </div>
</template>

<style scoped>
.home-page {
  font-family: Arial, sans-serif;
  width: 100%;
}

/* 顶部蓝色区域 */
.top-bar {
  background-color: #91d0fb; /* DodgerBlue */
  padding: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.search-input {
  width: 300px;
  padding: 8px 12px;
  border: none;
  border-radius: 20px;
  outline: none;
}


/* 图片轮播（简易横向滚动） */
.banner-slider {
  overflow: hidden;
  margin: 5px 0;
  height: 200px;
}

.banner-images {
  display: flex;
  animation: scrollBanner 15s linear infinite;
}

.banner-img {
  height: 200px;
  flex-shrink: 0;
  width: 100%;
  object-fit: cover;
}

@keyframes scrollBanner {
  0% { transform: translateX(0); }
  100% { transform: translateX(calc(-100% * (3 - 1))); } /* 3张图 */
}

/* 小说列表区域 */
.novel-sections {
  display: flex;
  flex-direction: column;
  gap: 30px;
  padding: 0 20px;
}

.novel-list h2 {
  font-size: 20px;
  margin-bottom: 12px;
  color: #333;
}

.novel-item {
  display: flex;
  align-items: center;
  padding: 12px 0;
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
  margin-bottom: 10px;
}

.author {
  color: #666;
  font-size: 14px;
}

.metric {
  color: #1e90ff;
  font-weight: bold;
  min-width: 100px;
  text-align: right;
}
</style>
