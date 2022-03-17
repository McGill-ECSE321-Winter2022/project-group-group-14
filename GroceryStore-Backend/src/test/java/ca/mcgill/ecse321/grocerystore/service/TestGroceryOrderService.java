package ca.mcgill.ecse321.grocerystore.service;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import java.sql.Time;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.grocerystore.dao.CustomerRepository;
import ca.mcgill.ecse321.grocerystore.dao.GroceryOrderRepository;
import ca.mcgill.ecse321.grocerystore.dao.GroceryStoreRepository;
import ca.mcgill.ecse321.grocerystore.dao.InventoryItemRepository;
import ca.mcgill.ecse321.grocerystore.dao.OrderItemRepository;
import ca.mcgill.ecse321.grocerystore.dao.StoreScheduleRepository;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderType;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule.Day;

/**
 * 
 * @author clarissabaciu
 *
 */
@ExtendWith(MockitoExtension.class)
public class TestGroceryOrderService {
	
	@Mock
	private GroceryOrderRepository orderDao;
	@Mock
	private CustomerRepository customerDao;
	@Mock
	private OrderItemRepository orderItemDao;
	@Mock
	private InventoryItemRepository inventoryItemDao;
	
	@InjectMocks 
	private GroceryOrderService orderService;
	
	private static final int testId = 1;
	private static final int testTotalCost = 4;
	private static final Customer testCustomer = new Customer();
	private static final Customer testCustomer2 = new Customer();
    private static final OrderItem testOrderItem1 = new OrderItem();
    private static final OrderItem testOrderItem2 = new OrderItem();
    private static final InventoryItem testInventoryItem = new InventoryItem();
    private static final ArrayList<OrderItem> testOrderItemsList = new ArrayList<OrderItem>();
    private static final OrderType testOrderType = OrderType.Delivery;
    private static final OrderStatus testOrderStatus = OrderStatus.Received;
    private static final String testItemName = "ketchup";
    

	@BeforeAll
	public static void instantiateVariables() {
		//initialize customer
		testCustomer.setAccountId(testId);
		testCustomer.setAddress("39 west town"); 		//not in town
		testCustomer.setEmail("customer@mail.com");
		testCustomer.setPassword("xxxxx");
		testCustomer.setUsername("customer");
		testCustomer.setPhoneNumber("5141234567");
		
		testCustomer2.setAccountId(testId+1);
		testCustomer2.setAddress("39 west town"); 		//not in town
		testCustomer2.setEmail("customer2@mail.com");
		testCustomer2.setPassword("xxxxx");
		testCustomer2.setUsername("customer2");
		testCustomer2.setPhoneNumber("5141234567");
		
		//initialize inventory item
		testInventoryItem.setAvailability(true);
		testInventoryItem.setCurrentStock(3);
		testInventoryItem.setItemId(testId);
		testInventoryItem.setName(testItemName);
		testInventoryItem.setPrice(2);
		//initialize order items
		testOrderItem1.setAvailability(true);
		testOrderItem1.setCurrentStock(3);
		testOrderItem1.setItemId(testId);
		testOrderItem1.setName(testItemName);
		testOrderItem1.setPrice(2);
		
		testOrderItem2.setAvailability(true);
		testOrderItem2.setCurrentStock(3);
		testOrderItem2.setItemId(testId+1);
		testOrderItem2.setName(testItemName);
		testOrderItem2.setPrice(2);
		
		//create list of order items	
		testOrderItemsList.add(0, testOrderItem1);
		testOrderItemsList.add(1, testOrderItem2);	
	}
	
	@BeforeEach
	public void setMockOutput() {
		
	    lenient().when(orderDao.findByOrderId(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(testId)) {
	            GroceryOrder order = new GroceryOrder();
	            order.setOrderId(testId);
	            order.setOrderStatus(testOrderStatus);
	            order.setOrderType(testOrderType);
	            order.setTotalCost(testTotalCost);
	            order.setCustomer(testCustomer);
	            order.setOrderItems(testOrderItemsList);
	            return order;
	        } else {
	            return null;
	        }  
	    });
	   
	    lenient().when(orderDao.findGroceryOrdersByCustomer(any(Customer.class))).thenAnswer((InvocationOnMock invocation) -> {
	        if (invocation.getArgument(0).equals(testCustomer))	
	        {
	            ArrayList<GroceryOrder> orderList = new ArrayList<>();
	            GroceryOrder order = new GroceryOrder();
	            order.setOrderId(testId);
	            order.setOrderStatus(testOrderStatus);
	            order.setOrderType(testOrderType);
	            order.setTotalCost(testTotalCost);
	            order.setCustomer(testCustomer);
	            order.setOrderItems(testOrderItemsList);
	            orderList.add(order);
	            return orderList;
	        } else
	        {
	            return null;
	        }
        });
	    
	    lenient().when(orderDao.findByOrderType(any(OrderType.class))).thenAnswer((InvocationOnMock invocation) -> {
	        if (invocation.getArgument(0).equals(testOrderType))	
	        {
	            ArrayList<GroceryOrder> orderList = new ArrayList<>();
	            GroceryOrder order = new GroceryOrder();
	            order.setOrderId(testId);
	            order.setOrderStatus(testOrderStatus);
	            order.setOrderType(testOrderType);
	            order.setTotalCost(testTotalCost);
	            order.setCustomer(testCustomer);
	            order.setOrderItems(testOrderItemsList);
	            orderList.add(order);
	            return orderList;
	        } else
	        {
	            return null;
	        }
        });
	    
	    lenient().when(orderDao.findByOrderStatus(any(OrderStatus.class))).thenAnswer((InvocationOnMock invocation) -> {
	        if (invocation.getArgument(0).equals(testOrderStatus))	
	        {
	            ArrayList<GroceryOrder> orderList = new ArrayList<>();
	            GroceryOrder order = new GroceryOrder();
	            order.setOrderId(testId);
	            order.setOrderStatus(testOrderStatus);
	            order.setOrderType(testOrderType);
	            order.setTotalCost(testTotalCost);
	            order.setCustomer(testCustomer);
	            order.setOrderItems(testOrderItemsList);
	            orderList.add(order);
	            return orderList;
	        } else
	        {
	            return null;
	        }
        });
	    
	    lenient().when(inventoryItemDao.findByName(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
	        if (invocation.getArgument(0).equals(testItemName))	
	        {
	            InventoryItem inventoryItem = new InventoryItem();
	    		inventoryItem.setAvailability(true);
	    		inventoryItem.setCurrentStock(3);
	    		inventoryItem.setItemId(testId);
	    		inventoryItem.setName(testItemName);
	    		inventoryItem.setPrice(2);
	            return inventoryItem;
	        } else
	        {
	            return null;
	        }
        });
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
	        return invocation.getArgument(0);
	    };
	    lenient().when(orderDao.save(any(GroceryOrder.class))).thenAnswer(returnParameterAsAnswer);
	    lenient().when(inventoryItemDao.save(any(InventoryItem.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(customerDao.existsById(anyInt())).thenReturn(true);
		lenient().when(orderItemDao.existsById(anyInt())).thenReturn(true);
		
	    

	   /**
	    * - need delete for order, orderitem
	    * 
	    */
	    
	    //orderDao.existsById(id)
	    lenient().when(orderDao.existsById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(testId)) {
	            return true;
	        } else {
	            return false;
	        }  
	    });
	    

	}
	
//-------------------------------------------------------CREATE METHODS------------------------------------------------------------
	
	
	// can create order where customer is not in town and total cost must be increased
	@Test
	public void testCreateOrder() {
		GroceryOrder order = null;
	    try{
	    	order = orderService.createOrder(testCustomer, testOrderItemsList, testOrderType);
	    }catch (IllegalArgumentException e) {
	    	System.out.println(e.getMessage());
	    	fail();
	    }
	    assertOrder(order);
	}

	@Test
	public void testCreateInStoreOrder() {
		GroceryOrder order = null;
	    try{
	    	order = orderService.createInStoreOrder(testOrderItemsList);
	    }catch (IllegalArgumentException e) {
	    	System.out.println(e.getMessage());
	    	fail();
	    }
	    assertInStoreOrder(order);
	}

//-------------------------------------------------------GET METHODS------------------------------------------------------------
	@Test
	public void testGetOrderByID()
	{
	    GroceryOrder order = null;
	    try
	    {
	        order = orderService.getOrderById(testId);

	    } catch (IllegalArgumentException e)
	    {
	        fail();
	    }
	    assertOrder(order);

	}
	
	@Test
	public void testGetOrdersByCustomer()
	{
	    List<GroceryOrder> orders = null;
	    try
	    {
	        orders = orderService.getOrdersByCustomer(testCustomer);

	    } catch (IllegalArgumentException e)
	    {
	        fail();
	    }
	    assertNotNull(orders);
	    assertTrue(orders.size() == 1);
	    assertOrder(orders.get(0));

	}
	
	@Test
	public void testGetOrdersByOrderStatus()
	{
	    List<GroceryOrder> orders = null;
	    try
	    {
	        orders = orderService.getOrdersByOrderStatus(testOrderStatus);

	    } catch (IllegalArgumentException e)
	    {
	        fail();
	    }
	    assertNotNull(orders);
	    assertTrue(orders.size() == 1);
	    assertOrder(orders.get(0));
	}
	
	@Test
	public void testGetOrdersByOrderType()
	{
	    List<GroceryOrder> orders = null;
	    try
	    {
	        orders = orderService.getOrdersByOrderType(testOrderType);

	    } catch (IllegalArgumentException e)
	    {
	        fail();
	    }
	    assertNotNull(orders);
	    assertTrue(orders.size() == 1);
	    assertOrder(orders.get(0));
	}
	
//	@Test
//	public void testGetAllOrders()
//	{
//	    List<GroceryOrder> orders = null;
//	    try
//	    {
//	        orders = orderService.getAllOrders();
//
//	    } catch (IllegalArgumentException e)
//	    {
//	        fail();
//	    }
//	    System.out.println(orders);
////	    assertTrue(orders.size() == 1);
//	    assertOrder(orders.get(0));
//	}
////	

//-------------------------------------------------------UPDATE & DELETE METHODS------------------------------------------------------------
	@Test
	public void updateOrder() {
		GroceryOrder orderBefore = null;
		GroceryOrder orderAfter = null;
	    try {
	    	orderBefore = orderService.createOrder(testCustomer2, testOrderItemsList, OrderType.PickUp); //create order with different attributes
	    	orderBefore.setOrderId(testId);//make sure both orders have the same id
            orderAfter = new GroceryOrder();
            orderAfter.setOrderId(testId);
            orderAfter.setOrderStatus(testOrderStatus);
            orderAfter.setOrderType(testOrderType);
            orderAfter.setTotalCost(testTotalCost);
            orderAfter.setCustomer(testCustomer);
            orderAfter.setOrderItems(testOrderItemsList);            
	    	orderService.modifyOrder(orderAfter);
	    } catch (IllegalArgumentException e) {
	        fail();
	    }
	    assertOrder(orderService.getOrderById(testId)); 	//order from before should now have the same properties as the orderAfter	
	}
	
//    @Test
//    public void testDelete()
//    {
//    	GroceryOrder order = null;
//    	GroceryOrder deleted = null;
//	    try{
//	    	order = orderService.createOrder(testCustomer, testOrderItemsList, testOrderType);
//	    	orderService.deleteOrder(order);
//	    	deleted = orderService.getOrderById(order.getOrderId());
//	    }catch (IllegalArgumentException e) {
//	    	System.out.println(e.getMessage());
//	    	fail();
//	    }
//	    
//        assertNull(deleted);
//       
//    }
//    
//-------------------------------------------------ASSERTION METHODS------------------------------------------------------------	
	private void assertOrder(GroceryOrder order) {
	    assertNotNull(order);
	    assertEquals(order.getOrderStatus(),OrderStatus.Received);
	    assertEquals(order.getTotalCost(),testTotalCost);
	    assertEquals(order.getCustomer().getAccountId(),testCustomer.getAccountId());
	    assertFalse(order.getOrderItems().isEmpty());
        assertEquals(order.getOrderItems().get(0).getItemId(),testOrderItem1.getItemId());
        assertEquals(order.getOrderItems().get(1).getItemId(),testOrderItem2.getItemId());
	}
	
	private void assertInStoreOrder(GroceryOrder order) {
	    assertNotNull(order);
	    assertEquals(order.getOrderType(),OrderType.InStore);
	    assertEquals(order.getOrderStatus(),OrderStatus.Received);
	    assertEquals(order.getTotalCost(),testTotalCost);
	    assertNull(order.getCustomer());	//grocery order should not have a customer
	    assertFalse(order.getOrderItems().isEmpty());
        assertEquals(order.getOrderItems().get(0).getItemId(),testOrderItem1.getItemId());
        assertEquals(order.getOrderItems().get(1).getItemId(),testOrderItem2.getItemId());
	}

	
	
}
