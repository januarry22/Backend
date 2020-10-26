<template>
  <v-sparkline
    :value="value"
    :gradient="gradient"
    :smooth="radius || false"
    :padding="padding"
    :line-width="width"
    :stroke-linecap="lineCap"
    :gradient-direction="gradientDirection"
    :fill="fill"
    :type="type"
    :auto-line-width="autoLineWidth"
    auto-draw
  ></v-sparkline>
  
</template>

<script>
import axios from 'axios'
  const gradients = [
    ['#222'],
    ['#42b3f4'],
    ['red', 'orange', 'yellow'],
    ['purple', 'violet'],
    ['#00c6ff', '#F0F', '#FF0'],
    ['#f72047', '#ffd200', '#1feaea'],
  ]

  export default {
    data(){
      // width: 2,
      // radius: 10,
      // padding: 8,
      // lineCap: 'round',
      // gradient: gradients[5],
     return{
      value: [],
     } 
      // gradientDirection: 'top',
      // gradients,
      // fill: false,
      // type: 'trend',
      // autoLineWidth: false,
    },

    mounted(){
      this.fetchStock()

    },

    methods: {
        fetchStock () {
        this.stock_user_id=this.Userinfo.username
        axios
          .get('http://localhost:9000/api/stock/list/'+this.stock_user_id)
          .then(Response=>
            this.value=Response.data.stock_quantity
          )
      },
  }
  }
</script>