import { createRouter, createWebHistory } from 'vue-router'
import layout from "../layout/layout.vue";

const routes = [
  {
    path: '/',
    name: 'layout',
    component: layout,
    redirect:"/cover",
    children:[
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: () => import("../views/login")
  },
  {
    path: '/register',
    name: 'register',
    component: () => import("../views/register")
  },
  {
    path:'/cover',
    name:'cover',
    component: () => import("../views/cover")
  },
  {
    path:'/appointment',
    name:'appointment',
    component: () => import("../views/appointment")
  },
  {
    path: '/personal_center',
    name:'personal_center',
    component: () => import("../views/personal_center")
  },
  {
    path:'/reservation',
    name:'reservation',
    component: ()=>import("../views/reservation")
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
