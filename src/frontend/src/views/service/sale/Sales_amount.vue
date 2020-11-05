<template>
  <v-container class="grey lighten-5">
    <v-row no-gutters>

    <v-col sm="6">
        <v-card class="mx-auto text-center">
          <v-row justify="space-around">
                <v-date-picker
                v-model="date"
                full-width
                :landscape="$vuetify.breakpoint.smAndUp"
                class="mt-4" color="green lighten-1"
                ></v-date-picker>
          </v-row>
        </v-card>
    </v-col>


    <v-col sm="6">
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

            <v-card-actions class="justify-center">
            <v-btn block text>Go to Report</v-btn>
            </v-card-actions>
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
        423,
        446,
        675,
        510,
        590,
        610,
        760,
      ],
    }),
  }
</script>