package ca.mcgill.ecse321.grocerystore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.grocerystore.dao.InventoryItemRepository;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;


@ExtendWith(MockitoExtension.class)
public class TestInventoryItemService {
	
	@Mock
	private InventoryItemRepository inventoryItemDao;

	@InjectMocks
	private InventoryItemService inventoryItemService;

	private static final String INVENTORY_ITEM_NAME = "Jelly Beans";
	private static final String NON_EXISTING_INVENTORY_ITEM = "Flying Chocolate";

	@BeforeEach
	public void setMockOutput() {
	    lenient().when(inventoryItemDao.findByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	    	if(invocation.getArgument(0).equals(INVENTORY_ITEM_NAME)) {
	    		InventoryItem inventoryItem = new InventoryItem();
	    		inventoryItem.setName(INVENTORY_ITEM_NAME);
	    		inventoryItem.setPrice(7);
	    		inventoryItem.setCurrentStock(12);
	    		return inventoryItem;
	    	} if(invocation.getArgument(0).equals("tamween")) {
	    		InventoryItem inventoryItem = new InventoryItem();
	    		inventoryItem.setName("tamween");
	    		inventoryItem.setPrice(7);
	    		inventoryItem.setCurrentStock(12);
	    		return inventoryItem;
	    	} else {
	    		return null;
	    	}
	    });
	    lenient().when(inventoryItemDao.findByItemId(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	    	if(invocation.getArgument(0).equals(3)) {
	    		InventoryItem inventoryItem = new InventoryItem();
	    		inventoryItem.setName(INVENTORY_ITEM_NAME);
	    		inventoryItem.setPrice(7);
	    		inventoryItem.setCurrentStock(12);
	    		inventoryItem.setItemId(3);
	    		return inventoryItem;
	    	} else {
	    		return null;
	    	}
	    });
	    
	    lenient().when(inventoryItemDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
	    	
	    	InventoryItem inventoryItem = new InventoryItem();
    		inventoryItem.setName("kosa");
    		inventoryItem.setPrice(12);
    		inventoryItem.setCurrentStock(17);
    		InventoryItem inventoryItem2 = new InventoryItem();
    		inventoryItem2.setName("btngan");
    		inventoryItem2.setPrice(12);
    		inventoryItem2.setCurrentStock(17);
    		InventoryItem inventoryItem3 = new InventoryItem();
    		inventoryItem3.setName("krnb");
    		inventoryItem3.setPrice(12);
    		inventoryItem3.setCurrentStock(17);
           
    		List<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
    		inventoryItems.add(inventoryItem);
    		inventoryItems.add(inventoryItem2);
    		inventoryItems.add(inventoryItem3);
	    		return inventoryItems;
	    });
	    
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(inventoryItemDao.save(any(InventoryItem.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	
	@Test
	public void testCreateInventoryItem() {

		String name = "bateekh";
		int price = 7;
		int currentStock = 12;
		InventoryItem inventoryItem = null;
		try {
			inventoryItem = inventoryItemService.createInventoryItem(name,price,currentStock);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(inventoryItem);
		assertEquals(name, inventoryItem.getName());
		assertEquals(price, inventoryItem.getPrice());
		assertEquals(currentStock, inventoryItem.getCurrentStock());
	}
	@Test
	public void testUpdateInventoryItem() {
		
		String name = "tamween";
		int price = 9;
		int currentStock = 13;
		InventoryItem updatedInventoryItem = null;
		
		try {
			updatedInventoryItem = inventoryItemService.updateInventoryItemInfo(name,price,currentStock);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(updatedInventoryItem);
		assertEquals(name, updatedInventoryItem.getName());
		assertEquals(price, updatedInventoryItem.getPrice());
		assertEquals(currentStock, updatedInventoryItem.getCurrentStock());
	}
	
	@Test
	public void testGetAllInventoryItems() {
		
		List<InventoryItem> inventoryItems = null;
		try {
			inventoryItems = inventoryItemService.getAllInventoryItems();
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(inventoryItems);
		assertEquals(3, inventoryItems.size());
	}
	
	@Test
	public void testDeleteInventoryItem() {
		
		InventoryItem inventoryItem = inventoryItemService.getInventoryItemByName(INVENTORY_ITEM_NAME);
		InventoryItem deletedInventoryItem = null;
		
		try {
			deletedInventoryItem = inventoryItemService.deleteInventoryItem(INVENTORY_ITEM_NAME);
			inventoryItem = inventoryItemService.getInventoryItemById(inventoryItem.getItemId());
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNull(inventoryItem);
		assertNotNull(deletedInventoryItem);
		assertEquals(deletedInventoryItem.getName(), INVENTORY_ITEM_NAME);
	}
	
	@Test
	public void testGetInventoryItemByName() {
		
		InventoryItem inventoryItem = null;
		try {
			inventoryItem = inventoryItemService.getInventoryItemByName(INVENTORY_ITEM_NAME);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(inventoryItem);
		assertEquals(inventoryItem.getName(), INVENTORY_ITEM_NAME);
	}
	@Test
	public void testGetInventoryItemById() {
		
		InventoryItem inventoryItem = null;
		try {
			inventoryItem = inventoryItemService.getInventoryItemById(3);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(inventoryItem);
		assertEquals(inventoryItem.getName(), INVENTORY_ITEM_NAME);
		assertEquals(inventoryItem.getItemId(), 3);
	}
	
	@Test
	public void testGetExistingInventoryItem() {
		InventoryItem inventoryItem = null;
        try
        {
            inventoryItem = inventoryItemService.getInventoryItemByName(INVENTORY_ITEM_NAME);

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(inventoryItem);
        assertEquals(INVENTORY_ITEM_NAME, inventoryItem.getName());
	}

	@Test
	public void testGetNonExistingInventoryItem() {
		assertNull(inventoryItemService.getInventoryItemByName(NON_EXISTING_INVENTORY_ITEM));
	}
	

}