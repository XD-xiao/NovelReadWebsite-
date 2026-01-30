<script setup>
import { getIsNotice } from "@/api/notice.js";
import { ref, onMounted } from "vue";

const isNotice = ref(false);
import { useUserStore } from "@/stores/index.js";
const store = useUserStore()
// 发送请求检查是否有未读通知
const checkForUnreadNotices = async () => {
  try {
    const res = await getIsNotice(store.token); // 假设此函数返回一个promise，且当有未读消息时response.data为true
    isNotice.value = res.data.data; // 更新isNotice状态
  } catch (error) {
    console.error("Error fetching unread notices:", error);
  }
};

// 组件挂载后执行检查
onMounted(() => {
  checkForUnreadNotices();
});
</script>

<template>
  <el-container class="layout-container">
    <!-- 主内容区 -->
    <el-main>
      <router-view></router-view>
    </el-main>

    <!-- 底部导航栏 -->
    <el-footer height="60px" style="padding: 0; border-top: 1px solid #eee;">
      <el-menu mode="horizontal" :default-active="$route.path" router class="bottom-menu">
        <el-menu-item index="/home"><span>主页</span></el-menu-item>
        <el-menu-item index="/Notifications">
          <el-badge v-if="isNotice" is-dot class="badgeItem" :offset="[2, 16]">
            <span>通知</span>
          </el-badge>
          <span v-else>通知</span>
        </el-menu-item>
        <el-menu-item index="/UserHome"><span>我的</span></el-menu-item>
      </el-menu>
    </el-footer>
  </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
  height: 100vh;
  display: flex;
  flex-direction: column;
}

.el-main {
  flex: 1;
  padding: 0;
}

.bottom-menu {
  height: 100%;
  border: none;
  display: flex;
  justify-content: space-around;
  align-items: center;
  background-color: #60abf8; /*  添加蓝色背景 */

  ::v-deep(.el-menu-item) {
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: white !important; // 确保文字是白色

    // 移除 hover 和 active 时的背景色
    &:hover,
    &.is-active {
      background-color: transparent !important;
    }
  }

  .badgeItem{
    color: white;
  }
}

</style>
