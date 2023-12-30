import { createRouter, createWebHistory } from 'vue-router'
import { unauthorized } from "@/net";

import Login from '@/components/Login.vue'
import Register from '@/components/Register.vue'
import Home from '@/components/Home.vue'
import ItemList from '@/components/ItemList.vue'
import Cart from '@/components/Cart.vue'
import Personal from '@/components/Personal.vue'
import store from "@/store";

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
                }, {
                    path: 'register',
                    name: 'welcome-register',
                    component: () => import('@/views/welcome/RegisterPage.vue')
                }, {
                    path: 'forget',
                    name: 'welcome-forget',
                    component: () => import('@/views/welcome/ForgetPage.vue')
                }
            ]
        }, 
        {
            name:"login",
            path:'/login',
            component:Login
        },
        {
            name:"register",
            path:'/register',
            component:Register
        },
        {
            name:"home",
            path: '/home',
            component: Home,
        },
        {
            name:'cart',
            path: '/cart',
            component: Cart,
            meta:{isAuth:true}
        },
        {
            name:"itemList",
            path:'/itemList',
            component:ItemList,
            meta:{title:"itemList",isAuth:true}
        },
        {
            name:"personal",
            path:'/personal',
            component:Personal,
            meta:{isAuth:true}
        }
    ]
})

router.beforeEach((to, from, next) => {
    const isUnauthorized = unauthorized()
    if(to.name.startsWith('welcome') && !isUnauthorized) {
        next('/home')
    } else if(to.fullPath.startsWith('/home') && isUnauthorized) {
        next('/')
    } else {
        next()
    }
})

export default router
