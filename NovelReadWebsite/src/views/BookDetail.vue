<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRoute } from 'vue-router' // 👈 新增：用于获取路由参数
import {
  getBookInfoService,
} from '@/api/book.js'
import {
  addFavoriteService,
  addRatingService, deleteFavoriteService,
} from '@/api/userHome.js'

// 获取路由参数
const route = useRoute()
const bookId = computed(() => route.params.bookId) // 响应式 bookId

// 响应式数据
const book = ref(null)
const loading = ref(true)
const error = ref(null)

import { useUserStore } from "@/stores/index.js";
const store = useUserStore()

// 获取书籍详情
const fetchBookDetail = async () => {
  try {
    loading.value = true
    error.value = null

    const res = await getBookInfoService(bookId.value , store.token)
    // 假设后端返回结构为 { data: { ...bookData } }
    book.value = res.data.data
  } catch (err) {
    console.error('获取书籍信息失败:', err)
    error.value = '加载失败，请稍后重试'
  } finally {
    loading.value = false
  }
}

// 页面加载时获取数据
onMounted(() => {
  if (bookId.value) {
    fetchBookDetail()
  } else {
    error.value = '无效的书籍 ID'
    loading.value = false
  }
})

// ====== 以下是你原有的交互逻辑（稍作调整） ======
import { useRouter } from 'vue-router'
const router = useRouter()

const goBack = () => {
  router.push('/home')
}

// 收藏
const toggleCollect = async () => {
  if (book.value) {
    book.value.isCollected = !book.value.isCollected
  }
  if (book.value.isCollected) {
    const res = await addFavoriteService(userStore.token , book.value.id)
  }
  else {
    const res = await deleteFavoriteService(userStore.token , book.value.id)
  }
}

// 评分
const userStore = useUserStore()
const handleStarClick = async (score) => {
  if (book.value && book.value.userRating === null) {

    const res = await addRatingService(userStore.token, book.value.id ,score)

    book.value.userRating = score
  }
}

const handleRead = () => {
  if (book.value && book.value.chapters?.length > 0) {
    router.push(`/book/${book.value.id}/${book.value.chapters[0].id}`)
  }
}

const goBookChapter = (bookId, chapterId) => {
  router.push(`/book/${bookId}/${chapterId}`)
}

const hasUserRated = computed(() => book.value?.userRating !== null)
</script>

<template>
  <div class="book-detail-wrapper">
    <div class="back-btn-container">
      <button class="back-btn" @click="goBack">←</button>
    </div>

    <!-- 加载状态 -->
    <div v-if="loading" class="book-info-card">
      <p>加载中...</p>
    </div>

    <!-- 错误状态 -->
    <div v-else-if="error" class="book-info-card">
      <p style="color: red;">{{ error }}</p>
      <button @click="fetchBookDetail" style="margin-top: 1rem;">重试</button>
    </div>

    <!-- 正常内容 -->
    <div v-else-if="book" class="book-info-card">
      <div class="header-row">
        <img :src="book.cover" :alt="book.title" class="cover" />

        <div class="meta-info">
          <h1 class="title">{{ book.title }}</h1>
          <p class="author">作者：{{ book.author }}</p>

          <div class="status-row">
            <span
                class="status"
                :class="book.status === '已完结' ? 'completed' : 'ongoing'"
            >
              {{ book.status }}
            </span>
            <span class="last-updated">最后更新：{{ book.lastUpdated }}</span>
          </div>
        </div>
      </div>

      <div class="actions">
        <div class="rating-section">
          <div v-if="!hasUserRated" class="rate-prompt">
            <label>为本书评分：</label>
            <div class="stars">
              <span
                  v-for="star in 5"
                  :key="star"
                  @click="handleStarClick(star)"
                  class="star"
              >★</span>
            </div>
          </div>
          <div v-else class="rated-display">
            你评了 {{ book.userRating }} 分 · 本书评分：{{
              book.rating?.toFixed(1)
            }} / 5.0
          </div>
        </div>

        <button class="collect-btn" @click="toggleCollect">
          {{ book.isCollected ? '已收藏' : '收藏' }}
        </button>
      </div>

      <div class="summary">
        <h2>内容概要</h2>
        <p>{{ book.summary }}</p>
      </div>

      <div class="tags">
        <span v-for="tag in book.tags" :key="tag" class="tag">{{ tag }}</span>
      </div>

      <div class="toc">
        <h2>目录</h2>
        <ul class="chapter-list">
          <li v-for="chap in book.chapters" :key="chap.id">
            <a @click="goBookChapter(book.id, chap.id)">{{ chap.title }}</a>
          </li>
        </ul>
      </div>
    </div>

    <button
        v-if="!loading && book"
        class="read-btn"
        @click="handleRead"
    >
      立即阅读
    </button>
  </div>
</template>

<style scoped>
/* 最外层 wrapper：确保能撑开页面高度 */
.book-detail-wrapper {
  position: relative;
  width: 100%;
}

/* 返回按钮：固定在视口左上角 */
.back-btn-container {
  position: fixed;
  margin-top: 10px;
  top: 0.75rem;
  left: 0.75rem;
  z-index: 1001;
}

.back-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 36px;
  height: 36px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 50%;
  color: #333;
  font-size: 1.3rem;
  cursor: pointer;
  transition: all 0.2s ease;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  padding: 0;
  margin: 0;
  outline: none;
}

.back-btn:hover {
  background-color: #f5f5f5;
  transform: scale(1.1);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

/* 卡片内容区 */
.book-info-card {
  max-width: 900px;
  margin: 1rem auto;
  padding: 1rem;
  padding-bottom: 4rem; /* 为底部 fixed 按钮预留空间 */
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  font-family: 'Segoe UI', sans-serif;
  /* 关键：不要 min-height: 100vh 或类似限制 */
}

/* 封面与元信息同行 */
.header-row {
  display: flex;
  gap: 1.2rem;
  margin-bottom: 1.2rem;
  align-items: flex-start;
}

.cover {
  width: 100px;
  height: 130px;
  object-fit: cover;
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);
  flex-shrink: 0;
}

.meta-info {
  flex: 1;
  overflow: hidden;
}

.title {
  font-size: 1.6rem;
  margin: 0 0 0.3rem;
  color: #222;
  line-height: 1.3;
  word-wrap: break-word;
}

.author {
  color: #555;
  margin: 0 0 0.5rem;
  font-size: 0.95rem;
}

.status-row {
  display: flex;
  flex-wrap: wrap;
  gap: 0.8rem;
  align-items: center;
}

.status {
  padding: 0.25rem 0.65rem;
  border-radius: 20px;
  font-size: 0.8rem;
  font-weight: bold;
}

.status.completed {
  background-color: #e6f4ea;
  color: #137333;
}

.status.ongoing {
  background-color: #fff8e1;
  color: #d97706;
}

.last-updated {
  color: #777;
  font-size: 0.85rem;
}

/* 评分与收藏 */
.actions {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  gap: 0.8rem;
  margin-bottom: 1.3rem;
}

.rating-section {
  display: flex;
  flex-direction: column;
  gap: 0.3rem;
  flex: 1;
  min-width: 180px;
}

.rate-prompt {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.stars {
  display: flex;
  cursor: pointer;
}

.star {
  color: #ddd;
  font-size: 1.1rem;
}

.stars:hover .star {
  color: #ffc107;
}

.rated-display {
  font-size: 0.9rem;
  color: #444;
}

.collect-btn {
  padding: 0.5rem 0.9rem;
  background: #f0f0f0;
  border: 1px solid #ccc;
  border-radius: 6px;
  cursor: pointer;
  transition: all 0.2s;
  font-size: 0.95rem;
  white-space: nowrap;
}

.collect-btn:hover {
  background: #e0e0e0;
}

/* 内容概要 */
.summary h2,
.toc h2 {
  font-size: 1.15rem;
  margin: 0.6rem 0 0.5rem;
  color: #333;
}

.summary p {
  line-height: 1.6;
  color: #444;
  font-size: 0.95rem;
}

/* 标签 */
.tags {
  margin: 0.9rem 0;
}

.tag {
  display: inline-block;
  background: #eef5ff;
  color: #1976d2;
  padding: 0.25rem 0.65rem;
  border-radius: 20px;
  font-size: 0.8rem;
  margin-right: 0.4rem;
  margin-bottom: 0.4rem;
}

/* 目录：普通列表，随页面滚动 */
.toc h2 {
  margin-top: 1.2rem;
}

.toc .chapter-list {
  list-style: none;
  padding-left: 0;
  margin: 0;
  font-size: 0.9rem;
}

.toc .chapter-list li {
  margin-bottom: 0.2rem;
}

.toc .chapter-list li a {
  display: block;
  padding: 0.4rem 0.6rem;
  color: #333;
  text-decoration: none;
  transition: background 0.2s;
  border-radius: 4px;
}

.toc .chapter-list li a:hover {
  background: #f0f0f0;
}

/* 阅读按钮固定在底部居中 */
.read-btn {
  position: fixed;
  bottom: 1rem;
  left: 50%;
  transform: translateX(-50%);
  padding: 0.75rem 2rem;
  background: #1976d2;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 1.05rem;
  cursor: pointer;
  transition: background 0.2s;
  z-index: 1000;
  box-shadow: 0 4px 12px rgba(25, 118, 210, 0.3);
  white-space: nowrap;
}

.read-btn:hover {
  background: #1565c0;
}

/* 移动端适配 */
@media (max-width: 768px) {
  .back-btn-container {
    top: env(safe-area-inset-top, 0.75rem);
  }

  .read-btn {
    bottom: calc(env(safe-area-inset-bottom, 1rem) + 0.5rem);
  }

  .book-info-card {
    margin: 0.5rem auto;
    padding: 0.8rem;
    padding-bottom: 4rem;
  }
}
</style>