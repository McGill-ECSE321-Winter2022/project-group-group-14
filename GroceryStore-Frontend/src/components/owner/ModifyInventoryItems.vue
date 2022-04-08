<template>
  <div class="inventory">
    <div class="overlay" v-if="errorInventory">
      <div class="popup">
        <h5>{{ errorInventory }}</h5>
        <!-- <button class="mediumButton" >Close</button> -->
        <router-link to="/showInventoryItemsOwner">
          <button class="mediumButton">
            Show All Items
          </button>
        </router-link>
        <button class="mediumButton" @click="clearError()">Close</button>
        
        
      </div>
    </div>
    <div class="overlay" v-if="successInventory">
      <div class="popup">
        <h5>{{ successInventory }}</h5>
        <!-- <button class="mediumButton" >Close</button> -->
        <router-link to="/showInventoryItemsOwner">
          <button class="mediumButton">
            Show All Items
          </button>
        </router-link>
        <button class="mediumButton" onClick="window.location.reload();">Close</button>
        
        
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
    <div class="bg-color-gradient">
      
        <div class="verticalandhorizontal-center">

        <h2 class="heading">Create / Update Inventory Items</h2>

        <br>

        <h6 class="subheading">Item Name</h6>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="newInventoryItem.name"
            class="form-control no-margin"
            id="floatingInput"
            placeholder="Inventory Item's Name"
            required
          />
        </div>

        <button class="button" v-if="newInventoryItem.name" @click="getInventoryItemsByName(newInventoryItem.name)">auto-fill</button>

        <h6 class="subheading">Item Price</h6>
        <div class="form-floating mb-3">
          <input
            type="number"
            min="1"
            v-model="newInventoryItem.price"
            class="form-control"
            id="floatingInput"
            placeholder="Inventory Item's Price"
            required
          />
        </div>

        <h6 class="subheading">Item Stock Number</h6>
        <div class="form-floating mb-3">
          <input
            type="number"
            min="1"
            v-model="newInventoryItem.currentStock"
            class="form-control"
            id="floatingPassword"
            placeholder="Inventory Item Stock Number"
            required
          />
        </div>

        <h6 class="subheading">Item Image</h6>
        <div class="form-floating mb-3">
          <input
            style="margin-bottom: 0"
            type="text"
            min="1"
            v-model="newInventoryItem.image"
            class="form-control"
            id="floatingPassword"
            placeholder="Inventory Item Image URL"
            required
          />
        </div>
        
        <button class="button" @click="defaultImage()">default image URL</button>

        

        <div style="margin-bottom: 10px" class="form-group form-check">
          <input type="checkbox" class="form-check-input" id="exampleCheck1" v-model="newInventoryItem.availability">
          <label class="form-check-label" for="exampleCheck1">Available Online</label>
        </div>

        <button style="margin-bottom: 0" class="button" v-if="newInventoryItem.price || newInventoryItem.name || newInventoryItem.currentStock || newInventoryItem.image" @click="clear()">Clear All</button>
        <div>
              <button style="margin-top: 40px" class="largeButton" v-bind:disabled="!newInventoryItem.price || !newInventoryItem.name || !newInventoryItem.currentStock" type="CreateButton" @click="createInventoryItem(newInventoryItem.name,newInventoryItem.price,newInventoryItem.currentStock, newInventoryItem.image, newInventoryItem.availability)" :class="{'disabled' : !newInventoryItem.price || !newInventoryItem.name || !newInventoryItem.currentStock}">
                Create Inventory Item
              </button>
              <br>
              <button class="largeButton" v-bind:disabled="!newInventoryItem.price || !newInventoryItem.name || !newInventoryItem.currentStock" type="UpdateButton" @click="updateInventoryItem(newInventoryItem.name,newInventoryItem.price,newInventoryItem.currentStock, newInventoryItem.image , newInventoryItem.availability)" :class="{'disabled' : !newInventoryItem.price || !newInventoryItem.name || !newInventoryItem.currentStock}">
                Update Inventory Item
              </button>
              <button class="largeButton" v-bind:disabled="!newInventoryItem.name" type="DeleteButton" @click="deleteInventoryItem(newInventoryItem.name)" :class="{'disabled' : !newInventoryItem.name}">
                Delete Inventory Item
              </button>
        </div>
        
      </div>
    </div>
        
  </div>
</template>
<script src="./modifyInventoryItem.js">
</script>
<style scoped>

.form-group {
  margin-bottom: 30px;
}

.no-margin {
  margin-bottom: 0;
}


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

    .form {
        margin-top: 140px;
        margin-left: 550px;
        margin-right: 550px;
        margin-bottom: 20px;
        display: grid;
        row-gap: 10px;
        column-gap: 90px;
        grid-template-columns: auto;
    }

    .button {
      margin-bottom: 30px;
    }

    .mediumButton {
      margin: 2%;
    }

    label {
        font-size: 18px;
        margin-bottom: 0;
        font-weight: 700;
    }


    input {
        border: 1px solid black;
        border-radius: 4px;
        text-align: center;
        margin-bottom: 30px;
    }


.bg-color-gradient {
  height: 100vh;
}
    
  
</style>