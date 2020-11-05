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
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="date"
                    label="날짜"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="date"
                  no-title
                  scrollable
                >
                  <v-spacer></v-spacer>
                  <v-btn
                    text
                    color="primary"
                    @click="menu = false"
                  >
                    Cancel
                  </v-btn>
                  <v-btn
                    text
                    color="primary"
                    @click="$refs.menu.save(date)"
                  >
                    OK
                  </v-btn>
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
                        <th class="text-left">이름</th>
                        <th class="text-left">날짜</th>
                        <th class="text-left">시급</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="item in employee_nameList" :key="item.name">
                        <td>{{ item.employee_name }}</td>
                        <td>{{ date }}</td>
                        <td>{{ item.employee_rank_pay }}</td>
                        </tr>
                    </tbody>
                    </template>
            </v-simple-table>
            </div>
        </v-card>
        <v-spacer></v-spacer>
    </v-col>


    </v-row>
  </v-container>
</template>

<script>
import {mapState} from 'vuex'
import axios from 'axios';

  export default {
    data:()=> ({
        date: new Date().toISOString().substr(0, 10),
        menu: false,
        pagination: {},
        memo: 'memo',
        select:null,
        employee_nameList : []
      
    }),
    computed:{
      
      ...mapState(["Userinfo"])
    },
    mounted() {
          this.fetchEmployee_name()
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
      },

      fetchEmployee_name(){
        this.employee_user_id=this.Userinfo.username
          axios
          .get('http://localhost:9000/api/employee/employee_nameList/'+this.employee_user_id)
          .then(Response=>
            this.employee_nameList=Response.data
        )
      }
    },
  }
</script>