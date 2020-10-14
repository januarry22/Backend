<template>
    <v-data-table
        :headers="headers"
        :items="Expand_list"
        sort-by="expand_date"
        class="elevation-1"
      >
       <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>지출 관리</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on }">
            <v-btn
              color="primary"
              dark
              class="mb-2"
              v-on="on"
            >지출 입력</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{formTitle}}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                 <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.expand_id" label="id"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.expand_content" label="지출내역"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.expand_date" label="지출날짜"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.expand_price" label="지출가격"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.expand_val" label="지출유형"></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
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

  export default {
    data: () => ({
      dialog: false,
      headers: [
        {
          text: '지출 id',
          align: 'start',
          sortable: false,
          value: 'expand_id',
        },
        { text: '지출 내역', value: 'expand_content' },
        { text: '지출 날짜', value: 'expand_date' },
        { text: '지출 가격', value: 'expand_price' },
        { text: '결제 유형', value: 'expand_val' },
        { text: 'Actions', value: 'action', sortable: false },
      ],
      Expand_list: [],
      editedIndex: -1,
      editedItem: {
        expand_content: '',
        expand_date: '',
        expand_price: '',
        expand_val: '',
      },
      defaultItem: {
        expand_content: 'ex) 물품구입',
        expand_date: new Date(),
        expand_price: 'ex) 25000',
        expand_val: 'ex) 카드',
      },

    }),
    // data(){
    //   return{
    //     expand:[],
    //   }
    // },

    computed: {
      formTitle () {
        return this.editedIndex === -1 ? '새로운 지출' : '수정'
      },
      ...mapState(["Userinfo"]),
  //    ...mapState(["Expand_list"])
    },

    watch: {
      dialog (val) {
        val || this.close()
      },
    },

    created () {
    //  this.initialize()
    //  this.$store.dispatch('ExpandList')
    },
    mounted(){
      this.fetchItems()

    },


    methods: {
     // ...mapActions(['ExpandInsert']),
      fetchItems () {
        axios
          .get('http://localhost:9000/api/expand/list')
          .then(Response=>
            this.Expand_list=Response.data
          )

      },

      editItem (item) {
        this.editedIndex = this.Expand_list.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true

     //   this.expand_id=this.editedItem.expand_id
      },

      deleteItem (item) {
        const index = this.Expand_list.indexOf(item)
        this.deleteItem = Object.assign({}, item)

        this.expand_id=this.deleteItem.expand_id
        //console.log(this.expand_id)
 

        if(confirm('삭제하시겠습니까?')){
          axios
          .delete('http://localhost:9000/api/expand/delete/'+this.expand_id)
          .then(Response=>{
            this.fetchItems()
          })
          this.Expand_list.splice(index, 1)

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
        this.expand_id=this.editedItem.expand_id
       // console.log(this.expand_id)
        if (this.editedIndex > -1) {
          Object.assign(this.Expand_list[this.editedIndex], this.editedItem)
          axios
            .delete('http://localhost:9000/api/expand/delete/'+this.expand_id)
            .then(Response=>{
             this.fetchItems()
             })
          axios
            .post('http://localhost:9000/api/expand/insert',{
                expand_content: this.editedItem.expand_content,
                expand_date: this.editedItem.expand_date,
                expand_price: this.editedItem.expand_price,
                expand_val: this.editedItem.expand_val
          })
          .then(Response=>{
            this.fetchItems()
          })
        } else {
          this.Expand_list.push(this.editedItem)

          axios
          .post('http://localhost:9000/api/expand/insert',{
                expand_content: this.editedItem.expand_content,
                expand_date: this.editedItem.expand_date,
                expand_price: this.editedItem.expand_price,
                expand_val: this.editedItem.expand_val
          })
          .then(Response=>{
            this.fetchItems()
          })
        }
        this.close()
      },
    },
  }
</script>