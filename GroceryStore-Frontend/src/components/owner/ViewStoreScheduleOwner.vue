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

    <table>
      <caption style="text-align:center">Weekly Schedule</caption>
    <tr>
        <th scope="row">Day</th>
        <th scope="row">Opening Hours</th>
        <th scope="row">Closing Hours</th>
        <th colspan="3" scope="row">Active Workers (Morning, Afternoon, Night)</th>

    </tr>
    <tr class="schedule" v-for="storeSchedule in storeSchedules" :key=storeSchedule.dayOpen>

        <td class="news">{{ storeSchedule.dayOpen }}</td>
        <td class="news">{{ storeSchedule.openingTime }}</td>
        <td class="news">{{ storeSchedule.closingTime }}</td>
        <td class="news"></td>
        <td class="news"></td>
        <td class="news"></td>
        <!-- <td>{{ idk use employeeschedule }}</td> -->
    </tr>
</table>
<span v-if="errorPerson" style="color:red">Error: {{errorPerson}} </span>
  </div>

</template>

<script src="./StoreScheduleOwner.js">
</script>


<style scoped>
h1, h2 {
  text-align: center;
  margin-left: auto;
  margin-right: auto;
  font-weight: bold;
}

td,th {
    border: 1px solid rgb(190, 190, 190);
    padding: 10px;
}

td {
    text-align: center;
}

tr:nth-child(even) {
    background-color: #eee;
}

th[scope="row"] {
    background-color: #d7d9f2;
}

caption {
    padding: 10px;
    caption-side: top;
}

table {
    
    margin-left: auto;
    margin-right: auto;
    margin-top: 6%;
    
    width: 75%;
    table-layout: auto;
    border-collapse: separate;
    border: 2px solid rgb(200, 200, 200);
    letter-spacing: 1px;
    font-family: sans-serif;
    font-size: .8rem;
}

.news {
  width: 16.6%;
}
</style>
