<template>
    <div >
        <CustomerNavigationBar></CustomerNavigationBar>

        <div class="background-img">
            <img src="../../assets/orange3.jpg">
        </div>
        <div class="verticalandhorizontal-center">
            <h2 class="heading">Welcome Customer! </h2>
            <br>
                <h4> Place an order : </h4>
                <br>
                    <router-link to="/showCustomerInventoryItems">
                        <button class="largeButton">
                            Delivery
                        </button>

                    </router-link>
                    <br>
                     <router-link to="/showCustomerInventoryItems">
                        <button class="largeButton">
                           Pick up
                        </button>
                    </router-link>
                    <br>
                    <br>
                <h4> Already placed an order? </h4>  
                <br> 

                    <router-link to="/viewOrderStatus">
                        <button class="largeButton">
                            View your order's status
                        </button>
                    </router-link>
                
        </div>
    </div>
</template>

<style>
.largeButton {
    width: 250px;
}
.verticalandhorizontal-center {
    padding: 2% 6% 2% 6%;
    background-color: white;
    border-radius: 4%;
    margin-top: 1%;
    box-shadow: 0 0 10px 7px rgb(0,0,0,0.3);
  }
</style>


<script>
import CustomerNavigationBar from '@/components/customer/CustomerNavigationBar'
export default{
    name:'CustomerWelcomePage',
    components:{
        CustomerNavigationBar
    }
}
</script>
