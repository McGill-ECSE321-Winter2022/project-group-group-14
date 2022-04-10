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
    employees: [],
    newEmployeeSchedule: {
        shift: '',
        day: '',
        employee: ''
      },
    errorEmployee: '',
    response: []
    }
},

created: function () {
    // Initializing schedules from backend
        AXIOS.get('/employeeSchedules/')
        .then(response => {
            // JSON responses are automatically parsed.
            this.employeeSchedules = response.data
        })
        .catch(e => {
            this.errorEmployee = e
        }),
        AXIOS.get('/employees/login/getAll')
        .then(response => {
            // JSON responses are automatically parsed.
            this.employees = response.data
            console.log(response.data)
        })
        .catch(e => {
            this.errorEmployee = e
        })
},

methods: {
    createEmployeeSchedule: function (shift, day, employeeUsername) {
        AXIOS.post('/employeeSchedules/create/'.concat(day), {}, {params: {shift: shift, employeeUsername: employeeUsername}})
          .then(response => {
          // JSON responses are automatically parsed.
            this.employeeSchedules.push(response.data)
            this.errorEmployee = 'Employee schedule added!'
            this.newEmployeeSchedule = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            console.log(errorMsg)
            this.errorEmployee = errorMsg
          })
      },
      deleteEmployeeSchedule: function (day) {
        AXIOS.delete('/employeeSchedules/delete/'.concat(day), {}, {})
          .then(response => {
          // JSON responses are automatically parsed.
            // this.storeSchedules
            this.errorEmployee = 'Employee schedule deleted!'
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
