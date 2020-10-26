<template>
  <v-container class="grey lighten-5">
    <v-row>

    <v-col sm="6">
        <v-card>
            <v-card-title>
                <h3>시급 계산</h3>
            </v-card-title>
                  <!-- <v-text-field
                        v-model="name"
                        :counter="10"
                        :rules="nameRules"
                        label="Name"
                        required
                  ></v-text-field> -->
                    <v-select
                        v-model="select"
                        :items="employee_name"
                        :rules="[v => !!v || 'Item is required']"
                        label="이름"
                        required
                    ></v-select>
                  <v-menu
                        ref="menu"
                        v-model="menu"
                        :close-on-content-click="false"
                        :return-value.sync="date"
                        transition="scale-transition"
                        offset-y
                        max-width="290px"
                        min-width="290px"
                    >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                            v-model="date"
                            label="날짜"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                            suffix="Date"
                      ></v-text-field>
                    </template>
                    <v-date-picker
                        v-model="date"
                        type="month"
                        no-title
                        scrollable
                    >
                    <v-spacer></v-spacer>
                        <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                        <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                    </v-date-picker>
                  </v-menu>

                    <v-text-field
                        label="출근 시간"
                        value="12:30:00"
                        type="time"
                        suffix="PST"
                    ></v-text-field>

                    <v-text-field
                        label="퇴근 시간"
                        value="12:30:00"
                        type="time"
                        suffix="PST"
                    ></v-text-field>

                    <v-select
                        v-model="select"
                        :items="items"
                        :rules="[v => !!v || 'Item is required']"
                        label="시급"
                        required
                    ></v-select>
          <v-card-actions>
                    <v-btn
                        color="primary"
                        class="mr-4"
                        @click="save"
                    >입력 </v-btn>

                    <v-btn
                        color="green"
                        @click="calc"
                    >계산</v-btn>
          </v-card-actions>
        </v-card>
    </v-col>


    <v-col sm="6">
        <v-card>
            <v-spacer></v-spacer>
            <div>
             <v-simple-table height="300px">
                    <template v-slot:default>
                    <thead>
                        <tr>
                        <th class="text-left">Name</th>
                        <th class="text-left">Calories</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="item in desserts" :key="item.name">
                        <td>{{ item.name }}</td>
                        <td>{{ item.calories }}</td>
                        </tr>
                    </tbody>
                    </template>
            </v-simple-table>
            </div>
        </v-card>
        <v-spacer></v-spacer>
            <v-card
                    class="mx-auto"
                    full-width
                    tile
                >
                   <v-simple-table dense>
                        <template v-slot:default>
                        <thead>
                            <tr>
                            <th class="text-left">Name</th>
                            <th class="text-left">Calories</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="item in desserts" :key="item.name">
                            <td>{{ item.name }}</td>
                            <td>{{ item.calories }}</td>
                            </tr>
                        </tbody>
                        </template>
                    </v-simple-table>
                </v-card>

    </v-col>


    </v-row>
  </v-container>
</template>

<script>
  export default {
    data () {
      return {
        pagination: {},
        memo: 'memo',
        headers: [
          {
            text: 'Dessert (100g serving)',
            align: 'start',
            sortable: false,
            value: 'name',
          },
          { text: 'Calories', value: 'calories' },
          { text: 'Fat (g)', value: 'fat' },
          { text: 'Carbs (g)', value: 'carbs' },
          { text: 'Protein (g)', value: 'protein' },
          { text: 'Iron (%)', value: 'iron' },
        ],
        employee_name: [],
      }
    },
    methods: {
      save () {
        this.snack = true
        this.snackColor = 'success'
        this.snackText = 'Data saved'
      },
      cancel () {
        this.snack = true
        this.snackColor = 'error'
        this.snackText = 'Canceled'
      },
      open () {
        this.snack = true
        this.snackColor = 'info'
        this.snackText = 'Dialog opened'
      },
      close () {
        console.log('Dialog closed')
      },
      click(){
          this.te
      }
    },
  }
</script>