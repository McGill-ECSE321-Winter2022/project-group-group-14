import axios from 'axios'
// import swal from 'sweetalert'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    // baseURL: backendUrl,
    // headers: { 'Access-Control-Allow-Origin': frontendUrl }
  })

  export default {
      name: 'LoginPage',

      data() {
          return {
            username: '',
            password: ''
          }
        },
      

      methods: {
          loginUser: function(username, password){
              AXIOS.post('/login/'+username+'?password='+password,{},{})
              .then(response => {
                
                  // if(userId.localeCompare("Owner") == 0){
                  //     this.$router.push('/homeOwner')    // need to change to owner homepage
                  // }

                  // else if(userId.localeCompare("Admin") == 0){
                  //   this.$router.push('/homeAdmin')      // need to change to owner homepage
                  // }

                  // else{
                  //   this.$router.push('/home')
                  // }
              })
              .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                this.errorProfile = errorMsg
                // swal("ERROR", e.response.data, "error");
              });
          },
      }
  }
