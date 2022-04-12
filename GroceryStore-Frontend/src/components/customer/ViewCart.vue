<template>
    <div >

    <div id="popup1" class="overlay" v-if="successMsg">
        <div class="popup">
            <h5>{{ successMsg }}</h5>
             <button class="mediumButton" onClick="window.location.reload();">Close</button>
        </div>
    </div>
    <div id="popup2" class="overlay" v-if="error">
        <div class="popup">
            <h5>{{ error }}</h5>
            <button class="mediumButton" onClick="window.location.reload();">Close</button>
        </div>
    </div>
    <CustomerNavigationBar></CustomerNavigationBar>


        <div class="verticalandhorizontal-center" style= "height : 90%">
            <br>
            <h2 class="heading">Cart for {{email}}</h2>

            
<!-- 
            <button class="button" v-if="groceryOrders[0].orderId" @click="getOrder(groceryOrders[0].orderId)">
                get order items
            </button> -->


          
                <!-- <div v-for="orderItem in orderItems" :key=orderItem.name>
                    <ul style="list-style-type:square">
                        <li> {{ orderItem.name}} :  ${{ orderItem.price}}.00 </li>
                    </ul>  
                </div> -->

                <div v-for="index in (itemIndices)" :key=index >
                   
                            <button class="button" @click="deleteItem(orderId, itemNames[index])"> - </button> 
                            {{ itemNames[index]}} x {{ itemQuantity[index]}} :  ${{ itemCosts[index]}}.00 
                            <button class="button"> + </button> 
                            
            
                </div> 

                
               
                

            <div>
               <h4 class="heading">Total Cost : ${{groceryOrders[0].totalCost}}.00</h4>  
            </div>


                <!-- <div v-if="groceryOrders[0].type === 'delivery'"> 
                   <h4> Possibly display address here </h4>
                </div> -->
                <!-- <h4> Address is not correct?</h4>
                <router-link to="/modifyAddress">
                        <button class="largeButton">
                            Modify Address
                        </button>
                    </router-link> -->

                     <router-link :to="{ name: 'ThankYou', params:{ email: email, orderId: groceryOrders[0].orderId}}">
                        <button class="largeButton" v-if="groceryOrders[0].orderId" @click="placeOrder(groceryOrders[0].orderId)">
                        Place Order
                    </button>
                    </router-link>
                    <br>
                    <br>

                   <small> Please note that an extra 10$ delivery fee is included for out of town deliveries.</small>

                   <br>
                   <br>
                   

                  <h5> Made a mistake? Modify or delete you order.</h5>
                   <button class="button" v-if="groceryOrders[0].orderId" @click="toggleType(groceryOrders[0].orderId)">
                    Change Order Type
                </button>

                  <router-link :to="{ name: 'customerWelcomePage', params: { email: email , orderId: orderId }}"> 
                    <button class="button_delete" v-if="groceryOrders[0].orderId" @click="deleteOrder(groceryOrders[0].orderId)">
                        Delete Order
                    </button>
                </router-link>
        </div>
    </div>
</template>

<style scoped>





</style>

<script>
import CustomerNavigationBar from '@/components/customer/CustomerNavigationBar'
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
export default{
    name:'groceryorder',
    components:{
        CustomerNavigationBar
    },
    data () {
        return {
            email : this.$route.params.email,
            orderId: this.$route.params.orderId,
            // newOrderItem :{
            //     name : '',
            //     price: '',
            //     itemId:'',
            // },
            orderItems:[],
            groceryOrders :[],
            newGroceryOrder : {
                orderId:'',
                totalCost:'',
                orderType:'',
                orderStatus:'',
                orderItems: [],
                customer:''
            },
            itemIndices:[],
            itemNames:[], 
            itemCosts:[],
            itemQuantity:[],
            one : '1',
            error: '',
            successMsg:'',
            response: [],
            
        }
    },
    created: function() {
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
              this.errorInventory = e.response.data
              console.log(e.response.data)
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
          })
        //   console.log(this.orderItems);
        //   this.itemIndices = [];
        //     this.itemNames = [];
        //     this.itemCosts = [];
        //     this.itemQuantity = [];
        //     for (let index = 0; index < this.orderItems.length; ++index) {
        //         if (this.itemNames.includes(this.orderItems[index].name)){
        //             const i = this.itemNames.indexOf(this.orderItems[index].name);
        //             var cost = parseInt(this.itemCosts[i]) + parseInt(this.orderItems[index].price);
        //             var quant = parseInt(this.itemQuantity[i]) + 1;
        //             this.itemQuantity[i] = quant.toString();
        //             this.itemCosts[i] = cost.toString();
        //         }else{
        //             // var number = 1;
        //             this.itemQuantity.push(this.one);
        //             this.itemIndices.push(this.itemIndices.length);
        //             this.itemNames.push(this.orderItems[index].name);
        //             this.itemCosts.push(this.orderItems[index].price.toString()); 
        //             // console.log(type);
        //             // this.itemCosts.push(items[index].price.toString());
        //         }

        //     }

        //     console.log(this.itemQuantity);
        //     console.log(this.itemIndices);
        //     console.log(this.itemNames);
        //     console.log(this.itemCosts);
            // return this.itemIndices;
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
              console.log(e.response.data)
          })
        },
         deleteOrder: function (orderId){
          AXIOS.delete('/orders/delete/'.concat(orderId),{},{})
          .then(response => {
              this.groceryOrders.push(response.data)
              console.log(response.data)
              successMsg = " Order has been deleted!"
          })
          .catch(e => {
              this.error = e.response.data
              console.log(e.response.data)
          })
        },
        toggleType: function (orderId){
          AXIOS.post('/orders/toggleType/'.concat(orderId),{},{})
          .then(response => {
              this.groceryOrders.push(response.data)
              console.log(response.data)
              successMsg = " Your order type has been modified!"
          })
          .catch(e => {
            //   this.error = e.response.data
            //   console.log(e.response)
          })
        },
        deleteItem: function (orderID, itemName){
            AXIOS.delete("/orders/deleteItem/".concat(orderID).concat("/").concat(itemName),{},{})
            .then(response =>{
                this.orderItems=response.data.orderItems
                console.log(e.response.data)
            })
            .catch(e => {
              this.error = e.response.data
              console.log(e.response.data)
          })
        },
        summarize: function(items){
            this.itemIndices = [];
            this.itemNames = [];
            this.itemCosts = [];
            this.itemQuantity = [];
            for (let index = 0; index < items.length; ++index) {
                if (this.itemNames.includes(items[index].name)){
                    const i = this.itemNames.indexOf(items[index].name);
                    var cost = parseInt(this.itemCosts[i]) + parseInt(items[index].price);
                    var quant = parseInt(this.itemQuantity[i]) + 1;
                    this.itemQuantity[i] = quant.toString();
                    this.itemCosts[i] = cost.toString();
                }else{
                    // var number = 1;
                    this.itemQuantity.push(this.one);
                    this.itemIndices.push(this.itemIndices.length);
                    this.itemNames.push(items[index].name);
                    this.itemCosts.push(items[index].price.toString()); 
                    // console.log(type);
                    // this.itemCosts.push(items[index].price.toString());
                }

            }
            console.log(this.itemQuantity);
            console.log(this.itemIndices);
            console.log(this.itemNames);
            console.log(this.itemCosts);
            return this.itemIndices;

        }
        
        // ,
        // getOrderItems: function (orderId) {
        //     AXIOS.post('/orders/orderItems/'.concat(orderId), {}, {})
        //     .then(response => {
        //     // JSON responses are automatically parsed.
        //         this.groceryOrders[0].orderItems.push(response.data)//push or switch to equals?
        //         console.log(response.data)
        //         this.error = ''
        //     })
        //     .catch(e => {
        //         var errorMsg = e.response.data
        //         console.log(errorMsg)
        //         this.error = errorMsg
        //     })
        // },
        // getOrder: function (email){
        //   AXIOS.get('/orders/customer/'.concat(email),{},{})
        //   .then(response => {
        //       // JSON responses are automatically parsed.
        //       this.groceryOrders = response.data
        //       console.log(response.data)
        //   })
        //   .catch(e => {
        //       this.error = e.response.data
        //       console.log(e.response.data)
        //   })
        // }
        
    }
}
</script>

<style scoped>

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
    overflow : scroll;
  }
.heading {
    margin-top: 20px;
    margin-bottom: 50px;
}

</style>

