<template>
    <div >

     <div id="popup1" class="overlay" v-if="successMsg">
          <div class="popup">
            <h5>{{ successMsg }}</h5>
            <button class="mediumButton" onClick="window.location.reload();">Close</button>
          </div>
     </div>


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
            <h2 class="heading">Welcome {{this.email}} (Employee)! </h2>
                
                <button class="largeButton"  @click="createInstoreOrder()">
                    Log a purchase
                </button>

<!-- 
                    <router-link to="/showEmployeeInventoryItems">
                        <button class="largeButton">
                            Log a purchase
                        </button>
                    </router-link> -->
                    <br>

                     <router-link to="/viewIncompleteOrders">
                        <button class="largeButton">
                            Complete an order
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

<script src="./groceryOrder.js"> 
</script>

<style scoped>
.overlay {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.7);
  transition: opacity 500ms;
  opacity: 100%;
  z-index: 100;
}

.popup {
  margin: auto;
  margin-top: 40vh;
  padding: 20px;
  background: #fff;
  border-radius: 5px;
  width: 30%;
  transition: all 5s ease-in-out;
}

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