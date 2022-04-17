<template>
    <div >
        <owner-navigation-bar></owner-navigation-bar>

        <div class="bg-color-gradient">
            
        </div>
        <div class="verticalandhorizontal-center">
          <h1 v-if="!this.username" class="heading">Welcome! </h1>
          <h1 v-if="this.username" class="heading">Welcome {{this.username}}! </h1>
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
export default{
  components: { OwnerNavigationBar },
  
    data()
    {
        return {
          email:this.$route.params.email,
          username:''
          }
    },
    created: function() {

            AXIOS.get('/owners/'.concat(this.email),{},{})
            .then(response => {
                this.username=response.data.username
            })
            .catch(e => {
            })
    },
}
</script>

<style scoped>

h1 {
  font-size: 60px;
}

.bg-color-gradient {
  height: 100vh;
}

.verticalandhorizontal-center {
    padding: 2% 6% 2% 6%;
    background-color: transparent; 
    border-radius: 15px;
    margin-top: 1%;
    box-shadow: 0 0 10px 7px rgb(0,0,0,0);
  }
</style>