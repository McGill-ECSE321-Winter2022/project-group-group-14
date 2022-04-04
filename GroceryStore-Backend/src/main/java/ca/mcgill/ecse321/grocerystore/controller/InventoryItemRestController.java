package ca.mcgill.ecse321.grocerystore.controller;

import java.util.ArrayList;
import java.util.List;

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
	@PostMapping(value = { "/inventoryItems/create/{name}", "/inventoryItems/create/{name}/" })
	public ResponseEntity<?> createInventoryItem(@PathVariable("name") String name, @RequestParam int price, @RequestParam int currentStock) {
		try {
			InventoryItem inventoryItem = inventoryItemService.createInventoryItem(name,price,currentStock);
			return ResponseEntity.ok(convertToDto(inventoryItem));
		  } catch (IllegalArgumentException ex) {
		    return ResponseEntity.badRequest().body(ex.getMessage());
		  }
		
	}
//	/**@author Youssof Mohamed*/
//	@PostMapping(value = { "/inventoryItems/create/{name}", "/inventoryItems/create/{name}/" })
//	public InventoryItemDto createInventoryItem(@PathVariable("name") String name, @RequestParam int price, @RequestParam int currentStock) throws IllegalArgumentException {
//		InventoryItem inventoryItem = inventoryItemService.createInventoryItem(name,price,currentStock);
//		return convertToDto(inventoryItem);
//	}
	
	//-------------------------------------------------------UPDATE MAPPINGS------------------------------------------------------------
	
	/**@author Youssof Mohamed*/
	@PutMapping(value = { "/inventoryItems/update/{name}", "/inventoryItems/update/{name}/" })
	public ResponseEntity<?> upadateInventoryItem(@PathVariable("name") String name, @RequestParam int price, @RequestParam int currentStock) throws IllegalArgumentException {
		try {
		InventoryItem inventoryItem = inventoryItemService.updateInventoryItemInfo(name,price,currentStock);
		return ResponseEntity.ok(convertToDto(inventoryItem));
	  } catch (IllegalArgumentException ex) {
	    return ResponseEntity.badRequest().body(ex.getMessage());
	  }
	}
	
	/**@author Youssof Mohamed*/
	@PutMapping(value = { "/inventoryItems/toggle/{name}", "/inventoryItems/toggle/{name}/" })
	public ResponseEntity<?> toggleInventoryItemAvailability(@PathVariable("name") String name) throws IllegalArgumentException {
		
	try{
		InventoryItem inventoryItem = inventoryItemService.toggleInventoryItemAvailability(name);
		return ResponseEntity.ok(convertToDto(inventoryItem));
	  } catch (IllegalArgumentException ex) {
	    return ResponseEntity.badRequest().body(ex.getMessage());
	  }
	}
	
	//-------------------------------------------------------GET MAPPINGS------------------------------------------------------------
	
	/**@author Youssof Mohamed*/
	@GetMapping(value = { "/inventoryItems/getByName/{name}", "/inventoryItems/getByName/{name}/" })
	public ResponseEntity<?> getInventoryItemByName(@PathVariable("name") String name) throws IllegalArgumentException {
	try{	
		return ResponseEntity.ok(convertToDto(inventoryItemService.getInventoryItemByName(name)));
	} catch (IllegalArgumentException ex) {
	    return ResponseEntity.badRequest().body(ex.getMessage());
	  }
	}
	
	/**@author Youssof Mohamed*/
	@GetMapping(value = { "/inventoryItems/getById", "/inventoryItems/getById/" })
	public ResponseEntity<?> getInventoryItemById(@RequestParam int id) throws IllegalArgumentException {
	try{
		return ResponseEntity.ok(convertToDto(inventoryItemService.getInventoryItemById(id)));
	} catch (IllegalArgumentException ex) {
	    return ResponseEntity.badRequest().body(ex.getMessage());
	  }
	}
	
	/**@author Youssof Mohamed*/
	@GetMapping(value = { "/inventoryItems/get", "/inventoryItems/get/" })
	public ResponseEntity<?> getInventoryItems() throws IllegalArgumentException {
		
		try {
			return ResponseEntity.ok(convertToDto(inventoryItemService.getAllInventoryItems()));
		  } catch (IllegalArgumentException ex) {
		    return ResponseEntity.badRequest().body(ex.getMessage());
		  }
	}
	
	//-------------------------------------------------------DELETE MAPPINGS------------------------------------------------------------
	

	/**@author Youssof Mohamed*/
	@DeleteMapping(value = { "/inventoryItems/delete/{name}", "/inventoryItems/delete/{name}/" })
	public ResponseEntity<?> deleteInventoryItem(@PathVariable("name") String name) throws IllegalArgumentException {
		try {
			return ResponseEntity.ok(convertToDto(inventoryItemService.deleteInventoryItem(name)));
		  } catch (IllegalArgumentException ex) {
		    return ResponseEntity.badRequest().body(ex.getMessage());
		  }
		
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

