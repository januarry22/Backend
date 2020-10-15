<template>
  <div>
    <v-data-table
      :headers="headers"
      :items="Stock_list"
    >
      <template v-slot:item.expand_name="props">
        <v-edit-dialog
          :return-value.sync="props.item.expand_name"
          @save="save"
          @cancel="cancel"
          @open="open"
          @close="close"
        > {{ props.item.name }}
          <template v-slot:input>
            <v-text-field
              v-model="props.item.expand_name"
              :rules="[max25chars]"
              label="Edit"
              single-line
              counter
            ></v-text-field>
          </template>
        </v-edit-dialog>
      </template>

      <template v-slot:item.stock_expire_date="props">
           <v-chip :color="getColor(props.item.stock_expire_date)" dark>{{ props.item.stock_expire_date }}</v-chip>
      </template>
      
      <template v-slot:item.iron="stock_quantity">
        <v-edit-dialog
          :return-value.sync="props.item.stock_quantity"
          large
          persistent
          @save="save"
          @cancel="cancel"
          @open="open"
          @close="close"
        >
          <div>{{ props.item.stock_quantity }}</div>
          <template v-slot:input>
            <div class="mt-4 title">Update stock_quantity</div>
          </template>
          <template v-slot:input>
            <v-text-field
              v-model="props.item.stock_quantity"
              :rules="[max25chars]"
              label="Edit"
              single-line
              counter
              autofocus
            ></v-text-field>
          </template>
        </v-edit-dialog>
      </template>

       <template v-slot:item.action="{ item }">
            <v-icon
              small
              @click="deleteItem(item)"
            >delete
            </v-icon>
      </template>
    </v-data-table>

    <v-snackbar v-model="snack" :timeout="3000" :color="snackColor">
      {{ snackText }}

      <template v-slot:action="{ attrs }">
        <v-btn v-bind="attrs" text @click="snack = false">Close</v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script>
import {mapActions, mapState} from 'vuex'
import axios from 'axios';

 export default {
    data () {
      return {
        snack: false,
        snackColor: '',
        snackText: '',
        max25chars: v => v.length <= 25 || 'Input too long!',
        pagination: {},
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
      }
    },
     mounted(){
      this.fetchStock()

    },

    methods: {
       fetchStock () {
        axios
          .get('http://localhost:9000/api/stock/list')
          .then(Response=>
            this.Stock_list=Response.data
          )
      },

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
       getColor (stock_expire_date) {
        if (stock_expire_date > 400) return 'red'
        else if (stock_expire_date > 200) return 'orange'
        else return 'green'
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
      
    },
  }
</script>