package ca.mcgill.ecse321.grocerystore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.OrderItemDto;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;
import ca.mcgill.ecse321.grocerystore.service.OrderItemService;

@CrossOrigin(origins = "*")
@RestController
public class OrderItemRestController {

	@Autowired
	private OrderItemService orderItemService;
	
	//-------------------------------------------------------CREATE MAPPINGS------------------------------------------------------------
	
	/**@author Youssof Mohamed*/
	@PostMapping(value = { "/orderItems/create/{name}", "/orderItems/create/{name}/" })
	public OrderItemDto createOrderItem(@PathVariable("name") String name) throws IllegalArgumentException {
		OrderItem orderItem = orderItemService.createOrderItem(name);
		return convertToDto(orderItem);
	}
	
	
	//-------------------------------------------------------UPDATE MAPPINGS------------------------------------------------------------
	
	/**@author Youssof Mohamed*/
	@PutMapping(value = { "/orderItems/update/{name}", "/orderItems/update/{name}/" })
	public OrderItemDto updateOrderItem(@PathVariable("name") String name,@RequestParam int price) throws IllegalArgumentException {
		OrderItem orderItem = orderItemService.updateOrderItemInfo(name,price);
		return convertToDto(orderItem);
	}
	
	
	
	//-------------------------------------------------------GET MAPPINGS------------------------------------------------------------
	
	/**@author Youssof Mohamed*/
	@GetMapping(value = { "/orderItems/getByName/{name}", "/orderItems/getByName/{name}/" })
	public List<OrderItemDto> getOrderItemByName(@PathVariable("name") String name) throws IllegalArgumentException {
		return convertToDto(orderItemService.getOrderItemsByName(name));
	}
	
	/**@author Youssof Mohamed*/
	@GetMapping(value = { "/orderItems/getById", "/orderItems/getById/" })
	public OrderItemDto getOrderItemById(@RequestParam int id) throws IllegalArgumentException {
		return convertToDto(orderItemService.getOrderItemByID(id));
	}
	
	/**@author Youssof Mohamed*/
	@GetMapping(value = { "/orderItems/get", "/orderItems/get/" })
	public List<OrderItemDto> getAllOrderItems() throws IllegalArgumentException {
		return convertToDto(orderItemService.getAllOrderItems());
	}
	
	
	
	//-------------------------------------------------------DELETE MAPPINGS------------------------------------------------------------
	
	
	/**@author Youssof Mohamed*/
	@DeleteMapping(value = { "/orderItems/delete/{name}", "/orderItems/delete/{name}/" })
	public OrderItemDto deleteOrderItem(@PathVariable("name") String name) throws IllegalArgumentException {
		return convertToDto(orderItemService.deleteOrderItem(name));
	}
	
	
	
	//-------------------------------------------------------CONVERSIONS------------------------------------------------------------
	
	
	/**@author Youssof Mohamed*/
	private OrderItemDto convertToDto(OrderItem orderItem) {
		if (orderItem == null) {
			throw new IllegalArgumentException("There is no such OrderItem!");
		}
		OrderItemDto orderItemDto = new OrderItemDto(orderItem.getName(),orderItem.getPrice(),orderItem.getItemId());
		return orderItemDto;
	}
	
	/**@author Youssof Mohamed*/
	private List<OrderItemDto> convertToDto(List<OrderItem> orderItems) {
		List<OrderItemDto> orderItemsDto = new ArrayList<OrderItemDto>(orderItems.size());
		
		for(OrderItem orderItem : orderItems) {
			orderItemsDto.add(new OrderItemDto(orderItem.getName(),orderItem.getPrice(),orderItem.getItemId()));
			}
		
		return orderItemsDto;
	}

}