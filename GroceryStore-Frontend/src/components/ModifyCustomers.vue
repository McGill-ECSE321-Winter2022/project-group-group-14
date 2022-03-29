<template>
  <div>
  <ul class="navBar">
        <li class="logo">STORIKO</li>
        <li class="page"><a href="#/modifyStoreSchedule">Modify Store Schedule</a></li>
        <li class="page"><a href="#/report">Report</a></li>
        <li class="page"><a href="#/showInventoryItemsOwner">Show Inventory Items</a></li> 
        <li class="page"><a href="#/modifyItems">Modify Inventory Items</a></li> 
        <li class="page"><a href="#/viewStoreScheduleOwner">View Store Schedule</a></li>
        <li class="page"><a href="#/employeeSchedules">Employee Schedules</a></li>
        <li class="page"><a href="#/modifyEmployees">Modify Employees</a></li>
        <li class="page"><a href="#/modifyCustomers">Modify Customers</a></li>
        <li class="page"><a href="#/deleteOwnerAccount">Delete Account</a></li>
        <li class="page"><a href="#/">Log Out</a></li>   
        
  </ul>
  <div class="verticalandhorizontal-center">

        <h2 class="heading">Create / Update Customer Account</h2>

        <br>

        <h6 class="subheading">Account to change (Leave this empty to create an account)</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="UsernameToChange"
            class="form-control"
            id="floatingInput"
            placeholder="Old Username"
          />
        </div>
        
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


        <div>

           
              <button class="largeButton" type="CreateButton">
                Update Account
              </button>
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


.page a {
  font-size: 13px;
}




</style>
