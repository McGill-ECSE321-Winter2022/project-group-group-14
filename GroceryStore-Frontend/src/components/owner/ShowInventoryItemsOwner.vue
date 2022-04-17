<template>
  <div>
    
    <owner-navigation-bar></owner-navigation-bar>
    

    
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
                    
                    <li class="info item-name">
                      {{ inventoryItem.name }}
                    </li>
                    <li class="info">
                      <img class="item-image" :src="inventoryItem.image" alt="">
                    </li>
                    <li class="info">
                      ${{ inventoryItem.price }}.00
                    </li>
                    <li class="info">
                      Stock: {{ inventoryItem.currentStock }}
                    </li>
                    <li v-if="inventoryItem.availability" class="info">
                      Available
                    </li>
                    <li v-if="!inventoryItem.availability" class="info">
                      Not Available
                    </li>

                    <li class="info add-item">
                      <button class="button" @click="deleteInventoryItem(inventoryItem.name)" onClick="window.location.reload();">Remove Item</button>
                    </li>
                    <router-link class="info edit" :to="{ name: 'ModifyInventoryItems', params: { nameToEdit: inventoryItem.name, priceToEdit: inventoryItem.price, currentStockToEdit: inventoryItem.currentStock, imageToEdit: inventoryItem.image, availabilityToEdit: inventoryItem.availability }}">
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
  padding-top: 82px;
  background-color:white;
  box-shadow: 0px 0px 9px 1px rgba(122, 122, 122, 0.4);
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
