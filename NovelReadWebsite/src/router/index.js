import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '@/stores/index.js'

const routes = [
  // 登录/注册（合一页面）
  {
    path: '/login',
    component:() =>import('@/views/Login.vue')
  },
  {
    path:'/',
    component: () => import('@/views/LayoutContainer.vue'),
    redirect: '/home',
    children:[
      {
        path: '/home',
        component: ()=> import('@/views/tabs/Home.vue')
      },
      {
        path: '/Notifications',
        component:()=>import('@/views/tabs/Notifications.vue')
      },
      {
        path:'/UserHome',
        component:()=>import('@/views/tabs/UserHome.vue')
      }
    ]
  },

  {
    path: '/search',
    component: () => import('@/views/Search.vue')
  },
  {
    path: '/book/:bookId',
    component: () => import('@/views/BookDetail.vue')
  },
  {
    path: '/book/:bookId/:chapterId',
    component: () => import('@/views/BookContent.vue')
  },
  {
    path: '/AdminPage',
    component: () => import('@/views/AdminPage.vue')
  }

]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

// 全局前置守卫
// router.beforeEach((to) => {
//   const userStore = useUserStore()
//   if (!userStore.token && to.path !== '/login') return '/login'
// })

export default router
