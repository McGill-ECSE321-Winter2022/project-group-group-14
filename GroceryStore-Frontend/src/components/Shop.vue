<template>
  <div class="shop">
  <ul class="navBar">
      <li class="logo">STORIKO</li>
      <li class="page"><a href="#/storeschedule">Store Schedule</a></li>
      <li class="page"><a href="">Delete Account</a></li>
      <li class="page"><a href="">Update Account</a></li>  
    </ul>
    <div class="grid-container">
      <div class="grid-item" v-for="inventoryItem in inventoryItems" :key=inventoryItem.name>
      <ul class="item">
                <li class="info">

                </li>
                <li class="info">
                  {{ inventoryItem.name }}
                </li>
                <li class="info">
                  ${{ inventoryItem.price }}.00
                </li>
                <li class="info">
                  Stock: {{ inventoryItem.currentStock }}
                </li>
                <li class="info">
                  <button class="button" @click="createInventoryItem(inventoryItem.name,inventoryItem.price,inventoryItem.currentStock)">Add Item</button>
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

.grid-item{
  margin: 10px;
}

.grid-container {
  display: grid;
  row-gap: 50px;
  column-gap: 50px;
  grid-template-columns: auto auto;
}
</style>
