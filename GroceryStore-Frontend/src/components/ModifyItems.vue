<template>
  <div class="shop">
  <ul class="navBar">
        <li class="logo">STORIKO</li>
        <li class="page"><a href="#/modifyStoreSchedule">Modify Store Schedule</a></li>
        <li class="page"><a href="#/report">Report</a></li>
        <li class="page"><a href="#/modifyItems">Modify Inventory Items</a></li> 
        <li class="page"><a href="#/viewStoreScheduleOwner">View Store Schedule</a></li>
        <li class="page"><a href="#/employeeSchedules">Employee Schedules</a></li>
        <li class="page"><a href="#/modifyEmployees">Modify Employees</a></li>
        <li class="page"><a href="#/modifyCustomers">Modify Customers</a></li>
        <li class="page"><a href="#/deleteOwnerAccount">Delete Account</a></li> 
        
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
              <li class="info">
                <button class="button" @click="removeInventoryItem(inventoryItem.name,inventoryItem.price,inventoryItem.currentStock)">Remove Item</button>
              </li>
              <li class="info">
                <a class="edit" href="">Edit Item</a>
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
h1, h2 {
  font-weight: normal;
}

.edit {
    font-size: 12px;
    color: black;
    transition: 0.3s;
}


.shop {
  margin-top: 150px;
  margin-bottom: 100px;
}

ul {
  list-style-type: none;
}

.info {
  font-weight: 700;
}

.item-name {
  font-size: 25px;
}

.item{
  padding: 15px 0px 15px 0px;
  margin: 0;
}

.grid-item{
  margin: 10px;
  border: 1px solid black;
  border-radius: 10px;
}

.grid-container {
  display: grid;
  row-gap: 70px;
  column-gap: 150px;
  margin-left: 50px;
  margin-right: 50px;
  grid-template-columns: auto auto auto;
}


</style>
