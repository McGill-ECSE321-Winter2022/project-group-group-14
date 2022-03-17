package ca.mcgill.ecse321.grocerystore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.grocerystore.dao.InventoryItemRepository;
import ca.mcgill.ecse321.grocerystore.dao.OrderItemRepository;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;


@ExtendWith(MockitoExtension.class)
public class TestInventoryItemService {
	
	@Mock
	private OrderItemRepository orderItemDao;
	@Mock
	private InventoryItemRepository inventoryItemDao;

	@InjectMocks
	private OrderItemService orderItemService;
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
	    
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(inventoryItemDao.save(any(InventoryItem.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	
	@Test
	public void testCreateInventoryItem() {
		assertEquals(0, orderItemService.getAllOrderItems().size());

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
	public void testDeleteInventoryItem() {
		
		InventoryItem inventoryItem = inventoryItemService.getInventoryItemByName(INVENTORY_ITEM_NAME);
		InventoryItem deletedInventoryItem = null;
		
		try {
			deletedInventoryItem = inventoryItemService.deleteInventoryItem(INVENTORY_ITEM_NAME);
			inventoryItem = inventoryItemService.getInventoryItemByID(inventoryItem.getItemId());
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNull(inventoryItem);
		assertNotNull(deletedInventoryItem);
		assertEquals(deletedInventoryItem.getName(), INVENTORY_ITEM_NAME);
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