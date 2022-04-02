import axios from 'axios'
// var config = require('../../../config')

var frontendUrl = process.env.FRONTEND_HOST + ':' + process.env.FRONTEND_PORT
var backendUrl = process.env.BACKEND_HOST + ':' + process.env.BACKEND_PORT

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
  })  

export default {
    name: 'customerAccount',
    data () {
        return {
            customers: [],
            newCustomerAccount: {
                email: '',
                username: '',
                password: '',
                address: '',
                phoneNumber: ''
            },
            errorCustomer: '',
            reponse: []
        }
    },

    // created: function(){
    //     AXIOS.post('/customers/youssof@gmail.com/youssof5/123Abc/1111stavenue/5148888888')
    //     .then(response => {
    //         this.customers = response.data
    //     })
    //     .catch(e => {
    //         this.errorCustomer = e
    //     })
    // },

    methods: {
        createCustomerAccount: function (newEmail, newUsername, newPassword, newAddress, newPhoneNumber){
            AXIOS.post('/customers/'.concat(newEmail).concat('/').concat(newUsername).concat('/').concat(newPassword).concat('/').concat(newAddress).concat('/').concat(newPhoneNumber),{},{})
            .then(response => {
                this.customers.push(response.data)
                this.errorCustomer = newEmail + ' is created successfully!'
                this.newCustomerAccount = ''
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorCustomer = errorMsg
            })
        }
    }
}

