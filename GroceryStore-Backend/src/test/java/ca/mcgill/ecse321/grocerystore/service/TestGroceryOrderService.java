//package ca.mcgill.ecse321.grocerystore.service;
//import java.util.List;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.lenient;
//import java.util.ArrayList;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.stubbing.Answer;
//
//import ca.mcgill.ecse321.grocerystore.dao.CustomerRepository;
//import ca.mcgill.ecse321.grocerystore.dao.GroceryOrderRepository;
//import ca.mcgill.ecse321.grocerystore.dao.InventoryItemRepository;
//import ca.mcgill.ecse321.grocerystore.dao.OrderItemRepository;
//import ca.mcgill.ecse321.grocerystore.model.Customer;
//import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
//import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderStatus;
//import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderType;
//import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
//import ca.mcgill.ecse321.grocerystore.model.OrderItem;
//
///**
// * 
// * @author clarissabaciu
// *
// */
//@ExtendWith(MockitoExtension.class)
//public class TestGroceryOrderService {
//	
//	@Mock
//	private GroceryOrderRepository orderDao;
//	@Mock
//	private CustomerRepository customerDao;
//	@Mock
//	private OrderItemRepository orderItemDao;
//	@Mock
//	private InventoryItemRepository inventoryItemDao;
//	
//	@InjectMocks 
//	private GroceryOrderService orderService;
//	
//	private static final int testId = 1;
//	private static final int testTotalCost = 4;
//	private static final Customer testCustomer = new Customer();
//	private static final Customer testCustomer2 = new Customer();
//	private static final Customer inTownCustomer = new Customer();
//    private static final OrderItem testOrderItem1 = new OrderItem();
//    private static final OrderItem testOrderItem2 = new OrderItem();
//    private static final OrderItem testOrderItem3 = new OrderItem();
//    private static final InventoryItem testInventoryItem = new InventoryItem();
//    private static final ArrayList<OrderItem> testOrderItemsList = new ArrayList<OrderItem>();
//    private static final OrderType testOrderType = OrderType.Delivery;
//    private static final OrderStatus testOrderStatus = OrderStatus.Received;
//    private static final String testItemName = "ketchup";
//    
//
//	@BeforeAll
//	public static void instantiateVariables() {
//		//initialize customer
//		testCustomer.setAccountId(testId);
//		testCustomer.setAddress("39 west town"); 		//not in town
//		testCustomer.setEmail("customer@mail.com");
//		testCustomer.setPassword("xxxxx");
//		testCustomer.setUsername("customer");
//		testCustomer.setPhoneNumber("5141234567");
//		
//		testCustomer2.setAccountId(testId+1);
//		testCustomer2.setAddress("39 west town"); 		//not in town
//		testCustomer2.setEmail("customer2@mail.com");
//		testCustomer2.setPassword("xxxxx");
//		testCustomer2.setUsername("customer2");
//		testCustomer2.setPhoneNumber("5141234567");
//		
//		inTownCustomer.setAccountId(testId+3);
//		inTownCustomer.setAddress("39 west Storiko"); 		//in town
//		inTownCustomer.setEmail("custome3@mail.com");
//		inTownCustomer.setPassword("xxxxx");
//		inTownCustomer.setUsername("customer3");
//		inTownCustomer.setPhoneNumber("5141234567");
//		
//		
//		
//		//initialize inventory item
//		testInventoryItem.setAvailability(true);
//		testInventoryItem.setCurrentStock(3);
//		testInventoryItem.setItemId(testId);
//		testInventoryItem.setName(testItemName);
//		testInventoryItem.setPrice(2);
//		//initialize order items
//	
//		testOrderItem1.setItemId(testId);
//		testOrderItem1.setName(testItemName);
//		testOrderItem1.setPrice(2);
//		
//
//		testOrderItem2.setItemId(testId+1);
//		testOrderItem2.setName(testItemName);
//		testOrderItem2.setPrice(2);
//	
//		testOrderItem3.setItemId(5);
//		testOrderItem3.setName("Potato");
//		testOrderItem3.setPrice(3);
//		
//		//create list of order items	
//		testOrderItemsList.add(0, testOrderItem1);
//		testOrderItemsList.add(1, testOrderItem2);	
//	}
//	
//	@BeforeEach
//	public void setMockOutput() {
//		
//	    lenient().when(orderDao.findByOrderId(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {    
//	            GroceryOrder order = new GroceryOrder();
//	            order.setOrderId(testId);
//	            order.setOrderStatus(testOrderStatus);
//	            order.setOrderType(testOrderType);
//	            order.setTotalCost(testTotalCost);
//	            order.setCustomer(testCustomer);
//	            order.setOrderItems(testOrderItemsList);
//	            return order;
//	    });
//	    
//	    lenient().when(customerDao.findByEmail(any(String.class))).thenAnswer( (InvocationOnMock invocation) -> {
//	    	 if (invocation.getArgument(0).equals("customer@mail.com"))	
//		        {
//		            Customer customer = new Customer(); 
//		    		testCustomer.setAccountId(testId);
//		    		testCustomer.setAddress("39 west town"); 		//not in town
//		    		testCustomer.setEmail("customer@mail.com");
//		    		testCustomer.setPassword("xxxxx");
//		    		testCustomer.setUsername("customer");
//		    		testCustomer.setPhoneNumber("5141234567");
//		            return customer;
//		        } else
//		        {
//		            return null;
//		        }
//	    });
//	    
//	   
//	    lenient().when(orderDao.findGroceryOrdersByCustomer(any(Customer.class))).thenAnswer((InvocationOnMock invocation) -> {
//	        if (invocation.getArgument(0).equals(testCustomer))	
//	        {
//	            ArrayList<GroceryOrder> orderList = new ArrayList<>();
//	            GroceryOrder order = new GroceryOrder();
//	            order.setOrderId(testId);
//	            order.setOrderStatus(testOrderStatus);
//	            order.setOrderType(testOrderType);
//	            order.setTotalCost(testTotalCost);
//	            order.setCustomer(testCustomer);
//	            order.setOrderItems(testOrderItemsList);
//	            orderList.add(order);
//	            return orderList;
//	        } else //if any other customer return null
//	        {
//	            return new ArrayList<>();
//	        }
//        });
//
//	    
//	    lenient().when(orderDao.findByOrderType(any(OrderType.class))).thenAnswer((InvocationOnMock invocation) -> {
//	        if (invocation.getArgument(0).equals(testOrderType))	
//	        {
//	            ArrayList<GroceryOrder> orderList = new ArrayList<>();
//	            GroceryOrder order = new GroceryOrder();
//	            order.setOrderId(testId);
//	            order.setOrderStatus(testOrderStatus);
//	            order.setOrderType(testOrderType);
//	            order.setTotalCost(testTotalCost);
//	            order.setCustomer(testCustomer);
//	            order.setOrderItems(testOrderItemsList);
//	            orderList.add(order);
//	            return orderList;
//	        } else
//	        {
//	            return null;
//	        }
//        });
//	    
//	    lenient().when(inventoryItemDao.findByName(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
//	        if (invocation.getArgument(0).equals(testItemName))	
//	        {
//	            InventoryItem inventoryItem = new InventoryItem();
//	    		inventoryItem.setAvailability(true);
//	    		inventoryItem.setCurrentStock(3);
//	    		inventoryItem.setItemId(testId);
//	    		inventoryItem.setName(testItemName);
//	    		inventoryItem.setPrice(2);
//	            return inventoryItem;
//	        } else
//	        {
//	            return null;
//	        }
//        });
//	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
//	        return invocation.getArgument(0);
//	    };
//	    lenient().when(orderDao.save(any(GroceryOrder.class))).thenAnswer(returnParameterAsAnswer);
//	    lenient().when(inventoryItemDao.save(any(InventoryItem.class))).thenAnswer(returnParameterAsAnswer);
//		lenient().when(customerDao.existsById(anyInt())).thenReturn(true);
//		lenient().when(orderDao.existsById(anyInt())).thenReturn(true);
//		lenient().when(orderItemDao.existsById(anyInt())).thenReturn(true);
//		
//	    
//
//	   /**
//	    * - need delete for order, orderitem
//	    * 
//	    */
//	    
//	    //orderDao.existsById(id)
//	    lenient().when(orderDao.existsById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
//	        if(invocation.getArgument(0).equals(testId)) {
//	            return true;
//	        } else {
//	            return false;
//	        }  
//	    });
//	    
//
//	}
//	
////-------------------------------------------------------CREATE METHODS------------------------------------------------------------
//	
//	
//	// can create order where customer is not in town and total cost must be increased
//	@Test
//	public void testCreateDeliveryOrder() {
//		GroceryOrder order = null;
//
//		GroceryOrder order3 = null;
//	    try{
//	    	order = orderService.createDeliveryOrder(testCustomer2);
//		    assertNotNull(order);
//		    assertEquals(order.getOrderStatus(),OrderStatus.Received);
//		    assertEquals(order.getCustomer().getAccountId(),testCustomer2.getAccountId());
//		    assertTrue(order.getOrderItems().isEmpty());
//		    assertTrue(order.getOrderType().equals(OrderType.Delivery));
//		    assertEquals(order.getTotalCost(),10); //since customer is out of town, delivery fee should be 10
//		    	    
//		    order3 = orderService.createDeliveryOrder(inTownCustomer);
//		    assertTrue(order3.getOrderType().equals(OrderType.Delivery));
//		    assertEquals(order3.getTotalCost(),0); //since customer is in town, delivery fee should not appear
//		    
//	    	
//	    }catch (IllegalArgumentException e) {
//	    	System.out.println(e.getMessage());
//	    	fail();
//	    }
//	}
//	
//	@Test
//	public void testCreatePickupOrder(){
//		GroceryOrder order2 = null;
//	    try{
//	        order2 = orderService.createPickupOrder(testCustomer2);
//	        assertTrue(order2.getOrderType().equals(OrderType.PickUp));
//	        assertTrue(order2.getTotalCost() == 0); 	//pick up order should not have a delivery fee
//	    }catch (IllegalArgumentException e) {
//	    	System.out.println(e.getMessage());
//	    	fail();
//	    }
//	}
//
//	@Test
//	public void testCreateSecondOrder() {
//		GroceryOrder order = null;
//		GroceryOrder order2 = null;
//	    try{
//	    	order = orderService.createDeliveryOrder(testCustomer);
//		    order2 = orderService.createDeliveryOrder(testCustomer);
//	    	
//	    }catch (IllegalArgumentException e) {
//	    	assertEquals(e.getMessage(),"Please complete your order before creating a new one." );
//	    }
//	   
//	}
//	
//	@Test
//	public void testAddToDeliveryOrderWithFee() {
//		GroceryOrder order = null;
//	    try{
//	    	order = orderService.createDeliveryOrder(testCustomer2);
//	    	assertEquals(order.getTotalCost(),10); 
//	    	order.setOrderId(testId);
//	    	order = orderService.addOrderItems(order, testOrderItemsList);
//	    	assertEquals(order.getOrderItems(),testOrderItemsList); 
//	    	assertEquals(order.getTotalCost(),14); 
//	    	assertEquals(order.getTotalCost(),14); 
//	    	assertEquals(OrderStatus.Received,order.getOrderStatus());
//	    	order = orderService.placeOrder(order);
//	    	assertEquals(OrderStatus.Processing,order.getOrderStatus());
//	    }catch (IllegalArgumentException e) {
//	    	System.out.println(e.getMessage());
//	    	fail();
//	    }
//	}
//	
//	@Test
//	public void testAddToDeliveryOrderWithoutFee() {
//		GroceryOrder order3 = null;
//	    try{
//	    	order3 = orderService.createDeliveryOrder(inTownCustomer);
//	    	order3.setOrderId(testId);
//	    	assertEquals(order3.getTotalCost(),0); 
//	    	order3.setOrderId(testId);
//	    	order3 = orderService.addOrderItems(order3, testOrderItemsList);
//	    	assertEquals(order3.getOrderItems(),testOrderItemsList); 
//	    	assertEquals(order3.getTotalCost(),4); 
//	    	assertEquals(OrderStatus.Received,order3.getOrderStatus());
//	    	order3 = orderService.placeOrder(order3);
//	    	assertEquals(OrderStatus.Processing,order3.getOrderStatus());
//	    }catch (IllegalArgumentException e) {
//	    	System.out.println(e.getMessage());
//	    	fail();
//	    }
//	}
//	
//	@Test
//	public void testAddToPickUpOrder() {
//		GroceryOrder order3 = null;
//	    try{
//	    	order3 = orderService.createPickupOrder(inTownCustomer);
//	    	order3.setOrderId(testId);
//	    	assertEquals(order3.getTotalCost(),0); 
//	    	order3.setOrderId(testId);
//	    	order3 = orderService.addOrderItems(order3, testOrderItemsList);
//	    	assertEquals(order3.getOrderItems(),testOrderItemsList); 
//	    	assertEquals(order3.getTotalCost(),4); 
//	    	assertEquals(OrderStatus.Received,order3.getOrderStatus());
//	    	order3 = orderService.placeOrder(order3);
//	    	assertEquals(OrderStatus.Processing,order3.getOrderStatus());
//	    }catch (IllegalArgumentException e) {
//	    	System.out.println(e.getMessage());
//	    	fail();
//	    }
//	}
//	
////	@Test
////	public void testToggleOrderType(){
////		GroceryOrder order = null;
////	    try{
////	    	order = orderService.createDeliveryOrder(testCustomer2);
////	    	assertEquals(order.getTotalCost(),10); 
////	    	order.setOrderId(testId);
////	    	order = orderService.addOrderItems(order, testOrderItemsList);
////	    	assertEquals(order.getTotalCost(),14); 
////	    	assertEquals(OrderType.Delivery,order.getOrderType());
////	    	orderService.toggleOrderType(order);
////	    	assertEquals(OrderType.PickUp,order.getOrderType());
////	    	assertEquals(order.getTotalCost(),4);  //10$ fee should have been removed 
////	    	orderService.toggleOrderType(order);
////	    	assertEquals(OrderType.Delivery,order.getOrderType());
////	    	assertEquals(order.getTotalCost(),14);  //10$ fee should have been added back 
////	    	order = orderService.placeOrder(order);
////
////	    }catch (IllegalArgumentException e) {
////	    	System.out.println(e.getMessage());
////	    	fail();
////	    }
////		
////	}
//	
//	
//	@Test
//	public void testCreateNullCustomerOrder() {
//		GroceryOrder order = null;
//	    try{
//	    	order = orderService.createDeliveryOrder(null);
//	    	order = orderService.addOrderItems(order, testOrderItemsList);
//	    	assertEquals(order.getOrderItems(),testOrderItemsList); 
//	    	assertEquals(OrderStatus.Received,order.getOrderStatus());
//	    	order = orderService.placeOrder(order);
//	    	assertEquals(OrderStatus.Completed,order.getOrderStatus());
//	    	
//	    }catch (IllegalArgumentException e) {
//	    	  assertEquals(e.getMessage(),"Please submit a valid user object." );
//	    }
//	  
//	}
//	
//
//	@Test
//	public void testCreateAndAddToInStoreOrder() {
//		GroceryOrder order = null;
//	    try{
//	    	order = orderService.createInStoreOrder();
//	    	order.setOrderId(testId);
//	    	order = orderService.addOrderItems(order, testOrderItemsList);
//	    	assertEquals(OrderStatus.Received,order.getOrderStatus());
//	    	assertEquals(4,order.getTotalCost());
//	    	assertInStoreOrder(order);
//	    	order = orderService.placeOrder(order);
//	    	assertEquals(OrderStatus.Completed,order.getOrderStatus());
//	    }catch (IllegalArgumentException e) {
//	    	System.out.println(e.getMessage());
//	    	fail();
//	    }
//	    
//	}
//
////-------------------------------------------------------GET METHODS------------------------------------------------------------
//
//	
//	
////	@Test
////	public void testGetOrderByID()
////	{
////	    GroceryOrder order = null;
////	    try
////	    {
////	        order = orderService.getOrderById(testId);
////	    } catch (IllegalArgumentException e)
////	    {
////	        fail();
////	    }
////	    assertOrder(order, true);
////	}
//	
//	@Test
//	public void testGetNonExistingOrderId() {
//		try {
//			
//		} catch (IllegalArgumentException e) {
//			assertEquals(e.getMessage(),"Please submit a valid order ID." );
//		}	
//	}
//	
//	@Test
//	public void testGetOrderItems()
//	{	
//		GroceryOrder order = null;
//	    List<OrderItem> items = null;
//	    try
//	    {	
//	    	order = orderService.createDeliveryOrder(testCustomer2);
//	    	assertEquals(order.getTotalCost(),10); 
//	    	order.setOrderId(testId);
//	    	order = orderService.addOrderItems(order, testOrderItemsList); 
//	    	items = orderService.getOrderItems(order);
//	    	assertEquals(items, testOrderItemsList);
//	    	
//
//	    } catch (IllegalArgumentException e)
//	    {
//	        fail();
//	    }
//	}
//	
//	
//	
//	@Test
//	public void testGetOrdersByCustomer()
//	{
//	    List<GroceryOrder> orders = null;
//	    try
//	    {
//	        orders = orderService.getOrdersByCustomer(testCustomer);
//
//	    } catch (IllegalArgumentException e)
//	    {
//	        fail();
//	    }
//	    assertNotNull(orders);
//	    assertTrue(orders.size() == 1);
//	    assertOrder(orders.get(0), true);
//
//	}
//	
//	@Test
//	public void testGetOrdersByOrderStatus()
//	{
//	    
//	    lenient().when(orderDao.findByOrderStatus(any(OrderStatus.class))).thenAnswer((InvocationOnMock invocation) -> {
//	        if (invocation.getArgument(0).equals(testOrderStatus))	
//	        {
//	            ArrayList<GroceryOrder> orderList = new ArrayList<>();
//	            GroceryOrder order = new GroceryOrder();
//	            order.setOrderId(testId);
//	            order.setOrderStatus(testOrderStatus);
//	            order.setOrderType(testOrderType);
//	            order.setTotalCost(testTotalCost);
//	            order.setCustomer(testCustomer);
//	            order.setOrderItems(testOrderItemsList);
//	            orderList.add(order);
//	            return orderList;
//	        } else
//	        {
//	            return null;
//	        }
//        });
//
//	    List<GroceryOrder> orders = null;
//	    try
//	    {
//	        orders = orderService.getOrdersByOrderStatus(testOrderStatus);
//
//	    } catch (IllegalArgumentException e)
//	    {
//	        fail();
//	    }
//	    assertNotNull(orders);
//	    assertTrue(orders.size() == 1);
//	    assertOrder(orders.get(0), true);
//	}
//	
//	@Test
//	public void testGetOrdersByOrderType()
//	{
//	    List<GroceryOrder> orders = null;
//	    try
//	    {
//	        orders = orderService.getOrdersByOrderType(testOrderType);
//
//	    } catch (IllegalArgumentException e)
//	    {
//	        fail();
//	    }
//	    assertNotNull(orders);
//	    assertTrue(orders.size() == 1);
//	    assertOrder(orders.get(0), true);
//	}
//	
////	@Test
////	public void testGetAllOrders()
////	{
////	   lenient().when(orderDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
////            ArrayList<GroceryOrder> orderList = new ArrayList<>();
////            GroceryOrder order = new GroceryOrder();
////            order.setOrderId(testId);
////            order.setOrderStatus(testOrderStatus);
////            order.setOrderType(testOrderType);
////            order.setTotalCost(testTotalCost);
////            order.setCustomer(testCustomer);
////            order.setOrderItems(testOrderItemsList);
////            orderList.add(order);
////            return orderList;
////        });
////	        
////	    List<GroceryOrder> orders = null;
////	    try
////	    {
////	        orders = orderService.getAllOrders();
////
////	    } catch (IllegalArgumentException e)
////	    {
////	        fail();
////	    }
////	    System.out.println(orders);
//////	    assertTrue(orders.size() == 1);
////	    assertOrder(orders.get(0), true);
////	}
////	
//
////-------------------------------------------------------UPDATE & DELETE METHODS------------------------------------------------------------
////
//	
//	@Test
//	public void testDeleteItemFromOrder() {
//		GroceryOrder order = null;
//	    try{
//	    	order = orderService.createDeliveryOrder(testCustomer2);
//	    	assertEquals(order.getTotalCost(),10); 
//	    	order.setOrderId(testId);
//	    	order = orderService.addOrderItems(order, testOrderItemsList);
//	    	assertEquals(order.getTotalCost(),14); 
//	    	order = orderService.deleteItemFromOrder(order, testOrderItemsList.get(0).getName());
//	    	
//	    	assertEquals(order.getTotalCost(), 12);
//
//	    }catch (IllegalArgumentException e) {
//	    	System.out.println(e.getMessage());
//	    	fail();
//	    }
//	}
//	
//	
//	
//	
////	@Test
////	public void updateOrder() {
////		GroceryOrder orderBefore = null;
////		GroceryOrder orderAfter = null;
////	    try {
////	    	orderBefore = orderService.createPickupOrder(testCustomer2);
////	    	orderBefore = orderService.addOrderItems(orderBefore, testOrderItemsList);
////	    	orderBefore = orderService.placeOrder(orderBefore);
////	    	
////	    	orderBefore.setOrderId(testId);//make sure both orders have the same id
////            orderAfter = new GroceryOrder();
////            orderAfter.setOrderId(testId);
////            orderAfter.setOrderStatus(testOrderStatus);
////            orderAfter.setOrderType(testOrderType);
////            orderAfter.setTotalCost(testTotalCost);
////            orderAfter.setCustomer(testCustomer);
////            orderAfter.setOrderItems(testOrderItemsList);            
////	    	orderService.modifyOrder(orderAfter);
////	    } catch (IllegalArgumentException e) {
////	        fail();
////	    }
////	    assertOrder(orderService.getOrderById(testId), true); 	//order from before should now have the same properties as the orderAfter	
////	}
////	
//
////SINCE WE ARE USING A MOCK DATABASE  IT IS IMPOSSIBLE TO THE DELETE METHODS. 
//	
////    @Test
////    public void testDelete()
////    {
////    	GroceryOrder order = null;
////    	GroceryOrder deleted = null;
////	    try{
////	    	order = orderService.createDeliveryOrder(testCustomer);
////	    	order = orderService.addOrderItems(order, testOrderItemsList);
////	    	order = orderService.placeOrder(order);
////	    	order.setOrderId(testId);
////	    	deleted = orderService.deleteOrder(order);
////	    	
////		    assertEquals(deleted.getCustomer().getAccountId(),testCustomer.getAccountId());
////		    assertFalse(deleted.getOrderItems().isEmpty());
////	        assertEquals(deleted.getOrderItems().get(0).getItemId(),testOrderItem1.getItemId());
////	        assertEquals(deleted.getOrderItems().get(1).getItemId(),testOrderItem2.getItemId());
////	    }catch (IllegalArgumentException e) {
////	    	System.out.println(e.getMessage());
////	    	fail();
////	    } 
////    }
////    
////    @Test
////    public void testDeleteAllCompletedOrders()
////    {
////    	GroceryOrder order = null;
////	    try{
////	    	order = orderService.createDeliveryOrder(testCustomer);
////	    	order.setOrderId(testId);
////	    	order = orderService.addOrderItems(order, testOrderItemsList);
////	    	order = orderService.placeOrder(order);
////	    	order.setOrderStatus(OrderStatus.Completed); // this method deletes all completed orders
////	    	orderService.deleteAllCompletedOrders();
////	    }catch (IllegalArgumentException e) {
////	    	fail();
////	    }
////    }
////    
////    @Test
////    public void testNoCompletedOrders()
////    {
////	    lenient().when(orderDao.findByOrderStatus(OrderStatus.Completed)).thenAnswer((InvocationOnMock invocation) -> {
////	    	return null;
////        });
////    	GroceryOrder order = null;
////	    try{
////	    	order = orderService.createDeliveryOrder(testCustomer);
////	    	order = orderService.addOrderItems(order, testOrderItemsList);
////	    	order = orderService.placeOrder(order);
////	    	order.setOrderId(testId);
////	    	orderService.deleteAllCompletedOrders();
////	    }catch (IllegalArgumentException e) {
////	    	assertEquals(e.getMessage().toString(), "List of orders for order status completed is null." ); //since they have been deleted
////	    }
////    }
//
//
////-------------------------------------------------EXTRA METHODS-------------------------------------------------------
//    
//    
////	@Test
////	public void testViewTotalSales() {	 //view total sales
////
////		int totalSales = 0;
////		try {
////			assertEquals(totalSales,0);
////			lenient().when(orderDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
////				GroceryOrder orderDel = null;
////				GroceryOrder orderInStore = null;
////				ArrayList<GroceryOrder> orders = new ArrayList<>();
////				orderDel = orderService.createDeliveryOrder(testCustomer2);
////				orderDel.setOrderId(testId);
////				orderDel = orderService.addOrderItems(orderDel, testOrderItemsList);
////				orderDel = orderService.placeOrder(orderDel);
////				orderInStore = orderService.createInStoreOrder();
////				orderInStore = orderService.addOrderItems(orderInStore, testOrderItemsList);
////		        orders.add(orderDel);
////		        orders.add(orderInStore);
////				return orders;
////			    });
////			
////			totalSales = orderService.viewTotalSales();
////			assertEquals(totalSales,18); //should add up to 18		
////		}catch (IllegalArgumentException e) {
////	    	System.out.println(e.getMessage());
////	    	fail();
////	    }
////
////	}
//
//    
//	@Test
//	public void testUpdateOrderStatusDelivery() {	 //total sales only for completed orders     
//		GroceryOrder orderDel = null;
//	    try{
//	    	orderDel = orderService.createDeliveryOrder(testCustomer2);
//			orderDel.setOrderId(testId);
//			orderDel = orderService.addOrderItems(orderDel, testOrderItemsList);
//			orderDel = orderService.placeOrder(orderDel);
//			
//			orderService.updateOrderStatus(orderDel);
//			assertEquals(orderDel.getOrderStatus(), OrderStatus.BeingDelivered);
//			orderService.updateOrderStatus(orderDel);
//			assertEquals(orderDel.getOrderStatus(), OrderStatus.Completed);
//	    
//	
//	    }catch (IllegalArgumentException e) {
//	    	System.out.println(e.getMessage());
//	    	fail();
//	    }
//	}
//	
//	@Test
//	public void testUpdateOrderStatusPickup() {	 //total sales only for completed orders     
//		GroceryOrder orderDel = null;
//	    try{
//	    	orderDel = orderService.createPickupOrder(testCustomer2);
//			orderDel.setOrderId(testId);
//			orderDel = orderService.addOrderItems(orderDel, testOrderItemsList);
//			orderDel = orderService.placeOrder(orderDel);
//			
//			orderService.updateOrderStatus(orderDel);
//			assertEquals(orderDel.getOrderStatus(), OrderStatus.ReadyForPickUp);
//			orderService.updateOrderStatus(orderDel);
//			assertEquals(orderDel.getOrderStatus(), OrderStatus.Completed);
//	    }catch (IllegalArgumentException e) {
//	    	System.out.println(e.getMessage());
//	    	fail();
//	    }
//	}
//	
//	@Test
//	public void testUpdateOrderStatusNotPlaced() {	 //total sales only for completed orders     
//		GroceryOrder orderDel = null;
//	    try{
//	    	orderDel = orderService.createPickupOrder(testCustomer2);
//			orderDel.setOrderId(testId);
//			orderDel = orderService.addOrderItems(orderDel, testOrderItemsList);
//			orderService.updateOrderStatus(orderDel);
//	    }catch (IllegalArgumentException e) {
//	    	System.out.println(e.getMessage());
//	    	assertEquals(e.getMessage(), "Customer is still adding items to order. Cannot start preparation.");
//	    }
//	}
//	
//	
//	@Test
//	public void testViewOrderStatus() {	 
//		GroceryOrder orderDel = null;
//	    try{
//	    	orderDel = orderService.createDeliveryOrder(testCustomer2);
//			orderDel.setOrderId(testId);
//			orderDel = orderService.addOrderItems(orderDel, testOrderItemsList);
//			orderDel = orderService.placeOrder(orderDel);
//			orderService.updateOrderStatus(orderDel); 
//
//	    	OrderStatus status = orderService.viewOrderStatus(orderDel);
//	    	assertEquals(status, OrderStatus.BeingDelivered);
//	    }catch (IllegalArgumentException e) {
//	    	System.out.println(e.getMessage());
//	    	fail();
//	    }
//	}
//	
//	@Test
//	public void testOrderStatusIsNull() {	 
//		GroceryOrder orderDel = null;
//	    try{
//	    	orderDel = orderService.createDeliveryOrder(testCustomer2);
//			orderDel.setOrderId(testId);
//			orderDel = orderService.addOrderItems(orderDel, testOrderItemsList);
//			orderDel = orderService.placeOrder(orderDel);
//	    	orderDel.setOrderStatus(null);
//	    	OrderStatus status = orderService.viewOrderStatus(orderDel);
//	    }catch (IllegalArgumentException e) {
//	    	assertEquals(e.getMessage(),"Order has no status.");
//	    }
//	}
////	
//	//will have to add extra enumeration to class
//	
//
//     
////    
////-------------------------------------------------ASSERTION METHODS------------------------------------------------------------	
//	private void assertOrder(GroceryOrder order, boolean inTown) {
//	    assertNotNull(order);
//	    assertEquals(order.getOrderStatus(),OrderStatus.Received);
//	    if (inTown) {
//	    	assertEquals(order.getTotalCost(),testTotalCost);
//	    }else {
//	    	assertEquals(order.getTotalCost(),testTotalCost+10); //extra fee if out of town
//	    }
//	    
//	    assertEquals(order.getCustomer().getAccountId(),testCustomer.getAccountId());
//	    assertFalse(order.getOrderItems().isEmpty());
//        assertEquals(order.getOrderItems().get(0).getItemId(),testOrderItem1.getItemId());
//        assertEquals(order.getOrderItems().get(1).getItemId(),testOrderItem2.getItemId());
//	}
//	
//	private void assertInStoreOrder(GroceryOrder order) {
//	    assertNotNull(order);
//	    assertEquals(order.getOrderType(),OrderType.InStore);
//	    assertEquals(order.getOrderStatus(),OrderStatus.Received);
//	    assertEquals(order.getTotalCost(),testTotalCost);
//	    assertNull(order.getCustomer());	//grocery order should not have a customer
//	    assertFalse(order.getOrderItems().isEmpty());
//        assertEquals(order.getOrderItems().get(0).getItemId(),testOrderItem1.getItemId());
//        assertEquals(order.getOrderItems().get(1).getItemId(),testOrderItem2.getItemId());
//	}
//
//	
//	
//}
