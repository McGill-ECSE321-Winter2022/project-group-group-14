<template>
  <div>
    <owner-navigation-bar></owner-navigation-bar>
    
    
    <h1>Customers</h1>
    <h2 class="empty" v-if="!customers.length">No Customer Accounts Yet!</h2>
    <div class="grid-container" v-if="customers.length">
    <div class="grid-item" v-for="customer in customers" :key=customer.name>
        <ul class="item">
                <li class="info item-name">
                    {{ customer.username }}
                </li>
                <li class="info">
                    Email: {{ customer.email }}
                </li>
                <li class="info">
                    Password: {{ customer.password }}
                </li>
                <li class="info">
                    Address: {{ customer.address }}
                </li>
                <li class="info">
                    Phone Number: {{ customer.phoneNumber }}
                </li>
                
        </ul>
    </div>
    </div>
     
        <h1>Employees</h1>
        <h2 class="empty" v-if="!employees.length">No Employee Accounts Yet!</h2>
        <div class="grid-container" v-if="employees.length">
        <div class="grid-item" v-for="employee in employees" :key=employee.name>
            <ul class="item">
                    <li class="info item-name">
                        {{ employee.username }}
                    </li>
                    <li class="info">
                        Email: {{ employee.email }}
                    </li>
                    <li class="info">
                        Password: {{ employee.password }}
                    </li>
                    
            </ul>
        </div>
        </div>
    
  </div>
</template>

<script >
import axios from 'axios'
import OwnerNavigationBar from './OwnerNavigationBar.vue'
var config = require('../../../config')

var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT

// var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
// var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})  
  

export default {
  components: { OwnerNavigationBar },
name: 'customers',
data () {
    return {
    customers: [],
    newCustomer: {
      email: '',
      username: '',
      password: '',
      address: '',
      phoneNumber: ''
    }, 
    errorCustomer: '',
    employees: [],
    newEmployee: {
      email: '',
      username: '',
      password: ''
    }, 
    errorEmployee: '',
    response: []
    }
},
created: function () {
// Initializing persons from backend
    AXIOS.get('/customers/login/getAll')
    .then(response => {
        // JSON responses are automatically parsed.
        this.customers = response.data
    })
    .catch(e => {
        this.errorCustomer = e.response.data
    }),
    AXIOS.get('/employees/login/getAll')
    .then(response => {
        // JSON responses are automatically parsed.
        this.employees = response.data
    })
    .catch(e => {
        this.errorEmployee = e.response.data
    })
    
}

}

  
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.empty {
    margin-top: 7%;
    color:rgb(177, 177, 177);
}

.item-name {
    text-align: center;
    margin-bottom: 20px;
}

.grid-item {
    text-align: left;
}

.grid-container {
    padding-top: 40px;
}

h1 {
    margin-top: 100px;
}

</style>
