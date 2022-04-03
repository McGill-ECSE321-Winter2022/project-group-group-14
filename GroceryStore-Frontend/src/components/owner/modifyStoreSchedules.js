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
name: 'storeschedule',
data () {
    return {
    storeSchedules: [],
    newStoreSchedule: {
        openingTime: '',
        closingTime: '',
        dayOpen: ''
      },
    errorSchedule: '',
    response: []
    }
},

created: function () {
    // Initializing schedules from backend
        AXIOS.get('/storeSchedules/get')
        .then(response => {
            // JSON responses are automatically parsed.
            this.storeSchedules = response.data
        })
        .catch(e => {
            this.errorSchedule = e
        })
        
    },

methods: {
    createStoreSchedule: function (openTime,closeTime,day) {
        AXIOS.post('/storeSchedules/create/'.concat(day), {}, {params: {openingTime: openTime, closingTime: closeTime}})
          .then(response => {
          // JSON responses are automatically parsed.
            this.storeSchedules.push(response.data)
            this.errorSchedule = day + ' is created successfully!'
            this.newStoreSchedule = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            console.log(errorMsg)
            this.errorSchedule = errorMsg
          })
      },
      updateStoreSchedule: function (openTime,closeTime,day) {
        AXIOS.put('/storeSchedules/update/'.concat(day), {}, {params: {openingTime: openTime, closingTime: closeTime}})
          .then(response => {
          // JSON responses are automatically parsed.
            this.storeSchedules.push(response.data)
            this.errorSchedule = day + ' is updated successfully!'
            this.newStoreSchedule = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            console.log(errorMsg)
            this.errorSchedule = errorMsg
          })
      },
      deleteStoreSchedule: function (day) {
        AXIOS.delete('/storeSchedules/delete/'.concat(day), {}, {})
          .then(response => {
          // JSON responses are automatically parsed.
            this.errorSchedule = day + ' is deleted successfully!'
            this.newStoreSchedule = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            console.log(errorMsg)
            this.errorSchedule = errorMsg
          })
      }


}
//...
}
