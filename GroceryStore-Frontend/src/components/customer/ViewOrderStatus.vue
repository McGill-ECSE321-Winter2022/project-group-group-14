<template>
    <div >
    <b-navbar fixed="top" toggleable="lg">
      <router-link :to="{name: 'customerWelcomePage',params: {email:curremail}}">
        <b-navbar-brand>STORIKO</b-navbar-brand>
      </router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">

            <b-nav-item :to="{ name: 'customerWelcomePage', params: { email: curremail }}"> Home </b-nav-item>



          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
      </b-collapse>
    </b-navbar>


        <div class="verticalandhorizontal-center">
            <h2 class="heading">Order Status </h2>
                <br>
                <h4> We are carefully preparing your order! </h4>
                <br>
                <h4> Nice little illustration of the order status </h4>
                <div v-if = "orderStatus"> Order Status : {{orderStatus}} </div>
                <div >
                  <img src="../../assets/status_d1.jpg" style = "width : 500px; heaight : 500px;">
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





export default{
    name:'ViewOrderStatus',
    data()
    {
        return {
            orderiD : this.$route.params.orderId,
            orderStatus :'',
            groceryOrders : [],
            response: []
        }

    },
      created: function() {
          AXIOS.get('/orders/'.concat(this.orderId),{},{})
          .then(response => {
              this.groceryOrders.push(response.data)
              this.orderStatus = response.data.orderStatus
          })
          .catch(e => {
          })
    }
    
}
</script>