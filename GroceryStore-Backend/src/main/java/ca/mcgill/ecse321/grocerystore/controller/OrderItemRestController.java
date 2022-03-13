package ca.mcgill.ecse321.grocerystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public OrderItemDto createPerson(@PathVariable("name") String name, @RequestParam int price, @RequestParam int currentStock) throws IllegalArgumentException {
		OrderItem orderItem = orderItemService.createOrderItem(name,price,currentStock);
		return convertToDto(orderItem);
	}
	@GetMapping(value = { "/orderItems/{name}", "/orderItems/{name}/" })
	public OrderItemDto createPerson(@PathVariable("name") String name) throws IllegalArgumentException {
		return convertToDto(orderItemService.getOrderItemByName(name));
	}
	
	private OrderItemDto convertToDto(OrderItem orderItem) {
		if (orderItem == null) {
			throw new IllegalArgumentException("There is no such OrderItem!");
		}
		OrderItemDto orderItemDto = new OrderItemDto(orderItem.getName(),orderItem.getPrice(),orderItem.getCurrentStock(),orderItem.getItemId());
		return orderItemDto;
	}

}