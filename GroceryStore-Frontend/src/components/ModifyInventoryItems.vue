<template>
  <div class="inventory">
  
    <div>
        <label for="iname">Item Name</label>
        <input type="text" id="iname" name="name" placeholder="Enter Name">
    </div>
    <div>
        <label for="iprice">Item Price</label>
        <input type="text" id="iprice" name="price" placeholder="Enter Price">
    </div>
    <div>
        <label for="istock">Item Stock</label>
        <input type="text" id="istock" name="currentStock" placeholder="Enter Stock">
    </div>

    <button class="button" @click="createInventoryItem(inventoryItem.name,inventoryItem.price,inventoryItem.currentStock)">Add Item</button>
  </div>
</template>

<style>
    .inventory {
        float: left;
        margin-left: 40px;
        margin-top: 100px;
        width: 70%;
    
    }

    .inventory div {
        margin-bottom: 50px;
    }



    input[type=text] {
        border: 1px solid black;
        border-radius: 4px;
    }

    .button {
        float: right;
    }
  
</style>