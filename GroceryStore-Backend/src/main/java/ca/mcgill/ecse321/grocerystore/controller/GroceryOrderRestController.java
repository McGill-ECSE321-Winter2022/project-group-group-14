package ca.mcgill.ecse321.grocerystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import ca.mcgill.ecse321.grocerystore.controller.OrderItemRestController;

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

	
//	/**
//	 * @param customerDto, list of orderItemDtos, orderType
//	 * @return delivery/pickup OrderDto
//	 */
//	@PostMapping(value = { "/orders", "/orders/" })
//	public GroceryOrderDto createOrder(@RequestParam(name = "Customer") CustomerDto customerDto,@RequestParam(name = "Items") List<OrderItemDto> orderItemDtos,
//			@RequestParam(name = "Order Type") String orderType) throws IllegalArgumentException  {
//		Customer customer = customerService.getCustomerByEmail(customerDto.getEmail());
//		List<OrderItem> orderItems = new ArrayList<OrderItem>();
//		for (OrderItemDto itemDto : orderItemDtos ) {
//			orderItems.add(orderItemService.getOrderItemByID(itemDto.getItemId()));
//		}
//		GroceryOrder order = orderService.createOrder(customer,orderItems, OrderType.valueOf(orderType));
//		return convertToDto(order);
//	}
	@PostMapping(value = { "/orders/{email}/{orderName}/{orderType}", "/orders/{email}/{orderName}/{orderType}/" })
	public GroceryOrderDto createOrder(@PathVariable  String email,@PathVariable String orderName,
			@PathVariable String orderType) throws IllegalArgumentException  {
		Customer customer = customerService.getCustomerByEmail(email);
		List<OrderItem> orderItems = orderItemService.getOrderItemsByName(orderName);
//		List<OrderItemDto> orderItemDtos = convertToDto(orderItems);
//		for (OrderItemDto itemDto : orderItemDtos ) {
//			orderItems.add(orderItemService.getOrderItemByID(itemDto.getItemId()));
//		}
		GroceryOrder order = orderService.createOrder(customer,orderItems, OrderType.valueOf(orderType));
		return convertToDto(order);
	}
	
	/**
	 * @param list of orderItemDtos
	 * @return InStore OrderDto 
	 */
	@PostMapping(value = { "/orders/inStore", "/orders/inStore/" })
	public GroceryOrderDto createInstoreOrder(@RequestParam(name = "Items") List<OrderItemDto> orderItemDtos) throws IllegalArgumentException  {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (OrderItemDto itemDto : orderItemDtos ) {
			orderItems.add(orderItemService.getOrderItemByID(itemDto.getItemId()));
		}
		GroceryOrder orderInStore = orderService.createInStoreOrder(orderItems);
		return convertToDto(orderInStore);
	}

	//-------------------------------------------------------GET METHODS------------------------------------------------------------
	
	/**
	 * @return all orders in system
	 */
	@GetMapping(value = { "/orders", "/orders/" })
	public List<GroceryOrderDto> getAllOrders() {
		List<GroceryOrderDto> orderDtos = new ArrayList<GroceryOrderDto>(); 
		for (GroceryOrder modelOrder : orderService.getAllOrders()) {
			orderDtos.add(convertToDto(modelOrder));
		}
		return orderDtos;
	}
	
	/**
	 * @param orderId
	 * @returns orders by order id
	 */
	@GetMapping(value = {"/orders/{orderId}","/orders/{orderId}/ "})
	public GroceryOrderDto getOrderById(@PathVariable("id") String id) throws IllegalArgumentException{
		GroceryOrder order = orderService.getOrderById(Integer.parseInt(id));
		return convertToDto(order);
	}
	
	
	/**
	 * @param Customer
	 * @return orders by customer id
	 */
	@GetMapping(value = { "/orders/customer", "/orders/customer/" })
	public List<GroceryOrderDto> getOrdersByCustomer(@RequestParam CustomerDto customerDto) throws IllegalArgumentException {
		Customer customer = customerService.getCustomerByID(customerDto.getAccountId());
		List<GroceryOrder> orders = orderService.getOrdersByCustomer(customer);
		List<GroceryOrderDto> orderDtos = new ArrayList<GroceryOrderDto>(); 
		for (GroceryOrder o: orders) {
			orderDtos.add(convertToDto(o));
		}
		return orderDtos;
	}
	
	/**
	 * @param orderType
	 * @return orders by order type
	 */
	@GetMapping(value = { "/orders/orderType/{orderType}", "/orders/orderType/{orderType}/" })
	public List<GroceryOrderDto> getOrdersByOrderType(@PathVariable("orderType") String orderType) throws IllegalArgumentException {
		List<GroceryOrder> orders = orderService.getOrdersByOrderType(OrderType.valueOf(orderType));
		List<GroceryOrderDto> orderDtos = new ArrayList<GroceryOrderDto>(); 
		for (GroceryOrder o: orders) {
			orderDtos.add(convertToDto(o));
		}
		return orderDtos;
	}
	
	/**
	 * @param orderStatus
	 * @return orders by order status
	 */
	@GetMapping(value = { "/orders/orderStatus/{orderStatus}", "/orders/orderStatus/{orderStatus}/" })
	public List<GroceryOrderDto> getOrdersByOrderStatus(@PathVariable("orderStatus") String orderStatus) throws IllegalArgumentException {
		List<GroceryOrder> orders = orderService.getOrdersByOrderStatus(OrderStatus.valueOf(orderStatus));
		List<GroceryOrderDto> orderDtos = new ArrayList<GroceryOrderDto>(); 
		for (GroceryOrder o: orders) {
			orderDtos.add(convertToDto(o));
		}
		return orderDtos;
	}
	
	
	 //-------------------------------------------------------UPDATE METHOD------------------------------------------------------------
	
	/**
	 * @param orderDto
	 * @return modified orderDto;
	 */
	@PutMapping(value = { "/orders", "/orders/" })
	public GroceryOrderDto modifyOrder(@RequestParam(name = "Order") GroceryOrderDto orderDto) throws IllegalArgumentException  {
		GroceryOrder order  = orderService.modifyOrder(orderService.getOrderById(orderDto.getOrderId()));
		return convertToDto(order);
	}
	
//-------------------------------------------------------DELETE METHODS------------------------------------------------------------
	
	/**
	 * @param orderId
	 */
	 @DeleteMapping({"/orders/delete/{orderId}","/orders/delete/{orderId}/"})
	 public GroceryOrderDto deleteOrder(@PathVariable("orderId") String orderId) {
		 GroceryOrder order = orderService.deleteOrder(orderService.getOrderById(Integer.parseInt(orderId)));
		 return convertToDto(order);
		  }
	 

	@DeleteMapping({"/orders/delete/all/completed", "orders/delete/all/completed/"})
	public void deleteAllCompletesOrders() {
		 orderService.deleteAllCompletedOrders();
	}
	 
//-------------------------------------------------------EXTRA METHODS------------------------------------------------------------
	
	/**
	 * @return total sales for completed orders
	 */
	@GetMapping(value = {"/orders/sales", "/orders/sales/"})
	public int viewTotalSales() {
		return orderService.viewTotalSales();
	}
	
	/**
	 * @param orderId
	 */
	@GetMapping(value = {"/orders/status/update/{orderId}/","/orders/status/update/{orderId}"})
	public GroceryOrderDto updateOrderStatus(@PathVariable("orderId") String orderId) {
		GroceryOrder order = orderService.updateOrderStatus(orderService.getOrderById(Integer.parseInt(orderId)));
		return convertToDto(order);
	}
	
	/**
	 * @param orderId
	 */
	@GetMapping(value = {"/orders/status/view/{orderId}/","/orders/status/view/{orderId}"})
	public String viewOrderStatus(@PathVariable("orderId") String orderId) {
		OrderStatus status = orderService.viewOrderStatus(orderService.getOrderById(Integer.parseInt(orderId)));
		return status.toString(); 	
	}
	
	/**
	 * @param orderId
	 */
	@GetMapping(value = {"/orders/payment/{orderId}","/orders/payment/{orderId}/"})
	public GroceryOrderDto payForOrder(@PathVariable("orderId") String orderId, @RequestParam(name = "Payment Information") String paymentInformation) {
		GroceryOrder order = orderService.payForOrder(paymentInformation,orderService.getOrderById(Integer.parseInt(orderId)));	// should add hashing for deliverable 3
		return convertToDto(order);
	}
	
//-------------------------------------------------------CONVERSIONS------------------------------------------------------------
	
	private GroceryOrderDto convertToDto(GroceryOrder o) {
		if (o == null) throw new IllegalArgumentException("There is no such OrderItem!");
		CustomerDto customerDto = convertToDto(o.getCustomer());
		List<OrderItemDto> itemDtos = convertToDtos(o.getOrderItems());
		return new GroceryOrderDto(o.getOrderId(),o.getTotalCost(), o.getOrderType().toString(), itemDtos, customerDto);	
	}
	
	private CustomerDto convertToDto(Customer customer) {
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
		if (orderItems.isEmpty()) {
			throw new IllegalArgumentException("This list of order items is empty.");
		}
		List<OrderItemDto> itemDtos = new ArrayList<OrderItemDto>();
		for (OrderItem oi : orderItems) {
			itemDtos.add(convertToDto(oi));
		}
		return itemDtos	;
	}
	
	private List<OrderItemDto> convertToDto(List<OrderItem> orderItems) {
		List<OrderItemDto> orderItemsDto = new ArrayList<OrderItemDto>(orderItems.size());
		
		for(OrderItem orderItem : orderItems) {
			if (orderItem == null) {
				throw new IllegalArgumentException("There is no such OrderItem!");
			}
			orderItemsDto.add(new OrderItemDto(orderItem.getName(),orderItem.getPrice(),orderItem.getItemId()));
			}
		
		return orderItemsDto;
	}

}
