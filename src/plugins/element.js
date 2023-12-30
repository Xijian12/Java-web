import Vue from 'vue'
import {
    Button,
    Form,
    FormItem,
    Input,
    Container,
    Header,
    Aside,
    Menu,
    Submenu,
    MenuItem,
    Main,
    Message,
    MessageBox,
    Carousel,
    CarouselItem,
    InputNumber,
    Checkbox
} from 'element-ui'
Vue.use(Button)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Container)
Vue.use(Header)
Vue.use(Aside)
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(Main)
Vue.use(Carousel)
Vue.use(CarouselItem)
Vue.use(InputNumber)
Vue.use(Checkbox)

Vue.prototype.$message = Message
Vue.prototype.$confirm = MessageBox.confirm