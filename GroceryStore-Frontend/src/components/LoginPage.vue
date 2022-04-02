<template>
<div>
        <div id="popup1" class="overlay" v-if="errorCustomer">
          <div class="popup">
            <h5>{{ errorCustomer }}</h5>
            <router-link :to="{ name: 'customerWelcomePage', params: { email: account.email }}">
            <button class="mediumButton">Close</button>
            </router-link>
          </div>
        </div>

        <div id="popup1" class="overlay" v-if="errorEmployee">
          <div class="popup">
            <h5>{{ errorEmployee }}</h5>
            <router-link :to="{ name: 'employeeWelcomePage', params: { email: account.email }}">
            <button class="mediumButton">Close</button>
            </router-link>
          </div>
        </div>

        <div id="popup1" class="overlay" v-if="errorOwner">
          <div class="popup">
            <h5>{{ errorOwner }}</h5>
            <router-link :to="{ name: 'ownerWelcomePage', params: { email: account.email }}">
            <button class="mediumButton">Close</button>
            </router-link>
          </div>
        </div>

  <div class="background-img">
    <img src="../assets/orange5.jpg">
  </div>
    <b-navbar fixed="top">
      <b-navbar-brand>STORIKO</b-navbar-brand>
      
    </b-navbar>
    <div>
      <div class="verticalandhorizontal-center">

          <h2 class="heading">Log In</h2>

          <br>
          <br>

          <div class="form-floating mb-3">
            <input
              type="text"
              v-model="account.email"
              class="form-control"
              id="floatingInput"
              placeholder="Email"
              required
            />
          </div>

          <!-- <div class="form-floating mb-3">
            <input
              type="text"
              v-model="account.username"
              class="form-control"
              id="floatingInput"
              placeholder="Username"
              required
            />
          </div> -->

          <div class="form-floating mb-3">
            <input
              type="password"
              v-model="account.password"
              class="form-control"
              id="floatingPassword"
              placeholder="Password"
              required
            />
          </div>

          <br>
          <br>

          <!-- <form action="/action_page.php">
            <label for="cars">Account Type:</label>
            <select name="accountType" id="accountType">
              <option value="customer">Customer</option>
              <option value="employee">Employee</option>
              <option value="owner">Owner</option>
            </select>
          </form> -->

          <!-- 
          <br>
          <br> -->


                <!-- <button v-bind:disabled="!account.email || !account.password" class="mediumButton" @click="loginCustomer(account.email,account.password)">
                
                  Customer Log In

                </button> -->


              <router-link :to="{ name: 'customerWelcomePage', params: { email: account.email }}">
                <button v-bind:disabled="!account.email || !account.password" class="mediumButton">
                  Customer Log In
                </button>
              </router-link>

              <br>

              <router-link :to="{ name: 'employeeWelcomePage', params: { email: account.email }}">
                <button v-bind:disabled="!account.email || !account.password" class="mediumButton">
                  Employee Log In
                </button>
              </router-link>

              <br>

              <router-link :to="{ name: 'ownerWelcomePage', params: { email: account.email }}">
                <button v-bind:disabled="!account.email || !account.password" class="mediumButton">
                  Owner Log In
                </button>
              </router-link>

              <br>
              <br>
              <br>
              <br>

              <p>Don't have an account?</p>
              <router-link to="/createCustomer">
                <button  type="CreateButton" class="button">
                  Create an account
                </button>
              </router-link>

      </div>
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
    name: 'account',
    data () {
        return {
            customers: [],
            account: {
                email: '',
                password: ''
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
      loginCustomer: function (email, password){
        AXIOS.get('/customers/login/'.concat(email).concat("/").concat(password))
        .then(response => {
           this.errorCustomer = newEmail + ' is logged in Successfully!'
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
  .verticalandhorizontal-center {
    padding: 2% 6% 2% 6%;
    background-color: white;
    border-radius: 4%;
    margin-top: 1%;
    box-shadow: 0 0 10px 7px rgb(0,0,0,0.3);
  }
</style>