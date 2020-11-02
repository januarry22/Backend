<template>
  <v-data-table
    :headers="headers"
    :items="Stock_list"
    sort-by="expand_id"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>재고 내역</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="primary"
              dark
              class="mb-2"
              v-bind="attrs"
              v-on="on"
            >재고 입력</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.stock_id" label="재고 ID"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.stock_name" label="재고명"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.stock_regi_date" label="재고등록날짜"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.stock_expire_date" label="재고유통기한"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.stock_quantity" label="재고량"></v-text-field>
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
      <template v-slot:item.action="{ item }">
            <v-icon
              small
              class="mr-2"
              @click="editItem(item)"
            >
              edit
            </v-icon>
            <v-icon
              small
              @click="deleteItem(item)"
            >
              delete
            </v-icon>
      </template>
  </v-data-table>
</template>


<script>
import {mapActions, mapState} from 'vuex'
import axios from 'axios';
import moment from 'moment';


  export default {
    data: () => ({
      dialog: false,
      headers: [
        {
          text: '재고 id',
          align: 'start',
          sortable: false,
          value: 'stock_id',
        },
        { text: '재고명', value: 'stock_name' },
        { text: '재고등록일', value: 'stock_regi_date' },
        { text: '재고유통기한', value: 'stock_expire_date' },
        { text: '재고량', value: 'stock_quantity' },
        { text: 'Actions', value: 'action', sortable: false },
      ],
      Stock_list: [],
      editedIndex: -1,
      editedItem: {

      },
      defaultItem: {

      },
    }),

    computed: {
      formTitle () {
        return this.editedIndex === -1 ? '재고 등록' : '수정'
      },
      
      ...mapState(["Userinfo"])
    },

    watch: {
      dialog (val) {
        val || this.close()
      },
    },
    mounted() {
      this.fetchStock()
    },

    methods: {
  
      fetchStock () {
        this.stock_user_id=this.Userinfo.username
        axios
          .get('http://localhost:9000/api/stock/list/'+this.stock_user_id)
          .then(Response=>
            this.Stock_list=Response.data
          )
      },

      editItem (item) {
        this.editedIndex = this.Stock_list.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.stock_user_id=this.Userinfo.username
       // console.log(user_id)
        this.dialog = true
      },

      deleteItem (item) {
        const index = this.Stock_list.indexOf(item)
        this.deleteItem = Object.assign({}, item)

        this.stock_id=this.deleteItem.stock_id
        if(confirm('삭제하시겠습니까?')){
          axios
          .delete('http://localhost:9000/api/stock/delete/'+this.stock_id)
          .then(Response=>{
            this.fetchStock()
          })
          this.Stock_list.splice(index, 1)

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
        this.stock_id=this.editedItem.stock_id
        this.stock_user_id=this.Userinfo.username
       // console.log(this.expand_id)
        if (this.editedIndex > -1) {
          Object.assign(this.Stock_list[this.editedIndex], this.editedItem)
          axios
            .delete('http://localhost:9000/api/stock/delete/'+this.stock_id)
            .then(Response=>{
             this.fetchStock()
             })
          axios
            .post('http://localhost:9000/api/stock/insert',{
                stock_name: this.editedItem.stock_name,
                stock_regi_date: this.editedItem.stock_regi_date,
                stock_expire_date: this.editedItem.stock_expire_date,
                stock_quantity: this.editedItem.stock_quantity,
               // stock_user_id: this.editedItem.username

          })
          .then(Response=>{
            this.fetchStock()
          })
        } else {
          this.Stock_list.push(this.editedItem)


          axios
          .post('http://localhost:9000/api/stock/insert',{
                stock_name: this.editedItem.stock_name,
                stock_regi_date: this.editedItem.stock_regi_date,
                stock_expire_date: this.editedItem.stock_expire_date,
                stock_quantity: this.editedItem.stock_quantity,
                stock_user_id: this.Userinfo.username
          })
          .then(Response=>{
            this.fetchStock()
          })
        }
        this.close()
      },
    },
  }
</script>