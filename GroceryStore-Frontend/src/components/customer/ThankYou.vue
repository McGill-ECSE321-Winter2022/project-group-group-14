<template>
    <div >
    <CustomerNavigationBar></CustomerNavigationBar>


        <div class="verticalandhorizontal-center">
            <h2 class="heading">Thank You for your order</h2>
            <br>
            <br>
            <!-- <div class="background-img">
                 <img src=status>
            </div> -->

            <div v-if ="groceryOrders[0]">
                Order Status : {{groceryOrders[0].orderStatus}}
            </div>
            <div v-if ="curremail">
                Email : {{curremail}}
            </div>
            <br>
            <br>
            <br>
            display store information here in case of issue :
            "Please contact customer support at ... in case of issues."

        </div>
    </div>
</template>

<style scoped>
</style>
<script>
import axios from 'axios'
import CustomerNavigationBar from '@/components/customer/CustomerNavigationBar'

var config = require('../../../config')

var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT

// var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
// var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})  
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})  



export default{
    name:'CustomerWelcomePage',
    components:{
        CustomerNavigationBar
    },
     data()
    {
        return {
            curremail : this.$route.params.email,
            groceryOrders :[],
            error: '',
            successMsg:'',
            response: []
        }
    },
    created: function() {
          AXIOS.get('/orders/customer/latest/'.concat(this.curremail),{},{})
          .then(response => {
              // JSON responses are automatically parsed.
              this.orderItems.push(response.data)
              console.log(response.data)
          })
          .catch(e => {
            //   this.errorInventory = e.response.data
            //   console.log(e.response.data)
          })
    }
}
</script>