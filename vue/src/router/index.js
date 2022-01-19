import { createRouter, createWebHistory } from 'vue-router'
import layout from "../layout/layout.vue";

const routes = [
  {
    path: '/',
    name: 'layout',
    component: layout,
    redirect:"/home",
    children:[{
      path: 'home',
      name: 'Home',
      component: () => import("@/views/Home"),
    }]
  },
  {
    path: '/login',
    name: 'login',
    component: () => import("@/views/login")
  },
  {
    path: '/register',
    name: 'register',
    component: () => import("@/views/register")
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
