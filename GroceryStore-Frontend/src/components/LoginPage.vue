<template>
<div>
        <div id="popup1" class="overlay" v-if="successCustomer">
          <div class="popup">
            <h5>{{ successCustomer }}</h5>
            <router-link :to="{ name: 'customerWelcomePage', params: { email: account.email }}">
            <button class="mediumButton">Login</button>
            </router-link>
          </div>
        </div>

        <div id="popup1" class="overlay" v-if="errorCustomer">
          <div class="popup">
            <h5>{{ errorCustomer }}</h5>
            <router-link to="/">    
            <button class="mediumButton" onClick="window.location.reload();">Close</button>
            </router-link> 
          </div>
        </div>

        <div id="popup1" class="overlay" v-if="successEmployee">
          <div class="popup">
            <h5>{{ successEmployee }}</h5>
            <router-link :to="{ name: 'employeeWelcomePage', params: { email: account.email }}">
            <button class="mediumButton">Login</button>
            </router-link>
          </div>
        </div>

        <div id="popup1" class="overlay" v-if="errorEmployee">
          <div class="popup">
            <h5>{{ errorEmployee }}</h5>
            <router-link to="/">    
            <button class="mediumButton" onClick="window.location.reload();">Close</button>
            </router-link> 
          </div>
        </div>

        <div id="popup1" class="overlay" v-if="successOwner">
          <div class="popup">
            <h5>{{ successOwner }}</h5>
            <router-link :to="{ name: 'ownerWelcomePage', params: { email: account.email }}">
            <button class="mediumButton">Login</button>
            </router-link>
          </div>
        </div>

        <div id="popup1" class="overlay" v-if="errorOwner">
          <div class="popup">
            <h5>{{ errorOwner }}</h5>
            <router-link to="/">    
            <button class="mediumButton" onClick="window.location.reload();">Close</button>
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


              <button v-bind:disabled="!account.email || !account.password" class="mediumButton" @click="loginCustomer(account.email,account.password)">
                
                Customer Log In

              </button>


              <!-- <router-link :to="{ name: 'customerWelcomePage', params: { email: account.email }}">
                <button v-bind:disabled="!account.email || !account.password" class="mediumButton">
                  Customer Log In
                </button>
              </router-link> -->

              <br>

              <button v-bind:disabled="!account.email || !account.password" class="mediumButton" @click="loginEmployee(account.email,account.password)">
                
                Employee Log In

              </button>

              <!-- <router-link :to="{ name: 'employeeWelcomePage', params: { email: account.email }}">
                <button v-bind:disabled="!account.email || !account.password" class="mediumButton">
                  Employee Log In
                </button>
              </router-link> -->

              <br>

              <button v-bind:disabled="!account.email || !account.password" class="mediumButton" @click="loginOwner(account.email,account.password)">
                
                Owner Log In

              </button>

              <!-- <router-link :to="{ name: 'ownerWelcomePage', params: { email: account.email }}">
                <button v-bind:disabled="!account.email || !account.password" class="mediumButton">
                  Owner Log In
                </button>
              </router-link> -->

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

              <br>
              <br>
              <p>Still having trouble?</p>
              <a href="https://github.com/McGill-ECSE321-Winter2022/project-group-group-14/wiki/Website-User---Guide">See the guide on our GitHub Wiki!</a>

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
            employees: [],
            owners: [],
            account: {
                email: '',
                password: ''
            },
            errorCustomer: '',
            successCustomer: '',
            errorEmployee: '',
            successEmployee: '',
            errorOwner: '',
            successOwner: '',
            reponse: []
        }
    },

    created: function(){
        AXIOS.post('/owners/dude@gmail.com/dude/123Abc')
        .then(response => {
          this.owners.push(response.data)
          console.log(response.data)
        })
        .catch(e => {
          this.errorOwner = e.reponse.data
          console.log("didnt work")
        })
        AXIOS.get('/customers/getAll')
        .then(response=> {
          console.log(response.data)
        })
    },

    methods: {
      loginCustomer: function (email, password){

        AXIOS.get('/customers/login/'.concat(email).concat("/").concat(password))
        .then(response => {
                this.successCustomer = email + ' is logged in Successfully!'
            })
            .catch(e => {
                var errorMsg = e.response.data
                console.log(errorMsg)
                this.errorCustomer = e.response.data
            })
        },

      loginEmployee: function (email, password){

        AXIOS.get('/employees/login/'.concat(email).concat("/").concat(password))
        .then(response => {
                this.successEmployee = email + ' is logged in Successfully!'
            })
            .catch(e => {
                var errorMsg = e.response.data
                console.log(errorMsg)
                this.errorEmployee = e.response.data
            })
        },

      loginOwner: function (email, password){

        AXIOS.get('/owners/login/'.concat(email).concat("/").concat(password))
        .then(response => {
                this.successOwner = email + ' is logged in Successfully!'
            })
            .catch(e => {
                var errorMsg = e.response.data
                console.log(errorMsg)
                this.errorOwner = e.response.data
            })
        },

      createOwner: function (){
        AXIOS.post('/owners/dude@gmail.com/dude/123Abc')
        .then(response => {
                this.successOwner = email + ' is logged in Successfully!'
            })
            .catch(e => {
                var errorMsg = e.response.data
                // var errorMsg = "could not create owner"
                console.log(errorMsg)
                this.errorOwner = e.response.data
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