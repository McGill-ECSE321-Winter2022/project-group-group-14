<template>
  <div>
  <b-navbar fixed="top" toggleable="lg">
      <b-navbar-brand>STORIKO</b-navbar-brand>
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

        <h2 class="heading">Delete Account</h2>

        <br>

        <h6 class="subheading">To delete your account, first enter your password</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="Password"
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
