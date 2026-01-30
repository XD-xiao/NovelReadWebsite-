<script setup>
import {ref, reactive, onMounted} from 'vue'
import router from "@/router/index.js";
import {
  getUserInfoService,
  getUserViewHistoryService,
  getUserFavoriteService,
  getUserRatingService,

} from '@/api/userHome.js'
onMounted(() => {
  getUserInfo();
  getUserViewHistory();
  getUserFavorites();
  getUserRatings();
})
import { useUserStore } from '@/stores'
const userStore = useUserStore()


// 用户信息
const user = ref({})
const getUserInfo = async () => {
  const res = await getUserInfoService( userStore.token)
  user.value = res.data.data
}

// 模拟数据
const history = ref([
  // { id: 1, chapterId 1, novelTitle: '星辰大海', chapterTitle: '第5章：风暴前夕', time: '2025-11-20' },
])
const getUserViewHistory = async () => {
  const res = await getUserViewHistoryService(userStore.token)
  history.value = res.data.data
}

const favorites = ref([
  // { id: 101, title: '星辰大海', author: '银河旅人' },
  // { id: 102, title: '末日回响', author: '废土诗人' },
  // { id: 103, title: '时光之书', author: '钟摆先生' },
  // { id: 104, title: '迷雾之城', author: '夜行者' }
])
const getUserFavorites = async () => {
  const res = await getUserFavoriteService(userStore.token)
  favorites.value = res.data.data
}

const ratings = ref([
  // { id: 201, title: '星辰大海', author: '银河旅人', score: 5 },
  // { id: 202, title: '末日回响', author: '废土诗人', score: 4 },
  // { id: 203, title: '迷雾之城', author: '夜行者', score: 3 },
  // { id: 204, title: '机械之心', author: '齿轮博士', score: 4 },
  // { id: 205, title: '虚空旅人', author: '星尘', score: 2 }
])
const getUserRatings = async () => {
  const res = await getUserRatingService(userStore.token)
  ratings.value = res.data.data
}

// 控制各区块展开状态
const expandState = reactive({
  history: false,
  favorites: false,
  ratings: false
})

// 切换展开
const toggleExpand = (type) => {
  expandState[type] = !expandState[type]
}

// 获取显示的列表（最多3项或全部）
const getVisibleList = (list, type) => {
  return expandState[type] ? list : list.slice(0, 3)
}

// 退出登录
const handleLogout = () => {
  if (confirm('确定要退出登录吗？')) {
    router.push('/login')
  }
}

const goBookChapter = (bookId,chapterId) =>{
  router.push(`/book/${bookId}/${chapterId}`)
}
const goBook = (bookId) => {
  router.push(`/book/${bookId}`)
}

</script>

<template>
  <div class="user-homepage">
    <!-- 用户卡片 -->
    <div class="user-card">
<!--      <img :src="user.avatar" alt="头像" class="avatar" />-->
      <div v-if="user.gender === '女'" class="avatar" >
        <svg t="1764688406734" class="avatar" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="943" width="200" height="200"><path d="M426.666667 540.444444h170.666666v113.777778H426.666667z" fill="#FFFFFF" p-id="944"></path><path d="M711.111111 553.955556H312.888889c-62.577778 0-113.777778-51.2-113.777778-113.777778v-64.711111c0-157.155556 127.288889-284.444444 284.444445-284.444445h56.888888c157.155556 0 284.444444 127.288889 284.444445 284.444445v64.711111c0 63.288889-51.2 113.777778-113.777778 113.777778z" fill="#FFC44F" p-id="945"></path><path d="M874.666667 931.555556H149.333333c-7.822222 0-14.222222-6.4-14.222222-14.222223v-128c0-62.577778 51.2-113.777778 113.777778-113.777777h526.222222c62.577778 0 113.777778 51.2 113.777778 113.777777v128c0 7.822222-6.4 14.222222-14.222222 14.222223z" fill="#5CBAEA" p-id="946"></path><path d="M724.622222 273.777778h-419.555555V369.777778c0 102.4 82.488889 184.888889 184.888889 184.888889h49.777777c102.4 0 184.888889-82.488889 184.888889-184.888889V273.777778z" fill="#FFFFFF" p-id="947"></path><path d="M775.111111 647.111111H611.555556v-56.888889h99.555555c78.222222 0 142.222222-64 142.222222-142.222222V376.888889c0-172.8-140.088889-312.888889-312.888889-312.888889h-56.888888C310.755556 64 170.666667 204.088889 170.666667 376.888889v71.111111c0 78.222222 64 142.222222 142.222222 142.222222h99.555555v56.888889H248.888889c-78.222222 0-142.222222 64-142.222222 142.222222v128c0 23.466667 19.2 42.666667 42.666666 42.666667h725.333334c23.466667 0 42.666667-19.2 42.666666-42.666667v-128c0-78.222222-64-142.222222-142.222222-142.222222zM312.888889 533.333333c-46.933333 0-85.333333-38.4-85.333333-85.333333V376.888889c0-141.511111 114.488889-256 256-256h56.888888c141.511111 0 256 114.488889 256 256v71.111111c0 46.933333-38.4 85.333333-85.333333 85.333333h-53.333333c45.511111-40.533333 74.666667-99.555556 74.666666-164.977777V292.266667c0-30.577778-24.888889-56.177778-56.177777-56.177778H347.733333c-30.577778 0-56.177778 24.888889-56.177777 56.177778v76.088889C291.555556 433.777778 320.711111 492.8 366.222222 533.333333H312.888889z m35.555555-164.977777V292.977778h327.111112v74.666666c0 90.311111-73.244444 163.555556-163.555556 163.555556-90.311111 0.711111-163.555556-73.244444-163.555556-162.844444zM554.666667 590.222222v56.888889H469.333333v-56.888889h85.333334z m305.777777 312.888889H163.555556v-113.777778c0-46.933333 38.4-85.333333 85.333333-85.333333h526.222222c46.933333 0 85.333333 38.4 85.333333 85.333333v113.777778z" fill="#194F82" p-id="948"></path><path d="M424.533333 348.444444m-28.444444 0a28.444444 28.444444 0 1 0 56.888889 0 28.444444 28.444444 0 1 0-56.888889 0Z" fill="#194F82" p-id="949"></path><path d="M597.333333 348.444444m-28.444444 0a28.444444 28.444444 0 1 0 56.888889 0 28.444444 28.444444 0 1 0-56.888889 0Z" fill="#194F82" p-id="950"></path><path d="M563.2 436.622222c3.555556-7.111111 0.711111-15.644444-6.4-19.2s-15.644444-0.711111-19.2 6.4C533.333333 434.488889 522.666667 440.888889 512 440.888889s-21.333333-6.4-25.6-16.355556c-3.555556-7.111111-12.088889-9.955556-19.2-6.4-7.111111 3.555556-9.955556 12.088889-6.4 19.2 9.244444 19.911111 29.866667 32.711111 51.2 32.711111 22.044444-0.711111 41.955556-13.511111 51.2-33.422222z" fill="#194F82" p-id="951"></path></svg>
      </div>
      <div v-else-if="user.gender === '男'" class="avatar">
        <svg t="1764688511581" class="avatar" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1100" width="200" height="200"><path d="M874.666667 931.555556H149.333333c-7.822222 0-14.222222-6.4-14.222222-14.222223v-128c0-62.577778 51.2-113.777778 113.777778-113.777777h526.222222c62.577778 0 113.777778 51.2 113.777778 113.777777v128c0 7.822222-6.4 14.222222-14.222222 14.222223z" fill="#5CBAEA" p-id="1101"></path><path d="M426.666667 540.444444h170.666666v113.777778H426.666667z" fill="#FFFFFF" p-id="1102"></path><path d="M724.622222 238.222222h-419.555555V362.666667c0 102.4 82.488889 184.888889 184.888889 184.888889h49.777777c102.4 0 184.888889-82.488889 184.888889-184.888889V238.222222z" fill="#FFFFFF" p-id="1103"></path><path d="M725.333333 220.444444H348.444444c-62.577778 0-113.777778-51.2-113.777777-113.777777h433.777777c31.288889 0 56.888889 25.6 56.888889 56.888889v56.888888z" fill="#FFC44F" p-id="1104"></path><path d="M410.311111 355.555556m-28.444444 0a28.444444 28.444444 0 1 0 56.888889 0 28.444444 28.444444 0 1 0-56.888889 0Z" fill="#194F82" p-id="1105"></path><path d="M611.555556 355.555556m-28.444445 0a28.444444 28.444444 0 1 0 56.888889 0 28.444444 28.444444 0 1 0-56.888889 0Z" fill="#194F82" p-id="1106"></path><path d="M775.111111 647.111111H611.555556v-69.688889c91.733333-39.111111 156.444444-130.133333 156.444444-236.088889V142.222222c0-46.933333-38.4-85.333333-85.333333-85.333333H227.555556c-15.644444 0-28.444444 12.8-28.444445 28.444444v28.444445c0 46.222222 22.755556 87.466667 56.888889 113.777778v113.777777c0 105.955556 64.711111 196.977778 156.444444 236.088889V647.111111H248.888889c-78.222222 0-142.222222 64-142.222222 142.222222v128c0 23.466667 19.2 42.666667 42.666666 42.666667h725.333334c23.466667 0 42.666667-19.2 42.666666-42.666667v-128c0-78.222222-64-142.222222-142.222222-142.222222zM256 113.777778h426.666667c15.644444 0 28.444444 12.8 28.444444 28.444444v56.888889H341.333333c-46.933333 0-85.333333-38.4-85.333333-85.333333z m56.888889 227.555555V256h398.222222v85.333333c0 109.511111-89.6 199.111111-199.111111 199.111111S312.888889 450.844444 312.888889 341.333333z m156.444444 252.444445c14.222222 2.133333 28.444444 3.555556 42.666667 3.555555s28.444444-1.422222 42.666667-3.555555V647.111111H469.333333v-53.333333z m391.111111 309.333333H163.555556v-113.777778c0-46.933333 38.4-85.333333 85.333333-85.333333h526.222222c46.933333 0 85.333333 38.4 85.333333 85.333333v113.777778z" fill="#194F82" p-id="1107"></path><path d="M563.2 443.733333c3.555556-7.111111 0.711111-15.644444-6.4-19.2s-15.644444-0.711111-19.2 6.4C533.333333 441.6 522.666667 448 512 448s-21.333333-6.4-25.6-16.355556c-3.555556-7.111111-12.088889-9.955556-19.2-6.4-7.111111 3.555556-9.955556 12.088889-6.4 19.2 9.244444 19.911111 29.866667 32.711111 51.2 32.711112 22.044444-0.711111 41.955556-13.511111 51.2-33.422223z" fill="#194F82" p-id="1108"></path></svg>
      </div>
      <div v-else class="avatar">
        <svg t="1764688572712" class="avatar" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2373" width="200" height="200"><path d="M512 944c-194.56 0-350.08-240-335.04-427.52C188.96 696.48 334.24 896 512 896s323.04-199.52 335.04-379.52C862.08 704 706.4 944 512 944z" fill="#69CC00" p-id="2374"></path><path d="M770.4 240l-42.4 42.72A32 32 0 0 1 688 288a333.44 333.44 0 0 0-352 0 32 32 0 0 1-39.36-4.48L253.6 240c4.64-17.44 4.8-36.8-20.96-62.08a61.12 61.12 0 0 0-102.24 28.64c-16-54.72 54.88-108 102.24-60.64 25.76 25.28 25.6 44.64 20.96 62.08l42.4 42.4A32 32 0 0 0 336 256a333.44 333.44 0 0 1 352 0 32 32 0 0 0 39.36-4.48l41.92-41.92a58.56 58.56 0 0 0 1.12 30.4zM893.92 208a64 64 0 0 0-123.68 0c-21.92-75.52 92.64-113.12 121.12-39.52a64 64 0 0 1 2.56 39.52z" fill="#C5EE09" p-id="2375"></path><path d="M778.4 290.88a32 32 0 0 0-2.4 42.56c52.32 66.24 75.2 151.52 69.6 199.04C826.4 708 684.48 896 512 896S197.6 708 178.4 532.48c-5.6-46.88 16-131.84 69.6-199.04a32 32 0 0 0-2.4-42.56L208 253.6c-17.44 4.64-36.8 4.8-62.08-20.96a62.08 62.08 0 0 1-16-25.76 61.12 61.12 0 0 1 102.24-28.64c25.76 25.28 25.6 44.64 20.96 62.08l42.4 42.4A32 32 0 0 0 336 288a333.44 333.44 0 0 1 352 0 32 32 0 0 0 39.36-4.48L770.4 240a53.12 53.12 0 0 1 0-32 64 64 0 0 1 123.68 0A64 64 0 0 1 832 256c-23.68 0-4.48-14.24-53.6 34.88z" fill="#84E507" p-id="2376"></path><path d="M456.96 727.04c-37.44 37.44-132 3.68-169.44-33.76a151.84 151.84 0 0 1-39.2-126.72c16.96-75.84 128-56.16 174.72-8.96 37.12 37.12 71.52 131.84 33.92 169.44zM736 693.28c-54.4 54.4-208 92.8-183.2-39.68a199.84 199.84 0 0 1 48-96c37.44-37.44 120.8-60 158.24-22.56S773.12 656 736 693.28z" fill="#FDA736" p-id="2377"></path><path d="M470.72 653.76c-18.72 72.64-137.92 36.8-183.2-8.48a156.32 156.32 0 0 1-39.2-78.72c16.96-75.84 128-56.16 174.72-8.96a202.08 202.08 0 0 1 47.68 96.16zM774.72 566.56A156.32 156.32 0 0 1 736 645.28c-46.24 46.24-164.48 80.8-183.2 8.32a199.84 199.84 0 0 1 48-96c46.4-47.36 156.96-67.04 173.92 8.96z" fill="#FED370" p-id="2378"></path><path d="M169.6 459.52a16 16 0 0 1 31.04 7.36C147.84 688 359.68 998.56 592 913.6a16 16 0 0 1 10.08 30.4C347.2 1037.12 109.76 710.24 169.6 459.52zM653.76 916.32a16 16 0 0 1-8.64-29.44c160-101.28 260.64-363.2 118.24-544a48 48 0 0 1 3.68-64C823.68 223.04 802.88 240 832 240a48 48 0 1 0-29.92-85.6 47.04 47.04 0 0 0-16 49.6c3.36 11.84-2.08 13.76-46.4 58.08a48 48 0 0 1-59.2 6.72 317.6 317.6 0 0 0-336 0 48 48 0 0 1-59.2-6.72c-42.24-42.24-49.76-45.76-46.56-57.92s4.64-25.6-16-46.56c-41.92-41.76-105.6 21.92-64 64 36 36.8 46.4 5.44 62.08 20.96l37.28 37.28a48 48 0 0 1 3.68 64 326.88 326.88 0 0 0-40.48 66.24 16 16 0 0 1-29.12-13.12c48-103.84 74.56-62.88 12.16-125.44A84.64 84.64 0 0 1 112 187.68a77.6 77.6 0 0 1 131.84-52.8c27.2 26.88 30.08 49.92 27.04 68.32l36.48 36.8a16 16 0 0 0 19.52 2.24 349.92 349.92 0 0 1 370.24 0 16 16 0 0 0 19.52-2.24c44.48-45.12 35.36-32 35.36-48a80 80 0 1 1 142.56 49.92 77.28 77.28 0 0 1-73.76 29.12l-31.04 31.2a16 16 0 0 0-1.12 21.28A347.68 347.68 0 0 1 864 540.64c0 209.6-175.04 375.68-210.24 375.68z" fill="#35214C" p-id="2379"></path><path d="M409.92 759.2A214.88 214.88 0 0 1 276.16 704c-40.96-40.32-68.16-134.4-22.56-180.32 22.72-22.72 60.32-29.6 103.2-19.04a16 16 0 0 1-7.68 31.04c-114.72-28.32-100 96-50.24 146.24 33.12 33.28 118.24 62.4 146.72 33.76 32-32-5.12-126.88-44.96-156.16a16 16 0 0 1 18.88-25.92c72.16 52.96 115.04 225.6-9.6 225.6zM554.72 738.4c-46.4-46.56-5.92-152.48 33.76-192 43.04-43.04 136.16-67.36 180.96-22.56s18.4 139.84-22.56 180.16a173.44 173.44 0 0 1-40.16 28.64 16 16 0 0 1-15.04-28.16 136.8 136.8 0 0 0 32-23.2c33.92-33.76 51.2-107.04 22.56-135.52s-104.32-8.8-135.52 22.56c-36.8 36.8-92.96 178.4 26.08 156a16 16 0 0 1 6.08 32c-31.04 6.08-65.6 4.48-88.16-17.92zM455.36 817.92a16 16 0 0 1 22.72 0 49.28 49.28 0 0 0 67.84 0 16 16 0 0 1 22.72 22.72c-53.28 53.12-137.92 2.08-113.28-22.72z" fill="#35214C" p-id="2380"></path></svg>
      </div>

      <div class="user-info">
        <h2 class="user-name">{{ user.name }}</h2>
        <button class="logout-btn" @click="handleLogout">退出</button>
      </div>
    </div>

    <!-- 历史记录 -->
    <section class="section">
      <div class="section-header">
        <h3 class="section-title">历史记录</h3>
        <button
            v-if="history.length > 3"
            class="detail-btn"
            @click="toggleExpand('history')"
        >
          {{ expandState.history ? '收起' : '详情' }}
        </button>
      </div>
      <ul class="record-list">
        <li
            v-for="item in getVisibleList(history, 'history')"
            :key="`history-${item.id}`"
            class="record-item"
            @click="goBookChapter(item.id, item.chapterId)"
        >
          <div>
            <strong>{{ item.novelTitle }}</strong> - {{ item.chapterTitle }}
          </div>
          <span class="time">{{ item.time }}</span>
        </li>
      </ul>
    </section>

    <!-- 收藏记录 -->
    <section class="section">
      <div class="section-header">
        <h3 class="section-title">收藏记录</h3>
        <button
            v-if="favorites.length > 3"
            class="detail-btn"
            @click="toggleExpand('favorites')"
        >
          {{ expandState.favorites ? '收起' : '详情' }}
        </button>
      </div>
      <ul class="record-list">
        <li
            v-for="novel in getVisibleList(favorites, 'favorites')"
            :key="`fav-${novel.id}`"
            class="record-item"
            @click="goBook(novel.id)"
        >
          <strong>{{ novel.title }}</strong> · {{ novel.author }}
        </li>
      </ul>
    </section>

    <!-- 评分记录 -->
    <section class="section">
      <div class="section-header">
        <h3 class="section-title">评分记录</h3>
        <button
            v-if="ratings.length > 3"
            class="detail-btn"
            @click="toggleExpand('ratings')"
        >
          {{ expandState.ratings ? '收起' : '详情' }}
        </button>
      </div>
      <ul class="record-list">
        <li
            v-for="rating in getVisibleList(ratings, 'ratings')"
            :key="`rating-${rating.id}`"
            class="record-item"
            @click="goBook(rating.id)"
        >
          <div>
            <strong>{{ rating.title }}</strong> · {{ rating.author }}
          </div>
          <div class="rating-stars">
            <span v-for="i in 5" :key="i" class="star" :class="{ filled: i <= rating.score }">★</span>
          </div>
        </li>
      </ul>
    </section>
  </div>
</template>

<style scoped>
.user-homepage {
  max-width: 600px;
  margin: 0 auto;
  padding: 16px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
  color: #333;
}

/* 用户卡片 */
.user-card {
  display: flex;
  align-items: center;
  gap: 16px;
  background: #91d0fb;
  padding: 16px;
  border-radius: 12px;
  box-shadow: 0 2px 6px rgba(0,0,0,0.05);
  margin-bottom: 24px;
}

.avatar {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid #ddd;
}

.user-info {
  flex: 1;
}

.user-name {
  margin: 0 0 8px 0;
  font-size: 1.25rem;
  font-weight: 600;
}

.logout-btn {
  padding: 6px 12px;
  font-size: 0.875rem;
  background-color: #ff4d4f;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.logout-btn:hover {
  opacity: 0.9;
}

/* Section header with title + detail button */
.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.section-title {
  font-size: 1.1rem;
  font-weight: 600;
  color: #222;
  padding-bottom: 6px;
  border-bottom: 1px solid #eee;
  flex: 1;
}

.detail-btn {
  background: none;
  border: none;
  color: #1890ff;
  font-size: 0.9rem;
  cursor: pointer;
  padding: 4px 8px;
  border-radius: 4px;
}

.detail-btn:hover {
  background-color: #f0faff;
}

/* 通用区块 */
.section {
  margin-bottom: 24px;
}

.record-list {
  list-style: none;
  padding: 0 0 0 10px;
  margin: 0;
}

.record-item {
  padding: 12px 0;
  border-bottom: 1px solid #f0f0f0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 0.95rem;
}

.record-item:last-child {
  border-bottom: none;
}

.time {
  color: #888;
  font-size: 0.875rem;
}

/* 评分星星 */
.rating-stars {
  display: flex;
  gap: 2px;
}

.star {
  color: #ddd;
  font-size: 0.9rem;
}

.star.filled {
  color: #ffb400;
}
</style>
