import { createRouter, createWebHistory } from 'vue-router'
import layout from "../layout/layout.vue";

const routes = [
  {
    path: '/',
    name: 'layout',
    component: layout,
    redirect:"/book",
    children:[{
      path: 'user',
      name: 'User',
      component: () => import("../views/User"),
    },
      {
      path: '/book',
      name: 'book',
      component: () => import("../views/book"),
        meta: {
          requireAuth: true
        }
    },
      {
        path: '/person',
        name: 'person',
        component: () => import("../views/person"),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/news',
        name: 'News',
        component: () => import("../views/News"),
        meta: {
          requireAuth: true
        }
      }
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
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
