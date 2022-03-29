<template>
    <div >
        <ul class="navBar">
            <li class="logo">STORIKO</li>    
            <li class="page"><a href="#/">Log Out</a></li>    
        </ul>

        <div class="verticalandhorizontal-center">
            <h2 class="heading">Welcome Employee! </h2>

                    <router-link to="/showEmployeeInventoryItems">
                        <button class="largeButton">
                            Make an order
                        </button>
                    </router-link>

                    <br>

                    <router-link to="/viewStoreEmployeeScheduleFromEmployee">
                        <button class="largeButton">
                            Something else
                        </button>
                    </router-link>
        
        </div>
    </div>
</template>

<style scoped>
</style>