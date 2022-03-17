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


import java.util.List;
import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class GroceryOrderRestController {
	
	@Autowired
	private GroceryOrderService orderService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderItemService orderItemService;

	
	//GET ORDERS
	
	/**
	 * @author clarissabaciu
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
	 * @author clarissabaciu
	 * @param orderId
	 * @returns orders by order id
	 */
	@GetMapping(value = {"/orders/{orderId}","/orders/{orderId}/ "})
	public GroceryOrderDto getOrderById(@PathVariable("id") String id) throws IllegalArgumentException{
		GroceryOrder order = orderService.getOrderById(Integer.parseInt(id));
		return convertToDto(order);
	}
	
	
	/**
	 * @author clarissabaciu
	 * @param customerId
	 * @return orders by customer id
	 */
	@GetMapping(value = { "/orders/customers/{customerId}", "/orders/customers/{customerId}/" })
	public List<GroceryOrderDto> getOrdersByAccountId(@PathVariable("customerId") String customerId) throws IllegalArgumentException {
		Customer customer = customerService.getCustomerByID(Integer.parseInt(customerId));
		List<GroceryOrder> orders = orderService.getOrdersByCustomer(customer);
		List<GroceryOrderDto> orderDtos = new ArrayList<GroceryOrderDto>(); 
		for (GroceryOrder o: orders) {
			orderDtos.add(convertToDto(o));
		}
		return orderDtos;
	}
	
	/**
	 * @author clarissabaciu
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
	 * @author clarissabaciu
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
	
	@GetMapping(value = { "/orders/orderStatus/{orderStatus}", "/orders/orderStatus/{orderStatus}/" })
	public List<GroceryOrderDto> getOrdersByOrderStatus(@PathVariable("orderStatus") String orderStatus) throws IllegalArgumentException {
		List<GroceryOrder> orders = orderService.getOrdersByOrderStatus(OrderStatus.valueOf(orderStatus));
		List<GroceryOrderDto> orderDtos = new ArrayList<GroceryOrderDto>(); 
		for (GroceryOrder o: orders) {
			orderDtos.add(convertToDto(o));
		}
		return orderDtos;
	}
	
	
	//CREATE ORDERS
	
	/**
	 * @author clarissabaciu
	 * @param customerDto
	 * @param orderItemDtos
	 * @param orderType
	 * @return  orderDto
	 */
	@PostMapping(value = { "/orders", "/orders/" })
	public GroceryOrderDto createOrder(@RequestParam(name = "Customer") CustomerDto customerDto,@RequestParam(name = "Items") List<OrderItemDto> orderItemDtos,
			@RequestParam(name = "Order Type") String orderType) throws IllegalArgumentException  {
		Customer customer = customerService.getCustomerByEmail(customerDto.getEmail());
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (OrderItemDto itemDto : orderItemDtos ) {
			orderItems.add(orderItemService.getOrderItemById(itemDto.getItemId()));
		}
		GroceryOrder order = orderService.createOrder(customer,orderItems, OrderType.valueOf(orderType));
		return convertToDto(order);
	}
	
	/**
	 * @author clarissabaciu
	 * @param totalCost
	 * @return InStore OrderDto 
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/orders/inStore", "/orders/inStore/" })
	public GroceryOrderDto createInstoreOrder(@RequestParam(name = "Items") List<OrderItemDto> orderItemDtos) throws IllegalArgumentException  {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (OrderItemDto itemDto : orderItemDtos ) {
			orderItems.add(orderItemService.getOrderItemById(itemDto.getItemId()));
		}
		GroceryOrder orderInStore = orderService.createInStoreOrder(orderItems);
		return convertToDto(orderInStore);
	}
	

	//UPDATE ORDER
	
	/**
	 * @author clarissabaciu
	 * @param orderDto
	 * @return modified orderDto;
	 * @throws IllegalArgumentException
	 */
	@PutMapping(value = { "/orders", "/orders/" })
	public GroceryOrderDto modifyOrder(@RequestParam(name = "Order") GroceryOrderDto orderDto) throws IllegalArgumentException  {
		GroceryOrder order  = orderService.modifyOrder(orderService.getOrderById(orderDto.getOrderId()));
		return convertToDto(order);
	}
	
	//DELETE ORDERS
	
	/**
	 * @author clarissabaciu
	 * @param orderId
	 * @return deleted order dto
	 */
	 @DeleteMapping({"/orders/{orderId}","/orders/{orderId}/"})
	 public GroceryOrderDto deleteOrder(@PathVariable("orderId") String orderId) {
		 GroceryOrder order = orderService.deleteOrder(orderService.getOrderById(Integer.parseInt(orderId)));
		 return convertToDto(order);
		  }
	 
	 /**
	  * @author clarissabaciu
	  */
	@DeleteMapping({"/orders/deleteAll", "orders/deleteAll/"})
	public void deleteAllOrders() {
		 orderService.deleteAllOrders();
	}
	 
	//EXTRA
	
	/**
	 * @author clarissabaciu
	 * @return total sales for completed orders
	 */
	@GetMapping(value = {"/orders/totalSales", "/orders/totalSales/"})
	public int getTotalSales() {
		return orderService.getTotalSales();
	}
	
	
	
	//CONVERSIONS
	
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

}
