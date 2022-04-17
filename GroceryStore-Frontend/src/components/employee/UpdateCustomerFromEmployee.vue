<template>
  <div>
 
  <b-navbar fixed="top" toggleable="lg">
      <router-link to="/employeeWelcomePage">
        <b-navbar-brand>STORIKO</b-navbar-brand>
      </router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/createCustomerFromEmployee">Create Customer</b-nav-item>
            <b-nav-item href="#/updateCustomerFromEmployee">Update Customer</b-nav-item>
            <b-nav-item href="#/viewStoreEmployeeScheduleEmployee">Schedule</b-nav-item>
            <!-- <b-nav-item href="#/modifyGroceryOrderStatus">Order Status</b-nav-item> -->
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  <div class="verticalandhorizontal-center">

        <h2 class="heading">Update Customer Account</h2>

        <br>

        <h6 class="subheading">Please enter the original username of the account to change</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="oldCustomerAccount.username"
            class="form-control"
            id="floatingInput"
            placeholder="Old username"
            required
          />
        </div>

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

              <button class="largeButton" type="CreateButton" @click="updateCustomerAccount(oldCustomerAccount.username,newCustomerAccount.email,newCustomerAccount.username,newCustomerAccount.password,newCustomerAccount.phoneNumber,newCustomerAccount.address)">
                Update Account
              </button>
            
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

export default {
    name: 'updatecustomer',
    data () {
        return {
            customers: [],
            oldCustomerAccount: {
                username: ''
            },
            newCustomerAccount: {
                email: '',
                username: '',
                password: '',
                address: '',
                phoneNumber: ''
            },
            reponse: []
        }
    },

    methods: {
        updateCustomerAccount: function (oldUsername, newEmail, newUsername, newPassword, newPhoneNumber, newAddress){
            AXIOS.put('/customers/update'.concat('/').concat(oldUsername).concat('/').concat(newEmail).concat('/').concat(newUsername).concat('/').concat(newPassword).concat('/').concat(newPhoneNumber).concat('/').concat(newAddress),{},{})
            .then(response => {
                this.customers.push(response.data)
                this.newCustomerAccount = ''
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
</style>
