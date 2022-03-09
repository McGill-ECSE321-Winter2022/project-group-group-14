package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.model.InventoryItem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional //don't need clear database method
public class TestInventoryItemPersistence {
	
	@Autowired
	private InventoryItemRepository inventoryItemRepository;

	//test Inventory Item persistence
	/**@author Youssof Mohamed */
	@Test
	public void testPersistAndLoadInvetoryItem(){

		
		//set values to Inventory Item attributes
		InventoryItem inventoryItem = new InventoryItem();
		String name = "btates";
		inventoryItem.setName(name);
		inventoryItem.setPrice(32);
		inventoryItem.setCurrentStock(15);

		
		//save Inventory Item object
		inventoryItemRepository.save(inventoryItem);
		
		//test findByItemId
		InventoryItem savedInventoryItem = inventoryItemRepository.findByItemId(inventoryItem.getItemId());
		assertNotNull(savedInventoryItem);
		assertEquals(inventoryItem.getName(), savedInventoryItem.getName());
		assertEquals(inventoryItem.getPrice(), savedInventoryItem.getPrice());
		assertEquals(inventoryItem.getCurrentStock(), savedInventoryItem.getCurrentStock());

		//test findByName
		savedInventoryItem=null;
		savedInventoryItem = inventoryItemRepository.findByName(inventoryItem.getName());
		assertNotNull(savedInventoryItem);
		assertEquals(inventoryItem.getName(), savedInventoryItem.getName());
		assertEquals(inventoryItem.getPrice(), savedInventoryItem.getPrice());
		assertEquals(inventoryItem.getCurrentStock(), savedInventoryItem.getCurrentStock());
		
	}


}


