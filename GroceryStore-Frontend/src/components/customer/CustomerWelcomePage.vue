<template>
    <div >

            <div id="popup1" class="overlay" v-if="successMsg">
                <div class="popup">
                 <h5>{{ successMsg }}</h5>
                 <router-link to="/showEmployeeInventoryItems">
                <button class="largeButton">
                    View grocery items
                </button>
            </router-link>

            </div>
         </div>
        <div id="popup2" class="overlay" v-if="errorOrder">
        <div class="popup">
            <h5>{{ errorOrder }}</h5>
            <button class="mediumButton" onClick="window.location.reload();">Close</button>
        </div>
    </div>

        <CustomerNavigationBar></CustomerNavigationBar>

        
        <div class="background-img">
            <img src="../../assets/orange3.jpg">
        </div>
        <div class="verticalandhorizontal-center">

            <h2 class="heading">Welcome {{this.curremail}} (Customer)! </h2>
            <br>

                <h4> Place an order : </h4>

                <br>
                 <!-- <button class="largeButton" v-if="curremail" @click="createPerson(curremail)">
                    createPerson
                </button> -->
                <button class="largeButton" v-if="curremail" @click="createDeliveryOrder(curremail)">
                    Delivery
                </button>

                <br>

                <button class="largeButton"  v-if="curremail" @click="createPickupOrder(curremail)">
                    Pick up
                </button>
                <br>
                <br>

                <h4> Already placed an order? </h4>  
                <br> 

                <router-link to="/viewOrderStatus">
                    <button class="largeButton">
                        View your order's status
                    </button>
                </router-link>

                <br>
                <br>

                <h4> Something else: </h4>  
                <br> 

                <router-link to="/updateCustomerAccount">
                    <button class="largeButton">
                        Account Settings
                    </button>
                </router-link>
                <br>
                <router-link to="/viewCustomerStoreSchedule">
                    <button class="largeButton">
                        Store Schedule
                    </button>
                </router-link>
                
        </div>
    </div>
</template>

<style>
.largeButton {
    width: 250px;
}
.verticalandhorizontal-center {
    padding: 2% 6% 2% 6%;
    background-color: white;
    border-radius: 4%;
    margin-top: 1%;
    box-shadow: 0 0 10px 7px rgb(0,0,0,0.3);
  }
</style>


<script>
import axios from 'axios'
import CustomerNavigationBar from '@/components/customer/CustomerNavigationBar'

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
    name:'CustomerWelcomePage',
    data()
    {
        return {
            // customers : [],
            // newCustomer : {
            //     email:this.$route.params.email,
            //     username:'user1',
            //     password:'1234abc',
            //     phoneNumber:'5145503791',
            //     address:'38 street green',
            // },
            // curremail : this.$route.params.email,
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
            errorOrder: '',
            successMsg:'',
            response: []
        }
    },
    components:{
        CustomerNavigationBar
    },
    methods: {
        createDeliveryOrder : function(email){
            AXIOS.post('/orders/delivery/'.concat(email), {}, {})
            .then(response => {
                this.groceryOrders.push(response.data) //add dto to the list of orders
                this.successMsg = 'Order has been successfully created! Please navigate to the list of inventory items : '
                console.log(this.groceryOrders)
                this.errorOrder = ''
                this.newGroceryOrder = ''
            })
            .catch(e => {
                this.successMsg = ''
                var errorMsg = e.response.data
                console.log(errorMsg)
                this.errorOrder = errorMsg
            })
        },
        createPickupOrder : function(email){
            AXIOS.post('/orders/pickup/'.concat(email), {}, {})
            .then(response => {
                this.groceryOrders.push(response.data) //add dto to the list of orders
                this.successMsg = 'Order has been successfully created! Please navigate to the list of inventory items : '
                this.errorOrder = ''
                console.log(this.groceryOrders)
                this.newGroceryOrder = ''
            })
            .catch(e => {
                this.successMsg = ''
                var errorMsg = e.response.data
                console.log(errorMsg)
                this.errorOrder = errorMsg
            })
        },
        // createPerson: function (email) { //create a customer that exists in system when the page loads
        // AXIOS.post('/customers/'.concat(email).concat('/').concat('username1').concat('/').concat('password1').concat('/').concat('5145503713').concat('/').concat('38addresspotato').concat('/'),{},{})
        // .then(response => {
        //         // JSON responses are automatically parsed.
        //         this.customers = response.data
        //         console.log(this.$route.params.email)
        //         console.log(this.curremail)
        //         console.log(response.data)

        //     }).catch(e => {
        //         this.successMsg = ''
        //         var errorMsg = e.response.data
        //         console.log(errorMsg)
        //         this.errorOrder = errorMsg
        //     })
        // }, 
        
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
  }
.heading {
    margin-top: 20px;
    margin-bottom: 50px;
}
</style>