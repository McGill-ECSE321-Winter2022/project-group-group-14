<template>
    <div >
    <CustomerNavigationBar></CustomerNavigationBar>


        <div class="verticalandhorizontal-center">
            <h2 class="heading">Thank you for your order!</h2>
            <br>



            <div v-if ="orderId">
                <h5>Order Number : {{orderId}}</h5>
            </div>

            <div>
                <br>
                <img src="@/assets/status_d1.jpg" v-if="d1" style = "width : 500px; ">
                <img src="@/assets/status_d2.png" v-if="d2" style = "width : 500px; ">
                <img src="@/assets/status_d3.png" v-if="d3" style = "width : 500px; ">
                <img src="@/assets/status_d4.png" v-if="d4" style = "width : 500px; ">
                <img src="@/assets/status_p1.png" v-if="p1" style = "width : 500px; ">
                <img src="@/assets/status_p2.png" v-if="p2" style = "width : 500px; ">
                <img src="@/assets/status_p3.png" v-if="p3" style = "width : 500px; ">
                <img src="@/assets/status_p4.png" v-if="p4" style = "width : 500px; ">

            </div>
            
            <br>
            <br>

            <span v-if ="groceryOrders[0]">
                <h4>{{returnStatus(groceryOrders[0])}}</h4>
            </span>
            <br>
            <br>
            <br>
            <br>

        </div>
    </div>
</template>

<style scoped>
</style>
<script>
import axios from 'axios'
import CustomerNavigationBar from '@/components/customer/CustomerNavigationBar'


var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT


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
            response: [],
            d1:'',
            d2:'',
            d3:'',
            d4:'',
            p1:'',
            p2:'',
            p3:'',
            p4:''
        }
    },
    created: function() {
            AXIOS.get('/orders/'.concat(this.orderId),{},{})
            .then(response => {
                // JSON responses are automatically parsed.
                
                this.groceryOrders.push(response.data)
                console.log(response.data)
            })
            .catch(e => {
            })
    },
    methods:{
        returnStatus: function(order){
            if (order.orderType == "Delivery"){ //order delivery pictures
                if (order.orderStatus == "Received"){
                    this.d1 = 'present'
                    this.d2 = ''
                    this.d3 = ''
                    this.d4 = ''
                    this.p1 = ''
                    this.p2 = ''
                    this.p3 = ''
                    this.p4 = ''
                    this.imageUrl = "status_d1.jpg";
                    return "Your order has been created. Our employees will start preparing it shortly.";
                   
                }else if (order.orderStatus == "Processing"){
                    this.d1 = ''
                    this.d2 = 'present'
                    this.d3 = ''
                    this.d4 = ''
                    this.p1 = ''
                    this.p2 = ''
                    this.p3 = ''
                    this.p4 = ''
                    this.imageUrl = "../../assets/status_d2.png";
                    return "Your order is being prepared.";
                }else if (order.orderStatus == "BeingDelivered"){
                    this.d1 = ''
                    this.d2 = ''
                    this.d3 = 'present'
                    this.d4 = ''
                    this.p1 = ''
                    this.p2 = ''
                    this.p3 = ''
                    this.p4 = ''
                    this.imageUrl = "../../assets/status_d3.png";
                    return "Your order is being delivered";
                }
                else if (order.orderStatus == "Completed"){
                    this.d1 = ''
                    this.d2 = ''
                    this.d3 = ''
                    this.d4 = 'present'
                    this.p1 = ''
                    this.p2 = ''
                    this.p3 = ''
                    this.p4 = ''
                    this.imageUrl = "status_d4.png";
                    return "Your latest order has been completed";
                }
            }else if(order.orderType == "PickUp") { //order is then pick up 
                if (order.orderStatus == "Received"){
                    this.d1 = ''
                    this.d2 = ''
                    this.d3 = ''
                    this.d4 = ''
                    this.p1 = 'present'
                    this.p2 = ''
                    this.p3 = ''
                    this.p4 = ''
                    this.imageUrl = "../../assets/status_p1.png";
                    return "Your order has been created. Our employees will start preparing it shortly.";
                }else if (order.orderStatus == "Processing"){
                    this.d1 = ''
                    this.d2 = ''
                    this.d3 = ''
                    this.d4 = ''
                    this.p1 = ''
                    this.p2 = 'present'
                    this.p3 = ''
                    this.p4 = ''
                    this.imageUrl = "../../assets/status_p2.png";
                    return "Your order is being prepared.";
                }else if (order.orderStatus == "ReadyForPickUp"){
                    this.d1 = ''
                    this.d2 = ''
                    this.d3 = ''
                    this.d4 = ''
                    this.p1 = ''
                    this.p2 = ''
                    this.p3 = 'present'
                    this.p4 = ''
                    this.imageUrl = "../../assets/status_p3.png";
                    return "Your order is ready for pick up.";
                }else if (order.orderStatus == "Completed"){
                    this.d1 = ''
                    this.d2 = ''
                    this.d3 = ''
                    this.d4 = ''
                    this.p1 = ''
                    this.p2 = ''
                    this.p3 = ''
                    this.p4 = 'present'
                    this.imageUrl = "../../assets/status_p4.png";
                    return "Your latest order has been completed";
                }
            }
            return "You have no active order."; 
        },
        returnUrl: function(){
            return this.imageUrl;
        }

    }
}
</script>