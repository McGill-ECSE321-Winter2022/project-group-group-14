<template>
<div>
  <b-navbar fixed="top" toggleable="lg">
      <router-link :to="{name: 'customerWelcomePage',params: {email:email , orderId: orderId }}">
        <b-navbar-brand>STORIKO</b-navbar-brand>
      </router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item :to="{ name: 'customerWelcomePage', params: { email: email , orderId: orderId }}"> Home </b-nav-item>
            <b-nav-item :to="{ name: 'ShowCustomerInventoryItems', params: { email: email , orderId: orderId }}"> View Items </b-nav-item>
            <b-nav-item :to="{ name: 'ViewCart', params: { email: email , orderId: orderId }}"> View Cart </b-nav-item>
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
            orderId: this.$route.params.orderId
        }
    },

}
</script>       


