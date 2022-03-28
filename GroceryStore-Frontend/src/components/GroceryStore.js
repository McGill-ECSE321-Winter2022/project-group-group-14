// function InventoyItemDto (name,price,currentStock) {
//     this.name = name
//     this.price = price
//     this.currentStock = currentStock
//   }
  
  function OrderItemDto (name) {
    this.name = name
  }

  export default {
    name: 'grocerystore',
    data () {
      return {
        orderItems: [],
        newOrderItem: '',
        errorPerson: '',
        response: []
      }
    },
    created: function () {
        // Test data
        const o1 = new OrderItemDto('batates')
        const o2 = new OrderItemDto('fasolia')
        // Sample initial content
        this.orderItems = [o1, o2]
    },
    methods: {
        createPerson: function (orderItemName) {
          // Create a new person and add it to the list of people
          var o = new OrderItemDto(orderItemName)
          this.orderItems.push(o)
          // Reset the name field for new people
          this.neworderItem = ''
        }
    }
    //...
  }

  