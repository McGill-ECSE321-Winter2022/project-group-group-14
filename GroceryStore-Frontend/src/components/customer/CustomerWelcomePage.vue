<template>
    <div >
        <b-navbar fixed="top">
      <b-navbar-brand>STORIKO</b-navbar-brand>
    
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
    
    </b-navbar>

        <div class="verticalandhorizontal-center">
            <h2 class="heading">Welcome Customer! </h2>
            <br>
                <h4> Place an order : </h4>
                    <router-link to="/showCustomerInventoryItems">
                        <button class="largeButton">
                            Delivery
                        </button>

                    </router-link>
                     <router-link to="/showCustomerInventoryItems">
                        <button class="largeButton">
                           Pick up
                        </button>
                    </router-link>
                    <br>
                    <br>
                <h4> Already placed an order? </h4>   

                    <router-link to="/viewOrderStatus">
                        <button class="largeButton">
                            View your order's status
                        </button>
                    </router-link>
                
        </div>
    </div>
</template>

<style scoped>
</style>