<template>
    <div >
    <CustomerNavigationBar></CustomerNavigationBar>


        <div class="verticalandhorizontal-center">
            <h2 class="heading">Order Status </h2>
                <br>
                <h4> We are carefully preparing your order! </h4>
                <br>
                <h4> nice little illustration of the order status </h4>


        </div>
    </div>
</template>

<script>
import CustomerNavigationBar from '@/components/customer/CustomerNavigationBar'
export default{
    name:'CustomerPayment',
    data()
    {
        return {

            
            curremail : this.$route.params.email,
        }

    },
    components:{
        CustomerNavigationBar
    }
    
}
</script>