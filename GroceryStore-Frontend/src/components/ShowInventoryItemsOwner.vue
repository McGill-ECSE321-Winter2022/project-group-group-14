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
    <div class="grid-container">
      <div class="grid-item" v-for="inventoryItem in inventoryItems" :key=inventoryItem.name>
        <ul class="item">
                  <li class="info">

                  </li>
                  <li class="info item-name">
                    {{ inventoryItem.name }}
                  </li>
                  <li class="info">
                    ${{ inventoryItem.price }}.00
                  </li>
                  <li class="info">
                    Stock: {{ inventoryItem.currentStock }}
                  </li>
                  <li class="info add-item">
                    <button class="button">Remove Item</button>
                  </li>
                  <li class="info">
                    <a class="edit" href="#/modifyItems">Edit Item</a>
                  </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script src="./InventoryItem.js">
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>



.info a{
  font-size: 12px;
  color: black;
  transition: 0.3s;
  margin-top: 10px;
}


.page a {
  font-size: 13px;
}



</style>
