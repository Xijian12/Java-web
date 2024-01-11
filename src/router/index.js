import { createRouter, createWebHistory } from 'vue-router'
import { unauthorized } from "@/net";

import Home from '@/components/Home.vue'
import BookDetail from '@/components/BookDetail.vue'
import BookDetailPage from "@/components/BookDetailPage.vue"
import SearchDisplay from '@/components/Search/SearchDisplay.vue'
import SearchDisplayPage from "@/components/SearchDisplayPage.vue"
import Personal from '@/components/Personal.vue'

// 后台
import AboutView from "@/components/Backend/AboutView.vue";
import NavView from "@/components/Backend/NavView.vue";
import HomeView from "@/components/Backend/HomeView.vue";
import BookTableView from "@/components/Backend/BookTableView.vue";
import BookView from "@/components/Backend/BookView.vue";
import BorrowView from "@/components/Backend/BorrowView.vue";
import ReturnView from "@/components/Backend/ReturnView.vue";
import OvertimeView from "@/components/Backend/OvertimeView.vue";
import UserView from "@/components/Backend/UserView.vue";
import SystemView from "@/components/Backend/SystemView.vue";



const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'welcome',
            meta: { show: false },
            component: () => import('@/views/WelcomeView.vue'),
            children: [
                {
                    path: '',
                    name: 'welcome-login',
                    meta: { show: false },
                    component: () => import('@/views/welcome/LoginPage.vue')
                },
                {
                    path: 'register',
                    name: 'welcome-register',
                    meta: { show: false },
                    component: () => import('@/views/welcome/RegisterPage.vue')
                },{
                    path: 'forget',
                    name: 'welcome-forget',
                    meta: { show: false },
                    component: () => import('@/views/welcome/ForgetPage.vue')
                }
            ]
        }, 
        {
            name:'home',
            path: '/home',
            component: Home,
            meta:{isAuth:true, show: false}
        },
        // 搜索展示页容器
        {
            path: "/SearchDisplayPage",
            name: "SearchDisplayPage",
            meta: { show: false },
            component: SearchDisplayPage,
            children: [
            // 搜索展示页
            {
                path: "/SearchDisplay",
                name: "SearchDisplay",
                meta: { show: false },
                component: SearchDisplay,
            }
            ]   
        },
        // 图书详情页容器
        {
            path: "/BookDetailPage",
            name: "BookDetailPage",
            meta: { show: false },
            component: BookDetailPage,
            children: [
                {
                    // 图书详情页
                    path: "/BookDetail/:id",
                    name: "BookDetail",
                    meta: { show: false },
                    component: BookDetail,
                }
            ]
        },
        //个人中心
        {
            name:"personal",
            path:'/personal',
            component:Personal,
            meta:{isAuth:true, show: false},
            children: [
                {
                    path: 'personalMyinfo',
                    name: 'personalMyinfo',
                    meta: { show: false },
                    component: () => import('@/components/Personal/main/MyInfo.vue')
                }, {
                    name: 'favorite',
                    path: 'favorite',
                    meta: { show: false },
                    component: () => import('@/components/Personal/main/bookManage/FavoriteBook.vue')
                },
                {
                    path: "publish",
                    name: 'publish',
                    meta: { show: false },
                    component: () => import("@/components/Personal/main/bookManage/PublishBook.vue")
                },
                {
                    path: "publishrecord",
                    name: 'publishrecord',
                    meta: { show: false },
                    component: () => import("@/components/Personal/main/bookManage/PublishRecord.vue")
                },
                {
                    path: "buyrecord",
                    name: 'buyrecord',
                    meta: { show: false },
                    component: () => import("@/components/Personal/main/orderManage/buyRecords.vue")
                },
                {
                    path: "sellrecord",
                    name: 'sellrecord',
                    meta: { show: false },
                    component: () => import("@/components/Personal/main/orderManage/sellRecords.vue")
                },
            ]
        },
        //后台
        {
            path: "/nav",
            name: "导航",
            component: NavView,
            redirect: "/backendhome",
            meta: { show: false },
            children: [
              {
                path: "/backendhome",
                name: "主页",
                meta: { show: true },
                components: { backendhome: HomeView },
              },
            ],
          },
          {
            path: "/nav",
            name: "图书",
            component: NavView,
            meta: { show: true },
            children: [
              {
                path: "/bookTable",
                name: "图书书目管理",
                meta: { show: true },
                components: { bookTable: BookTableView },
              },
              {
                path: "/book",
                name: "图书信息管理",
                meta: { show: true },
                components: { book: BookView },
              },
              {
                path: "/borrow",
                name: "借阅图书",
                meta: { show: true },
                components: { borrow: BorrowView },
              },
              {
                path: "/return",
                name: "归还图书",
                meta: { show: true },
                components: { return: ReturnView },
              },
              {
                path: "/overtime",
                name: "超时查询",
                meta: { show: true },
                components: { overtime: OvertimeView },
              },
            ],
          },
          {
            path: "/nav",
            name: "用户",
            component: NavView,
            meta: { show: true },
            children: [
              {
                path: "/user",
                name: "用户管理",
                meta: { show: true },
                components: { user: UserView },
              },
            ],
          },
          {
            path: "/nav",
            name: "其他",
            component: NavView,
            meta: { show: true },
            children: [
              {
                path: "/system",
                name: "系统设置",
                meta: { show: true },
                components: { system: SystemView },
              },
              {
                path: "/about",
                name: "关于",
                meta: { show: true },
                components: { about: AboutView },
              }
            ],
          },
          {
            name:"test",
            path:'/test',
            meta: { show: false },
            component: () => import('@/test.vue'),
        }
    ]
})

router.beforeEach((to, from, next) => {
    const isUnauthorized = unauthorized()
    if(to.name.startsWith('welcome') && !isUnauthorized) {
        next('/index')
    } else if(to.fullPath.startsWith('/index') && isUnauthorized) {
        next('/')
    } else {
        next()
    }
})


export default router
