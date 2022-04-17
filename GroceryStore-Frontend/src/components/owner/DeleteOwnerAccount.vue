<template>
  <div>
    
  <owner-navigation-bar></owner-navigation-bar>
  <div class="verticalandhorizontal-center">

        <h2 class="heading">Delete An Account</h2>

        <br>
        <br>

        <h6 class="subheading">Select the account to delete by its email</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="AccountToDelete.email"
            class="form-control"
            id="floatingInput"
            placeholder="Email of account to delete"
            required
          />
        </div>
        
           
        <button class="largeButton" type="CreateButton" @click="deleteCustomerAccount(Owner.password,AccountToDelete.email)">
          Delete Customer Account
        </button>
        <br>
        <button class="largeButton" type="CreateButton" @click="deleteEmployeeAccount(Owner.password,AccountToDelete.email)">
          Delete Employee Account
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
    name: 'deleteAccount',
    data () {
        return {
            Owner:{
              password: ''
            },
            AccountToDelete: {
                email: ''
            },
            reponse: []
        }
    },

    methods: {
        deleteCustomerAccount: function (password, email){
            AXIOS.delete('/customers/delete/'.concat(email))
            .then(response => {
                this.AccountToDelete = '',
                this.Owner = ''
            })
            .catch(e => {
                var errorMsg = e.response.data
                alert(errorMsg)
            })
        },
        deleteEmployeeAccount: function (password, email){
            AXIOS.delete('/employees/delete/'.concat(email))
            .then(response => {
                this.AccountToDelete = '',
                this.Owner = ''
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
