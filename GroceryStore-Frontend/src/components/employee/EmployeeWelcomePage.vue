<template>
    <div >

     


        <b-navbar fixed="top">
      <router-link to="/employeeWelcomePage">
        <b-navbar-brand>STORIKO</b-navbar-brand>
      </router-link>
    
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
    
    </b-navbar>

         <div class="background-img">
            <img src="../../assets/orange8.jpg">
        </div>
        <div class="verticalandhorizontal-center">
            <!-- <h2 class="heading">Welcome {{this.email}} (Employee)! </h2> -->
            <h2 class="heading">Welcome Employee! </h2>
                
                <button class="largeButton"  @click="createInstoreOrder()">
                    Log a purchase
                </button>


                <div v-if="orders[0]">
                    order id: {{orders[0].orderId}}
                </div>

<!-- 
                    <router-link to="/showEmployeeInventoryItems">
                        <button class="largeButton">
                            Log a purchase
                        </button>
                    </router-link> -->
                    <br>

                     <router-link to="/viewIncompleteOrders">
                        <button class="largeButton">
                            Prepare an order
                        </button>
                    </router-link>
                    <br>

                    <router-link to="/viewStoreEmployeeScheduleEmployee">
                        <button class="largeButton">
                            Something else
                        </button>
                    </router-link>
        
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
  


export default{
    name:'order',
    data()
    {
        return {
            orders: [],
            customers:[],
            errorOrder: '',
            successMsg:'',
            response: []
        }
    },
    methods: {
        createInstoreOrder : function(){
            AXIOS.post('/orders/inStore', {}, {})
            .then(response => {
                console.log(response.data)
                this.orders.push(response.data) //add dto to the list of orders
              
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