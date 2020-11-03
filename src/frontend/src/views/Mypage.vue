
<template>

  <v-container fluid>
    <v-data-iterator
      :items="user_info"
      hide-default-footer
    >
      <template v-slot:header>
        <v-toolbar
          class="mb-2"
          color="indigo darken-5"
          dark
          flat
        >
          <v-toolbar-title>MY PAGE | {{Userinfo.user_name}} 님</v-toolbar-title>

        </v-toolbar>
      </template>

      <template v-slot:default="props">
        <v-row>
          <v-col
            v-for="item in props.items"
            :key="item.name"
            cols="12"
            sm="12"
            md="12"
            lg="12"
          >
            <v-card>
              <v-card-title class="subheading font-weight-bold">{{ Userinfo.user_name }}</v-card-title>

              <v-divider></v-divider>

              <v-list dense>
                <v-list-item>
                  <v-list-item-content>이 름 : </v-list-item-content>
                  <v-list-item-content class="align-end">{{ Userinfo.user_name }}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>I D : </v-list-item-content>
                  <v-list-item-content class="align-end">{{ Userinfo.username }}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>비밀번호 : </v-list-item-content>
                  <v-list-item-content class="align-end">{{ Userinfo.password }}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>생년원일 : </v-list-item-content>
                  <v-list-item-content class="align-end">{{ Userinfo.birth }}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>전화번호 : </v-list-item-content>
                  <v-list-item-content class="align-end">{{ Userinfo.tel }}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>사업자 번호 : </v-list-item-content>
                  <v-list-item-content class="align-end">{{ Userinfo.serviceNum }}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>상호명 : </v-list-item-content>
                  <v-list-item-content class="align-end">{{ Userinfo.serviceName }}</v-list-item-content>
                </v-list-item>
              </v-list>
            </v-card>
          </v-col>
        </v-row>
      </template>

      <template v-slot:footer>
        <v-toolbar
          class="mt-2"
          color="indigo"
          dark
          flat
        >
          <v-toolbar-title class="subheading">welcome</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn>
              <v-icon>mdi-pencil</v-icon>수정
            </v-btn>
            <v-btn>
               <v-icon>mdi-logout </v-icon>탈퇴
            </v-btn>
          </v-toolbar-items>
        </v-toolbar>
      </template>
    </v-data-iterator>
  </v-container>
</template>

<script>

import {mapState} from "vuex"

  export default {
          computed: {
        ...mapState(["Userinfo"])
      },
    
    data: () => ({
      user_info: [
        {}
      ],
    }),
  }
</script>