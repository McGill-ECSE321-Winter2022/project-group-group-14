<template>
  <div class="inventory">
    
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
        
    </ul>
    <div>
        <div class="form">
            
                <label for="iname">Name</label>
                <input type="text" id="iname" name="name" placeholder="Enter Inventory Item Name">
            
            
                <label for="iprice">Price</label>
                <input type="number" id="iprice" name="price" placeholder="Enter Inventory Item Price">
            
            
                <label for="istock">Stock Number</label>
                <input type="number" id="istock" name="currentStock" placeholder="Enter Inventory Item Stock">
        </div>

        <button class="button" @click="createInventoryItem(inventoryItem.name,inventoryItem.price,inventoryItem.currentStock)">Create Inventory Item</button>
        <button class="button">Update Inventory Item</button>
    </div>
  </div>
</template>

<style scoped>

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
        font-size: 17px;
        width: fit-content;
        margin: 30px;
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