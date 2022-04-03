<template>
  <div>

    

  <!-- <b-navbar fixed="top" toggleable="lg">
      <router-link to="/customerWelcomePage">
        <b-navbar-brand>STORIKO</b-navbar-brand>
      </router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/showCustomerInventoryItems">View Items</b-nav-item>
            <b-nav-item href="#/viewCart">View Cart</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
      </b-collapse>
    </b-navbar> -->
    <CustomerNavigationBar></CustomerNavigationBar>


  <div class="grid-container">
      <button class="largeButton" v-if="curremail" @click="getOrder(curremail)">
          GetGroceryOrder
      </button>
    
      <div class="grid-item" v-for="inventoryItem in inventoryItems" :key=inventoryItem.name>
      <ul class="item">

                <li class="info" v-if="curremail">
                  email : {{curremail}}

                </li>

                <li class="info" v-if="groceryOrders[0].orderId">
                  id : {{groceryOrders[0].orderId}}

                </li>
                <li class="info item-name">
                  {{ inventoryItem.name }}
                </li>
                <li class="info">
                  ${{ inventoryItem.price }}.00
                </li>
                <li class="info">
                  Stock: {{ inventoryItem.currentStock }}
                </li>

                <h6 class="subheading">Quantity must be less or equal to stock</h6>
                <div class="form-floating mb-3">
                  <input
                    type="number"
                    min="0"
                    v-model="inventoryItem.quantity"
                    class="form-control"
                    id="floatingInput"
                    placeholder="Quantity"
                    required
                  />
                </div>
                <li class="info">
                  <button class="mediumButton add-item" @click="addOrderItems(groceryOrders[0].orderId,inventoryItem.name,inventoryItem.quantity)">Add to Cart</button>
                </li> 
                
         </ul>
      
      </div>
    </div>
  </div>
</template>

<script >
import axios from 'axios'
var config = require('../../../config')
import CustomerNavigationBar from '@/components/customer/CustomerNavigationBar'

var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT

// var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
// var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  // headers: { 'Access-Control-Allow-Origin': frontendUrl }
})  
  

export default {
    name: 'inventoryitem',

    components:{
        CustomerNavigationBar
    },

    data () {
        return {
        curremail : this.$route.params.email,
        groceryOrders: [],
        newGroceryOrder: {
            orderId:'',
            totalCost:'',
            orderType:'',
            orderStatus:'',
            orderItems: [],
            customer:''
        }, 
        inventoryItems: [],
        newInventoryItem: {

          name: '',
          price: '',
          currentStock: '',
          quantity:''
        }, 
        errorInventory: '',
        response: []
        }
    },
    created: function () {
    // Initializing persons from backend
        AXIOS.get('/inventoryItems/get')
        .then(response => {
            // JSON responses are automatically parsed.
            this.inventoryItems = response.data
        })
        .catch(e => {
            this.errorInventory = e
        })
       
    },
    methods: {
        addOrderItems: function (orderId,itemName,quantity) {
            AXIOS.post('/orders/add/'.concat(orderId), {}, {params: {itemName: itemName, quantity: quantity}})
            .then(response => {
            // JSON responses are automatically parsed.
                this.groceryOrders.push(response.data)
                console.log(response.data)
                this.errorInventory = ''
                this.newInventoryItem = ''
            })
            .catch(e => {
                var errorMsg = e.response.data
                console.log(errorMsg)
                this.errorPerson = errorMsg
            })
        },
        getOrder: function (email){
          AXIOS.get('/orders/customer/'.concat(email),{},{})
          .then(response => {
              // JSON responses are automatically parsed.
              this.groceryOrders = response.data
              console.log(response.data)
          })
          .catch(e => {
              this.errorInventory = e.response.data
              console.log(e.response.data)
          })


        }

    },
//     beforeMount(){
//       this.getOrder(this.email)
//  },
}

  
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
