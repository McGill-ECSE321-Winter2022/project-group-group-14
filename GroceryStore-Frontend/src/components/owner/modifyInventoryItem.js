import axios from 'axios'
import OwnerNavigationBar from './OwnerNavigationBar.vue';

var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})  
  

export default {
name: 'inventoryitem',
components: {OwnerNavigationBar},
data () {
    return {
    inventoryItems: [],
    newInventoryItem: {
      name: this.$route.params.nameToEdit,
      price: this.$route.params.priceToEdit,
      currentStock: this.$route.params.currentStockToEdit,
      availability: this.$route.params.availabilityToEdit,
      image: this.$route.params.imageToEdit
    },
    response: []
    }
},
created: function () {
// Initializing inventory items from backend
    AXIOS.get('/inventoryItems/get')
    .then(response => {
        this.inventoryItems = response.data
    })
    .catch(e => {
        var errorMsg = e.response.data
        alert(errorMsg)
    })
    
},

methods: {
    createInventoryItem: function (itemName,itemPrice,itemStock,itemImage,itemAvailability) {
      
      if(itemAvailability==null){
        itemAvailability='false'
      }
        AXIOS.post('/inventoryItems/create/'.concat(itemName), {}, {params: {price: itemPrice, currentStock: itemStock, image: itemImage, availability: itemAvailability}})
          .then(response => {
            this.inventoryItems.push(response.data)
            this.newInventoryItem = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
          })
      },
      updateInventoryItem: function (itemName,itemPrice,itemStock,itemImage,itemAvailability) {
        if(itemAvailability==null){
          itemAvailability='false'
        }
        AXIOS.put('/inventoryItems/update/'.concat(itemName), {}, {params: {price: itemPrice, currentStock: itemStock, image: itemImage, availability: itemAvailability}})
          .then(response => {
            this.inventoryItems.push(response.data),
            this.newInventoryItem = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
          })
      },
      deleteInventoryItem: function (itemName) {
        AXIOS.delete('/inventoryItems/delete/'.concat(itemName), {}, {})
          .then(response => {
            this.newInventoryItem = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
          })
      },
      getInventoryItemsByName: function (itemName) {
        AXIOS.get('/inventoryItems/getByName/'.concat(itemName), {}, {})
          .then(response => {
            this.newInventoryItem = response.data
          })
          .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
          })
      },
      defaultImage: function () {
        this.newInventoryItem.image = "https://transpower.ca/wp-content/themes/consultix/images/no-image-found-360x250.png"
      },
      clear: function () {
        this.newInventoryItem.name=''
        this.newInventoryItem.price=''
        this.newInventoryItem.currentStock=''
        this.newInventoryItem.availability=''
        this.newInventoryItem.image=''
      }


},
}

  