<template>
  <v-container class="grey lighten-5">
    <v-row no-gutters>

    <v-col sm="12">
        <v-card>      
           <v-card
                class="mx-auto text-center"
                color="green"
                dark
                full-width>
                    <v-card-text>
                    <v-sheet color="rgba(0, 0, 0, .12)">
                        <v-sparkline
                        :value="value"
                        color="rgba(255, 255, 255, .7)"
                        height="100"
                        padding="24"
                        stroke-linecap="round"
                        smooth
                        >
                        <template v-slot:label="item">
                            ${{ item.value }}
                        </template>
                        </v-sparkline>
                    </v-sheet>
                    </v-card-text>

                    <v-card-text>
                    <div class="display-1 font-weight-thin">Sales Last 24h</div>
                    </v-card-text>
            <v-divider></v-divider>
            </v-card>
        </v-card>
    </v-col>


    </v-row>
  </v-container>
</template>

<script>
  export default {
    data: () => ({
       date: new Date().toISOString().substr(0, 10),
       value: [
         
       ],
    }),
    computed:{
      values(){
        return this.sumList.map(x=>x.count);
      }
    }
  }
</script>