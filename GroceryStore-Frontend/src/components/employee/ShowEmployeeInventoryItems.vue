<template>
  <div>
  

  
  <b-navbar fixed="top" toggleable="lg">
      <router-link to="/employeeWelcomePage">
        <b-navbar-brand>STORIKO</b-navbar-brand>
      </router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/showEmployeeInventoryItems">Show Inventory Items</b-nav-item>
            <b-nav-item href="#/viewModifyEmployeeGroceryOrders">View Grocery Orders</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
      </b-collapse>
    </b-navbar>

  <div class="grid-container" style = "text-align: center;">
      <div class="grid-item2">
      <h4> Items </h4>
      <table>
      <tr>
        <th>Photo</th>
        <th>Name</th>
        <th>Price</th>
        <th>Stock</th>
        <th>Quantity</th>
        <th>Add</th>
      </tr>
      
      <tr v-for="inventoryItem in inventoryItems" :key=inventoryItem.name>
        
          <td><img class="item-image" :src="inventoryItem.image" alt="" style ="width : 100%; height : 100%;"></td>
          <td>{{ inventoryItem.name }}</td>
          <td>{{ inventoryItem.price }}</td>
          <td>{{ inventoryItem.currentStock }}</td>
          <td>
            <div class="form-floating mb-3">
            <label >Quantity</label>
            <input
              type="number"
              min="1"
              :max="inventoryItem.currentStock"
              v-model="inventoryItem.quantity"
              class="form-control"
              id="quantity"
              placeholder="qty"
              required
            />
          </div>
        </td>
        <td>
          <button class="mediumButton add-item" v-bind:disabled="!inventoryItem.quantity" @click="addOrderItems(groceryOrders[0].orderId,inventoryItem.name,inventoryItem.quantity)">Add to Cart</button>
        </td>
        </tr>
      </table>
      </div>
       <div class="grid-item1">

          <h4> Order Id : {{this.orderId}} </h4>
          <div v-if = "orderItems">
            <div v-for="index in (itemIndices)" :key=index >   
              {{ itemNames[index]}} x {{ itemQuantity[index]}} :  ${{ itemCosts[index]}}.00 


            </div> 
          </div>
          <br>
          Total Cost : {{groceryOrders[0].totalCost}}
          <br>
          <br>
            <router-link to="/employeeWelcomePage">
              <button class="largeButton" v-if="orderId" @click="placeOrder(orderId)">
                  Place Order
              </button>
            </router-link>
        </div>
    </div>
    </div>

</template>

<script>
import axios from 'axios'

var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})  
  

export default {
name: 'inventoryitem',
data () {
    return {
    orderId : this.$route.params.orderId,
    groceryOrders : [], 
    inventoryItems: [],
    orderItems:[],
    newInventoryItem: {
      name: '',
      price: '',
      currentStock: ''
    }, 
    itemIndices:[],
    itemNames:[], 
    itemCosts:[],
    itemQuantity:[],
    one : '1',
    response: []
    }
},
created: function () {
// Initializing persons from backend
    AXIOS.get('/inventoryItems/get')
    .then(response => {

        this.inventoryItems = response.data
        
    })
    .catch(e => {
      var errorMsg = e.response.data
      alert(errorMsg)
    }),
        
      AXIOS.get('/orders/'.concat(this.orderId),{},{})
      .then(response => {
          this.groceryOrders.push(response.data)
      })
      .catch(e => {
        var errorMsg = e.response.data
        alert(errorMsg)
      }),
      AXIOS.get('/orders/orderItems/'.concat(this.orderId),{},{})
      .then(response => {
          // JSON responses are automatically parsed.
          this.orderItems=response.data

          this.itemIndices = [];
            this.itemNames = [];
            this.itemCosts = [];
            this.itemQuantity = [];
            for (let index = 0; index < this.orderItems.length; ++index) {
                if (this.itemNames.includes(this.orderItems[index].name)){
                    const i = this.itemNames.indexOf(this.orderItems[index].name);
                    var cost = parseInt(this.itemCosts[i]) + parseInt(this.orderItems[index].price);
                    var quant = parseInt(this.itemQuantity[i]) + 1;
                    this.itemQuantity[i] = quant.toString();
                    this.itemCosts[i] = cost.toString();
                }else{
                    this.itemQuantity.push(this.one);
                    this.itemIndices.push(this.itemIndices.length);
                    this.itemNames.push(this.orderItems[index].name);
                    this.itemCosts.push(this.orderItems[index].price.toString()); 
                }

            }
      })
      .catch(e => {
      })
    
},

  methods: {
    placeOrder: function (orderId){
      AXIOS.post('/orders/place/'.concat(orderId),{},{})
      .then(response => {
        this.groceryOrders.push(response.data)
      })
      .catch(e => {
      })
    },
     addOrderItems: function (orderId,itemName,quantity) {
            AXIOS.post('/orders/add/'.concat(orderId), {}, {params: {itemName: itemName, quantity: quantity}})
            .then(response => {
            // JSON responses are automatically parsed.
                this.groceryOrders.push(response.data)
                this.newInventoryItem = ''
            })
            .catch(e => {
              var errorMsg = e.response.data
              alert(errorMsg)
            })
        }
  }
}



</script>

<style scoped>




table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
  
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
  width : 50px;
  text-align: center;
}

tr:nth-child(even) {
  background-color: #ffdab9;
  text-align: center;
}

label {
  margin-right: 20px;
  font-size: 18px;
}

#quantity {
  width: 80px;
  border-radius: 25px;
}

.form-control {
  display: inline;
  height: 30px;
}

.grid-item1{
  overflow: scroll;
  padding-top: 7%;
  box-shadow: 0 0 15px 5px rgb(0,0,0,0.2);
  margin: 10px;
  padding-left: 7%;
  padding-right: 7%;
  border-radius: 10px;
  transition: 0.3s;
  background-color: white;
  min-height: fit-content;
  min-width: 250px;
  max-height:600px;
  max-width : 500px;
  width : 50%;
  height: 100%;
  height: 500px;
}
.grid-item2 {
  overflow: scroll;
  box-shadow: 0 0 15px 5px rgb(0,0,0,0.2);
  margin: 10px;
  padding-top: 7%;
  padding-left: 7%;
  padding-right: 7%;
  border-radius: 10px;
  transition: 0.3s;
  background-color: white;
  min-height: fit-content;
  min-width: 250px;
  height:100%;
  width : 120%;
  height: 500px;

}

.verticalandhorizontal-center {
    padding: 2% 6% 2% 6%;
    background-color: white;
    border-radius: 4%;
    margin-top: 1%;
    box-shadow: 0 0 10px 7px rgb(0,0,0,0.3);
  }
.heading {
    margin-top: 20px;
    margin-bottom: 50px;
}

</style>
