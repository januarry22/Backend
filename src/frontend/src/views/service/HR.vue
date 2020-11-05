<template>
<v-card>
  <v-tabs
    v-model="tab"
    fixed-tabs
    background-color="indigo"
    dark
  >
    <v-tab href="#tab-1">
      급여산정
    </v-tab>
    <v-tab href="#tab-2">
      총 인건비
    </v-tab>
    <v-tab href="#tab-3">
      인사 관리
    </v-tab>
    <v-tab href="#tab-4">
      거래처 관리
    </v-tab>
  </v-tabs>
    <v-tabs-items v-model="tab">
        <v-tab-item 
            v-for="i in 4"
            :key="i"
            :value="'tab-' + i">
            <v-card flat>
              <b v-if ="i===1">
              <HR_pay/></b>
            </v-card>
            <v-card flat>
              <b v-if ="i===2">
              <HR_amount/>
              <HR_report/></b>
            </v-card>
            <v-card flat>
              <b v-if ="i===3">
              <HR_manage/></b>
            </v-card>
            <v-card flat>
              <b v-if ="i===4">
              <HR_seller/></b>
            </v-card>

        </v-tab-item>
        </v-tabs-items>

</v-card>
</template>

<script>
import HR_amount from './human/HR_amount'
import HR_manage from './human/HR_manage'
import HR_pay from './human/HR_pay'
import HR_seller from './human/HR_seller'
import HR_report from './human/HR_report'
  export default {
    data () {
      return {
        tab: null,
     }
    },
    components : {
        HR_amount,
        HR_manage,
        HR_pay,
        HR_seller,
        HR_report
    }
  }
</script>