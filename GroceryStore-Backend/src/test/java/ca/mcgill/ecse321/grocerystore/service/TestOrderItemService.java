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
	private static final String NON_EXISTING_ORDER_ITEM2 = "Real Flying Chocolate";
	private static final String UNAVAILABLE_ORDER_ITEM = "Big boy";

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
	            orderItem.setPrice(13);
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
	    	if(invocation.getArgument(0).equals("bateekh")) {
	    		InventoryItem inventoryItem = new InventoryItem();
	    		inventoryItem.setName("bateekh");
	    		inventoryItem.setPrice(12);
	    		inventoryItem.setCurrentStock(17);
	    		return inventoryItem;
	    	} else if(invocation.getArgument(0).equals(NON_EXISTING_ORDER_ITEM2)) {
	    		InventoryItem inventoryItem = new InventoryItem();
	    		inventoryItem.setName(NON_EXISTING_ORDER_ITEM2);
	    		inventoryItem.setPrice(12);
	    		inventoryItem.setCurrentStock(0);
	    		return inventoryItem;
	    	} else if(invocation.getArgument(0).equals(UNAVAILABLE_ORDER_ITEM)) {
	    		InventoryItem inventoryItem = new InventoryItem();
	    		inventoryItem.setName(UNAVAILABLE_ORDER_ITEM);
	    		inventoryItem.setPrice(12);
	    		inventoryItem.setCurrentStock(12);
	    		inventoryItem.setAvailability(false);
	    		return inventoryItem;
	    	} else if(invocation.getArgument(0).equals("besella")) {
	    		InventoryItem inventoryItem = new InventoryItem();
	    		inventoryItem.setName("besella");
	    		inventoryItem.setPrice(12);
	    		inventoryItem.setCurrentStock(17);
	    		return inventoryItem;
	    	}
	    	
	    	else {
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
	    	}
	    	else if(invocation.getArgument(0).equals(5)) {
	    		OrderItem orderItem = new OrderItem();
		        orderItem.setName("besella");
		        orderItem.setItemId(5);
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
    		InventoryItem inventoryItem2 = new InventoryItem();
    		inventoryItem2.setName("btngan");
    		inventoryItem2.setPrice(13);
    		inventoryItem.setCurrentStock(15);
            OrderItem orderItem = new OrderItem();
            orderItem.setName("kosa");
            orderItem.setPrice(12);
            OrderItem orderItem2 = new OrderItem();
            orderItem2.setName("kosa");
            orderItem2.setPrice(12);
            OrderItem orderItem3 = new OrderItem();
            orderItem3.setName("btngan");
            orderItem3.setPrice(13);
    		List<OrderItem> orderItems = new ArrayList<OrderItem>();
    		orderItems.add(orderItem);
    		orderItems.add(orderItem2);
    		orderItems.add(orderItem3);
	    		return orderItems;
	    });
	    
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(orderItemDao.save(any(OrderItem.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(inventoryItemDao.save(any(InventoryItem.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	//-------------------CreateOrderItem()---------------------------
	
	@Test
	public void testCreateOrderItem() {
		String name = "bateekh";
		InventoryItem inventoryItem = inventoryItemDao.findByName(name);
		OrderItem orderItem = null;
		try {
			orderItem = orderItemService.createOrderItem(name);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(orderItem);
		assertEquals(name, orderItem.getName());
		assertEquals(inventoryItem.getPrice(), orderItem.getPrice());
	}
	@Test
	public void testCreateOrderItemEmptyInventory() {
		
		String error = null;
		OrderItem orderItem = null;
		try {
			orderItem = orderItemService.createOrderItem(NON_EXISTING_ORDER_ITEM);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(orderItem);
		assertEquals(error, "No item exists in inventory named '" + NON_EXISTING_ORDER_ITEM + "'");
	}
	@Test
	public void testCreateOrderItemOutOfStock() {
		
		String error = null;
		OrderItem orderItem = null;
		try {
			orderItem = orderItemService.createOrderItem(NON_EXISTING_ORDER_ITEM2);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(orderItem);
		assertEquals(error, NON_EXISTING_ORDER_ITEM2 + " item is out of stock");
	}
	@Test
	public void testCreateOrderItemUnavailable() {
		
		String error = null;
		OrderItem orderItem = null;
		try {
			orderItem = orderItemService.createOrderItem(UNAVAILABLE_ORDER_ITEM);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		
		assertNull(orderItem);
		assertEquals(error, UNAVAILABLE_ORDER_ITEM + " item is not available for order");
	}
	
	//-------------------CancelOrderItem()---------------------------

	@Test
	public void testCancelOrderItem() {
		OrderItem deletedOrderItem = null;
		
		try {
			deletedOrderItem = orderItemService.cancelOrderItem(5);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNull(orderItemService.getOrderItemsByName(deletedOrderItem.getName()));
		assertNotNull(deletedOrderItem);
	}
	
	
	@Test
	public void testCancelNonExistingOrderItem() {
		String error = null;
		OrderItem deletedOrderItem = null;
		
		try {
			deletedOrderItem = orderItemService.cancelOrderItem(4);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(error);
		assertEquals(error,"No order item exists with that id");
		assertNull(deletedOrderItem);
	}
	
	//-------------------UpdateOrderItem()---------------------------

	@Test
	public void testUpdateOrderItem() {
		
		
		String name = "bateekh";
		
		int price = 9;
		OrderItem updatedOrderItem = null;
		
		try {
			updatedOrderItem = orderItemService.updateOrderItemInfo(name,price);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(updatedOrderItem);
		assertEquals(name, updatedOrderItem.getName());
		assertEquals(price, updatedOrderItem.getPrice());
	}
	
	
	@Test
	public void testUpdateOrderItemNull() {
		
		String error = null;
		String name = "manga";
		
		int price = 9;
		OrderItem updatedOrderItem = null;
		
		try {
			updatedOrderItem = orderItemService.updateOrderItemInfo(name,price);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(error);
		assertNull(updatedOrderItem);
		assertEquals("No such order item exists", error);
	}
	
	//-------------------GetMethods---------------------------
	
	//getAll()
	@Test
	public void testGetAllOrderItems() {
		
		List<OrderItem> orderItems = null;
		try {
			orderItems = orderItemService.getAllOrderItems();
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(orderItems);
		assertEquals(3, orderItems.size());
	}
	
	//getById()
	@Test
	public void testGetOrderItemById() {
		
		OrderItem orderItem = null;
		try {
			orderItem = orderItemService.getOrderItemByID(3);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(orderItem);
		assertEquals(orderItem.getName(), ORDER_ITEM_NAME);
		assertEquals(orderItem.getItemId(), 3);
	}
	
	
	//getByName()
	@Test
	public void testGetOrderItemsByName() {
		
		List<OrderItem> orderItems = null;
		try {
			orderItems = orderItemService.getOrderItemsByName(ORDER_ITEM_NAME);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(orderItems);
		assertEquals(1, orderItems.size());
		assertEquals(ORDER_ITEM_NAME, orderItems.get(0).getName());
	}
	
	//Non Existing
	@Test
	public void testGetNonExistingCustomer() {
		assertNull(orderItemService.getOrderItemsByName(NON_EXISTING_ORDER_ITEM));
	}
	
	//-------------------DeleteOrderItem()---------------------------
	
	@Test
	public void testDeleteOrderItem() {
		
		OrderItem deletedOrderItem = null;
		OrderItem orderItem = orderItemService.getOrderItemsByName(ORDER_ITEM_NAME).get(0);
		
		try {
			deletedOrderItem = orderItemService.deleteOrderItem(ORDER_ITEM_NAME);
			orderItem = orderItemService.getOrderItemByID(orderItem.getItemId());
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNull(orderItem);
		assertNotNull(deletedOrderItem);
	}
	
	
	

}