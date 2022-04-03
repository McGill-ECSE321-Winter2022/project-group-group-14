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
  


export default{

    data()
    {
        return {
            email:this.$route.params.email,
            orders: [],
            customers:[],
            newGroceryOrder: {
                orderId:'',
                totalCost:'',
                orderType:'',
                orderStatus:'',
                orderItems: [],
                customer:''
            }, 
            errorOrder: '',
            successMsg:'',
            response: []
        }
    },
    methods: {
        createInstoreOrder : function(){
            AXIOS.post('/orders/inStore', {}, {})
            .then(response => {
                this.orders.push(response.data) //add dto to the list of orders
                this.successMsg = 'Order has been successfully created! Please navigate to the list of inventory items : '
                this.errorOrder = ''
                console.log(this.successMsg)
                this.newGroceryOrder = ''
            })
            .catch(e => {
                this.successMsg = ''
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorOrder = errorMsg
            })

        }
        
    }
}