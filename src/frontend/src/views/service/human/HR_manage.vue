<template>
  <v-container fluid grid-list-md>
    <v-data-iterator
      :items="Employee_list"
      :rows-per-page-items="rowsPerPageItems"
      :pagination.sync="pagination"
      content-tag="v-layout"
      row
      wrap
    >
      <template v-slot:header>
        <v-toolbar
          class="mb-2"
          color="indigo darken-5"
          dark
          flat
        >
        <v-toolbar-title>사원 정보</v-toolbar-title>
          <v-divider
            class="mx-4"
            inset
            vertical
          ></v-divider>
        <v-spacer></v-spacer>

        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on}">
            <v-btn
              color="primary"
              dark
              class="mb-2"
              v-on="on"
            >사원 등록</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.employee_id" label="사원 ID"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.employee_name" label="사원이름"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.employee_birth" label="생년월일"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.employee_phone" label="연락처"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.employee_rank" label="직급"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.employee_rank_pay" label="직급 급여"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.employee_account_num" label="계좌번호"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.employee_account_bank" label="은행명"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.employee_regi_date" label="입사날짜"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.employee_quit_date" label="퇴사날짜"></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="save">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        </v-toolbar>
      </template>

      <template v-slot:item="props">
        <v-flex
          xs12
          sm6
          md4
          lg3
        >
          <v-card>
            <v-card-title class="subheading font-weight-bold">{{ props.item.employee_id }}</v-card-title>
            <v-divider></v-divider>
            <v-list dense>
              <v-list-tile>
                <v-list-tile-content>사원이름:</v-list-tile-content>
                <v-list-tile-content class="align-end">{{ props.item.employee_name }}</v-list-tile-content>
              </v-list-tile>
              <v-list-tile>
                <v-list-tile-content>생년월일:</v-list-tile-content>
                <v-list-tile-content class="align-end">{{ props.item.employee_birth }}</v-list-tile-content>
              </v-list-tile>
              <v-list-tile>
                <v-list-tile-content>연락처:</v-list-tile-content>
                <v-list-tile-content class="align-end">{{ props.item.employee_phone }}</v-list-tile-content>
              </v-list-tile>
              <v-list-tile>
                <v-list-tile-content>직급:</v-list-tile-content>
                <v-list-tile-content class="align-end">{{ props.item.employee_rank }}</v-list-tile-content>
              </v-list-tile>
              <v-list-tile>
                <v-list-tile-content>직급급여:</v-list-tile-content>
                <v-list-tile-content class="align-end">{{ props.item.employee_rank_pay }}</v-list-tile-content>
              </v-list-tile>
              <v-list-tile>
                <v-list-tile-content>계좌번호:</v-list-tile-content>
                <v-list-tile-content class="align-end">{{ props.item.employee_account_num }}</v-list-tile-content>
              </v-list-tile>
              <v-list-tile>
                <v-list-tile-content>은행명:</v-list-tile-content>
                <v-list-tile-content class="align-end">{{ props.item.employee_account_bank }}</v-list-tile-content>
              </v-list-tile>
              <v-list-tile>
                <v-list-tile-content>입사날짜:</v-list-tile-content>
                <v-list-tile-content class="align-end">{{ props.item.employee_regi_date }}</v-list-tile-content>
              </v-list-tile>
               <v-list-tile>
                <v-list-tile-content>퇴사날짜:</v-list-tile-content>
                <v-list-tile-content class="align-end">{{ props.item.employee_quit_date }}</v-list-tile-content>
              </v-list-tile>
            </v-list>
            <button @click="deleteItem(props.item.employee_id)">삭제</button>
          </v-card>
        </v-flex>
      </template>


      
    </v-data-iterator>

  </v-container>
</template>

<script>
import {mapActions, mapState} from 'vuex'
import axios from 'axios';
  export default {
    data: () => ({
      dialog: false,
      rowsPerPageItems: [4, 8, 12],
      pagination: {
        rowsPerPage: 4
      },
      Employee_list: [],
      editedIndex: -1,
      editedItem: {
      },
    }),
    mounted() {
      this.fetchEmployee()
    },
    computed: {
      formTitle () {
        return this.editedIndex === -1 ? '사원 등록' : '수정'
      },
    },

    watch: {
      dialog (val) {
        val || this.close()
      },
    },
    methods:{
      fetchEmployee(){
        axios
          .get('http://localhost:9000/api/employee/list')
          .then(Response=>{
            this.Employee_list=Response.data
          })
      },

      editItem (item) {
        this.editedIndex = this.Employee_list.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
        this.employee_id=this.editedItem.employee_id
      },

      deleteItem (employee_id) {
        const index = this.Employee_list.indexOf(item)
        this.deleteItem = Object.assign({}, item)

        this.employee_id=this.deleteItem.employee_id

        if(confirm('삭제하시겠습니까?')){
          axios
          .delete('http://localhost:9000/api/employee/delete/'+this.employee_id)
          .then(Response=>{
            this.fetchEmployee()
          })
          this.Employee_list.splice(index, 1)

        }
      },
      close () {
        this.dialog = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },
      save () {
        this.employee_id=this.editedItem.employee_id
       
        if (this.editedIndex > -1) {
          Object.assign(this.Employee_list[this.editedIndex], this.editedItem)
          axios
            .delete('http://localhost:9000/api/expand/delete/'+this.employee_id)
            .then(Response=>{
             this.fetchEmployee()
             })
          axios
            .post('http://localhost:9000/api/employee/insert',{
                employee_name: this.editedItem.employee_name,
                employee_birth: this.editedItem.employee_birth,
                employee_phone: this.editedItem.employee_phone,
                employee_rank: this.editedItem.employee_rank,
                employee_rank_pay: this.editedItem.employee_rank_pay,
                employee_account_num: this.editedItem.employee_account_num,
                employee_account_bank: this.editedItem.employee_account_bank,
                employee_regi_date: this.editedItem.employee_regi_date,
                employee_quit_date: this.editedItem.employee_quit_date
          })
          .then(Response=>{
            this.fetchItems()
          })
        } else {
          this.Employee_list.push(this.editedItem)
          axios
          .post('http://localhost:9000/api/employee/insert',{
               employee_name: this.editedItem.employee_name,
                employee_birth: this.editedItem.employee_birth,
                employee_phone: this.editedItem.employee_phone,
                employee_rank: this.editedItem.employee_rank,
                employee_rank_pay: this.editedItem.employee_rank_pay,
                employee_account_num: this.editedItem.employee_account_num,
                employee_account_bank: this.editedItem.employee_account_bank,
                employee_regi_date: this.editedItem.employee_regi_date,
                employee_quit_date: this.editedItem.employee_quit_date
          })
          .then(Response=>{
            this.fetchEmployee()
          })
        }
        this.close()
      },
    },


  }
</script>