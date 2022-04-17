<template>
    <div >


<b-navbar fixed="top">
      
        <b-navbar-brand>STORIKO</b-navbar-brand>
    
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
    
    </b-navbar>
        
        <div class="background-img">
            <img src="../../assets/orange3.jpg">
        </div>
        <div class="verticalandhorizontal-center">
            

            <h2 class="heading" v-if="this.username">Welcome {{this.username}} (Customer)! </h2>
                

              
                <h4> Place an order : </h4>

                <!-- <button class="largeButton" v-if="curremail" @click="createPerson(curremail)">
                    createPerson
                </button> -->
            
            
                <button class="largeButton" v-if="curremail && !groceryOrders" @click="createDeliveryOrder(curremail)" >
                    Delivery
                </button>
                <br>
                <button class="largeButton"  v-if="curremail && !groceryOrders" @click="createPickupOrder(curremail)" >
                    Pick up
                </button>
                <router-link :to="{ name: 'ShowCustomerInventoryItems', params: { email: curremail, orderId: newGroceryOrder.orderId }}">
                <button class="largeButton"  v-if="curremail && groceryOrders ">
                    Complete Current Order
                </button>
                </router-link>
          
                <br>
                <br>

                <h4> Already placed an order? </h4>  
                
                <router-link :to="{ name: 'ThankYou', params: { email: curremail,orderId: newGroceryOrder.orderId }}">
                    <button class="largeButton">
                        View your order's status
                    </button>
                </router-link>
                <br>
                 <router-link :to="{ name: 'ViewCart', params: { email: curremail,orderId: newGroceryOrder.orderId }}">
                    <button class="largeButton">
                        View Cart
                    </button>
                </router-link>

                <br>
                <br>

                <h4> Something else: </h4>  

                <router-link :to="{ name: 'UpdateCustomerAccount', params: { email: curremail }}">
                    <button class="largeButton">
                        Account Settings
                    </button>
                </router-link>
                <br>
                <router-link :to="{ name: 'ViewCustomerStoreSchedule', params: { email: curremail }}">
                    <button class="largeButton">
                        Store Schedule
                    </button>
                </router-link>
                
        </div>
    </div>
</template>







<script>
import axios from 'axios'
import CustomerNavigationBar from '@/components/customer/CustomerNavigationBar'


var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})  


export default{
    name:'CustomerWelcomePage',
    data()
    {
        return {

            
            curremail : this.$route.params.email,
            username : '', 

            groceryOrders: '',
            newGroceryOrder: {
                orderId : '',
                totalCost:'',
                orderType:'',
                orderStatus:'',
                orderItems: [],
                customer:''
            }, 
            response: []
        }
    },
    components:{
        CustomerNavigationBar
    },
    created: function() {
        console.log(this.groceryOrders)
        AXIOS.get('/customers/'.concat(this.curremail),{},{})
        .then(response => {
            this.username = response.data.username
        })
        ,
        AXIOS.get('/orders/customer/latest/'.concat(this.curremail),{},{})
        .then(response => {
            this.groceryOrders = response.data
            this.newGroceryOrder.orderId = response.data.orderId
        })
        .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
        })
    },



    methods: {
        createDeliveryOrder : function(email){
            AXIOS.post('/orders/delivery/'.concat(email), {}, {})
            .then(response => {
                this.groceryOrders=response.data //add dto to the list of orders
                this.newGroceryOrder.orderId = response.data.orderId
            })
            .catch(e => {
                var errorMsg = e.response.data
                alert(errorMsg)
            })
        },
        createPickupOrder : function(email){
            AXIOS.post('/orders/pickup/'.concat(email), {}, {})
            .then(response => {
                this.groceryOrders=response.data //add dto to the list of orders
                this.newGroceryOrder.orderId = response.data.orderId
            })
            .catch(e => {
                var errorMsg = e.response.data
                alert(errorMsg)
            })
        },
        
        
    }
    
}
</script>

<style scoped>
.largeButton {
    width: 250px;
}


.verticalandhorizontal-center {
    padding: 0% 6% 2% 6%;
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