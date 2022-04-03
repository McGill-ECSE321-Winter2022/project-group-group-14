<template>
<div>
  <b-navbar fixed="top" toggleable="lg">
      <router-link to="/customerWelcomePage">
        <b-navbar-brand>STORIKO</b-navbar-brand>
      </router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/customerWelcomePage/:email">Home</b-nav-item>
            <b-nav-item href="#/showCustomerInventoryItems">View Items</b-nav-item>
            <b-nav-item href="#/viewCart">View Cart</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
      </b-collapse>
    </b-navbar>
</div>
</template>