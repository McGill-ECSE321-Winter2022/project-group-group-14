<template>
    <div >
        <ul class="navBar">
            <li class="logo">STORIKO</li>      
            <li class="page"><a href="#/">Log Out</a></li>  
        </ul>

        <div class="verticalandhorizontal-center">
            <h2 class="heading">Welcome Owner! </h2>
                    <br>
                    <router-link to="/viewStoreScheduleOwner">
                        <button class="largeButton">
                            View Store Schedule
                        </button>
                    </router-link>
                    <br>
                    <br>
                    <router-link to="/modifyStoreSchedule">
                        <button class="largeButton">
                            Modify Store Schedule
                        </button>
                    </router-link>
                    <br>
                    <br>
                    <router-link to="/employeeSchedules">
                        <button class="largeButton">
                            Employee schedules
                        </button>
                    </router-link>
                    <br>
                    <br>
                    <router-link to="/deleteOwnerAccount">
                        <button class="largeButton">
                            Delete an account
                        </button>
                    </router-link>
                    <br>
                    <br>
                    <router-link to="/modifyCustomers">
                        <button class="largeButton">
                            Modify a customer account
                        </button>
                    </router-link>
                    <br>
                    <br>
                    <router-link to="/modifyEmployees">
                        <button class="largeButton">
                            Modify an employee account
                        </button>
                    </router-link>
                    <br>
                    <br>
                    <router-link to="/modifyItems">
                        <button class="largeButton">
                            Modify inventory item
                        </button>
                    </router-link>
                    <br>
                    <br>
                    <router-link to="/showInventoryItemsOwner">
                        <button class="largeButton">
                            View inventory items
                        </button>
                    </router-link>
                    <br>
                    <br>
                    <router-link to="/report">
                        <button class="largeButton">
                            View Report
                        </button>
                    </router-link>
                    
        
        </div>
    </div>
</template>

<style scoped>
</style>