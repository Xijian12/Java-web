import { createRouter, createWebHistory } from 'vue-router'
import { unauthorized } from "@/net";

import Home from '@/components/Home.vue'
import BookDetail from '@/components/BookDetail.vue'
import BookDetailPage from "@/components/BookDetailPage.vue"
import MaterialDetail from '@/components/MaterialDetail.vue'
import MaterialDetailPage from "@/components/MaterialDetailPage.vue"
import SearchDisplay from '@/components/Search/SearchDisplay.vue'
import SearchDisplayPage from "@/components/SearchDisplayPage.vue"
import SearchDisplayBookName from '@/components/Search/SearchDisplayBookName.vue'
import SearchDisplayPageBookName from "@/components/SearchDisplayPageBookName.vue"
import SearchDisplayMaterialSMS from '@/components/Search/SearchDisplayMaterialSMS.vue'
import SearchDisplayMaterialPageSMS from "@/components/SearchDisplayMaterialPageSMS.vue"
import Personal from '@/components/Personal.vue'

// 后台
import AboutView from "@/components/Backend/AboutView.vue";
import NavView from "@/components/Backend/NavView.vue";
import HomeView from "@/components/Backend/HomeView.vue";
import BookTableView from "@/components/Backend/BookTableView.vue";
import BookView from "@/components/Backend/BookView.vue";
import MaterialView from "@/components/Backend/MaterialView.vue";
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
        // 搜索图书展示页容器
        {
            path: "/SearchDisplayPage",
            name: "SearchDisplayPage",
            meta: { show: false },
            component: SearchDisplayPage,
            children: [
            // 搜索展示页
            {
                path: "/SearchDisplay/:id",
                name: "SearchDisplay",
                meta: { show: false },
                component: SearchDisplay,
            }
            ]   
        },
         // 搜索图书名称展示页容器
         {
          path: "/SearchDisplayPageBookName",
          name: "SearchDisplayPageBookName",
          meta: { show: false },
          component: SearchDisplayPageBookName,
          children: [
          // 搜索展示页
          {
              path: "/SearchDisplayBookName/:id",
              name: "SearchDisplayBookName",
              meta: { show: false },
              component: SearchDisplayBookName,
          }
          ]   
      },
        // 搜索资料SMS展示页容器
        {
          path: "/SearchDisplayMaterialPageSMS",
          name: "SearchDisplayMaterialPageSMS",
          meta: { show: false },
          component: SearchDisplayMaterialPageSMS,
          children: [
          // 搜索展示页
          {
              path: "/SearchDisplayMaterialSMS/:id",
              name: "SearchDisplayMaterialSMS",
              meta: { show: false },
              component: SearchDisplayMaterialSMS,
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
        // 资料详情页容器
        {
          path: "/MaterialDetailPage",
          name: "MaterialDetailPage",
          meta: { show: false },
          component: MaterialDetailPage,
          children: [
              {
                  // 图书详情页
                  path: "/MaterialDetail/:id",
                  name: "MaterialDetail",
                  meta: { show: false },
                  component: MaterialDetail,
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
                  name: 'bookViewPersonal',
                  path: 'bookViewPersonal',
                  meta: { show: false },
                  component: () => import('@/components/Personal/main/bookManage/BookViewPersonal.vue')
              },
                {
                  path: "publishMaterial",
                  name: 'publishMaterial',
                  meta: { show: false },
                  component: () => import("@/components/Personal/main/bookManage/PublishMaterial.vue")
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
                {
                  path: "publishrecordMaterial",
                  name: 'publishrecordMaterial',
                  meta: { show: false },
                  component: () => import("@/components/Personal/main/bookManage/PublishRecordMaterial.vue")
              },
                {
                  name: 'favoriteMaterial',
                  path: 'favoriteMaterial',
                  meta: { show: false },
                  component: () => import('@/components/Personal/main/bookManage/FavoriteMaterial.vue')
              },
              {
                name: 'materialViewPersonal',
                path: 'materialViewPersonal',
                meta: { show: false },
                component: () => import('@/components/Personal/main/bookManage/MaterialViewPersonal.vue')
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
                name: "公共图书管理",
                meta: { show: true },
                components: { bookTable: BookTableView },
              },
              {
                path: "/book",
                name: "图书信息管理",
                meta: { show: true },
                components: { book: BookView },
              },
            ],
          },
          {
            path: "/nav",
            name: "资料",
            component: NavView,
            meta: { show: true },
            children: [
              {
                path: "/material",
                name: "资料信息管理",
                meta: { show: true },
                components: { material: MaterialView },
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
                meta: { show: false },
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
    if(to.fullPath.startsWith('/backendhome') && isUnauthorized) {
        next('/')
    } else {
        next()
    }
})


export default router
