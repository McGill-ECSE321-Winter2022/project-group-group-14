<template>
  <div>

    <CustomerNavigationBar></CustomerNavigationBar>

      <div id="popup2" class="overlay" v-if="successMsg">
        <div class="popup">
            <h5>{{ successMsg }}</h5>
            <button class="mediumButton" onClick="window.location.reload();">Close</button>
        </div>
    </div>

  <div class="grid-container">
      <div class="grid-item" v-for="inventoryItem in inventoryItems" :key=inventoryItem.name>
        <ul class="item">
          <li class="info item-name">
            {{ inventoryItem.name }}
          </li>
          <li class="info">
            <img class="item-image" :src="inventoryItem.image" alt="">
          </li>
          <li class="info">
            ${{ inventoryItem.price }}.00
          </li>
          <li class="info">
            Stock: {{ inventoryItem.currentStock }}
          </li>
          <li v-if="inventoryItem.availability" class="info">
            Available
          </li>
          <li v-if="!inventoryItem.availability" class="info">
            Not Available
          </li>
          <br>
          <!-- <h6 class="subheading">Quantity must be less or equal to stock</h6> -->
          <!-- <div class="form-floating mb-3"> -->
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
            
          <!-- </div> -->
          <li class="info">
            <button class="mediumButton add-item" v-bind:disabled="!inventoryItem.quantity" @click="addOrderItems(groceryOrders[0].orderId,inventoryItem.name,inventoryItem.quantity)">Add to Cart</button>
          </li> 
         </ul>
      </div>
    </div>
  </div>
</template>

<script >
import axios from 'axios'
import CustomerNavigationBar from '@/components/customer/CustomerNavigationBar'

var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT


var AXIOS = axios.create({
  baseURL: backendUrl,
})  
  

export default {
    name: 'inventoryitem',

    components:{
        CustomerNavigationBar
    },

    data () {
        return {
        curremail : this.$route.params.email,
        orderId: this.$route.params.orderId,
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

          itemName: '',
          price: '',
          currentStock: '',
          quantity:'',
          availability: ''
        }, 
        response: []
        }
    },
    created: function () {
    // Initializing persons from backend
        AXIOS.get('/inventoryItems/get', {}, {})
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
          })
       
    },
    methods: {
        addOrderItems: function (orderId,itemName,quantity) {
            AXIOS.post('/orders/add/'.concat(orderId), {}, {params: {itemName: itemName, quantity: quantity}})
            .then(response => {
                this.groceryOrders.push(response.data)
                this.newInventoryItem = ''
            })
            .catch(e => {
                var errorMsg = e.response.data
                alert(errorMsg)
            })
        },
        getOrder: function (email){
          AXIOS.get('/orders/customer/'.concat(email),{},{})
          .then(response => {
              this.groceryOrders = response.data
          })
          .catch(e => {
              var errorMsg = e.response.data
              alert(errorMsg)
          })


        },
        getOrderById: function (id){
          AXIOS.get('/orders/'.concat(id),{},{})
          .then(response => {
              this.groceryOrders.push(response.data)
          })
          .catch(e => {
              var errorMsg = e.response.data
              alert(errorMsg)
          })


        }


    },  
}

  
</script>

<style scoped>

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

.grid-item {
  max-height: 450px;
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
