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

import ca.mcgill.ecse321.grocerystore.dto.InventoryItemDto;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
import ca.mcgill.ecse321.grocerystore.service.InventoryItemService;

@CrossOrigin(origins = "*")
@RestController
public class InventoryItemRestController {

	@Autowired
	private InventoryItemService inventoryItemService;
	
	
	//-------------------------------------------------------CREATE MAPPINGS------------------------------------------------------------
	
	/**@author Youssof Mohamed*/
	@PostMapping(value = { "/inventoryItems/{name}", "/inventoryItems/{name}/" })
	public InventoryItemDto createInventoryItem(@PathVariable("name") String name, @RequestParam int price, @RequestParam int currentStock) throws IllegalArgumentException {
		InventoryItem inventoryItem = inventoryItemService.createInventoryItem(name,price,currentStock);
		return convertToDto(inventoryItem);
	}
	
	//-------------------------------------------------------UPDATE MAPPINGS------------------------------------------------------------
	
	/**@author Youssof Mohamed*/
	@PutMapping(value = { "/inventoryItems/{name}", "/inventoryItems/{name}/" })
	public InventoryItemDto upadateInventoryItem(@PathVariable("name") String name, @RequestParam int price, @RequestParam int currentStock) throws IllegalArgumentException {
		InventoryItem inventoryItem = inventoryItemService.updateInventoryItemInfo(name,price,currentStock);
		return convertToDto(inventoryItem);
	}
	
	/**@author Youssof Mohamed*/
	@PutMapping(value = { "/inventoryItems/{name}", "/inventoryItems/{name}/" })
	public InventoryItemDto toggleInventoryItemAvailability(@PathVariable("name") String name) throws IllegalArgumentException {
		InventoryItem inventoryItem = inventoryItemService.toggleInventoryItemAvailability(name);
		return convertToDto(inventoryItem);
	}
	
	//-------------------------------------------------------GET MAPPINGS------------------------------------------------------------
	
	/**@author Youssof Mohamed*/
	@GetMapping(value = { "/inventoryItems/{name}", "/inventoryItems/{name}/" })
	public InventoryItemDto getInventoryItemByName(@PathVariable("name") String name) throws IllegalArgumentException {
		return convertToDto(inventoryItemService.getInventoryItemByName(name));
	}
	
	/**@author Youssof Mohamed*/
	@GetMapping(value = { "/inventoryItems", "/inventoryItems/" })
	public InventoryItemDto getInventoryItemById(@RequestParam int id) throws IllegalArgumentException {
		return convertToDto(inventoryItemService.getInventoryItemById(id));
	}
	
	/**@author Youssof Mohamed*/
	@GetMapping(value = { "/inventoryItems", "/inventoryItems/" })
	public List<InventoryItemDto> getInventoryItems() throws IllegalArgumentException {
		return convertToDto(inventoryItemService.getAllInventoryItems());
	}
	
	//-------------------------------------------------------DELETE MAPPINGS------------------------------------------------------------
	
	/**@author Youssof Mohamed*/
	@DeleteMapping(value = { "/inventoryItems/{name}", "/inventoryItems/{name}/" })
	public void deleteInventoryItem(@PathVariable("name") String name) throws IllegalArgumentException {
		inventoryItemService.deleteInventoryItem(name);
	}
	
	//-------------------------------------------------------CONVERSIONS------------------------------------------------------------
	
	/**@author Youssof Mohamed*/
	private InventoryItemDto convertToDto(InventoryItem inventoryItem) {
		if (inventoryItem == null) {
			throw new IllegalArgumentException("There is no such InventoryItem!");
		}
		InventoryItemDto inventoryItemDto = new InventoryItemDto(inventoryItem.getName(),inventoryItem.getPrice(),inventoryItem.getCurrentStock(),inventoryItem.getItemId());
		return inventoryItemDto;
	}
	
	/**@author Youssof Mohamed*/
	private List<InventoryItemDto> convertToDto(List<InventoryItem> inventoryItems) {
		List<InventoryItemDto> inventoryItemsDto = new ArrayList<InventoryItemDto>(inventoryItems.size());
		
		for(InventoryItem inventoryItem : inventoryItems) {
			
			inventoryItemsDto.add(new InventoryItemDto(inventoryItem.getName(),inventoryItem.getPrice(),inventoryItem.getCurrentStock(),inventoryItem.getItemId()));
			}
		
		return inventoryItemsDto;
	}

}