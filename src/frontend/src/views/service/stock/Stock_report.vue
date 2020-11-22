<template>
  <div class="container">
    <Donut v-if="loaded" 
        :chart-data="stock_day" 
        :chart-labels="stock_day_sum"/>
  </div>
  
</template>

<script>

import {mapState} from 'vuex'
import axios from 'axios'
import Donut from '@/components/DonutChart'
  export default {
    components:{
        Donut
    },
    props:{},
    data(){
      return{
        loaded:false,
        stock_day:[],
        stock_day_sum:[]
      }
    },

    mounted(){
      this.fetchStockData()

    },
    computed:{
        ...mapState(["Userinfo"])
    },

    methods: {
        fetchStockData () {
        this.stock_user_id=this.Userinfo.username
        axios
          .get('http://localhost:9000/api/stock/selectDaySum/'+this.stock_user_id)
          .then(Response=>{
              console.log(Response.data)
              this.stock_day=Response.data.map(download=>download.stock_day_sum)
              this.stock_day_sum=Response.data.map(download=>download.stock_day)
              this.loaded=true
        })
      },
  }
  }
</script>