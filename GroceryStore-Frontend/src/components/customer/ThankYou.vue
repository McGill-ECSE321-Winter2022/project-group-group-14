<template>
    <div >
    <CustomerNavigationBar></CustomerNavigationBar>


        <div class="verticalandhorizontal-center">
            <h2 class="heading">Thank You for your order</h2>
            <br>
            <br>
             feature order status here
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
import CustomerNavigationBar from '@/components/customer/CustomerNavigationBar'
export default{
    name:'CustomerWelcomePage',
    components:{
        CustomerNavigationBar
    }
}
</script>