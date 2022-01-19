import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import * as ElIconModules from '@element-plus/icons';

import '@/assets/css/global.css'
createApp(App).use(store).use(router).use(ElementPlus,{size:'small'}).mount('#app')

