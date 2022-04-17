<template>
  <div>
    <owner-navigation-bar></owner-navigation-bar>

    <div class="verticalandhorizontal-center">
      <h2 class="heading">Report</h2>
      <br>
      <h3 class="heading">Total Sales : $ {{totalSales}}.00 </h3>
      <table>
      <tr>
        <th>Id</th>
        <th>Cost</th>
        <th>Type</th>
        <th>Status</th>
        <th>Delete</th>
      </tr>
      <tr v-for="groceryOrder in groceryOrders" :key=groceryOrder.orderId>
        <td>{{ groceryOrder.orderId}}</td>
        <td>{{ groceryOrder.totalCost}}</td>
        <td>{{ groceryOrder.orderType}}</td>
        <td> {{ groceryOrder.orderStatus}}</td>
        <td>
          <button class="mediumButton" @click="deleteOrder(groceryOrder.orderId);reloadPage();">Delete</button>
        </td>
        </tr>
      </table>
      <br>
      <button class="largeButton" @click="deleteAllCompletedOrders();reloadPage();">Delete All Completed</button>
    </div>
  </div>
</template>


<script>
import axios from 'axios'
import OwnerNavigationBar from './OwnerNavigationBar.vue'

var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})  

export default{
  components: { OwnerNavigationBar },
  name: 'groceryorder',
  data()
  {
    return {
        groceryOrders: [],
        totalSales : '',
        newGroceryOrder: {
            orderId:'',
            totalCost:'',
            orderType:'',
            orderStatus:'',
            orderItems: [],
            customer:''
        }, 
         deletedGroceryOrder: {
            orderId:'',
            totalCost:'',
            orderType:'',
            orderStatus:'',
            orderItems: [],
            customer:''       
         },
        response: []
    }
  },
    created: function () {
// Initializing persons from backend
      AXIOS.get('/orders/')
      .then(response => {
          this.groceryOrders = response.data

      }).catch(e => {
          var errorMsg = e.response.data
          alert(errorMsg)
      }),
       AXIOS.get('/orders/sales')
      .then(response => {
          this.totalSales = response.data
      }).catch(e => {
          var errorMsg = e.response.data
          alert(errorMsg)
      })
    },
    methods: {
    deleteOrder: function (orderId){
      AXIOS.delete('/orders/delete/'.concat(orderId),{},{})
      .then(response => {
        this.deletedGroceryOrder = response.data;
        windows.location.reload()
      })
      .catch(e => {
        var errorMsg = e.response.data
        alert(errorMsg)
      })
    },
    deleteAllCompletedOrders: function (){
      AXIOS.delete('/orders/delete/all/completed',{},{})
      .then(response => {
        this.groceryOrders = []
        windows.location.reload()
      })
      .catch(e => {
        var errorMsg = e.response.data
        alert(errorMsg)
      })
    },
    reloadPage: function(){
      window.location.reload();
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

