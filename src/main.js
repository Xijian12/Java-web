import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from "axios";
import store from "./store"
import './assets/iconfont/iconfont.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'


axios.defaults.baseURL = 'http://localhost:8080'

const app = createApp(App)

app.use(ElementPlus)
app.use(router)
app.use(store)

app.mount('#app')
