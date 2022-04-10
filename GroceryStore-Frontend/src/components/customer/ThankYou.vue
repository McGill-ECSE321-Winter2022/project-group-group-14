<template>
    <div >
    <CustomerNavigationBar></CustomerNavigationBar>


        <div class="verticalandhorizontal-center">
            <h2 class="heading">Thank You for your order</h2>
            <br>
            <br>
            <!-- <div class="background-img">
                 <img src="../assets/d2_1.jpg">
            </div> -->
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