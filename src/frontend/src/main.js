import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/index'
import vuetify from './plugins/vuetify'
import VueMomentJs from "vue-momentjs"



new Vue({
  router,
  store,
  vuetify,
  VueMomentJs,
  render: h => h(App)
}).$mount('#app')
