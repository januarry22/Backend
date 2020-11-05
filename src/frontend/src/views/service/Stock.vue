<template>
<v-card>
  <v-tabs
    v-model="tab"
    fixed-tabs
    background-color="indigo"
    dark
  >
    <v-tab href="#tab-1">
      재고 입력
    </v-tab>
    <v-tab href="#tab-2">
      재고 관리
    </v-tab>

  </v-tabs>
    <v-tabs-items v-model="tab">
        <v-tab-item 
            v-for="i in 4"
            :key="i"
            :value="'tab-' + i">
            <v-card flat>
              <b v-if ="i===1">
              <Stock_insert/></b>
            </v-card>
            <v-card flat>
              <b v-if ="i===2">
              <Stock_manage/></b>
            </v-card>

        </v-tab-item>
        </v-tabs-items>

</v-card>
</template>

<script>
import Stock_manage from './stock/Stock_manage'
import Stock_insert from './stock/Stock_insert'
  export default {
    data () {
      return {
        tab: null,
     }
    },
    components : {
        Stock_manage,
        Stock_insert
    }
  }
</script>