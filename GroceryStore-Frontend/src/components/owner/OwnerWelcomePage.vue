<template>
    <div >
        <b-navbar fixed="top" toggleable="lg">
        <b-navbar-brand>STORIKO</b-navbar-brand>
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

        <div class="bg-color-gradient">
            
        </div>
        <div class="verticalandhorizontal-center">
            <h1 class="heading">Welcome {{this.username}} ! </h1>
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
  
    data()
    {
        return {
          email:this.$route.params.email,
          username:''
          }
    },
    created: function() {

            AXIOS.get('/owners/'.concat(this.email),{},{})
            .then(response => {
                // JSON responses are automatically parsed.
                this.username=response.data.username
                console.log(response.data)
            })
            .catch(e => {
                // this.errorInventory = e.response.data
                // console.log(e.response.data)
            })
    },
}
</script>

<style scoped>

h1 {
  font-size: 60px;
}

.bg-color-gradient {
  height: 100vh;
}

.verticalandhorizontal-center {
    padding: 2% 6% 2% 6%;
    background-color: transparent; 
    border-radius: 15px;
    margin-top: 1%;
    box-shadow: 0 0 10px 7px rgb(0,0,0,0);
  }
</style>