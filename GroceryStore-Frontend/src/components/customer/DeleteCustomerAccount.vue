<template>
  <div>
    <div id="popup1" class="overlay" v-if="successDelete">
          <div class="popup">
            <h5>{{ successDelete }}</h5>
            <router-link to="/">
            <button class="mediumButton">Close</button>
            </router-link>
          </div>
        </div>

        <div id="popup1" class="overlay" v-if="errorDelete">
          <div class="popup">
            <h5>{{ errorDelete }}</h5>
            <button class="mediumButton" onClick="window.location.reload();">Close</button>
          </div>
        </div>

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
           <!-- <router-link to="/"> -->
              <button class="largeButton" type="CreateButton" @click="deleteCustomerAccount(curremail)">
                Delete Your Account
              </button>
           <!-- </router-link> -->
           

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
            curremail : this.$route.params.email,
            successDelete:'',
            errorDelete:''

        }

    },
    methods: {
        deleteCustomerAccount: function (email){
            AXIOS.delete('/customers/delete/'.concat(email))
            .then(response => {
                this.successDelete = email + ' has been deleted successfully!'
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
