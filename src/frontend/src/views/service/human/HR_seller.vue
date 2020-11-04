<template>
  <v-data-table
    :headers="headers"
    :items="seller_list"
    sort-by="calories"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>거래처 내역</v-toolbar-title>
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
            >거래처 등록</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.seller_id" label="거래처 ID"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.seller_name" label="거래처명"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.seller_tel" label="연락처"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.seller_item" label="거래품목"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.seller_regdate" label="등록일자"></v-text-field>
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
            >edit
            </v-icon>
            <v-icon
              small
              @click="deleteItem(item)"
            >delete
            </v-icon>
      </template>

  </v-data-table>
</template>


<script>
import {mapActions, mapState} from 'vuex'
import axios from 'axios';

  export default {
    data: () => ({
      dialog: false,
      headers: [
        {
          text: '거래처 ID',
          align: 'start',
          sortable: false,
          value: 'seller_id',
        },
        { text: '거래처명', value: 'seller_name' },
        { text: '연락처', value: 'seller_tel' },
        { text: '거래품목', value: 'seller_item' },
        { text: '등록날짜', value: 'seller_regdate' },
        { text: 'Actions', value: 'action', sortable: false },
      ],
      seller_list: [],
      editedIndex: -1,
      editedItem: {
      },
      defaultItem: {
        seller_regdate: new Date().toISOString().substr(0, 10)
      },
    }),

    computed: {
      formTitle () {
        return this.editedIndex === -1 ? '거래처 등록' : '수정'
      },
    },

    watch: {
      dialog (val) {
        val || this.close()
      },
    },

    created () {

    },

    mounted(){
      this.fetchSeller()

    },

    methods: {

      fetchSeller () {
        axios
          .get('http://localhost:9000/api/seller/list')
          .then(Response=>
            this.seller_list=Response.data
          )
      },


      editItem (item) {
        this.editedIndex = this.seller_list.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },

      deleteItem (item) {
        const index = this.seller_list.indexOf(item)
        this.deleteItem = Object.assign({}, item)

        this.seller_id=this.deleteItem.seller_id
         if(confirm('삭제하시겠습니까?')){
          axios
          .delete('http://localhost:9000/api/seller/delete/'+this.seller_id)
          .then(Response=>{
            this.fetchSeller()
          })
          this.seller_list.splice(index, 1)

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
        this.seller_id=this.editedItem.seller_id
        if (this.editedIndex > -1) {
          Object.assign(this.seller_list[this.editedIndex], this.editedItem)
          axios
            .delete('http://localhost:9000/api/seller/delete/'+this.seller_id)
            .then(Response=>{
             this.fetchSeller()
             })
          axios
            .post('http://localhost:9000/api/seller/insert',{
                seller_name: this.editedItem.seller_name,
                seller_tel: this.editedItem.seller_tel,
                seller_item: this.editedItem.seller_item,
                seller_regdate: this.editedItem.seller_regdate
          })
          .then(Response=>{
            this.fetchSeller()
          })
        } else {
          this.seller_list.push(this.editedItem)

          axios
          .post('http://localhost:9000/api/seller/insert',{
                seller_name: this.editedItem.seller_name,
                seller_tel: this.editedItem.seller_tel,
                seller_item: this.editedItem.seller_item,
                seller_regdate: this.editedItem.seller_regdate
          })
          .then(Response=>{
            this.fetchSeller()
          })
        }
        this.close()
      },
    },
  }
</script>