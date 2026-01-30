<script setup>
import { ref } from 'vue'
import NovelManagement from "@/components/NovelManagement.vue";
import UserManagement from "@/components/UserManagement.vue";


// 当前激活的菜单项：'novel' 或 'user'
const activeTab = ref('novel')

function switchTab(tab) {
  activeTab.value = tab
}
</script>

<template>
  <div class="admin-layout">
    <!-- 左侧导航栏 -->
    <nav class="sidebar">
      <h2 class="logo">小说后台</h2>
      <ul class="menu">
        <li
            :class="{ active: activeTab === 'novel' }"
            @click="switchTab('novel')"
        >
          <span>📚</span>
          <span>小说管理</span>
        </li>
        <li
            :class="{ active: activeTab === 'user' }"
            @click="switchTab('user')"
        >
          <span>👥</span>
          <span>用户管理</span>
        </li>
      </ul>
    </nav>

    <!-- 主内容区 -->
    <main class="main-content">
      <NovelManagement v-if="activeTab === 'novel'"></NovelManagement>
      <UserManagement v-if="activeTab === 'user'"></UserManagement>
    </main>
  </div>
</template>

<style scoped>
.admin-layout {
  display: flex;
  height: 100vh;
  background-color: #f5f7fa;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.sidebar {
  width: 220px;
  background: linear-gradient(180deg, #2c3e50, #1a1a2e);
  color: white;
  padding: 20px 0;
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
}

.logo {
  text-align: center;
  margin-bottom: 30px;
  font-size: 1.4rem;
  font-weight: 600;
  letter-spacing: 1px;
}

.menu {
  list-style: none;
  padding: 0;
  margin: 0;
}

.menu li {
  padding: 14px 24px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 12px;
  transition: all 0.3s ease;
}

.menu li:hover {
  background-color: rgba(255, 255, 255, 0.1);
}

.menu li.active {
  background-color: #3498db;
  border-left: 4px solid #ffffff;
}

.menu li span:nth-child(2) {
  font-size: 1rem;
}

.main-content {
  flex: 1;
  padding: 30px;
  overflow-y: auto;
  background-color: #f9fbfd;
}
</style>