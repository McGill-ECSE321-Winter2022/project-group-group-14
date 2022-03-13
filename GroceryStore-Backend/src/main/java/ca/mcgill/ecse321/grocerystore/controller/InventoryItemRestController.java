package ca.mcgill.ecse321.grocerystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.InventoryItemDto;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
import ca.mcgill.ecse321.grocerystore.service.InventoryItemService;

@CrossOrigin(origins = "*")
@RestController
public class InventoryItemRestController {

	@Autowired
	private InventoryItemService inventoryItemService;
	
	@PostMapping(value = { "/inventoryItems/{name}", "/inventoryItems/{name}/" })
	public InventoryItemDto createPerson(@PathVariable("name") String name, @RequestParam int price, @RequestParam int currentStock) throws IllegalArgumentException {
		InventoryItem inventoryItem = inventoryItemService.createInventoryItem(name,price,currentStock);
		return convertToDto(inventoryItem);
	}
	@GetMapping(value = { "/orderItems/{name}", "/orderItems/{name}/" })
	public InventoryItemDto createPerson(@PathVariable("name") String name) throws IllegalArgumentException {
		return convertToDto(inventoryItemService.getInventoryItemByName(name));
	}
	
	private InventoryItemDto convertToDto(InventoryItem inventoryItem) {
		if (inventoryItem == null) {
			throw new IllegalArgumentException("There is no such OrderItem!");
		}
		InventoryItemDto inventoryItemDto = new InventoryItemDto(inventoryItem.getName(),inventoryItem.getPrice(),inventoryItem.getCurrentStock(),inventoryItem.getItemId());
		return inventoryItemDto;
	}

}