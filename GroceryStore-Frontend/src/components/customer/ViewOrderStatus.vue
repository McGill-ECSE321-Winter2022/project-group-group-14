<template>
    <div >
    <b-navbar fixed="top" toggleable="lg">
      <router-link :to="{name: 'customerWelcomePage',params: {email:curremail}}">
        <b-navbar-brand>STORIKO</b-navbar-brand>
      </router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            
            <!-- <b-nav-item href="#/customerWelcomePage/:email">Home</b-nav-item>
            <b-nav-item href="#/showCustomerInventoryItems/:email">View Items</b-nav-item>
            <b-nav-item href="#/viewCart/:email">View Cart</b-nav-item> -->

            <b-nav-item :to="{ name: 'customerWelcomePage', params: { email: curremail }}"> Home </b-nav-item>
            <!-- <b-nav-item :to="{ name: 'ShowCustomerInventoryItems', params: { email: email }}"> View Items </b-nav-item>
            <b-nav-item :to="{ name: 'ViewCart', params: { email: email }}"> View Cart </b-nav-item> -->



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