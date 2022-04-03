<template>
  <div class="storeschedule">
    <div id="popup1" class="overlay" v-if="errorSchedule">
          <div class="popup">
            <h5>{{ errorSchedule }}</h5>
            <!-- <button class="mediumButton" >Close</button> -->
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

        <h2 class="heading">Modify Store Schedule</h2>

        <br>
        
        <br>

        <h6 class="subheading">Select the day you want to modify:</h6>
        <div class="form-floating mb-3">
            <input class="form-control" list="days" id="floatingInput" placeholder="e.g. Monday"
            v-model="newStoreSchedule.dayOpen" required>
            <datalist id="days">
              <option value="Sunday"></option>
              <option value="Monday"></option>
              <option value="Tuesday"></option>
              <option value="Wednesday"></option>
              <option value="Thursday"></option>
              <option value="Friday"></option>
              <option value="Saturday"></option>
              </datalist>
        </div>

        <h6 class="subheading">Select the new opening time:</h6>
        <div class="form-floating mb-3">

          <input class="form-control" list="times" id="floatingInput" placeholder="e.g. 09:30"
          v-model="newStoreSchedule.openingTime" required>
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
        </div>

        <h6 class="subheading">Select the new closing time:</h6>
        <div class="form-floating mb-3">
          <input class="form-control" list="times" id="floatingInput" placeholder="e.g. 18:00" 
          v-model="newStoreSchedule.closingTime" required>
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
        </div>
        <div>

           
              <button class="largeButton" v-bind:disabled="!newStoreSchedule.openingTime || !newStoreSchedule.closingTime || !newStoreSchedule.dayOpen" type="CreateButton" @click="createStoreSchedule(newStoreSchedule.openingTime, newStoreSchedule.closingTime, newStoreSchedule.dayOpen)">
                Create Day Schedule
              </button>
              <button class="largeButton" v-bind:disabled="!newStoreSchedule.openingTime || !newStoreSchedule.closingTime || !newStoreSchedule.dayOpen" type="CreateButton" @click="updateStoreSchedule(newStoreSchedule.openingTime, newStoreSchedule.closingTime, newStoreSchedule.dayOpen)">
                Update Day Schedule
              </button>
              <button class="largeButton" type="DeleteButton" v-bind:disabled="!newStoreSchedule.dayOpen" @click="deleteStoreSchedule(newStoreSchedule.dayOpen)">
                Delete Day Schedule
              </button>

        </div>

    </div>
  </div>
</div>
</template>

<script src="./modifyStoreSchedules.js">
</script>


<style scoped>

.overlay {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.7);
  transition: opacity 500ms;
  opacity: 100%;
  z-index: 100;
}

.popup {
  margin: auto;
  margin-top: 40vh;
  padding: 20px;
  background: #fff;
  border-radius: 5px;
  width: 30%;
  transition: all 5s ease-in-out;
}

h1, h2 {
  font-weight: normal;
}

.page a {
  font-size: 13px;
}

.verticalandhorizontal-center {
    padding: 2% 6% 2% 6%;
    background-color: white;
    border-radius: 4%;
    margin-top: 1%;
    box-shadow: 0 0 10px 7px rgb(0,0,0,0.3);
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
        font-size: 17px;
        width: fit-content;
        margin: 30px;
    }

    .mediumButton {
      margin: 0;
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
</style>
