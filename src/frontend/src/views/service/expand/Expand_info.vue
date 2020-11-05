<template>
  <v-container class="grey lighten-5">
    <v-row no-gutters>

    <v-col sm="8">
    <v-card>
        <v-card-title>
            <v-text-field
                v-model="search"
                label="Search"
                single-line
                hide-details
            ></v-text-field>
        </v-card-title>
            <v-data-table
            :headers="headers"
            :items="Expand_list"
            :search="search"
        ></v-data-table>
    </v-card>
    </v-col>

    <v-col sm="4">
    <v-card
    class="mx-auto"
    full-width
    full-height
  >
    <v-list-item two-line>
      <v-list-item-content>
        <v-list-item-title class="headline">San Francisco</v-list-item-title>
        <v-list-item-subtitle>z</v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>

    <v-card-text>
      <v-row align="center">
        <v-col class="display-3" cols="6">
          23&deg;C
        </v-col>
        <v-col cols="6">
          <v-img
            src="https://cdn.vuetifyjs.com/images/cards/sun.png"
            alt="Sunny image"
            width="92"
          ></v-img>
        </v-col>
      </v-row>
    </v-card-text>

    <v-list-item>
      <v-list-item-icon>
        <v-icon>mdi-send</v-icon>
      </v-list-item-icon>
      <v-list-item-subtitle>23 km/h</v-list-item-subtitle>
    </v-list-item>

    <v-list-item>
      <v-list-item-icon>
        <v-icon>mdi-cloud-download</v-icon>
      </v-list-item-icon>
      <v-list-item-subtitle>48%</v-list-item-subtitle>
    </v-list-item>

    <v-list class="transparent">
      <v-list-item
        v-for="item in forecast"
        :key="item.day"
      >
        <v-list-item-title>{{ item.day }}</v-list-item-title>

        <v-list-item-icon>
          <v-icon>{{ item.icon }}</v-icon>
        </v-list-item-icon>

        <v-list-item-subtitle class="text-right">
          {{ item.temp }}
        </v-list-item-subtitle>
      </v-list-item>
    </v-list>

    <v-divider></v-divider>

    <v-card-actions>
      <v-btn text>Full Report</v-btn>
    </v-card-actions>
  </v-card>
    </v-col>

    </v-row>
  </v-container>
</template>

<script>
import {mapState, mapActions} from "vuex"
import axios from 'axios';
  export default {
    data () {
      return {
        search: '',
          headers: [
            {
              text: '지출 id',
              align: 'start',
              sortable: false,
              value: 'expand_id',
            },
            { text: '지출 내역', value: 'expand_content' },
            { text: '지출 날짜', value: 'expand_date' },
            { text: '지출 가격', value: 'expand_price' },
            { text: '결제 유형', value: 'expand_val' },
          ],
          Expand_list:[]
      }
    },
    computed:{
     // ...mapState(["Expand_list"])
    },
    created(){
     // this.$store.dispatch('ExpandList')
    },  
    mounted(){
      this.fetchItems()

    },
    methods: {
      fetchItems () {
           axios
           .get('http://localhost:9000/api/expand/list')
            .then(Response=>
            this.Expand_list=Response.data
         )
      },
    }
  }
</script>