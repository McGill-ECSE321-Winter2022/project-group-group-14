import axios from 'axios'
var config = require('../../../config')

var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT

// var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
// var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})  
  

export default {
name: 'inventoryitem',
data () {
    return {
    inventoryItems: [],
    newInventoryItem: {
      name: '',
      price: '',
      currentStock: ''
    }, 
    errorInventory: '',
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
        this.errorInventory = e
    })
    
},

methods: {
    createInventoryItem: function (itemName,itemPrice,itemStock) {
        AXIOS.post('/inventoryItems/create/'.concat(itemName), {}, {params: {price: itemPrice, currentStock: itemStock}})
          .then(response => {
          // JSON responses are automatically parsed.
            this.inventoryItems.push(response.data)
            this.errorInventory = itemName + ' is created successfully!'
            this.newInventoryItem = ''
          })
          .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorInventory = errorMsg
          })
      },
      updateInventoryItem: function (itemName,itemPrice,itemStock) {
        AXIOS.put('/inventoryItems/update/'.concat(itemName), {}, {params: {price: itemPrice, currentStock: itemStock}})
          .then(response => {
          // JSON responses are automatically parsed.
            this.inventoryItems.push(response.data)
            this.errorInventory = itemName + ' is updated successfully!'
            this.newInventoryItem = ''
          })
          .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorInventory = errorMsg
          })
      },
      deleteInventoryItem: function (itemName) {
        AXIOS.delete('/inventoryItems/delete/'.concat(itemName), {}, {})
          .then(response => {
          // JSON responses are automatically parsed.
            // this.inventoryItems
            this.errorInventory = itemName + ' is deleted successfully!'
            this.newInventoryItem = ''
          })
          .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorInventory = errorMsg
          })
      }


},
}

  