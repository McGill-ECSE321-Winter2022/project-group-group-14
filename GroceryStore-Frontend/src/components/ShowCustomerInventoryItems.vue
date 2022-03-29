<template>
  <div>
  <ul class="navBar">
        <li class="logo">STORIKO</li>
        <li class="page"><a href="#/">Log Out</a></li>  
        <li class="page"><a href="#/viewCart">View Cart</a></li>
        <li class="page"><a href="#/showCustomerInventoryItems">View Items</a></li>
        <!-- <li class="page"><a href="#/viewModifyCustomerGroceryOrders">View Past Grocery Orders</a></li> -->
        <!-- <li class="page"><a href="#/customerPayment">Payment</a></li>         -->
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
                  <button class="button add-item" @click="createInventoryItem(inventoryItem.name,inventoryItem.price,inventoryItem.currentStock)">Add Item</button>
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


</style>
