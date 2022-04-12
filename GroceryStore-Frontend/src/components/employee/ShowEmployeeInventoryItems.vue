<template>
  <div>
  <div id="popup1" class="overlay" v-if="successMsg">
    <div class="popup">
      <h5>{{ successMsg }}</h5>
       <button class="mediumButton" onClick="window.location.reload();">Close</button>

    </div>
    </div>
    <div id="popup2" class="overlay" v-if="errorInventory">
        <div class="popup">
            <h5>{{ errorInventory }}</h5>
            <button class="mediumButton" onClick="window.location.reload();">Close</button>
        </div>
    </div>

  
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
                   
                            <!-- <button class="button" @click="deleteItem(orderId, itemNames[index])" onClick="window.location.reload();"> - </button>  -->
                            {{ itemNames[index]}} x {{ itemQuantity[index]}} :  ${{ itemCosts[index]}}.00 
                            <!-- <button class="button" @click="addItem   (orderId, itemNames[index])" onClick="window.location.reload();"> + </button>  -->
                            
            
                </div> 
          <!-- <div v-for="orderItem in orderItems" :key=orderItem.name>
            <ul style="list-style-type:square">
            <li> {{ orderItem.name}} :  ${{ orderItem.price}}.00 </li>
            </ul>  
          </div> -->
        </div>
        <br>
        Total Cost : {{groceryOrders[0].totalCost}}
        <br>
        <br>
        <!-- <div class="form-floating mb-3"> -->
          <!-- <input v-model="orderId" placeholder="id"> -->
          <!-- <p>Your order id is : {{ this.orderId }}</p> -->
          <router-link to="/employeeWelcomePage">
            <button class="largeButton" v-if="orderId" @click="placeOrder(orderId)">
                Place Order
            </button>
          </router-link>


            <!-- <input
              type="text"
              v-model="orderId"
              class="form-control"
              id="floatingInput"
              placeholder="id"
              required
            /> -->
        <!-- </div> -->
        </div>
        

      
<!-- ------------------------------------------- -->



          

      
    </div>
    </div>

</template>

<script>
import axios from 'axios'
var config = require('../../../config')

var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT

// var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
// var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

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
    errorInventory: '',
    successMsg:'',
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
        // JSON responses are automatically parsed.

        this.inventoryItems = response.data
         
         
        
    })
    .catch(e => {
        this.errorInventory = e
    }),
        
      AXIOS.get('/orders/'.concat(this.orderId),{},{})
      .then(response => {
          // JSON responses are automatically parsed.
          this.groceryOrders.push(response.data)
          console.log(response.data)
      })
      .catch(e => {
          this.errorInventory = e.response.data
          console.log(e.response.data)
      }),
      AXIOS.get('/orders/orderItems/'.concat(this.orderId),{},{})
      .then(response => {
          // JSON responses are automatically parsed.
          this.orderItems=response.data
          console.log(response.data)

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
                    // var number = 1;
                    this.itemQuantity.push(this.one);
                    this.itemIndices.push(this.itemIndices.length);
                    this.itemNames.push(this.orderItems[index].name);
                    this.itemCosts.push(this.orderItems[index].price.toString()); 
                    // console.log(type);
                    // this.itemCosts.push(items[index].price.toString());
                }

            }

            console.log(this.itemQuantity);
            console.log(this.itemIndices);
            console.log(this.itemNames);
            console.log(this.itemCosts);
      })
      .catch(e => {
          // this.errorInventory = e.response.data
          // console.log(e.response.data)
      })
    
},

  methods: {
    placeOrder: function (orderId){
      AXIOS.post('/orders/place/'.concat(orderId),{},{})
      .then(response => {
        this.groceryOrders.push(response.data)
        console.log(response.data)
        successMsg = " Order has been successfully placed!"
      })
      .catch(e => {
      })
    },
     addOrderItems: function (orderId,itemName,quantity) {
            AXIOS.post('/orders/add/'.concat(orderId), {}, {params: {itemName: itemName, quantity: quantity}})
            .then(response => {
            // JSON responses are automatically parsed.
                this.groceryOrders.push(response.data)
                console.log(response.data)
                this.errorInventory = ''
                this.newInventoryItem = ''
                this.successMsg = 'Successfully added!'
            })
            .catch(e => {
                this.errorInventory = e.response.data
                console.log(errorInventory)
            })
        }
  }
}



</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>




/* div.itemSelection {
  background-color: #f7a851;
  width: 110px;
  height: 110px;
  overflow: scroll;
} */

table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
  
  /* table-layout : fixed; */
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
  overflow:scroll;
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
  height: 100%
}
.grid-item2 {
  overflow:scroll;
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

}

.overlay {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.7);
  transition: opacity 500ms;
  opacity: 100%;
  z-index: 100;
}

.popup {
  margin: auto;
  margin-top: 40vh;
  padding: 20px;
  background: #fff;
  border-radius: 5px;
  width: 30%;
  transition: all 5s ease-in-out;
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
