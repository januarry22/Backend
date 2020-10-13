<template>
  <v-container class="grey lighten-5">
    <v-row>

    <v-col sm="6">
        <v-card>
 <v-form
                    ref="form"
                    v-model="valid"
                    :lazy-validation="lazy"
                    >
                    <v-text-field
                        v-model="name"
                        :counter="10"
                        :rules="nameRules"
                        label="Name"
                        required
                    ></v-text-field>

                    <v-text-field
                        v-model="email"
                        :rules="emailRules"
                        label="E-mail"
                        required
                    ></v-text-field>
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
                            label="Picker in menu"
                            readonly
                            v-bind="attrs"
                            v-on="on"
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
                        label="Label Text"
                        value="12:30:00"
                        type="time"
                        suffix="PST"
                    ></v-text-field>

                    <v-text-field
                        label="Label Text"
                        value="12:30:00"
                        type="time"
                        suffix="PST"
                    ></v-text-field>

                    <v-select
                        v-model="select"
                        :items="items"
                        :rules="[v => !!v || 'Item is required']"
                        label="Item"
                        required
                    ></v-select>

                    <v-btn
                        color="primary"
                        class="mr-4"
                        @click="sync"
                    >
                        입력
                    </v-btn>

                    <v-btn
                        color="green"
                        @click="resetValidation"
                    >
                        계산
                    </v-btn>
                    </v-form>

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
        desserts: [
          {
            name: 'Frozen Yogurt',
            calories: 159,
            fat: 6.0,
            carbs: 24,
            protein: 4.0,
            iron: '1%',
          },
          {
            name: 'Ice cream sandwich',
            calories: 237,
            fat: 9.0,
            carbs: 37,
            protein: 4.3,
            iron: '1%',
          },
          {
            name: 'Eclair',
            calories: 262,
            fat: 16.0,
            carbs: 23,
            protein: 6.0,
            iron: '7%',
          },
          {
            name: 'Cupcake',
            calories: 305,
            fat: 3.7,
            carbs: 67,
            protein: 4.3,
            iron: '8%',
          },
          {
            name: 'Gingerbread',
            calories: 356,
            fat: 16.0,
            carbs: 49,
            protein: 3.9,
            iron: '16%',
          },
          {
            name: 'Jelly bean',
            calories: 375,
            fat: 0.0,
            carbs: 94,
            protein: 0.0,
            iron: '0%',
          },
          {
            name: 'Lollipop',
            calories: 392,
            fat: 0.2,
            carbs: 98,
            protein: 0,
            iron: '2%',
          },
          {
            name: 'Honeycomb',
            calories: 408,
            fat: 3.2,
            carbs: 87,
            protein: 6.5,
            iron: '45%',
          },
          {
            name: 'Donut',
            calories: 452,
            fat: 25.0,
            carbs: 51,
            protein: 4.9,
            iron: '22%',
          },
          {
            name: 'KitKat',
            calories: 518,
            fat: 26.0,
            carbs: 65,
            protein: 7,
            iron: '6%',
          },
        ],
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