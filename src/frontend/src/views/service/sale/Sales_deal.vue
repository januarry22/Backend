<template>
  <v-card>
    <v-card-title>
      매출 거래 내역
      <v-spacer></v-spacer>      
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="검색"
        single-line
        hide-details
      ></v-text-field>

    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="Sale_list"
      :search="search"
      :group-by="groupby"
    ></v-data-table>
  </v-card>
</template>

<script>
import {mapState} from 'vuex'
import axios from 'axios';

  export default {
    data() {
      return {
        search: '',
        headers: [
          {
            text: '결제 ID',
            align: 'start',
            sortable: false,
            value: 'sale_deal_id',
          },
          { text: '거래 날짜', value: 'sale_deal_date' },
          { text: '결제 유형', value: 'sale_deal_pay' },
          { text: '결제 가격', value: 'sale_deal_price' },
          { text: '결제 품목', value: 'sale_deal_item' }
        ],
        Sale_list: [],
      }
    },
    computed:{
      
      ...mapState(["Userinfo"])
    },
    mounted(){
      this.fetchSale()

    },
    methods:{
      fetchSale () {
        this.sale_user_id=this.Userinfo.username
        axios
          .get('http://localhost:9000/api/sale/list/'+this.sale_user_id)
          .then(Response=>
            this.Sale_list=Response.data
          )
      },
    }
  }
</script>