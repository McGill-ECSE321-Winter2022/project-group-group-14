<template>
  <div>
    <div id="popup1" class="overlay" v-if="errorCustomer">
          <div class="popup">
            <h5>{{ errorCustomer }}</h5>
            <!-- <button class="mediumButton" >Close</button> -->
            <button class="mediumButton" onClick="window.location.reload();">Close</button>
          </div>
        </div>
        <div id="popup1" class="overlay" v-if="errorUpdateCustomer">
          <div class="popup">
            <h5>{{ errorUpdateCustomer }}</h5>
            <!-- <button class="mediumButton" >Close</button> -->
            <button class="mediumButton" onClick="window.location.reload();">Close</button>
          </div>
        </div>
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

        <h2 class="heading">Create Customer Account</h2>
        <!-- <h2 class="heading">Create / Update Customer Account</h2> -->

        <br>

        <!-- <h6 class="subheading">Account to change (Leave this empty to create an account)</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="oldCustomerAccount.Username"
            class="form-control"
            id="floatingInput"
            placeholder="Old Username"
          />
        </div>
         -->
        <br>

        <h6 class="subheading">Emails must be unique within the system</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="newCustomerAccount.email"
            class="form-control"
            id="floatingInput"
            placeholder="Email"
            required
          />
        </div>

        <h6 class="subheading">Usernames must be unique within the system</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="newCustomerAccount.username"
            class="form-control"
            id="floatingInput"
            placeholder="Username"
            required
          />
        </div>

        <h6 class="subheading">Passwords must contain both a capital letter and numerical character</h6>
        <div class="form-floating mb-3">
          <input
            type="password"
            v-model="newCustomerAccount.password"
            class="form-control"
            id="floatingPassword"
            placeholder="Password"
            required
          />
        </div>

        <h6 class="subheading">Please enter a valid address </h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="newCustomerAccount.address"
            class="form-control"
            id="floatingInput"
            placeholder="Address"
            required
          />
        </div>

        <h6 class="subheading">Phone numbers must contain exactly 10 numerical characters </h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="newCustomerAccount.phoneNumber"
            class="form-control"
            id="floatingInput"
            placeholder="Phone Number"
            required
          />
        </div>

        <br>
        <br>


              <button class="largeButton" type="CreateButton" @click="createCustomerAccount(newCustomerAccount.email,newCustomerAccount.username,newCustomerAccount.password,newCustomerAccount.phoneNumber,newCustomerAccount.address)">
                Create Account
              </button>
            
            <br> 

              <!-- <button class="largeButton" type="CreateButton" @click="updateCustomerAccount(oldCustomerAccount.username,newCustomerAccount.email,newCustomerAccount.username,newCustomerAccount.password,newCustomerAccount.phoneNumber,newCustomerAccount.address)">
                Update Account
              </button> -->

       

    </div>
  </div>
</template>

<script>
import axios from 'axios'
// var config = require('../../../config')

var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
  })  

export default {
    name: 'createcustomer',
    data () {
        return {
            customers: [],
            oldCustomerAccount:{
              username: ''
            },
            newCustomerAccount: {
                email: '',
                username: '',
                password: '',
                address: '',
                phoneNumber: ''
            },
            errorCustomer: '',
            errorUpdateCustomer: '',
            reponse: []
        }
    },

    // created: function(){
    //     AXIOS.post('/customers/youssof@gmail.com/youssof5/123Abc/1111stavenue/5148888888')
    //     .then(response => {
    //         this.customers = response.data
    //     })
    //     .catch(e => {
    //         this.errorCustomer = e
    //     })
    // },

    methods: {
        createCustomerAccount: function (newEmail, newUsername, newPassword, newPhoneNumber, newAddress){
            AXIOS.post('/customers/'.concat(newEmail).concat('/').concat(newUsername).concat('/').concat(newPassword).concat('/').concat(newPhoneNumber).concat('/').concat(newAddress),{},{})
            .then(response => {
                this.customers.push(response.data)
                this.errorCustomer = newEmail + ' is created successfully!'
                this.newCustomerAccount = ''
            })
            .catch(e => {
                var errorMsg = e.response.data
                console.log(errorMsg)
                this.errorCustomer = errorMsg
            })
        },
        // updateCustomerAccount: function (oldUsername, newEmail, newUsername, newPassword, newPhoneNumber, newAddress){
        //     AXIOS.put('/customers/update'.concat('/').concat(oldUsername).concat('/').concat(newEmail).concat('/').concat(newUsername).concat('/').concat(newPassword).concat('/').concat(newPhoneNumber).concat('/').concat(newAddress),{},{})
        //     .then(response => {
        //         this.customers.push(response.data)
        //         this.errorUpdateCustomer = newEmail + ' is updated successfully!'
        //         this.newCustomerAccount = ''
        //     })
        //     .catch(e => {
        //         var errorMsg = e.response.data
        //         console.log(errorMsg)
        //         this.errorUpdateCustomer = errorMsg
        //     })
        // }
    }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->


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
</style>
