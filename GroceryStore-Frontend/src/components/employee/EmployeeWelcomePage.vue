<template>
    <div >
        <b-navbar fixed="top">
      <b-navbar-brand>STORIKO</b-navbar-brand>
    
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
    
    </b-navbar>

        <div class="verticalandhorizontal-center">
            <h2 class="heading">Welcome Employee! </h2>

                    <router-link to="/showEmployeeInventoryItems">
                        <button class="largeButton">
                            Place an order
                        </button>
                    </router-link>

                    <br>

                     <router-link to="/viewIncompleteOrders">
                        <button class="largeButton">
                            View all incomplete orders
                        </button>
                    </router-link>
                    <br>

                    <router-link to="/viewStoreEmployeeScheduleEmployee">
                        <button class="largeButton">
                            Something else
                        </button>
                    </router-link>
        
        </div>
    </div>
</template>

<style scoped>
</style>