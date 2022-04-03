package ca.mcgill.ecse321.grocerystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.CustomerDto;
import ca.mcgill.ecse321.grocerystore.dto.GroceryOrderDto;
import ca.mcgill.ecse321.grocerystore.dto.OrderItemDto;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderType;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;
import ca.mcgill.ecse321.grocerystore.service.CustomerService;
import ca.mcgill.ecse321.grocerystore.service.GroceryOrderService;
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

 //-------------------------------------------------------CREATE MAPPINGS------------------------------------------------------------
	public CustomerDto createTempCustomer(String email) {
		Customer customer = customerService.createCustomer(email,"username1","abC123","5145543332","38greenlane");
		return convertToCDto(customer);
		
	}//temporary fct
	
	
	
	@PostMapping(value = { "/orders/delivery/{email}", "/orders/delivery/{email}/" })
	public ResponseEntity<?> createDeliveryOrder(@PathVariable  String email) throws IllegalArgumentException  {
		try {
			//should be deleted afterwards
			createTempCustomer(email);
			Customer customer = customerService.getCustomerByEmail(email);
			GroceryOrder order = orderService.createDeliveryOrder(customer);
					
			return ResponseEntity.ok(new GroceryOrderDto(order.getOrderId(),order.getTotalCost(), order.getOrderType().toString(),order.getOrderStatus().toString(), convertToCDto(customer)));	
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	@PostMapping(value = { "/orders/pickup/{email}", "/orders/pickup/{email}/" })
	public ResponseEntity<?>  createPickupOrder(@PathVariable  String email) throws IllegalArgumentException  {
		try {
			//should be deleted afterwards
			createTempCustomer(email);
			
			Customer customer = customerService.getCustomerByEmail(email);
			GroceryOrder order = orderService.createPickupOrder(customer);
			return ResponseEntity.ok(new GroceryOrderDto(order.getOrderId(),order.getTotalCost(), order.getOrderType().toString(),order.getOrderStatus().toString(), convertToCDto(customer)));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/**
	 * @param list of orderItemDtos
	 * @return InStore OrderDto 
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
	
	//-------------------------------------------------------ADD TO ORDER------------------------------------------------------------	

	@PostMapping(value = { "/orders/add/{orderId}/", "/orders/add/{orderId}" })
	public ResponseEntity<?>  addOrderItems(@PathVariable  String orderId,@RequestParam String itemName, @RequestParam int quantity) throws IllegalArgumentException  {
		try {
			GroceryOrder order = orderService.getOrderById(Integer.parseInt(orderId));
			List<OrderItem> orderItems = new ArrayList<OrderItem>();
			for (int i = 0 ; i<quantity ;i++ ) {	//loop over everything to create order items
				OrderItem orderItem = orderItemService.createOrderItem(itemName);
				orderItems.add(orderItem);
			}
			
			GroceryOrder updatedOrder = orderService.addOrderItems(order,orderItems);
			return ResponseEntity.ok(convertToDto(updatedOrder));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	//-------------------------------------------------------Finished adding to order--------------------------------------	
	
	/**
	 * 
	 * @param orderId
	 * @return should change order from received -> processing
	 */
	@PostMapping(value = { "/orders/place/{orderId}/", "/orders/place/{orderId}" }) 
	public ResponseEntity<?>  placeOrder(@PathVariable  String orderId) throws IllegalArgumentException  {
		try {
			GroceryOrder order = orderService.getOrderById(Integer.parseInt(orderId));
			GroceryOrder placedOrder = orderService.placeOrder(order);
			return ResponseEntity.ok(convertToDto(placedOrder));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
	
	//-------------------------------------------------------GET METHODS------------------------------------------------------------
	
	/**
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
	public ResponseEntity<?>  getOrderById(@PathVariable("id") String id) throws IllegalArgumentException{
		try {
			GroceryOrder order = orderService.getOrderById(Integer.parseInt(id));
			return ResponseEntity.ok(convertToDto(order));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	/**
	 * @param Customer
	 * @return orders by customer id
	 */
<<<<<<< HEAD
	@GetMapping(value = { "/orders/{customerEmail}", "/orders/customerEmail/" })
	public List<GroceryOrderDto> getOrdersByCustomer(@PathVariable("email") String email) throws IllegalArgumentException {
		Customer customer = customerService.getCustomerByEmail(email);
		List<GroceryOrder> orders = orderService.getOrdersByCustomer(customer);
		List<GroceryOrderDto> orderDtos = new ArrayList<GroceryOrderDto>(); 
		for (GroceryOrder o: orders) {
			orderDtos.add(convertToDto(o));
=======
	@GetMapping(value = { "/orders/customer", "/orders/customer/" })
	public ResponseEntity<?>  getOrdersByCustomer(@RequestParam CustomerDto customerDto) throws IllegalArgumentException {
		try {
			Customer customer = customerService.getCustomerByID(customerDto.getAccountId());
			List<GroceryOrder> orders = orderService.getOrdersByCustomer(customer);
			List<GroceryOrderDto> orderDtos = new ArrayList<GroceryOrderDto>(); 
			for (GroceryOrder o: orders) {
				orderDtos.add(convertToDto(o));
			}
			return ResponseEntity.ok(orderDtos);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
>>>>>>> ca6d180fa2055d43ce2056dee8893366085701a1
		}
	}
	
	/**
	 * @param orderType
	 * @return orders by order type
	 */
	@GetMapping(value = { "/orders/orderType/{orderType}", "/orders/orderType/{orderType}/" })
	public ResponseEntity<?>  getOrdersByOrderType(@PathVariable("orderType") String orderType) throws IllegalArgumentException {
		try {
			List<GroceryOrder> orders = orderService.getOrdersByOrderType(OrderType.valueOf(orderType));
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
	 * @param orderStatus
	 * @return orders by order status
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
	
	
	 //-------------------------------------------------------UPDATE METHOD------------------------------------------------------------
	
	/**
	 * @param orderDto
	 * @return modified orderDto;
	 */
	@PutMapping(value = { "/orders", "/orders/" })
	public ResponseEntity<?>  modifyOrder(@RequestParam(name = "Order") GroceryOrderDto orderDto) throws IllegalArgumentException  {
		try {
			GroceryOrder order  = orderService.modifyOrder(orderService.getOrderById(orderDto.getOrderId()));
			return ResponseEntity.ok(convertToDto(order));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
//-------------------------------------------------------DELETE METHODS------------------------------------------------------------
	
	/**
	 * @param orderId
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
	 

	@DeleteMapping({"/orders/delete/all/completed", "orders/delete/all/completed/"})
	public ResponseEntity<?>  deleteAllCompletedOrders() {
		try {
			orderService.deleteAllCompletedOrders();
			return ResponseEntity.ok(null);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
	}
	

	@DeleteMapping({"/orders/deleteItem/{orderId}/{itemName}", "orders/delete/all/{orderId}/{itemName}/"})
	public ResponseEntity<?>  deleteItemFromOrder(@PathVariable("orderId") String orderId,@RequestParam String itemName, @RequestParam int quantity) throws IllegalArgumentException  {
		try {
			GroceryOrder order = orderService.getOrderById(Integer.parseInt(orderId));
			List<OrderItem> orderItems = orderItemService.getOrderItemsByName(itemName);
			GroceryOrder updatedOrder = orderService.deleteItemFromOrder(order, orderItems, quantity);
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
	@GetMapping(value = {"/orders/status/update/{orderId}/","/orders/status/update/{orderId}"})
	public ResponseEntity<?>  updateOrderStatus(@PathVariable("orderId") String orderId) {
		try {
			GroceryOrder order = orderService.updateOrderStatus(orderService.getOrderById(Integer.parseInt(orderId)));
			return ResponseEntity.ok( convertToDto(order));
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
	
//	/**
//	 * @param orderId
//	 */
//	@GetMapping(value = {"/orders/payment/{orderId}","/orders/payment/{orderId}/"})
//	public GroceryOrderDto payForOrder(@PathVariable("orderId") String orderId, @RequestParam(name = "Payment Information") String paymentInformation) {
//		GroceryOrder order = orderService.payForOrder(paymentInformation,orderService.getOrderById(Integer.parseInt(orderId)));	// should add hashing for deliverable 3
//		return convertToDto(order);
//	}
//	
//-------------------------------------------------------CONVERSIONS------------------------------------------------------------
	

	
	private GroceryOrderDto convertToDto(GroceryOrder o) {
		if (o == null) throw new IllegalArgumentException("There is no such OrderItem!");
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
	
//	private List<OrderItemDto> convertToDto(List<OrderItem> orderItems) {
//		List<OrderItemDto> orderItemsDto = new ArrayList<OrderItemDto>(orderItems.size());
//		
//		for(OrderItem orderItem : orderItems) {
//			if (orderItem == null) {
//				throw new IllegalArgumentException("There is no such OrderItem!");
//			}
//			orderItemsDto.add(new OrderItemDto(orderItem.getName(),orderItem.getPrice(),orderItem.getItemId()));
//			}
//		
//		return orderItemsDto;
//	}

}
