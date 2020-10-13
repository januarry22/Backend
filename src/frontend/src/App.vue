<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      app
    >
      <v-list dense>
        <v-list-item router :to ="{name:'Home'}">
              <v-list-item-action>
                <v-icon>mdi-home</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Home</v-list-item-title>
              </v-list-item-content>
        </v-list-item>

        <v-list-item router :to ="{name:'Service'}">
              <v-list-item-action>
                <v-icon>mdi-television</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Service</v-list-item-title>
              </v-list-item-content>
        </v-list-item>
        <v-list-item router :to ="{name:'Mypage'}">
              <v-list-item-action>
                <v-icon>mdi-AccountDetails</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>마이페이지</v-list-item-title>
              </v-list-item-content>
        </v-list-item>

      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      app
      color="indigo"
      dark
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>회계 서비스</v-toolbar-title>
       <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn router :to="{name:'Login' }">
              <v-icon>mdi-account-circle</v-icon>로그인
            </v-btn>
            <v-btn v-if="login_success" @click="$store.dispatch('Logout')">
               <v-icon>mdi-logout </v-icon>로그아웃
            </v-btn>

            <v-btn @click="$router.go(-1)">
              <v-icon>mdi-backspace</v-icon>뒤로가기
            </v-btn>
          </v-toolbar-items>
          </v-app-bar>

         <v-main>
        <router-view/>
    </v-main>
    <v-footer
      color="indigo"
      app
    >
      <span class="white--text">&copy; {{ new Date().getFullYear() }}</span>
    </v-footer>
  </v-app>
</template>

<script>
import {mapState, mapMutations, mapGetters} from 'vuex'
  export default {
    props: {
      source: String,
    },
    data: () => ({
      drawer: null,
    }),
    computed:{
    
    },
    created(){
      if(this.Userinfo.user_token==null){
      this.$store.commit("INSERT_TOKEN")
      console.log("token"+this.Userinfo.user_token)
      }
    }
  }

</script>