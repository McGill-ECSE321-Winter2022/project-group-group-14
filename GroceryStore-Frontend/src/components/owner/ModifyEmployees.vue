<template>
  <div class="modEmp">

  <owner-navigation-bar></owner-navigation-bar>
  <div class="verticalandhorizontal-center">

        <h2 class="heading">Create Employee Account</h2>
        <!-- <h2 class="heading">Create / Update Employee Account</h2> -->

        <br>

        <h6 class="subheading">Account to change (Leave this empty to create an account)</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="oldEmployeeAccount.usernameToChange"
            class="form-control"
            id="floatingInput"
            placeholder="Old Username"
          />
        </div>
        
        <br>

        <h6 class="subheading">Emails must be unique within the system</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="newEmployeeAccount.email"
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
            v-model="newEmployeeAccount.username"
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
            v-model="newEmployeeAccount.password"
            class="form-control"
            id="floatingPassword"
            placeholder="Password"
            required
          />
        </div>

           
              <button class="largeButton" type="CreateButton" @click="createEmployeeAccount(newEmployeeAccount.email,newEmployeeAccount.username,newEmployeeAccount.password)">
                Create Account
              </button>
              <button class="largeButton" type="UpdateButton" @click="updateEmployeeAccount(oldEmployeeAccount.usernameToChange,newEmployeeAccount.email,newEmployeeAccount.username,newEmployeeAccount.password)">
                Update Account
              </button>  
              

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
    name: 'createemployee',
    data () {
        return {
            employees: [],
            oldEmployeeAccount:{
              usernameToChange: ''
            },
            newEmployeeAccount: {
                email: '',
                username: '',
                password: ''
            },
            reponse: []
        }
    },

    methods: {
        createEmployeeAccount: function (newEmail, newUsername, newPassword){
            AXIOS.post('/employees/'.concat(newEmail).concat('/').concat(newUsername).concat('/').concat(newPassword),{},{})
            .then(response => {
                this.employees.push(response.data)
                this.newEmployeeAccount = ''
            })
            .catch(e => {
                var errorMsg = e.response.data
                alert(errorMsg)
            })
        },
        updateEmployeeAccount: function (oldUsername, newEmail, newUsername, newPassword){
            AXIOS.put('/employees/update'.concat('/').concat(oldUsername).concat('/').concat(newEmail).concat('/').concat(newUsername).concat('/').concat(newPassword),{},{})
            .then(response => {
                this.employees.push(response.data)
                this.newEmployeeAccount = ''
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
