import axios from 'axios'
import OwnerNavigationBar from './OwnerNavigationBar.vue';

var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})  
  

export default {
name: 'storeschedule',
components: { OwnerNavigationBar },
data () {
    return {
    storeSchedules: [],
    newStoreSchedule: {
        openingTime: '',
        closingTime: '',
        dayOpen: ''
      },
    response: []
    }
},

created: function () {
    // Initializing schedules from backend
        AXIOS.get('/storeSchedules/get')
        .then(response => {
            this.storeSchedules = response.data
        })
        .catch(e => {
          var errorMsg = e.response.data
          alert(errorMsg)
        })
        
    },

methods: {
    createStoreSchedule: function (openTime,closeTime,day) {
        AXIOS.post('/storeSchedules/create/'.concat(day), {}, {params: {openingTime: openTime, closingTime: closeTime}})
          .then(response => {
            this.storeSchedules.push(response.data)
            this.newStoreSchedule = ''
          })
          .catch(e => {var errorMsg = e.response.data
            alert(errorMsg)
          })
      },
      updateStoreSchedule: function (openTime,closeTime,day) {
        AXIOS.put('/storeSchedules/update/'.concat(day), {}, {params: {params: {openingTime: openTime, closingTime: closeTime}}})
          .then(response => {
            this.storeSchedules.push(response.data)
            this.newStoreSchedule = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
          })
      },
      deleteStoreSchedule: function (day) {
        AXIOS.delete('/storeSchedules/delete/'.concat(day), {}, {})
          .then(respons => {
            this.newStoreSchedule = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
          })
      }


  }
}
