<template>
  <div>
  <ul class="navBar">
        <li class="logo">STORIKO</li>
        <li class="page"><a href="#/deleteCustomerAccount">Delete Account</a></li>
        <li class="page"><a href="#/updateCustomerAccount">Update Customer Account</a></li>
        <li class="page"><a href="#/viewCustomerStoreSchedule">View Store Schedule</a></li>    
        <li class="page"><a href="#/">Log Out</a></li>      
  </ul>
  <div class="verticalandhorizontal-center">

        <h2 class="heading">Delete Account</h2>

        <br>

        <h6 class="subheading">To delete your account, first enter your password</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="UsernameToChance"
            class="form-control"
            id="floatingInput"
            placeholder="Password"
            required
          />
        </div>
           
              <button class="largeButton" type="CreateButton">
                Delete Your Account
              </button>

        </div>
  </div>
</template>

<script>
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


ul {
  list-style-type: none;
}




</style>
