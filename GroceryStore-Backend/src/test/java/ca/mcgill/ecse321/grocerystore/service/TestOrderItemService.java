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
import ca.mcgill.ecse321.grocerystore.dao.OrderItemRepository;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;


@ExtendWith(MockitoExtension.class)
public class TestOrderItemService {
	
	@Mock
	private OrderItemRepository orderItemDao;
	@Mock
	private InventoryItemRepository inventoryItemDao;

	@InjectMocks
	private OrderItemService orderItemService;
	@InjectMocks
	private InventoryItemService inventoryItemService;

	private static final String ORDER_ITEM_NAME = "Jelly Beans";
	private static final String NON_EXISTING_ORDER_ITEM = "Flying Chocolate";

	@BeforeEach
	public void setMockOutput() {
	    lenient().when(orderItemDao.findByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(ORDER_ITEM_NAME)) {
	        	InventoryItem inventoryItem = new InventoryItem();
	    		inventoryItem.setName(ORDER_ITEM_NAME);
	    		inventoryItem.setPrice(12);
	    		inventoryItem.setCurrentStock(17);
	            OrderItem orderItem = new OrderItem();
	            orderItem.setName(ORDER_ITEM_NAME);
	    		List<OrderItem> orderItems = new ArrayList<OrderItem>();
	    		orderItems.add(orderItem);
	            return orderItems;
	        } else if(invocation.getArgument(0).equals("bateekh")) {
	        	InventoryItem inventoryItem = new InventoryItem();
	    		inventoryItem.setName("bateekh");
	    		inventoryItem.setPrice(12);
	    		inventoryItem.setCurrentStock(17);
	    		OrderItem orderItem = new OrderItem();
	    		orderItem.setName("bateekh");
	    		orderItem.setPrice(12);
	    		List<OrderItem> orderItems = new ArrayList<OrderItem>();
	    		orderItems.add(orderItem);
	    		return orderItems;
	        }
	    		else {
	            return null;
	        }
	    });
	    
	    lenient().when(inventoryItemDao.findByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	    	if(invocation.getArgument(0).equals(ORDER_ITEM_NAME)) {
	    		InventoryItem inventoryItem = new InventoryItem();
	    		inventoryItem.setPrice(12);
	    		inventoryItem.setCurrentStock(17);
	    		inventoryItem.setName(ORDER_ITEM_NAME);
	    		return inventoryItem;
	    	} else if(invocation.getArgument(0).equals("bateekh")) {
	    		InventoryItem inventoryItem = new InventoryItem();
	    		inventoryItem.setName("bateekh");
	    		inventoryItem.setPrice(12);
	    		inventoryItem.setCurrentStock(17);
	    		return inventoryItem;
	    	} else {
	    		return null;
	    	}
	    });
	    
	    lenient().when(orderItemDao.findByItemId(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	    	if(invocation.getArgument(0).equals(3)) {
	    		InventoryItem inventoryItem = new InventoryItem();
	    		inventoryItem.setName(ORDER_ITEM_NAME);
	    		inventoryItem.setPrice(7);
	    		inventoryItem.setCurrentStock(12);
	    		OrderItem orderItem = new OrderItem();
		        orderItem.setName(ORDER_ITEM_NAME);
		        orderItem.setItemId(3);
	    		return orderItem;
	    	} else {
	    		return null;
	    	}
	    });
	    
	    lenient().when(orderItemDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
	    	
	    	InventoryItem inventoryItem = new InventoryItem();
    		inventoryItem.setName("kosa");
    		inventoryItem.setPrice(12);
    		inventoryItem.setCurrentStock(17);
            OrderItem orderItem = new OrderItem();
            orderItem.setName("kosa");
            OrderItem orderItem2 = new OrderItem();
            orderItem2.setName("kosa");
            OrderItem orderItem3 = new OrderItem();
            orderItem3.setName("btngan");
    		List<OrderItem> orderItems = new ArrayList<OrderItem>();
    		orderItems.add(orderItem);
    		orderItems.add(orderItem2);
    		orderItems.add(orderItem3);
	    		return orderItems;
	    });
	    
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(orderItemDao.save(any(OrderItem.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(inventoryItemDao.save(any(InventoryItem.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	
	@Test
	public void testCreateOrderItem() {
		String name = "bateekh";
		InventoryItem inventoryItem = inventoryItemDao.findByName(name);
		OrderItem orderItem = null;
		try {
			orderItem = orderItemService.createOrderItem(name);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(orderItem);
		assertEquals(name, orderItem.getName());
		assertEquals(inventoryItem.getPrice(), orderItem.getPrice());
	}
	@Test
	public void testGetAllOrderItems() {
		
		List<OrderItem> orderItems = null;
		try {
			orderItems = orderItemService.getAllOrderItems();
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(orderItems);
		assertEquals(3, orderItems.size());
	}
	@Test
	public void testGetOrderItemsByName() {
		
		List<OrderItem> orderItems = null;
		try {
			orderItems = orderItemService.getOrderItemsByName(ORDER_ITEM_NAME);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(orderItems);
		assertEquals(1, orderItems.size());
	}
	
	@Test
	public void testDeleteOrderItem() {
		
		OrderItem orderItem = orderItemService.getOrderItemsByName(ORDER_ITEM_NAME).get(0);
		
		try {
			orderItemService.deleteOrderItem(ORDER_ITEM_NAME);
			orderItem = orderItemService.getOrderItemById(orderItem.getItemId());
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNull(orderItem);
	}
	
	@Test
	public void testGetOrderItemById() {
		
		OrderItem orderItem = null;
		try {
			orderItem = orderItemService.getOrderItemById(3);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(orderItem);
		assertEquals(orderItem.getName(), ORDER_ITEM_NAME);
		assertEquals(orderItem.getItemId(), 3);
	}
	
	@Test
	public void testUpdateOrderItem() {
//		assertEquals(0, orderItemService.getAllOrderItems().size());
		
		
		String name = "bateekh";
		
		int price = 9;
		OrderItem updatedOrderItem = null;
		
		try {
			updatedOrderItem = orderItemService.updateOrderItemInfo(name,price);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(updatedOrderItem);
		assertEquals(name, updatedOrderItem.getName());
		assertEquals(price, updatedOrderItem.getPrice());
	}
	
	
	
	@Test
	public void testGetExistingOrderItem() {
		OrderItem orderItem = null;
        try
        {
            orderItem = orderItemService.getOrderItemsByName(ORDER_ITEM_NAME).get(0);

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(orderItem);
        assertEquals(ORDER_ITEM_NAME, orderItem.getName());
	}

	@Test
	public void testGetNonExistingCustomer() {
		assertNull(orderItemService.getOrderItemsByName(NON_EXISTING_ORDER_ITEM));
	}
	

}