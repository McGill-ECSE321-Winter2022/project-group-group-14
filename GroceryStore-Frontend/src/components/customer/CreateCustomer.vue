<template>
  <div >
     <div id="popup1" class="overlay" v-if="errorCustomer">
          <div class="popup">
            <h5>{{ errorCustomer }}</h5>
            <!-- <button class="mediumButton" >Close</button> -->
            <button class="mediumButton" onClick="window.location.reload();">Close</button>
          </div>
        </div>
    <b-navbar fixed="top" >
      
      <b-navbar-brand>STORIKO</b-navbar-brand>
      
      
    </b-navbar>
    <div class="verticalandhorizontal-center">

        <h2 class="heading">Create Customer Account</h2>

        <br>
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

        <div>

            <router-link to="/">
              <button class="largeButton" type="CreateButton" @click="createCustomerAccount(newCustomerAccount.email,newCustomerAccount.username,newCustomerAccount.password,newCustomerAccount.phoneNumber,newCustomerAccount.address)">
                Create Account
              </button>
            </router-link>
            
            <br> 

            <router-link to="/">         
              <button class="largeButton">
                Cancel
              </button>
            </router-link>
            

        </div>

    </div>
    <br />
    <br />
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
            newCustomerAccount: {
                email: '',
                username: '',
                password: '',
                address: '',
                phoneNumber: ''
            },
            errorCustomer: '',
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
                console.log(response.data)
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorCustomer = errorMsg
                
            })
        }
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
</style>
