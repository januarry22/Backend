


<template>
<v-container fluid>
 
      <v-col cols="400">
        <v-row
          :align="alignment"
          :justify="justify"
          style="height: 600px;"
        >
          <v-btn
            class="ma-10 pa-10"
            width="200px"
            height="200px" router :to ="{name:'Sales'}">
            매출
          </v-btn>
          <v-btn
            class="ma-10 pa-10"
            width="200px"
            height="200px" router :to ="{name:'Expand'}">
            지출
          </v-btn>
          <v-btn
            class="ma-10 pa-10"
            width="200px"
            height="200px" router :to ="{name:'Stock'}">
            재고
          </v-btn>
          <v-btn
            class="ma-10 pa-10"
            width="200px"
            height="200px" router :to ="{name:'HR'}">
            인사
          </v-btn>
        </v-row>
      </v-col>

</v-container>

    
</template>
<script>
  export default {
    data () {
      return {
     
        alignment: 'center',
        dense: false,
        justify: 'center'
      }
    },
  }
</script>