<template>
  <div>
  
  <b-navbar fixed="top" toggleable="lg">
      <router-link to="/employeeWelcomePage">
        <b-navbar-brand>STORIKO</b-navbar-brand>
      </router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/deleteEmployeeAccount">Delete Account</b-nav-item>
            <b-nav-item href="#/createCustomerFromEmployee">Create Customer</b-nav-item>
            <b-nav-item href="#/updateCustomerFromEmployee">Update Customer</b-nav-item>
            <b-nav-item href="#/viewStoreEmployeeScheduleEmployee">Schedule</b-nav-item>
            <b-nav-item href="#/modifyGroceryOrderStatus">Order Status</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  <div class="verticalandhorizontal-center">

        <h2 class="heading">Create Customer Account</h2>

        <br>
        <br>

        <h6 class="subheading">Emails must be unique within the system</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="Email"
            class="form-control"
            id="floatingInput"
            placeholder="Email"
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
            placeholder="Username"
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
            placeholder="Password"
            required
          />
        </div>

        <h6 class="subheading">Please enter a valid address </h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="Address"
            class="form-control"
            id="floatingInput"
            placeholder="Address"
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
            placeholder="Phone Number"
            required
          />
        </div>

        <br>
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
