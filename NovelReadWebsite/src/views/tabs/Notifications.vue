<script setup>
import {ref, onMounted} from 'vue'
import {getNotificationsService} from "@/api/notice.js";

onMounted(() => {
  getNotifications();
})

// 示例通知数据（移除 expanded 字段）
// const notifications = ref([
//   {
//     id: 1,
//     title: '系统更新',
//     content: '系统将于今晚 2:00 进行维护，请提前保存工作。',
//     read: false
//   },
//   {
//     id: 2,
//     title: '新消息',
//     content: '你有一条来自管理员的重要通知：请于本周五前完成年度考核表的填写。内容很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长......',
//     read: false
//   },
//   {
//     id: 3,
//     title: '恭喜！',
//     content: '你已成功完成本季度目标！',
//     read: true
//   }
// ])
import { useUserStore } from "@/stores/index.js";
const store = useUserStore()

const notifications = ref([])
const getNotifications = async () => {
  const res = await getNotificationsService(store.token);
  notifications.value = res.data.data;
}

// 用于记录哪些长通知被展开（仅存储 id）
const expandedIds = ref(new Set())

// 判断内容是否“过长”（例如超过 100 字符）
const isContentLong = (content) => {
  return content.length > 100
}

// 删除通知
const removeNotification = (id) => {
  notifications.value = notifications.value.filter(n => n.id !== id)
  expandedIds.value.delete(id) // 同时清理展开状态
}

// 标记为已读
const markAsRead = (id) => {
  const notif = notifications.value.find(n => n.id === id)
  if (notif) notif.read = true
}

// 切换展开（仅对长内容有效）
const toggleExpand = (id, content) => {
  if (!isContentLong(content)) return // 短内容不处理
  const set = expandedIds.value
  if (set.has(id)) {
    set.delete(id)
  } else {
    set.add(id)
  }
  expandedIds.value = new Set(set) // 触发响应式更新
}

// 计算属性：是否展开
const isExpanded = (id) => {
  return expandedIds.value.has(id)
}
</script>

<template>
  <div class="notification-container">
    <header class="header-box">
      <h2>通知</h2>
    </header>

    <div class="card-list">
      <div
          v-for="item in notifications"
          :key="item.id"
          class="notification-card"
          :class="{ 'read': item.read }"
      >
        <h3 class="card-title">{{ item.title }}</h3>

        <!-- 内容区域 -->
        <div
            class="card-content"
            :class="{
            'expandable': isContentLong(item.content),
            'expanded': isExpanded(item.id)
          }"
            @click="toggleExpand(item.id, item.content)"
        >
          {{ item.content }}
        </div>

        <div class="card-actions">
          <button
              class="btn-read"
              @click.stop="markAsRead(item.id)"
              :disabled="item.read"
          >
            {{ item.read ? '已读' : '标记为已读' }}
          </button>
          <button class="btn-delete" @click.stop="removeNotification(item.id)">
            删除
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.notification-container {
  width: 100%;
  max-width: 600px;
  margin: 0 auto;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

.header-box {
  background-color: #91d0fb;
  padding: 12px 16px;
  margin-bottom: 16px;
  text-align: center;
}

.header-box h2 {
  margin: 0;
  font-size: 1.25rem;
  font-weight: 600;
  color: #333;
}

.card-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
  padding: 10px;
}

.notification-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 16px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
  transition: background-color 0.2s;
}

.notification-card.read {
  background-color: #f9f9f9;
  opacity: 0.8;
}

.card-title {
  margin: 0 0 8px 0;
  font-size: 1.1rem;
  font-weight: 600;
  color: #222;
}

.card-content {
  font-size: 0.95rem;
  line-height: 1.5;
  color: #444;
  overflow: hidden;
  position: relative;
}

/* 默认只显示两行 */
.card-content:not(.expandable) {
  /* 短内容：正常显示，不可点击 */
  cursor: default;
}

.card-content.expandable {
  /* 长内容：默认截断，可点击 */
  cursor: pointer;
  max-height: 3em; /* 2 行 * 1.5 line-height = 3em */
  transition: max-height 0.3s ease;
}

.card-content.expanded {
  max-height: none;
}

/* 省略号效果（仅对可展开且未展开的内容） */
.card-content.expandable:not(.expanded)::after {
  content: '…';
  position: absolute;
  bottom: 0;
  right: 0;
  padding-left: 20px;
  background: linear-gradient(to right, transparent, white 70%);
}

.card-content.expanded::after,
.card-content:not(.expandable)::after {
  display: none;
}

.card-actions {
  margin-top: 12px;
  display: flex;
  justify-content: flex-end;
  gap: 8px;
}

.card-actions button {
  padding: 6px 12px;
  font-size: 0.875rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: opacity 0.2s;
}

.btn-read {
  background-color: #e6f7ff;
  color: #1890ff;
}

.btn-read:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.btn-delete {
  background-color: #fff1f0;
  color: #ff4d4f;
}

.btn-read:hover:not(:disabled),
.btn-delete:hover {
  opacity: 0.8;
}
</style>
