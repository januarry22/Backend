<template>
<v-card>
  <v-tabs
    v-model="tab"
    fixed-tabs
    background-color="indigo"
    dark
  >
    <v-tab href="#tab-1">
      지출 내역
    </v-tab>
    <v-tab href="#tab-2">
      지출 관리
    </v-tab>
    <v-tab href="#tab-3">
      지출 통계
    </v-tab>
  </v-tabs>
    <v-tabs-items v-model="tab">
        <v-tab-item 
            v-for="i in 4"
            :key="i"
            :value="'tab-' + i">
            <v-card flat>
              <b v-if ="i===1">
              <Expand_info/></b>
            </v-card>
            <v-card flat>
              <b v-if ="i===2">
              <Expand_manage/></b>
            </v-card>
            <v-card flat>
              <b v-if ="i===3">
              <Expand_amount/></b>
            </v-card>

        </v-tab-item>
        </v-tabs-items>

</v-card>
</template>

<script>
import Expand_manage from './expand/Expand_manage'
import Expand_amount from './expand/Expand_amount'
import Expand_info from './expand/Expand_info'
  export default {
    data () {
      return {
        tab: null,
     }
    },
    components : {
        Expand_manage,
        Expand_amount,
        Expand_info
    }
  }
</script>