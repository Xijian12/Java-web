import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as $ from 'jquery'
import * as echarts from 'echarts'
import '@style/global.scss'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

if (!window.jQuery) {
  window.jQuery = $
}

const app = createApp(App);
app.use(store);
app.use(router);
app.use(ElementPlus);
app.use(echarts);
app.config.globalProperties.$echarts = echarts
app.config.globalProperties.$currentUser = {}
app.config.globalProperties.$url = 'http://localhost:9999'
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.mount('#app')

export default app
