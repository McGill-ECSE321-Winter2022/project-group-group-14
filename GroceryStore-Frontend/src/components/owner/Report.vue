<template>
  <div>
    <b-navbar fixed="top" toggleable="lg">
        <router-link to="/ownerWelcomePage">
          <b-navbar-brand>STORIKO</b-navbar-brand>
        </router-link>
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav class="ml-auto">
              <b-nav-item-dropdown text="InventoryItems">
                <b-dropdown-item href="#/showInventoryItemsOwner">Show Inventory Items</b-dropdown-item>
                <b-dropdown-item href="#/modifyItems">Modify Inventory Items</b-dropdown-item>
              </b-nav-item-dropdown>
              <b-nav-item-dropdown text="Schedules">
                <b-dropdown-item href="#/modifyStoreSchedule">Modify Store Schedule</b-dropdown-item>
                <b-dropdown-item href="#/viewStoreScheduleOwner">View Store Schedule</b-dropdown-item>
                <b-dropdown-item href="#/employeeSchedules">Employee Schedules</b-dropdown-item>
              </b-nav-item-dropdown>
              <b-nav-item-dropdown text="Modify Accounts">
                <b-dropdown-item href="#/modifyEmployees">Modify Employees</b-dropdown-item>
                <b-dropdown-item href="#/modifyCustomers">Modify Customers</b-dropdown-item>
                <b-dropdown-item href="#/deleteOwnerAccount">Delete Account</b-dropdown-item>
              <b-dropdown-item href="#/showUsers">View All Accounts</b-dropdown-item>
              </b-nav-item-dropdown>
              <b-nav-item href="#/report">Report</b-nav-item>
            </b-navbar-nav>
            <b-navbar-nav class="ml-auto">
              <b-nav-item href="#/">Log Out</b-nav-item>
            </b-navbar-nav>
        </b-collapse>
    </b-navbar>

    <div class="verticalandhorizontal-center">
      <h2 class="heading">Report</h2>
      <br>
      <h3 class="heading">Total Sales :$ {{totalSales}}.00 </h3>



      <div v-for= "groceryOrder in groceryOrders" :key=groceryOrder.orderId>
        <ul style="list-style-type:square">
          <li> Order Id : {{ groceryOrder.orderId}}, Order Cost : {{ groceryOrder.totalCost}}, Order Type : {{ groceryOrder.orderType}}, Order Status : {{ groceryOrder.orderStatus}}</li>

        </ul>

         
       </div>
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

export default{
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
        errorOrder: '',
        successMsg:'',
        response: []
    }
  },
    created: function () {
// Initializing persons from backend
      AXIOS.get('/orders/')
      .then(response => {
          // JSON responses are automatically parsed.
          this.groceryOrders = response.data

      }).catch(e => {
          this.successMsg = ''
          var errorMsg = e.response.data
          console.log(errorMsg)
          this.errorOrder = errorMsg
      }),
       AXIOS.get('/orders/sales')
      .then(response => {
          // JSON responses are automatically parsed.
          this.totalSales = response.data
      }).catch(e => {
          this.successMsg = ''
          var errorMsg = e.response.data
          console.log(errorMsg)
          this.errorOrder = errorMsg
      })

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





</style>

