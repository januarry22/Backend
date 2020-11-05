<template>
<v-card>
  <v-tabs
    v-model="tab"
    fixed-tabs
    background-color="indigo"
    dark
  >
    <v-tab href="#tab-1">
      거래 내역
    </v-tab>
    <v-tab href="#tab-2">
      매출 관리
    </v-tab>
    <v-tab href="#tab-3">
      매출 통계 및 분석
    </v-tab>
    <v-tab href="#tab-4">
      총 매출 정산
    </v-tab>
  </v-tabs>
    <v-tabs-items v-model="tab">
        <v-tab-item
            v-for="i in 4"
            :key="i"
            :value="'tab-' + i"
        >
            <v-card flat>
              <b v-if ="i===1">
              <Sales_deal/></b>
            </v-card>
            <v-card flat>
              <b v-if ="i===2">
              <Sales_manage/></b>
            </v-card>
            <v-card flat>
              <b v-if ="i===3">
              <Sales_info/></b>
            </v-card>
            <v-card flat>
              <b v-if ="i===4">
              <Sales_amount/>
              <Sales_report/>
              </b>
            </v-card>

        </v-tab-item>
        </v-tabs-items>

</v-card>
</template>

<script>


import Sales_deal from './sale/Sales_deal'
import Sales_amount from './sale/Sales_amount'
import Sales_info from './sale/Sales_info'
import Sales_manage from './sale/Sales_manage'
import Sales_report from './sale/Sales_report'
  export default {
    data () {
      return {
        tab: null,
     }
    },
    components : {
        Sales_deal,
        Sales_amount,
        Sales_info,
        Sales_manage,
        Sales_report
    }
  }
</script>