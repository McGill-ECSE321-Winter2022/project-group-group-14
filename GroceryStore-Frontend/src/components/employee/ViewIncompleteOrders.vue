<template>
    <div >
         <b-navbar fixed="top" >
            <router-link to="/employeeWelcomePage">
                <b-navbar-brand>STORIKO</b-navbar-brand>
            </router-link>
           
                <b-navbar-nav class="ml-auto">
                    <b-nav-item href="#/">Log Out</b-nav-item>
                </b-navbar-nav>
        </b-navbar>

        <div class="verticalandhorizontal-center">
            <h2 class="heading">List all incomplete orders here! </h2>
            <br>
            <br>
                <h4> Select an order from the list to view items and update order status </h4>
        </div>
    </div>
</template>

<style scoped>
</style>