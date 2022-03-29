<template>
    <div >
  <b-navbar fixed="top" toggleable="lg">
      <b-navbar-brand>STORIKO</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/showCustomerInventoryItems">View Items</b-nav-item>
            <b-nav-item href="#/viewCart">View Cart</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
      </b-collapse>
    </b-navbar>

        <div class="verticalandhorizontal-center">
            <h2 class="heading">Cart</h2>
            <br>
                <h4> Feature List of items and total price here</h4>




            <br>
            <br>
                <h4> If order is a delivery order, feature customer address here</h4>
                <h4> Address is not correct?</h4><router-link to="/modifyAddress">
                        <button class="largeButton">
                            Modify Address
                        </button>
                    </router-link>

                <br>

                <br>

                    <router-link to="/thankYou">
                        <button class="largeButton">
                            Place Order
                        </button>
                    </router-link>
                <br>
                <br>
                <h5> Made a mistake? Delete your order and start again.</h5>
                        <button class="button_delete">
                            Delete Order
                        </button>
        </div>
    </div>
</template>

<style scoped>
</style>