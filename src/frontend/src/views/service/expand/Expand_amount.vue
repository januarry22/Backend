<template>
  <v-container class="grey lighten-5">
  
        <v-card full-width>
           <Expand_report/>
        </v-card>
        
    <v-card>
        <v-card-title>
            <v-select
                v-model="value"
                :items="items"
                label="Select Item"
                multiple
                ><template v-slot:selection="{ item, index }">
                <v-chip v-if="index === 0">
                <span>{{ item }}</span>
                </v-chip>
                <span
                v-if="index === 1"
                class="grey--text caption"
                >(+{{ value.length - 1 }} others)</span>
            </template>
            </v-select>
        </v-card-title>
            <v-data-table
                :headers="headers"
                :items="Expand_list"
                :items-per-page="5"
                class="elevation-1"
            ></v-data-table>
        </v-card>


  </v-container>
</template>

<script>
import {mapActions, mapState} from 'vuex'
import axios from 'axios';

import Expand_report from './Expand_report'
  export default {
    
    data: ()=> ({
   
        items: ['카드','현금'],
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
        Expand_list: [],

    }),
    components: {
      Expand_report
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