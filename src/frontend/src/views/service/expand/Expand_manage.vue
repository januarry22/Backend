<template>
  <v-data-table
    :headers="headers"
    :items="expand"
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
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="primary"
              dark
              class="mb-2"
              v-bind="attrs"
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
                    <v-text-field v-model="editedItem.calories" label="지출내역"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.fat" label="지출날짜"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.carbs" label="지출가격"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.protein" label="지출유형"></v-text-field>
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
    <template v-slot:item.actions="{ item }">
      <v-icon
        small
        class="mr-2"
        @click="editItem(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
        small
        @click="deleteItem(item)"
      >
        mdi-delete
      </v-icon>
    </template>
    <template v-slot:no-data>
      <v-btn color="primary" @click="initialize">Reset</v-btn>
    </template>
  </v-data-table>
</template>


<script>
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
        { text: 'Actions', value: 'actions', sortable: false },
      ],
      expand: [],
      editedIndex: -1,
      editedItem: {
        expand_content: 0,
        expand_date: 0,
        expand_price: 0,
        expand_val: 0,
      },
      defaultItem: {
        expand_content: 'ex) 물품구입',
        expand_date: 'today()',
        expand_price: 'ex) 25000',
        expand_val: 'ex) 카드',
      },
    }),

    computed: {
      formTitle () {
        return this.editedIndex === -1 ? '새로운 지출' : '수정'
      },
    },

    watch: {
      dialog (val) {
        val || this.close()
      },
    },

    created () {
      this.initialize()
    },

    methods: {
      initialize () {
       
      },

      editItem (item) {
        this.editedIndex = this.expand.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },

      deleteItem (item) {
        const index = this.expand.indexOf(item)
        confirm('삭제하시겠습니까?') && this.expand.splice(index, 1)
      },

      close () {
        this.dialog = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },

      save () {
        if (this.editedIndex > -1) {
          Object.assign(this.expand[this.editedIndex], this.editedItem)
        } else {
          this.desserts.push(this.editedItem)
        }
        this.close()
      },
    },
  }
</script>