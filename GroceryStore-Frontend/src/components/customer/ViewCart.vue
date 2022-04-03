<template>
    <div >
    <CustomerNavigationBar></CustomerNavigationBar>

        <div class="verticalandhorizontal-center">
            <h2 class="heading">Cart</h2>

            <br>
                <div v-for= "orderItem in orderItems" :key=orderItem.name>
                    <ul style="list-style-type:square">
                        <li> {{ orderItem.name}} :  {{ orderItem.price}} </li>
                    </ul>  
                </div>
            <div>
               <h4 class="heading">Total Cost : {{groceryOrder.totalCost}}</h4>  
            </div>
            <br>
            <br>

                <div v-if="groceryOrder.type === 'delivery'"> 
                   <h4> Possibly display address here </h4>
                </div>
                <!-- <h4> Address is not correct?</h4>
                <router-link to="/modifyAddress">
                        <button class="largeButton">
                            Modify Address
                        </button>
                    </router-link> -->

                <br>

                <br>

                    <router-link to="/thankYou">
                        <button class="largeButton" v-if="curremail" @click="placeOrder(groceryOrders.orderId)">
                            Place Order
                        </button>
                    </router-link>
                <br>
                <br>
                <h5> Made a mistake? Delete your order and start again.</h5>
                        <button class="button_delete" v-if="curremail" @click="deleteOrder(groceryOrders.orderId)">
                            Delete Order
                        </button>
        </div>
    </div>
</template>

<style scoped>
</style>
<script>
import CustomerNavigationBar from '@/components/customer/CustomerNavigationBar'
export default{
    name:'CustomerWelcomePage',
    components:{
        CustomerNavigationBar
    },
    data () {
        return {
            email : this.$route.params.email,
            orderItems:[],
            newOrderItem :{
                name : '',
                price: '',
                itemId:'',
            },
            groceryOrders :[],
            groceryOrder : {
                orderId:'',
                totalCost:'',
                orderType:'',
                orderStatus:'',
                orderItems: [],
                customer:''
            },
            errorOrder: '',
            successMsg:'',
            response: []
        }
    },

}
</script>