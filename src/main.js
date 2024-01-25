import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from "axios";
import store from "./store";
import './assets/iconfont/iconfont.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import "element-plus/theme-chalk/index.css";
import zhCn from "element-plus/es/locale/lang/zh-cn";
// import Viewer from 'v-viewer'
// import 'viewerjs/dist/viewer.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
axios.defaults.baseURL = 'http://localhost:8080'
const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
// app.use(Viewer, {
//     defaultOptions: {
//       inline: false,  //默认值：false。启用内联模式。
//       button: true,  //在查看器的右上角显示按钮。
//       navbar: true,  //指定导航栏的可见性。
//       title: true,  //指定标题的可见性和内容。
//       toolbar: true,  //指定工具栏及其按钮的可见性和布局。
//       tooltip: true,  //放大或缩小时显示带有图像比率（百分比）的工具提示。
//       movable: true,  //启用以移动图像。
//       zoomable: true,  //启用以缩放图像
//       rotatable: true,  //启用以旋转图像
//       scalable: true,  //启用以缩放图像。
//       transition: true,  //为某些特殊元素启用CSS3转换。
//       fullscreen: true,  //启用以在播放时请求全屏。
//       keyboard: true,  //启用键盘支持。
//       // url: 'src',  //默认值：'src'。定义获取原始图像URL以供查看的位置。
//     },
//   });
app.use(ElementPlus, { locale: zhCn });
app.use(router)
app.use(store)
app.mount('#app')
