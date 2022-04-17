<template>
  <div>
    
  <owner-navigation-bar></owner-navigation-bar>
  <div class="verticalandhorizontal-center">

        <h2 class="heading">Create Customer Account</h2>
        <!-- <h2 class="heading">Create / Update Customer Account</h2> -->


        <h6 class="subheading">Account to change (Leave this empty to create an account)</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="oldCustomerAccount.username"
            class="form-control"
            id="floatingInput"
            placeholder="Old Username"
          />
        </div>
        

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


              <button class="largeButton" type="CreateButton" @click="createCustomerAccount(newCustomerAccount.email,newCustomerAccount.username,newCustomerAccount.password,newCustomerAccount.phoneNumber,newCustomerAccount.address)">
                Create Account
              </button>
              <button class="largeButton" type="UpdateButton" @click="updateCustomerAccount(oldCustomerAccount.username,newCustomerAccount.email,newCustomerAccount.username,newCustomerAccount.password,newCustomerAccount.phoneNumber,newCustomerAccount.address)">
                Update Account
              </button>        

              <!-- <button class="largeButton" type="CreateButton" @click="updateCustomerAccount(oldCustomerAccount.username,newCustomerAccount.email,newCustomerAccount.username,newCustomerAccount.password,newCustomerAccount.phoneNumber,newCustomerAccount.address)">
                Update Account
              </button> -->

       

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

export default {
  components: { OwnerNavigationBar },
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
            reponse: []
        }
    },


    methods: {
        createCustomerAccount: function (newEmail, newUsername, newPassword, newPhoneNumber, newAddress){
            AXIOS.post('/customers/'.concat(newEmail).concat('/').concat(newUsername).concat('/').concat(newPassword).concat('/').concat(newPhoneNumber).concat('/').concat(newAddress),{},{})
            .then(response => {
                this.customers.push(response.data)
                this.newCustomerAccount = ''
            })
            .catch(e => {
                var errorMsg = e.response.data
                alert(errorMsg)
            })
        },
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
<!-- Add "scoped" attribute to limit CSS to this component only -->


<style scoped>

.verticalandhorizontal-center {
    padding: 2% 6% 2% 6%;
    background-color: white;
    border-radius: 4%;
    margin-top: 1%;
    box-shadow: 0 0 10px 7px rgb(0,0,0,0.3);
  }
</style>
