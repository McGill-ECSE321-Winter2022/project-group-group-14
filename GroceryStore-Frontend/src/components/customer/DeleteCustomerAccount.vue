<template>
  <div>
  <b-navbar fixed="top" toggleable="lg">
      <router-link :to="{name: 'customerWelcomePage',params: {email:curremail}}">
        <b-navbar-brand>STORIKO</b-navbar-brand>
      </router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            
            <b-nav-item :to="{ name: 'customerWelcomePage', params: { email: curremail }}"> Home </b-nav-item>
            <b-nav-item :to="{ name: 'UpdateCustomerAccount', params: { email: curremail }}"> Update Info </b-nav-item>
            <b-nav-item :to="{ name: 'DeleteCustomerAccount', params: { email: curremail }}"> Delete Account </b-nav-item>



          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
      </b-collapse>
    </b-navbar>

  <div class="verticalandhorizontal-center">

        <h2 class="heading">Delete Account</h2>

        <br>

        <h6 class="subheading">Are you sure? To delete your account, click below</h6>
        <!-- <div class="form-floating mb-3">
          <input
            type="password"
            class="form-control"
            id="floatingInput"
            placeholder="Password"
            required
          />
        </div> -->
           <router-link to="/">
              <button class="largeButton" type="CreateButton" @click="deleteCustomerAccount()">
                Delete Your Account
              </button>
           </router-link>
           

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
  
export default{
    name:'DeleteCustomer',
    data()
    {
        return {
            curremail : this.$route.params.email

        }

    },
    methods: {
        deleteCustomerAccount: function (){
            AXIOS.delete('/customers/delete/'.concat(curremail))
            .then(response => {
                this.errorDelete = email + ' has been deleted successfully!'
                this.AccountToDelete = '',
                this.Owner = ''
            })
            .catch(e => {
                var errorMsg = e.response.data
                console.log(errorMsg)
                this.errorDelete = errorMsg
            })
        }
    }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->


<style scoped>


ul {
  list-style-type: none;
}

</style>
