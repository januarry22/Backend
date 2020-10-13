import Axios from '../axios.js'
import Vue from 'vue'
import Vuex from 'vuex'
import router from '../router/index'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    Userinfo:{
      username: null,
      user_name: null,
      authorities: [],
      user_token:null
      },
    Expand_list:[],


    login_err: false,
    login_success: false
  },
  mutations: {
    SET_USER(state, data){
      state.Userinfo.username=data.username
      state.Userinfo.user_name=data.user_name
      state.Userinfo.authorities=data.authorities
      state.Userinfo.user_token=data.token

      router.push("/mypage")
    },
    INSERT_TOKEN(state){
      state.Userinfo.user_token=localStorage.getItem("token")
      state.login_success=true
    },
    GET_ExpandList(state, data){
      state.Expand_list=data
    },
    SET_ExpandList(state, data){
      state.Expand_list=data
    }

  },
  getters : {
     isLoggedIn: state => state.SET_USER,
    // authStatus: state => state.status,
    allUsers: state=>{
      return state.UserList.length
    }
  },

  actions: {

    loginProcess({commit},payload){
      //로그인 시도
      return new Promise((resolve, reject)=>{
        Axios.post('http://localhost:9000/api/auth/signin', payload)
        .then(Response=>{
          console.log(Response.data)
          if(Response.data.username!=null){
            Axios.defaults.headers.common['Authorization']='Bearer ${Response.data.token}'
            commit('SET_USER', Response.data)
            localStorage.setItem("token", Response.data.token)

          }
        })
        .catch(Error=>{
          console.log('error')
          reject(Error)
        })
      })
    },

    getUser({commit}){
            let token=localstorage.getItem('token') || '';
            fetch('http://localhost:9000/api/auth/signin',{
              headers:{
                'Authorization': token,
              }
            })
    },

    Logout(){
      localStorage.removeItem("token")
      router.push('/')
    },




    SignUp({commit}, payload){
      console.log(payload)
      return new Promise((resolve, reject)=>{
        Axios.post('http://localhost:9000/api/auth/signup', payload)
        .then(Response=>{
          console.log(Response.data)
          if(Response.data==="success"){
            router.push("/login")
          }
        })
        .catch(Error=>{
          console.log('error')
          reject(Error)
        })
      })
    }

  },

  ExpandList({commit}){
    return new Promise((resolve, reject)=>{
      Axios.get('http://localhost:9000/api/expand/list')
        .then(Response=>{
          console.log(Response.data)
          commit('GET_ExpandList', Response.data)
        })
        .catch(Error=>{
          console.log('error')
          reject(Error)
        })
    
      })
  },

  ExpandInsert({commit}, payload){
    return new Promise((resolve, reject)=>{
      Axios.post('http://localhost:9000/api/expand/insert', payload)
        .then(Response=>{
          console.log(Response.data)
          if(Response.data==="success"){
            commit('SET_ExpandList', Response.data)
          }
        })
        .catch(Error=>{
          console.log('error')
          reject(Error)
        })
    })

  },
  






 
  modules: {
  }

})
