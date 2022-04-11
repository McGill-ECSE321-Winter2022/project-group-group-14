<template>
    <div >
    <CustomerNavigationBar></CustomerNavigationBar>


        <div class="verticalandhorizontal-center">
            <h2 class="heading">Thank You for your order!</h2>
            <br>


            <!-- <div v-if ="curremail">
                Email : {{curremail}}
            </div>

            <div v-if ="orderId">
                order Id : {{orderId}}
            </div> -->


            <div v-if = "imageUrl" >
                <img :src="returnUrl()" style = "width : 500px; " >
                <!-- <img :src=imageUrl style = "width : 500px; "> -->
                <br>
                IMAGE SHOULD BE HERE,  URL = :{{imageUrl}}
            </div>

            
            <br>

            <span v-if ="groceryOrders[0]">
                {{returnStatus(groceryOrders[0])}}
            </span>
            <br>




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


export default{
    name:'CustomerWelcomePage',
    components:{
        CustomerNavigationBar
    },
     data()
    {
        return {
            curremail : this.$route.params.email,
            orderId: this.$route.params.orderId,
            imageUrl : "",
            groceryOrders :[],
            error: '',
            successMsg:'',
            response: []
        }
    },
    created: function() {
        //   AXIOS.get('/orders/customer/latest/'.concat(this.curremail),{},{})
        //   .then(response => {
        //       // JSON responses are automatically parsed.
        //       this.orderItems.push(response.data)
        //       console.log(response.data)
        //   })
        //   .catch(e => {
        //     //   this.errorInventory = e.response.data
        //     //   console.log(e.response.data)
        //   }),
            AXIOS.get('/orders/'.concat(this.orderId),{},{})
            .then(response => {
                // JSON responses are automatically parsed.
                this.groceryOrders.push(response.data)
                console.log(response.data)
            })
            .catch(e => {
                // this.errorInventory = e.response.data
                // console.log(e.response.data)
            })
    },
    methods:{
        returnStatus: function(order){
            if (order.orderType == "Delivery"){ //order delivery pictures
                if (order.orderStatus == "Received"){
                    this.imageUrl = "../../assets/status_d1.jpg";
                    return "Your order has been created, please add items and place it. Our employees can then start preparing it.";
                }else if (order.orderStatus == "Processing"){
                    this.imageUrl = "../../assets/status_d2.jpg";
                    return "Your order is being prepared.";
                }else if (order.orderStatus == "BeingDelivered"){
                    this.imageUrl = "../../assets/status_d3.jpg";
                    return "Your order is being delivered";
                }else if (order.orderStatus == "Completed"){
                    this.imageUrl = "../../assets/status_d4.jpg";
                    return "Your latest order has been completed";
                }
            }else if(order.orderType == "PickUp") { //order is then pick up 
                if (order.orderStatus == "Received"){
                    this.imageUrl = "../../assets/status_p1.jpg";
                    return "Your order has been created, please add items and place it. Our employees can then start preparing it.";
                }else if (order.orderStatus == "Processing"){
                    this.imageUrl = "../../assets/status_p2.jpg";
                    return "Your order is being prepared.";
                }else if (order.orderStatus == "ReadyForPickUp"){
                    this.imageUrl = "../../assets/status_p3.jpg";
                    return "Your order is ready for pick up.";
                }else if (order.orderStatus == "Completed"){
                    this.imageUrl = "../../assets/status_p4.jpg";
                    return "Your latest order has been completed";
                }
            }
            return "Could not read order status"; 
        },
        returnUrl: function(){
            return this.imageUrl;
        }

    }
}
</script>