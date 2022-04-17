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
components: { OwnerNavigationBar },
data () {
    return {
    inventoryItems: [],
    searchByName: '',
    newInventoryItem: {
      name: '',
      price: '',
      currentStock: '',
      availability: ''
    }, 
    response: []
    }
},
created: function () {
// Initializing persons from backend
    AXIOS.get('/inventoryItems/get')
    .then(response => {
        // JSON responses are automatically parsed.
        this.inventoryItems = response.data
    })
    .catch(e => {
      var errorMsg = e.response.data
      alert(errorMsg)
    })
    
},

methods: {
  
  getInventoryItems: function () {
    AXIOS.get('/inventoryItems/get/', {}, {})
      .then(response => {
        this.inventoryItems = response.data,
        this.searchByName = '',
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
          this.inventoryItems = [],
          this.inventoryItems.push(response.data),
          this.newInventoryItem = ''
        })
        .catch(e => {
          var errorMsg = e.response.data
          alert(errorMsg)
        })
    },
    createInventoryItem: function (itemName,itemPrice,itemStock) {
        AXIOS.post('/inventoryItems/create/'.concat(itemName), {}, {params: {price: itemPrice, currentStock: itemStock}})
          .then(response => {
            this.inventoryItems.push(response.data)
            this.newInventoryItem = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
          })
      },
      updateInventoryItem: function (itemName,itemPrice,itemStock) {
        AXIOS.put('/inventoryItems/update/'.concat(itemName), {}, {params: {price: itemPrice, currentStock: itemStock}})
          .then(response => {
            this.inventoryItems.push(response.data)
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
      }


}
}

  