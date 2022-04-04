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
name: 'employeeschedule',
data () {
    return {
    employeeSchedules: [],
    newEmployeeSchedule: {
        shift: '',
        day: ''
      },
    errorEmployee: '',
    response: []
    }
},

created: function () {
    // Initializing schedules from backend
        AXIOS.get('/employeeSchedules/get')
        .then(response => {
            // JSON responses are automatically parsed.
            this.employeeSchedules = response.data
        })
        .catch(e => {
            this.errorEmployee = e
        })
        
    },

methods: {
    createEmployeeSchedule: function (shift, day) {
        AXIOS.post('/employeeSchedules/'.concat(day), {}, {params: {shift: newshift}})
          .then(response => {
          // JSON responses are automatically parsed.
            this.employeeSchedules.push(response.data)
            this.errorEmployee = ''
            this.newEmployeeSchedule = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            console.log(errorMsg)
            this.errorEmployee = errorMsg
          })
      },
      updateEmployeeSchedule: function (shift,day) {
        AXIOS.put('/employeeSchedules/update/'.concat(day), {}, {params: {params: {shift: newshift}}})
          .then(response => {
          // JSON responses are automatically parsed.
            this.employeeSchedules.push(response.data)
            this.errorEmployee = ''
            this.newEmployeeSchedule = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            console.log(errorMsg)
            this.errorSchedule = errorMsg
          })
      },
      deleteEmployeeSchedule: function (day) {
        AXIOS.delete('/employeeSchedules/delete/'.concat(day), {}, {})
          .then(response => {
          // JSON responses are automatically parsed.
            // this.storeSchedules
            this.errorEmployee = ''
            this.newEmployeeSchedule = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            console.log(errorMsg)
            this.errorEmployee = errorMsg
          })
      }


}
//...
}
