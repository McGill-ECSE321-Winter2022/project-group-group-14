<template>
    <div >
         <b-navbar fixed="top" >
            <router-link to="/employeeWelcomePage">
                <b-navbar-brand>STORIKO</b-navbar-brand>
            </router-link>
           
                <b-navbar-nav class="ml-auto">
                    <b-nav-item href="#/">Log Out</b-nav-item>
                </b-navbar-nav>
        </b-navbar>

    <div class="verticalandhorizontal-center">
      <h2 class="heading">Orders to Prepare</h2>
      <br>

      <h4> Orders to Prepare</h4>
      <table>
      <tr>
        <th>Id</th>
        <th>Cost</th>
        <th>Type</th>
        <th>Status</th>
        <th>Update</th>
      </tr>
      
      <tr v-for="groceryOrder in groceryOrders" :key=groceryOrder.orderId>

          <td>{{ groceryOrder.orderId}}</td>
          <td>{{ groceryOrder.totalCost}}</td>
          <td>{{ groceryOrder.orderType}}</td>
          <td> {{ groceryOrder.orderStatus}}</td>
        <td>

           <button class="mediumButton" @click="updateOrderStatus(groceryOrder.orderId)" onClick="window.location.reload();">
                    Update Status
           </button>

       

        </td>
        </tr>
      </table>
    </div>
    </div>
</template>

<style scoped>
</style>

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

export default{
  name: 'groceryorder',
  data()
  {
    return {
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
    created: function () {
// Initializing persons from backend
      AXIOS.get('/orders/toUpdate')
      .then(response => {
          // JSON responses are automatically parsed.
          this.groceryOrders = response.data

      }).catch(e => {
          this.successMsg = ''
          var errorMsg = e.response.data
          console.log(errorMsg)
          this.errorOrder = errorMsg
      })
    },
    methods:{
      updateOrderStatus: function(orderId){
        AXIOS.post('/orders/status/update/'.concat(orderId),{},{})
        .then(response => {
            // JSON responses are automatically parsed.
            console.log(response.data)
            this.newGroceryOrder = response.data
        }).catch(e => {
          var errorMsg = e.response.data
          console.log(errorMsg)
          this.errorOrder = errorMsg
      })

      }

    }

  
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.page a {
  font-size: 13px;
}

.item {
  padding-bottom: 30px;
}

.info a{
  font-size: 12px;
  color: black;
  transition: 0.3s;
  margin-top: 10px;
}

.edit {
  margin-top: 20px;
}

.page a {
  font-size: 11px;
}
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
  width : 100px;
  text-align: center;
}

tr:nth-child(even) {
  background-color: #ffdab9;
  text-align: center;
}





</style>
