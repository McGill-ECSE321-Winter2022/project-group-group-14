package ca.mcgill.ecse321.grocerystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.CustomerDto;
import ca.mcgill.ecse321.grocerystore.dto.GroceryOrderDto;
import ca.mcgill.ecse321.grocerystore.dto.OrderItemDto;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderType;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;
import ca.mcgill.ecse321.grocerystore.service.CustomerService;
import ca.mcgill.ecse321.grocerystore.service.GroceryOrderService;
import ca.mcgill.ecse321.grocerystore.service.InventoryItemService;
import ca.mcgill.ecse321.grocerystore.service.OrderItemService;

import java.util.List;
import java.util.ArrayList;


/**
 * 
 * @author clarissabaciu
 *
 */

@CrossOrigin(origins = "*")
@RestController
public class GroceryOrderRestController {
	
	@Autowired
	private GroceryOrderService orderService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private InventoryItemService inventoryItemService;

 //-------------------------------------------------------CREATE GROCERY ORDERS (POST MAPPINGS)------------------------------------------------------------
	/**
	 * 
	 * @param email
	 * @return created delivery order dto or error as response entities
	 * @throws IllegalArgumentException
	 * 
	 */
	@PostMapping(value = { "/orders/delivery/{email}", "/orders/delivery/{email}/" })
	public ResponseEntity<?> createDeliveryOrder(@PathVariable  String email) throws IllegalArgumentException  {
		try {
			Customer customer = customerService.getCustomerByEmail(email);			//retrieve customer using email
			GroceryOrder order = orderService.createDeliveryOrder(customer);		//call service method create a delivery grocery order using the queried customer				
			return ResponseEntity.ok(convertToDto(order)); 		//return converted order as a response entity
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());		//return error as a response entity in case of failure
		}
	}

	/**
	 * 
	 * @param email
	 * @return created pickup order dto or error as response entities 
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/orders/pickup/{email}", "/orders/pickup/{email}/" })
	public ResponseEntity<?>  createPickupOrder(@PathVariable  String email) throws IllegalArgumentException  {
		try {
			Customer customer = customerService.getCustomerByEmail(email);			//retrieve customer using email
			GroceryOrder order = orderService.createPickupOrder(customer);			//call service method to create a pick up order
			return ResponseEntity.ok(convertToDto(order)); 
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	 /**
	  * 
	  * @return inStore grocery order DTO
	  * @throws IllegalArgumentException
	  */
	@PostMapping(value = { "/orders/inStore", "/orders/inStore/" })
	public ResponseEntity<?>  createInstoreOrder() throws IllegalArgumentException  {
		try {
			GroceryOrder orderInStore = orderService.createInStoreOrder();
			return ResponseEntity.ok(new GroceryOrderDto(orderInStore.getOrderId(),orderInStore.getTotalCost(), orderInStore.getOrderType().toString(),orderInStore.getOrderStatus().toString()));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//-------------------------------------------------------ADD ITEMS TO ORDER (POST MAPPING) ------------------------------------------------------------	

	/**
	 * 
	 * @param orderId
	 * @param itemName
	 * @param quantity
	 * @return grocery order DTO with newly added items
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/orders/add/{orderId}/", "/orders/add/{orderId}" })
	public ResponseEntity<?>  addOrderItems(@PathVariable  String orderId,@RequestParam String itemName, @RequestParam int quantity) throws IllegalArgumentException  {
		try {
			GroceryOrder order = orderService.getOrderById(Integer.parseInt(orderId)); 	//retrieve grocery order from database
			List<OrderItem> orderItems = new ArrayList<OrderItem>();		
			InventoryItem inventoryItem = inventoryItemService.getInventoryItemByName(itemName);	//get inventory item with the name of the item we want to add
			if(inventoryItem == null) throw new IllegalArgumentException("No Item with that name exists.");	
			if(inventoryItem.getCurrentStock()<quantity) throw new IllegalArgumentException("No Enough Items in stock."); //make sure quantity is less or equal to stock available
			for (int i = 0 ; i<quantity ;i++ ) {	//loop over everything to create order items
				OrderItem orderItem = orderItemService.createOrderItem(itemName);			//create a number of order items and add them to a list according to the quantity specified				
				orderItems.add(orderItem);
			}
			GroceryOrder updatedOrder = orderService.addOrderItems(order,orderItems);	//call service method to add the list of order items to grocery order
			return ResponseEntity.ok(convertToDto(updatedOrder));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	//-------------------------------------------------------FINISHED ADDING ITEMS TO ORDER--------------------------------------	
	
	/**
	 * 
	 * @param orderId
	 * @return should change order from received -> processing
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/orders/place/{orderId}/", "/orders/place/{orderId}" }) 
	public ResponseEntity<?>  placeOrder(@PathVariable  String orderId) throws IllegalArgumentException  {
		try {
			GroceryOrder order = orderService.getOrderById(Integer.parseInt(orderId)); 	//retrieve order
			GroceryOrder placedOrder = orderService.placeOrder(order);	//call service method to place the order, status : received -> processing
			return ResponseEntity.ok(convertToDto(placedOrder));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//-------------------------------------------------------GET MAPPINGS------------------------------------------------------------

	/**
	 * 
	 * @param orderId
	 * @return orderItems in an order
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value = { "/orders/orderItems/{orderId}", "/orders/orderItems/{orderId}/" })
	public ResponseEntity<?> getOrderItems(@PathVariable("orderId") String orderId) throws IllegalArgumentException {
		try {
			GroceryOrder order = orderService.getOrderById(Integer.parseInt(orderId)); 
			return ResponseEntity.ok(convertToDtos(orderService.getOrderItems(order)));	//return orderItems in retrieved gorcery order	
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
	}

	/**
	 * 
	 * @return all orders in system
	 */
	@GetMapping(value = { "/orders", "/orders/" })
	public ResponseEntity<?>  getAllOrders() {
		try {
			List<GroceryOrderDto> orderDtos = new ArrayList<GroceryOrderDto>(); 
			for (GroceryOrder modelOrder : orderService.getAllOrders()) {
				orderDtos.add(convertToDto(modelOrder));
			}
			return ResponseEntity.ok(orderDtos);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	/**
	 * @param orderId
	 * @returns orders by order id
	 */
	@GetMapping(value = {"/orders/{orderId}","/orders/{orderId}/ "})
	public ResponseEntity<?>  getOrderById(@PathVariable("orderId") String orderId) throws IllegalArgumentException{
		try {
			GroceryOrder order = orderService.getOrderById(Integer.parseInt(orderId)); //retrieve order by id
			return ResponseEntity.ok(convertToDto(order));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	/**
	 * 
	 * @param email
	 * @return order DTOs belonging to this customer
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value =  { "/orders/customer/{email}", "/orders/customer/{email}/" })
	public ResponseEntity<?>  getOrdersByCustomer(@PathVariable("email") String email) throws IllegalArgumentException {
		try{
			Customer customer = customerService.getCustomerByEmail(email);	 		
			List<GroceryOrder> orders = orderService.getOrdersByCustomer(customer);
			List<GroceryOrderDto> orderDtos = new ArrayList<GroceryOrderDto>(); 
			for (GroceryOrder o: orders) {
				orderDtos.add(convertToDto(o));
			}
			return ResponseEntity.ok(orderDtos);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param email
	 * @return order DTO for coressponding customer where order status = "received", orders that have not been placed yet
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value =  { "/orders/customer/received/{email}", "/orders/customer/received/{email}/" })
	public ResponseEntity<?>  getReceivedOrdersByCustomer(@PathVariable("email") String email) throws IllegalArgumentException {
		try{
			Customer customer = customerService.getCustomerByEmail(email);
			GroceryOrder order = orderService.getReceivedOrdersByCustomer(customer);
			return ResponseEntity.ok(convertToDto(order));
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param email
	 * @return latest grocery order DTO created for this particular email 
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value =  { "/orders/customer/latest/{email}", "/orders/customer/latest/{email}/" })
	public ResponseEntity<?>  getLatestOrderByCustomer(@PathVariable("email") String email) throws IllegalArgumentException {
		try{
			Customer customer = customerService.getCustomerByEmail(email);
			GroceryOrder order = orderService.getLatestOrderByCustomer(customer);
			return ResponseEntity.ok(convertToDto(order));
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/**
	 * @param orderType
	 * @return orders by order type'
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value = { "/orders/orderType/{orderType}", "/orders/orderType/{orderType}/" })
	public ResponseEntity<?>  getOrdersByOrderType(@PathVariable("orderType") String orderType) throws IllegalArgumentException {
		try {
			List<GroceryOrder> orders = orderService.getOrdersByOrderType(OrderType.valueOf(orderType));
			List<GroceryOrderDto> orderDtos = new ArrayList<GroceryOrderDto>(); 
			for (GroceryOrder o: orders) {		//converting orders
				orderDtos.add(convertToDto(o));
			}
			return ResponseEntity.ok(orderDtos);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/**
	 * @param orderStatus
	 * @return orders by order status
	 *  @throws IllegalArgumentException
	 */
	@GetMapping(value = { "/orders/orderStatus/{orderStatus}", "/orders/orderStatus/{orderStatus}/" })
	public ResponseEntity<?>  getOrdersByOrderStatus(@PathVariable("orderStatus") String orderStatus) throws IllegalArgumentException {
		try {
			List<GroceryOrder> orders = orderService.getOrdersByOrderStatus(OrderStatus.valueOf(orderStatus));
			List<GroceryOrderDto> orderDtos = new ArrayList<GroceryOrderDto>(); 
			for (GroceryOrder o: orders) {
				orderDtos.add(convertToDto(o));
			}
			return ResponseEntity.ok(orderDtos);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return a list of all orders that need preparation (where status == "processing" or "beingDelivered" or "ReadyForPickUp")
	 * @functionality for employees to view the orders they need to prepare (that have been placed but are not completed yet)
	 */
	@GetMapping(value = { "/orders/toUpdate/", "/orders/toUpdate" })
	public ResponseEntity<?>  getOrdersToUpdate() throws IllegalArgumentException {
		try {	
			List<GroceryOrder> processingOrders = orderService.getOrdersByOrderStatus(OrderStatus.Processing);
			List<GroceryOrder> deliveryOrders = orderService.getOrdersByOrderStatus(OrderStatus.BeingDelivered);
			List<GroceryOrder> pickUpOrders = orderService.getOrdersByOrderStatus(OrderStatus.ReadyForPickUp);
			List<GroceryOrderDto> orderDtos = new ArrayList<GroceryOrderDto>(); 
			for (GroceryOrder o: processingOrders) {
				orderDtos.add(convertToDto(o));
			}
			for (GroceryOrder o: deliveryOrders) {
				orderDtos.add(convertToDto(o));
			}
			for (GroceryOrder o: pickUpOrders) {
				orderDtos.add(convertToDto(o));
			}
			
			return ResponseEntity.ok(orderDtos);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	 //-------------------------------------------------------UPDATE METHOD------------------------------------------------------------
	// NOT NECESSARY FOR THE FUNCTIONALITY OF OUR WEBSITE


//	/**
//	 * @param orderDto
//	 * @return modified orderDto;
//	 */
//	@PutMapping(value = { "/orders", "/orders/" })
//	public ResponseEntity<?>  modifyOrder(@RequestParam(name = "Order") GroceryOrderDto orderDto) throws IllegalArgumentException  {
//		try {
//			GroceryOrder order  = orderService.modifyOrder(orderService.getOrderById(orderDto.getOrderId()));
//			return ResponseEntity.ok(convertToDto(order));
//		}catch(IllegalArgumentException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
//	
//-------------------------------------------------------DELETE METHODS------------------------------------------------------------
	

	 /**
	  * 
	  * @param orderId
	  * @return deleted order
	  */
	 @DeleteMapping({"/orders/delete/{orderId}","/orders/delete/{orderId}/"})
	 public ResponseEntity<?>  deleteOrder(@PathVariable("orderId") String orderId) {
		 try {
			 GroceryOrder order = orderService.deleteOrder(orderService.getOrderById(Integer.parseInt(orderId)));
			 return ResponseEntity.ok(convertToDto(order));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	 
	/**
	 * @functionality for the owner to delete all orders that have been completed so they no longer show up in monthly report
	 * @return null
	 */
	@DeleteMapping({"/orders/delete/all/completed", "orders/delete/all/completed/"})
	public ResponseEntity<?>  deleteAllCompletedOrders() {
		try {
			orderService.deleteAllCompletedOrders();
			return ResponseEntity.ok(null);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
	}
	
	/**
	 * 
	 * @param orderId
	 * @param itemName
	 * @return grocery order DTO
	 * @throws IllegalArgumentException
	 * 
	 * @functionality delete one order at a time from a grocery order
	 */
	@DeleteMapping({"/orders/deleteItem/{orderId}/{itemName}", "orders/deleteItem/{orderId}/{itemName}/"})
	public ResponseEntity<?>  deleteItemFromOrder(@PathVariable("orderId") String orderId, @PathVariable("itemName") String itemName) throws IllegalArgumentException  {
		try {
			GroceryOrder order = orderService.getOrderById(Integer.parseInt(orderId));
			GroceryOrder updatedOrder = orderService.deleteItemFromOrder(order,itemName);
			return ResponseEntity.ok(convertToDto(updatedOrder));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}		
	}
	
	 
//-------------------------------------------------------EXTRA METHODS------------------------------------------------------------
	
	/**
	 * @return total sales for completed orders
	 */
	@GetMapping(value = {"/orders/sales", "/orders/sales/"})
	public ResponseEntity<?>  viewTotalSales() {
		try {
			return ResponseEntity.ok( orderService.viewTotalSales());
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/**
	 * @param orderId
	 * updates order status to the next one, should be done by an employee 
	 * order status 
	 * received -> processing -> beingDelivered/ReadyForPickUp -> completed 
	 */
	@PostMapping(value = {"/orders/status/update/{orderId}/","/orders/status/update/{orderId}"})
	public ResponseEntity<?>  updateOrderStatus(@PathVariable("orderId") String orderId) {
		try {
			GroceryOrder order = orderService.updateOrderStatus(orderService.getOrderById(Integer.parseInt(orderId)));
			return ResponseEntity.ok( convertToDto(order));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param orderId
	 * @return grocery order whose order type has been toggled
	 */
	@PostMapping(value = {"/orders/toggleType/{orderId}/","/orders/toggleType/{orderId}"})
	public ResponseEntity<?>  toggleOrderType(@PathVariable("orderId") String orderId) {
		try {
			GroceryOrder order = orderService.toggleOrderType(orderService.getOrderById(Integer.parseInt(orderId)));
			return ResponseEntity.ok(convertToDto(order));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	/**
	 * @param orderId
	 */
	@GetMapping(value = {"/orders/status/view/{orderId}/","/orders/status/view/{orderId}"})
	public ResponseEntity<?>  viewOrderStatus(@PathVariable("orderId") String orderId) {
		try {
			OrderStatus status = orderService.viewOrderStatus(orderService.getOrderById(Integer.parseInt(orderId)));	
			return ResponseEntity.ok(status.toString());
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	

//-------------------------------------------------------CONVERSIONS------------------------------------------------------------
	

	
	private GroceryOrderDto convertToDto(GroceryOrder o) {
		if (o == null) throw new IllegalArgumentException("There is no such Order!");
		if (o.getOrderType().equals(OrderType.InStore)) { //if the order is in store, it is not associated to a customer
			
			List<OrderItemDto> itemDtos = convertToDtos(o.getOrderItems());
			return new GroceryOrderDto(o.getOrderId(),o.getTotalCost(), o.getOrderType().toString(),o.getOrderStatus().toString(), itemDtos);	
		}else {
			CustomerDto customerDto = convertToCDto(o.getCustomer());
			List<OrderItemDto> itemDtos = convertToDtos(o.getOrderItems());
			return new GroceryOrderDto(o.getOrderId(),o.getTotalCost(), o.getOrderType().toString(),o.getOrderStatus().toString(), itemDtos, customerDto);	
			
		}
		
	}
	
	private CustomerDto convertToCDto(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("There is no such Customer!");
		}
		CustomerDto customerDto = new CustomerDto(customer.getEmail(),customer.getUsername(),customer.getPassword(),customer.getAccountId(),customer.getPhoneNumber(),customer.getAddress());
		return customerDto;
	}
	
	private OrderItemDto convertToDto(OrderItem orderItem) {
		if (orderItem == null) {
			throw new IllegalArgumentException("There is no such OrderItem.");
		}
		OrderItemDto orderItemDto = new OrderItemDto(orderItem.getName(),orderItem.getPrice(),orderItem.getItemId());
		return orderItemDto;
	}
	
	private List<OrderItemDto> convertToDtos(List<OrderItem> orderItems) {
//		if (orderItems.isEmpty()) {
//			throw new IllegalArgumentException("This list of order items is empty.");
//		}
		List<OrderItemDto> itemDtos = new ArrayList<OrderItemDto>();
		for (OrderItem oi : orderItems) {
			itemDtos.add(convertToDto(oi));
		}
		return itemDtos	;
	}
}