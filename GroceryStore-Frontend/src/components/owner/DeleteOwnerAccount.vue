<template>
  <div>
  <b-navbar fixed="top" toggleable="lg">
      <router-link to="/ownerWelcomePage">
        <b-navbar-brand>STORIKO</b-navbar-brand>
      </router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item-dropdown text="InventoryItems">
              <b-dropdown-item href="#/showInventoryItemsOwner">Show Inventory Items</b-dropdown-item>
              <b-dropdown-item href="#/modifyItems">Modify Inventory Items</b-dropdown-item>
            </b-nav-item-dropdown>
            <b-nav-item-dropdown text="Schedules">
              <b-dropdown-item href="#/modifyStoreSchedule">Modify Store Schedule</b-dropdown-item>
              <b-dropdown-item href="#/viewStoreScheduleOwner">View Store Schedule</b-dropdown-item>
              <b-dropdown-item href="#/employeeSchedules">Employee Schedules</b-dropdown-item>
            </b-nav-item-dropdown>
            <b-nav-item-dropdown text="Modify Accounts">
              <b-dropdown-item href="#/modifyEmployees">Modify Employees</b-dropdown-item>
              <b-dropdown-item href="#/modifyCustomers">Modify Customers</b-dropdown-item>
              <b-dropdown-item href="#/deleteOwnerAccount">Delete Account</b-dropdown-item>
            </b-nav-item-dropdown>
            <b-nav-item href="#/report">Report</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  <div class="verticalandhorizontal-center">

        <h2 class="heading">Delete An Account</h2>

        <br>

        <h6 class="subheading">To delete an account, first enter your password</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="Password"
            class="form-control"
            id="floatingInput"
            placeholder="Owner Password"
            required
          />
        </div>

        <br>
        <br>

        <h6 class="subheading">Select the account to delete</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="Username"
            class="form-control"
            id="floatingInput"
            placeholder="Username of account to delete"
            required
          />
        </div>
        
           
        <button class="largeButton" type="CreateButton">
          Delete Customer Account
        </button>
        <br>
        <button class="largeButton" type="CreateButton">
          Delete Employee Account
        </button>

        </div>

  </div>
</template>

<script>
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.verticalandhorizontal-center {
    padding: 2% 6% 2% 6%;
    background-color: white;
    border-radius: 4%;
    margin-top: 1%;
    box-shadow: 0 0 10px 7px rgb(0,0,0,0.3);
  }
.page a {
        font-size: 13px;
}




</style>
