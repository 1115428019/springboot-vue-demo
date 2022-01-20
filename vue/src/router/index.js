import { createRouter, createWebHistory } from 'vue-router'
import layout from "../layout/layout.vue";

const routes = [
  {
    path: '/',
    name: 'layout',
    component: layout,
    redirect:"/user",
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
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
