import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login';
import BookMain from '../components/book/BookMain';
import BookList from '../components/book/BookList';
import TypeList from '../components/book/TypeList';
import UserList from '../components/system/UserList';
import SystemMain from '../components/system/SystemMain';
import BookSelfList from '../components/system/BookSelfList';
import BorrowList from '../components/book/BorrowList';
import AnalyseMain from "../components/analyse/AnalyseMain";
import BookAnalyse from "../components/analyse/BookAnalyse";
import BorrowAnalyse from "../components/analyse/BorrowAnalyse";
import UserAnalyse from "../components/analyse/UserAnalyse";
import UserMain from "../components/user/UserMain";
import UserBorrowList from "../components/user/UserBorrowList";
import UserPasswordForm from "../components/user/UserPasswordForm";
import Welcome from "../views/Welcome";
import UserPhoneForm from "@/components/user/UserPhoneForm";

const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home,
    redirect: { path: '/home/welcome' },
    children: [
      {
        path: 'book',
        name: 'book',
        component: BookMain,
        redirect: { path: '/home/book/booklist' },
        children: [
          {
            path: 'booklist',
            component: BookList
          },
          {
            path: 'borrowlist',
            component: BorrowList
          },
          {
            path: 'typelist',
            component: TypeList
          }
        ]
      },
      {
        path: 'system',
        name: 'system',
        component: SystemMain,
        redirect: { path: '/home/system/userlist' },
        children: [
          {
            path: 'userlist',
            component: UserList
          },
          {
            path: 'bookselflist',
            component: BookSelfList
          }
        ]
      },
      {
        path: 'analyse',
        component: AnalyseMain,
        redirect: { path: '/home/analyse/book' },
        children: [
          {
            path: 'book',
            component: BookAnalyse
          },
          {
            path: 'borrow',
            component: BorrowAnalyse
          },
          {
            path: 'user',
            component: UserAnalyse
          }
        ]
      },
      {
        path: 'user',
        name: 'user',
        component: UserMain,
        redirect: { path: '/home/user/borrow' },
        children: [
          {
            path: 'borrow',
            component: UserBorrowList
          },
          {
            path: 'password',
            component: UserPasswordForm
          },
          {
            path: 'phone',
            component: UserPhoneForm
          },
        ]
      },
      {
        path: 'welcome',
        component: Welcome,
      }
    ]
  },
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
