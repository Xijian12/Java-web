import { createRouter, createWebHistory } from 'vue-router'
import { unauthorized } from "@/net";

import Home from '@/components/Home.vue'
import BookDetail from '@/components/BookDetail.vue'
import BookDetailPage from "@/components/BookDetailPage.vue"
import SearchDisplay from '@/components/Search/SearchDisplay.vue'
import SearchDisplayPage from "@/components/SearchDisplayPage.vue"
import Personal from '@/components/Personal.vue'



const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'welcome',
            component: () => import('@/views/WelcomeView.vue'),
            children: [
                {
                    path: '',
                    name: 'welcome-login',
                    component: () => import('@/views/welcome/LoginPage.vue')
                },
                {
                    path: 'register',
                    name: 'welcome-register',
                    component: () => import('@/views/welcome/RegisterPage.vue')
                },{
                    path: 'forget',
                    name: 'welcome-forget',
                    component: () => import('@/views/welcome/ForgetPage.vue')
                }
            ]
        }, 
        {
            name:'home',
            path: '/home',
            component: Home,
            meta:{isAuth:true}
        },
        // 搜索展示页容器
        {
            path: "/SearchDisplayPage",
            name: "SearchDisplayPage",
            component: SearchDisplayPage,
            children: [
            // 搜索展示页
            {
                path: "/SearchDisplay",
                name: "SearchDisplay",
                component: SearchDisplay,
            }
            ]   
        },
        // 图书详情页容器
        {
            path: "/BookDetailPage",
            name: "BookDetailPage",
            component: BookDetailPage,
            children: [
                {
                    // 图书详情页
                    path: "/BookDetail/:id",
                    name: "BookDetail",
                    component: BookDetail,
                }
            ]
        },
        //个人中心
        {
            name:"personal",
            path:'/personal',
            component:Personal,
            meta:{isAuth:true},
            children: [
                {
                    path: 'personalMyinfo',
                    name: 'personalMyinfo',
                    component: () => import('@/components/Personal/main/MyInfo.vue')
                }, {
                    name: 'favorite',
                    path: 'favorite',
                    component: () => import('@/components/Personal/main/bookManage/FavoriteBook.vue')
                },
                {
                    path: "publish",
                    name: 'publish',
                    component: () => import("@/components/Personal/main/bookManage/PublishBook.vue")
                },
                {
                    path: "publishrecord",
                    name: 'publishrecord',
                    component: () => import("@/components/Personal/main/bookManage/PublishRecord.vue")
                },
                {
                    path: "buyrecord",
                    name: 'buyrecord',
                    component: () => import("@/components/Personal/main/orderManage/buyRecords.vue")
                },
                {
                    path: "sellrecord",
                    name: 'sellrecord',
                    component: () => import("@/components/Personal/main/orderManage/sellRecords.vue")
                },
            ]
        },
          {
            name:"test",
            path:'/test',
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
