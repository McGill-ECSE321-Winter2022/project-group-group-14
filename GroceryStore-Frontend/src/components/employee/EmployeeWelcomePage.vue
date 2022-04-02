<template>
    <div >
        <b-navbar fixed="top">
      <router-link to="/employeeWelcomePage">
        <b-navbar-brand>STORIKO</b-navbar-brand>
      </router-link>
    
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
    
    </b-navbar>

         <div class="background-img">
            <img src="../../assets/orange8.jpg">
        </div>
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
.verticalandhorizontal-center {
    padding: 2% 6% 2% 6%;
    background-color: white;
    border-radius: 4%;
    margin-top: 1%;
    box-shadow: 0 0 10px 7px rgb(0,0,0,0.3);
  }
.heading {
    margin-top: 20px;
    margin-bottom: 50px;
}
</style>