import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';


// router.beforeEach((to,from,next) => {
//     if(to.path === "/login"||to.path === "/register"||to.path === "/cover"||to.path === "/appointment"){
//         next()
//     }else{
//         if (sessionStorage.getItem('user')){
//             next()
//         } else {
//             next("/login")
//         }
//     }
// })

import '@/assets/css/global.css'
createApp(App).use(store).use(router).use(ElementPlus,{size:'small'}).mount('#app')

