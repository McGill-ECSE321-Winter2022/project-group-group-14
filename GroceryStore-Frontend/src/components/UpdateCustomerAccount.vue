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

        <h2 class="heading">Update Your Account</h2>

        <br>

        <h6 class="subheading">First, please enter your original password</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="OriginalPassword"
            class="form-control"
            id="floatingInput"
            placeholder="Original Password"
            required
          />
        </div>

        <br>
        <br>

        <h6 class="subheading">Emails must be unique within the system</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="Email"
            class="form-control"
            id="floatingInput"
            placeholder="New Email"
            required
          />
        </div>

        <h6 class="subheading">Usernames must be unique within the system</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="Username"
            class="form-control"
            id="floatingInput"
            placeholder="New Username"
            required
          />
        </div>

        <h6 class="subheading">Passwords must contain both a capital letter and numerical character</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="Password"
            class="form-control"
            id="floatingPassword"
            placeholder="New Password"
            required
          />
        </div>

        <h6 class="subheading">Please enter a valid address</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="Address"
            class="form-control"
            id="floatingInput"
            placeholder="New Address"
            required
          />
        </div>

        <h6 class="subheading">Phone numbers must contain exactly 10 numerical characters </h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="PhoneNumber"
            class="form-control"
            id="floatingInput"
            placeholder="New Phone Number"
            required
          />
        </div>

        <br>

        <div>

           
              <button class="largeButton" type="CreateButton">
                Create Account
              </button>

        </div>

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
