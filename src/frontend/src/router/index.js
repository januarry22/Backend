import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Service from '../views/Service.vue'
import Login from '../views/Login.vue'
import SignUp from '../views/SignUp.vue'
import Mypage from '../views/Mypage.vue'

import HR from '../views/service/HR.vue'
import Sales from '../views/service/Sales.vue'
import Stock from '../views/service/Stock.vue'
import Expand from '../views/service/Expand.vue'

import store from '../store/index'

Vue.use(VueRouter)



const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/mypage",
    name: "Mypage",
    component: Mypage,
  //  beforeEnter: requireAuth()
  },
  {
    path: "/signup",
    name: "SignUp",
    component: SignUp
  },
  {
    path: "/service",
    name: "Service",
    component: Service,
    beforeEnter: (to, from, next) => {
      if (store.state.loggedIn == false) {
          next(false);
      } else {
          next();
      }
    }
  },
  {
    path: "/sales",
    name: "Sales",
    component: Sales
  },
  {
    path: "/expand",
    name: "Expand",
    component: Expand
  },
  {
    path: "/stock",
    name: "Stock",
    component: Stock
  },
  {
    path: "/HR",
    name: "HR",
    component: HR
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,

})

// router.beforeEach((to, from, next) => {
//   if(to.matched.some(record => record.meta.requiresAuth)) {
//     if (store.getters.isLoggedIn) {
//       next()
//       return
//     }
//     next('/login')
//   } else {
//     next()
//   }
// })


// const rejectAuthUser = {
//   beforeEnter( to, from, next){
//   if(Store.state.login_success === true){
//     // 이미 로그인된 유저는 접근 막음
//     alert("이미 로그인을 하였습니다.")
//     next({name: 'Home'})
//   }else{
//     next()
//     // 경로 설정 하지 않으면 다음 실행
//   }
// }
// }

// const onlyAuthUser = {
//   beforeEach( to, from, next) {
//   if(Store.state.login_success === false){
//     // 로그인 되지 않은 유저
//     alert("로그인 후 이용가능합니다")
//     next({name: 'Home'})
//   }else{
//     next("/")
//     // 경로 설정 하지 않으면 다음 실행
//   }
// }
// }

export default router
