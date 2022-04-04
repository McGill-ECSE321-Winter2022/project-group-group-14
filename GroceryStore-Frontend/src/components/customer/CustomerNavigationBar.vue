<template>
<div>
  <b-navbar fixed="top" toggleable="lg">
      <router-link :to="{name: 'customerWelcomePage',params: {email:email}}">
        <b-navbar-brand>STORIKO</b-navbar-brand>
      </router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            
            <!-- <b-nav-item href="#/customerWelcomePage/:email">Home</b-nav-item>
            <b-nav-item href="#/showCustomerInventoryItems/:email">View Items</b-nav-item>
            <b-nav-item href="#/viewCart/:email">View Cart</b-nav-item> -->

            <b-nav-item :to="{ name: 'customerWelcomePage', params: { email: email }}"> Home </b-nav-item>
            <b-nav-item :to="{ name: 'ShowCustomerInventoryItems', params: { email: email }}"> View Items </b-nav-item>
            <b-nav-item :to="{ name: 'ViewCart', params: { email: email }}"> View Cart </b-nav-item>



          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
      </b-collapse>
    </b-navbar>
</div>
</template>

<script>
export default{
    name:'CustomerNavigationBar',
    data () {
        return {
            email : this.$route.params.email,
        }
    },

}
</script>       


