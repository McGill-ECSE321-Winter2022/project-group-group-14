<template>
    <div >

    
    <CustomerNavigationBar></CustomerNavigationBar>


        <div class="verticalandhorizontal-center" >
            <br>
            <h2 class="heading">Cart for {{email}}</h2>
  
                <small>{{groceryOrders[0].orderType}} order </small>
                <br>
                <br>
            

                <div v-for="index in (itemIndices)" :key=index >
                   
                            <button class="button" @click="deleteItem(orderId, itemNames[index])" onClick="window.location.reload();"> - </button> 
                            {{ itemNames[index]}} x {{ itemQuantity[index]}} :  ${{ itemCosts[index]}}.00 
                            <button class="button" @click="addItem   (orderId, itemNames[index])" onClick="window.location.reload();"> + </button> 
                            
            
                </div> 

            <div>
               <h4 class="heading">Total Cost : ${{groceryOrders[0].totalCost}}.00</h4>  
            </div>


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
var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT
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
            totalCost:'',
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
            response: [],
            
        }
    },
    created: function() {
          
          AXIOS.get('/orders/orderItems/'.concat(this.orderId),{},{})
          .then(response => {
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

          })
          .catch(e => {
              this.errorInventory = e.response.data
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
        placeOrder: function (orderId){
        AXIOS.post('/orders/place/'.concat(orderId),{},{})
          .then(response => {
              this.groceryOrders.push(response.data)
          })
          .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
          })
        },
         deleteOrder: function (orderId){
          AXIOS.delete('/orders/delete/'.concat(orderId),{},{})
          .then(response => {
              this.groceryOrders.push(response.data)
          })
          .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
          })
        },
        toggleType: function (orderId){
          AXIOS.post('/orders/toggleType/'.concat(orderId),{},{})
          .then(response => {
              this.groceryOrders.push(response.data)
              window.location.reload();
          })
          .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
          })
        },
        deleteItem: function (orderID, itemName){
            AXIOS.delete("/orders/deleteItem/".concat(orderID).concat("/").concat(itemName),{},{})
            .then(response =>{
                this.orderItems=response.data.orderItems
            })
            .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
          })
        },
        addItem: function (orderId,itemName) {
            AXIOS.post('/orders/add/'.concat(orderId), {}, {params: {itemName: itemName, quantity: "1"}})
            .then(response => {
                this.orderItems=response.data.orderITems
                this.groceryOrders.push(response.data)
                // this.newInventoryItem = ''
                // this.successMsg = 'Successfully added!'
            })
            .catch(e => {
                var errorMsg = e.response.data
                alert(errorMsg)
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
            return this.itemIndices;

        }
        
        
    }
}
</script>

<style scoped>



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

