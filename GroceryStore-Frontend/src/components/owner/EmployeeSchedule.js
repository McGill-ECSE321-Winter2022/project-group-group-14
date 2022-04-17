import axios from 'axios'

import OwnerNavigationBar from './OwnerNavigationBar.vue';

var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})  
  

export default {
name: 'employeeschedule',
components: { OwnerNavigationBar },
data () {
    return {
    employeeSchedules: [],
    employees: [],
    newEmployeeSchedule: {
        shift: '',
        day: '',
        employee: ''
      },
    response: []
    }
},

created: function () {
    // Initializing schedules from backend
        AXIOS.get('/employeeSchedules/')
        .then(response => {
            this.employeeSchedules = response.data
        })
        .catch(e => {
          var errorMsg = e.response.data
          alert(errorMsg)
        }),
        AXIOS.get('/employees/login/getAll')
        .then(response => {
            this.employees = response.data
        })
        .catch(e => {
          var errorMsg = e.response.data
          alert(errorMsg)
        })
},

methods: {
    createEmployeeSchedule: function (shift, day, employeeUsername) {
        AXIOS.post('/employeeSchedules/create/'.concat(day), {}, {params: {shift: shift, employeeUsername: employeeUsername}})
          .then(response => {
            this.employeeSchedules.push(response.data)
            this.newEmployeeSchedule = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
          })
      },
      deleteEmployeeSchedule: function (day) {
        AXIOS.delete('/employeeSchedules/delete/'.concat(day), {}, {})
          .then(response => {
            this.newEmployeeSchedule = ''
          })
          .catch(e => {
            var errorMsg = e.response.data
            alert(errorMsg)
          })
      }


}
//...
}
