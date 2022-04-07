<template>
  <div>
    <div id="popup1" class="overlay" v-if="errorInventory">
          <div class="popup">
            <h5>{{ errorInventory }}</h5>
            <!-- <button class="mediumButton" >Close</button> -->
            <button class="mediumButton" @click="close()">Close</button>
          </div>
        </div>
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
              <b-dropdown-item href="#/showUsers">View All Accounts</b-dropdown-item>
            </b-nav-item-dropdown>
            <b-nav-item href="#/report">Report</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item href="#/">Log Out</b-nav-item>
          </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    

    
    <div class="search" v-if="inventoryItems.length">
      <router-link to="/modifyItems">
      <button class="largeButton">
        Create New +
      </button>
    </router-link>
      <b-input-group 
        size="sm"
        class="mb-3"
        
      > 
        <b-form-input v-model="searchByName" placeholder="Enter Item Name"></b-form-input>
        <b-input-group-append>
          <b-button size="lg" text="Button" variant="success" :class="{'disabled' : !searchByName}" v-bind:disabled="!searchByName" @click="getInventoryItemsByName(searchByName)">Search</b-button>
          <b-button size="lg" text="Button" variant="success" :class="{'disabled' : !searchByName}" v-bind:disabled="!searchByName" @click="getInventoryItems()">Clear</b-button>
        </b-input-group-append>
      </b-input-group>
    </div>

    <div>

      <div v-if="!inventoryItems.length">
        <h2 class="empty">No Items!</h2>
        <router-link to="/modifyItems">
          <button class="mediumButton">
            Create New +
          </button>
        </router-link>
        
      </div>
      
    
      <div class="grid-container" v-if="inventoryItems.length">
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
                      Available Online: {{ inventoryItem.availability }}
                    </li>

                    <li class="info add-item">
                      <button class="button" @click="deleteInventoryItem(inventoryItem.name)" onClick="window.location.reload();">Remove Item</button>
                    </li>
                    <router-link class="info edit" :to="{ name: 'ModifyInventoryItems', params: { nameToEdit: inventoryItem.name, priceToEdit: inventoryItem.price, currentStockToEdit: inventoryItem.currentStock, availabilityToEdit: inventoryItem.availability }}">
                      <a>Edit Item</a>
                    </router-link>
                    
          </ul>
        </div>
    </div>
    
    </div>
  </div>
</template>

<script src="./inventoryItemOwner.js">
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.form-control:focus {
  border-color:#f7a851;
  box-shadow: none;
}

.largeButton {
  float:left;
  margin-left: 10%;
}

.input-group {
  margin-right: 5%;
  float: right;
  width: 30%;
}

.search {
  width: 100%;
  position: fixed;
  margin-top: 90px;
}

.empty {
    margin-top: 270px;
    margin-bottom: 50px;
    color:rgb(177, 177, 177);
}

.item {
  padding-bottom: 30px;
}

.info a{
  font-size: 12px;
  color: black;
  transition: 0.3s;
  margin-top: 10px;
}

.edit {
  margin-top: 20px;
}

.page a {
  font-size: 11px;
}





</style>
