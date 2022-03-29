function InventoryItemDto (name,price,currentStock) {
  this.name = name
  this.price = price
  this.currentStock = currentStock
}


export default {
name: 'inventoryitem',
data () {
  return {
  inventoryItems: [],
  newInventoryItem: '',
  errorInventory: '',
  response: []
  }
},

created: function () {
  // Test data
  const i1 = new InventoryItemDto('Batates',15,23)
  const i2 = new InventoryItemDto('Fasolia',7,22)
  const i3 = new InventoryItemDto('Betengan',15,23)
  const i4 = new InventoryItemDto('Karafs',7,22)
  const i5 = new InventoryItemDto('Molto',15,23)
  const i6 = new InventoryItemDto('Leban',7,22)
  const i7 = new InventoryItemDto('Salsa',15,23)
  const i8 = new InventoryItemDto('Tuna',7,22)
  const i9 = new InventoryItemDto('Batates',15,23)
  const i10 = new InventoryItemDto('Fasolia',7,22)
  const i11 = new InventoryItemDto('Betengan',15,23)
  const i12 = new InventoryItemDto('Karafs',7,22)
  const i13 = new InventoryItemDto('Molto',15,23)
  const i14 = new InventoryItemDto('Leban',7,22)
  const i15 = new InventoryItemDto('Salsa',15,23)
  const i16 = new InventoryItemDto('Tuna',7,22)
  // Sample initial content
  this.inventoryItems = [i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16]
},
methods: {
  createInventoryItem: function (itemName,itemPrice,itemStock) {
      // Create a new person and add it to the list of people
      var i = new InventoryItemDto(itemName,itemPrice,itemStock)
      this.inventoryItems.push(i)
      // Reset the name field for new people
      this.newInventoryItem = ''
  }

}
//...
}

