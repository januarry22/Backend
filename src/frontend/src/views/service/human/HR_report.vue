<template>
  <v-data-table
    :headers="headers"
    :items="stockSum"
    :items-per-page="5"
    item-key="name"
    class="elevation-1"
    :footer-props="{
      showFirstLastPage: true,
      firstIcon: 'mdi-arrow-collapse-left',
      lastIcon: 'mdi-arrow-collapse-right',
      prevIcon: 'mdi-minus',
      nextIcon: 'mdi-plus'
    }"
  ></v-data-table>
</template>

<script>
import axios from 'axios'
import {mapState} from 'vuex'
  export default {
    data () {
      return {
        headers: [
          {
            text: '날짜',
            align: 'start',
            value: 'stock_day',
          },
          { text: '일별합계', value: 'stock_day_sum' },
        ],
        stockSum: [],
      }
    },
    mounted(){
      this.fetchStocksum()

    },
    computed:{
      
      ...mapState(["Userinfo"])
    },

    methods: {
        fetchStocksum() {
        this.stock_user_id=this.Userinfo.username
        axios
          .get('http://localhost:9000/api/stock/selectDaySum/'+this.stock_user_id)
          .then(Response=>
            this.stockSum=Response.data
          
          )
      },
  }
  }
</script>