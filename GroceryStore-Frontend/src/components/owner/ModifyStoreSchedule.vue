<template>
  <div>
  <b-navbar fixed="top" toggleable="lg">
      <b-navbar-brand>STORIKO</b-navbar-brand>
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



<form>

<label for="day">Select Day:</label>
  <input class="selectDay" list="days" name="day">
  <datalist id="days">
    <option value="Sunday"></option>
    <option value="Monday"></option>
    <option value="Tuesday"></option>
    <option value="Wednesday"></option>
    <option value="Thursday"></option>
    <option value="Friday"></option>
    <option value="Saturday"></option>
  </datalist>

<label for="time">Select new Opening Time:</label>
  <input class="changeOpen" list="times" name="time">
  <datalist id="times">
    <option value="08:00"></option>
    <option value="08:15"></option>
    <option value="08:30"></option>
    <option value="08:45"></option>
    <option value="09:00"></option>
    <option value="09:15"></option>
    <option value="09:30"></option>
    <option value="09:45"></option>
    <option value="10:00"></option>
    <option value="10:15"></option>
    <option value="10:30"></option>
    <option value="10:45"></option>
    <option value="11:00"></option>
    <option value="11:15"></option>
    <option value="11:30"></option>
    <option value="11:45"></option>
    <option value="12:00"></option>
    <option value="12:15"></option>
    <option value="12:30"></option>
    <option value="12:45"></option>
    <option value="13:00"></option>
    <option value="13:15"></option>
    <option value="13:30"></option>
    <option value="13:45"></option>
    <option value="14:00"></option>
    <option value="14:15"></option>
    <option value="14:30"></option>
    <option value="14:45"></option>
    <option value="15:00"></option>
    <option value="15:15"></option>
    <option value="15:30"></option>
    <option value="15:45"></option>
    <option value="16:00"></option>
    <option value="16:15"></option>
    <option value="16:30"></option>
    <option value="16:45"></option>
    <option value="17:00"></option>
    <option value="17:15"></option>
    <option value="17:30"></option>
    <option value="17:45"></option>
    <option value="18:00"></option>
    <option value="18:15"></option>
    <option value="18:30"></option>
    <option value="18:45"></option>
    <option value="19:00"></option>
    <option value="19:15"></option>
    <option value="19:30"></option>
    <option value="19:45"></option>
    <option value="20:00"></option>
  </datalist>

<label for="time">Select new Closing Time:</label>
  <input class="changeClose" list="times" name="time">
  <datalist id="times">
    <option value="08:00"></option>
    <option value="08:15"></option>
    <option value="08:30"></option>
    <option value="08:45"></option>
    <option value="09:00"></option>
    <option value="09:15"></option>
    <option value="09:30"></option>
    <option value="09:45"></option>
    <option value="10:00"></option>
    <option value="10:15"></option>
    <option value="10:30"></option>
    <option value="10:45"></option>
    <option value="11:00"></option>
    <option value="11:15"></option>
    <option value="11:30"></option>
    <option value="11:45"></option>
    <option value="12:00"></option>
    <option value="12:15"></option>
    <option value="12:30"></option>
    <option value="12:45"></option>
    <option value="13:00"></option>
    <option value="13:15"></option>
    <option value="13:30"></option>
    <option value="13:45"></option>
    <option value="14:00"></option>
    <option value="14:15"></option>
    <option value="14:30"></option>
    <option value="14:45"></option>
    <option value="15:00"></option>
    <option value="15:15"></option>
    <option value="15:30"></option>
    <option value="15:45"></option>
    <option value="16:00"></option>
    <option value="16:15"></option>
    <option value="16:30"></option>
    <option value="16:45"></option>
    <option value="17:00"></option>
    <option value="17:15"></option>
    <option value="17:30"></option>
    <option value="17:45"></option>
    <option value="18:00"></option>
    <option value="18:15"></option>
    <option value="18:30"></option>
    <option value="18:45"></option>
    <option value="19:00"></option>
    <option value="19:15"></option>
    <option value="19:30"></option>
    <option value="19:45"></option>
    <option value="20:00"></option>
  </datalist>

<br>
  <input class="confirmation" type="submit" value="Change Time">
</form>

<span v-if="errorPerson" style="color:red">Error: {{errorPerson}} </span>
  </div>
</template>

<script src="./StoreScheduleOwner.js">
</script>


<style scoped>

.selectDay {
  margin-top: 12%;
  margin-right: 6%;
}

.changeOpen {
  margin-top: 12%;
  margin-right: 6%;
}

.changeClose {
  margin-top: 12%;
}

.confirmation {
  margin-top: 20%;
}
</style>
