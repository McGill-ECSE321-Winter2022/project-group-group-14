package ca.mcgill.ecse321.grocerystore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.OrderItemDto;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;
import ca.mcgill.ecse321.grocerystore.service.OrderItemService;

@CrossOrigin(origins = "*")
@RestController
public class OrderItemRestController {

	@Autowired
	private OrderItemService orderItemService;
	
	@PostMapping(value = { "/orderItems/{name}", "/orderItems/{name}/" })
	public OrderItemDto createOrderItem(@PathVariable("name") String name) throws IllegalArgumentException {
		OrderItem orderItem = orderItemService.createOrderItem(name);
		return convertToDto(orderItem);
	}
	@GetMapping(value = { "/orderItems/{name}", "/orderItems/{name}/" })
	public List<OrderItemDto> getOrderItem(@PathVariable("name") String name) throws IllegalArgumentException {
		return convertToDto(orderItemService.getOrderItemsByName(name));
	}
	@PutMapping(value = { "/orderItems/{name}", "/orderItems/{name}/" })
	public OrderItemDto updateOrderItem(@PathVariable("name") String name, @RequestParam int price) throws IllegalArgumentException {
		return convertToDto(orderItemService.updateOrderItemInfo(name,price));
	}
	@DeleteMapping(value = { "/orderItems/{name}", "/orderItems/{name}/" })
	public void deleteOrderItem(@PathVariable("name") String name) throws IllegalArgumentException {
		orderItemService.deleteOrderItem(name);
	}
	
	private OrderItemDto convertToDto(OrderItem orderItem) {
		if (orderItem == null) {
			throw new IllegalArgumentException("There is no such OrderItem!");
		}
		OrderItemDto orderItemDto = new OrderItemDto(orderItem.getName(),orderItem.getPrice(),orderItem.getItemId());
		return orderItemDto;
	}
	
	private List<OrderItemDto> convertToDto(List<OrderItem> orderItems) {
		List<OrderItemDto> orderItemsDto = new ArrayList<OrderItemDto>(orderItems.size());
		
		for(OrderItem orderItem : orderItems) {
			if (orderItem == null) {
				throw new IllegalArgumentException("There is no such OrderItem!");
			}
			orderItemsDto.add(new OrderItemDto(orderItem.getName(),orderItem.getPrice(),orderItem.getItemId()));
//			orderItemsDto.set(orderItems.indexOf(orderItem), new OrderItemDto(orderItem.getName(),orderItem.getPrice(),orderItem.getCurrentStock(),orderItem.getItemId()));
		}
		
		return orderItemsDto;
	}

}