<template>
<div>

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
          


              <button :class="{'disabled' : !account.email || !account.password}" v-bind:disabled="!account.email || !account.password" class="mediumButton" @click="loginCustomer(account.email,account.password)">
                
                Customer Log In

              </button>


              <br>

              <button :class="{'disabled' : !account.email || !account.password}" v-bind:disabled="!account.email || !account.password" class="mediumButton" @click="loginEmployee(account.email,account.password)">
                
                Employee Log In

              </button>

              <br>

              <button :class="{'disabled' : !account.email || !account.password}" v-bind:disabled="!account.email || !account.password" class="mediumButton" @click="loginOwner(account.email,account.password)">
                
                Owner Log In

              </button>


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
            reponse: []
        }
    },

    created: function(){
        AXIOS.post('/owners/marwan.kanaan@mcgill.ca/Marwan/123Abc')
        .then(response => {
          this.owners.push(response.data)
          console.log(response.data)
        })
        .catch(e => {
          var errorMsg = e.response.data
          alert(errorMsg)
        })
    },

    methods: {
      loginCustomer: function (email, password){

        AXIOS.get('/customers/login/'.concat(email).concat("/").concat(password))
        .then(() => {
          this.$router.push({ name: 'customerWelcomePage', params: { email: this.account.email }})
            })
            .catch(e => {
                var errorMsg = e.response.data
                alert(errorMsg)
            })
        },

      loginEmployee: function (email, password){

        AXIOS.get('/employees/login/'.concat(email).concat("/").concat(password))
        .then(() => {
          this.$router.push({ name: 'employeeWelcomePage', params: { email: this.account.email }})
            })
            .catch(e => {
                var errorMsg = e.response.data
                alert(errorMsg)
            })
        },

      loginOwner: function (email, password){

        AXIOS.get('/owners/login/'.concat(email).concat("/").concat(password))
        .then(() => {
          this.$router.push({ name: 'ownerWelcomePage', params: { email: this.account.email }})
            })
            .catch(e => {
                var errorMsg = e.response.data
                alert(errorMsg)
            })
        },

    
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

</style>