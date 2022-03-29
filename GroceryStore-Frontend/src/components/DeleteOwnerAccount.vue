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

.page a {
        font-size: 13px;
}




</style>
