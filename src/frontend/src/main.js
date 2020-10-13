import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/index'
import vuetify from './plugins/vuetify'


new Vue({
  router,
  store,
  vuetify,
  beforeCreate(){
  //   localStorage.getItem("data")
  //   this.$store.commit('SET_USER')
   },
  // created(){
    
  //   window.addEventListener('beforeunload', function(event){
  //     event.returnValue='Write something'
      
  //     console.log(SET_USER)
  //     this.$router.push('/')

  //   })
  // },
  render: h => h(App)
}).$mount('#app')
