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
import static org.mockito.Mockito.lenient;
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
import ca.mcgill.ecse321.grocerystore.dao.InventoryItemRepository;
import ca.mcgill.ecse321.grocerystore.dao.OrderItemRepository;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderType;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;

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
    private static final OrderItem testOrderItem3 = new OrderItem();
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
	
		testOrderItem1.setItemId(testId);
		testOrderItem1.setName(testItemName);
		testOrderItem1.setPrice(2);
		

		testOrderItem2.setItemId(testId+1);
		testOrderItem2.setName(testItemName);
		testOrderItem2.setPrice(2);
	
		testOrderItem3.setItemId(5);
		testOrderItem3.setName("Potato");
		testOrderItem3.setPrice(3);
		
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
	    assertOrder(order, false);
	}
	
	@Test
	public void testCreateNullCustomerOrder() {
		GroceryOrder order = null;
	    try{
	    	order = orderService.createOrder(null, testOrderItemsList, testOrderType);
	    }catch (IllegalArgumentException e) {
	    	  assertEquals(e.getMessage(),"Please select a proper customer." );
	    }
	  
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
	    assertOrder(order, true);

	}
	
	@Test
	public void testGetNonExistingOrderId() {
		try {
			
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(),"Please submit a valid order ID." );
		}	
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
	    assertOrder(orders.get(0), true);

	}
	
	@Test
	public void testGetOrdersByOrderStatus()
	{
	    
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
	    assertOrder(orders.get(0), true);
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
	    assertOrder(orders.get(0), true);
	}
	
	@Test
	public void testGetAllOrders()
	{
	   lenient().when(orderDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
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
        });
	        
	    List<GroceryOrder> orders = null;
	    try
	    {
	        orders = orderService.getAllOrders();

	    } catch (IllegalArgumentException e)
	    {
	        fail();
	    }
	    System.out.println(orders);
//	    assertTrue(orders.size() == 1);
	    assertOrder(orders.get(0), true);
	}
//	

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
	    assertOrder(orderService.getOrderById(testId), true); 	//order from before should now have the same properties as the orderAfter	
	}
	

	    
	
	
	
    @Test
    public void testDelete()
    {
    	GroceryOrder order = null;
    	GroceryOrder deleted = null;
	    try{
	    	order = orderService.createOrder(testCustomer, testOrderItemsList, testOrderType);
	    	order.setOrderId(testId);
	    	deleted = orderService.deleteOrder(order);
	    }catch (IllegalArgumentException e) {
	    	System.out.println(e.getMessage());
	    	fail();
	    }
	    assertOrder(deleted, false);
    }
    
    @Test
    public void testDeleteAll()
    {
    	GroceryOrder order = null;
	    try{
	    	order = orderService.createOrder(testCustomer, testOrderItemsList, testOrderType);
	    	order.setOrderId(testId);
	    	order.setOrderStatus(OrderStatus.Completed); // this method deletes all completed orders
	    	orderService.deleteAllCompletedOrders();
	    }catch (IllegalArgumentException e) {
	    	assertEquals(e.getMessage().toString(), "List of orders for order status completed is null." ); //since they have been deleted
	    }
    }
    
    @Test
    public void testDeleteAllNoCompletedOrders()
    {
	    lenient().when(orderDao.findByOrderStatus(OrderStatus.Completed)).thenAnswer((InvocationOnMock invocation) -> {
	    	return null;
        });
    	GroceryOrder order = null;
	    try{
	    	order = orderService.createOrder(testCustomer, testOrderItemsList, testOrderType);
	    	order.setOrderId(testId);
	    	orderService.deleteAllCompletedOrders();
	    }catch (IllegalArgumentException e) {
	    	assertEquals(e.getMessage().toString(), "List of orders for order status completed is null." ); //since they have been deleted
	    }
    }


//-------------------------------------------------EXTRA METHODS-------------------------------------------------------
    
    
	@Test
	public void testViewTotalSales() {	 //total sales only for completed orders    
	    lenient().when(orderDao.findByOrderStatus(any(OrderStatus.class))).thenAnswer((InvocationOnMock invocation) -> {
	        if (invocation.getArgument(0).equals(OrderStatus.Completed))	
	        {
	            ArrayList<GroceryOrder> orderList = new ArrayList<>();
	            GroceryOrder order = new GroceryOrder();
	            order.setOrderId(testId);
	            order.setOrderStatus(OrderStatus.Completed);
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
	    
		GroceryOrder order1 = null;
		int totalSales = 0;
	    try{
	    	order1 = orderService.createOrder(testCustomer, testOrderItemsList, testOrderType);
	    	order1.setOrderStatus(OrderStatus.Completed);
	    	order1.setOrderId(1);

	    	totalSales = orderService.viewTotalSales();
	    	
	    }catch (IllegalArgumentException e) {
	    	System.out.println(e.getMessage());
	    	fail();
	    }
	    assertEquals(totalSales+10, order1.getTotalCost()); // +10 fee since it is out of town delivery
	}
	
    
	@Test
	public void testUpdateOrderStatus() {	 //total sales only for completed orders     
		GroceryOrder order = null;
	    try{
	    	order = orderService.createOrder(testCustomer, testOrderItemsList, testOrderType);	//try delivery
	    	order = orderService.updateOrderStatus(order);
	    	assertEquals(order.getOrderStatus(), OrderStatus.Processing);
	    	order = orderService.updateOrderStatus(order);
	    	assertEquals(order.getOrderStatus(), OrderStatus.BeingDelivered);
	    	order = orderService.updateOrderStatus(order);
	    	assertEquals(order.getOrderStatus(), OrderStatus.Completed);
	    	
	    	order.setOrderType(OrderType.PickUp); 						//try pick up 
	    	order.setOrderStatus(OrderStatus.Received);
	    	order = orderService.updateOrderStatus(order);
	    	assertEquals(order.getOrderStatus(), OrderStatus.Processing);
	    	order = orderService.updateOrderStatus(order);
	    	assertEquals(order.getOrderStatus(), OrderStatus.ReadyForPickUp);
	    	order = orderService.updateOrderStatus(order);
	    	assertEquals(order.getOrderStatus(), OrderStatus.Completed);
	
	
	    }catch (IllegalArgumentException e) {
	    	System.out.println(e.getMessage());
	    	fail();
	    }
	}
	
	@Test
	public void testViewOrderStatus() {	 
		GroceryOrder order = null;
	    try{
	    	order = orderService.createOrder(testCustomer, testOrderItemsList, testOrderType);
	    	order.setOrderStatus(OrderStatus.BeingDelivered);
	    	OrderStatus status = orderService.viewOrderStatus(order);
	    	assertEquals(status, OrderStatus.BeingDelivered);

	    }catch (IllegalArgumentException e) {
	    	System.out.println(e.getMessage());
	    	fail();
	    }
	}
	
	@Test
	public void testOrderStatusIsNull() {	 
		GroceryOrder order = null;
	    try{
	    	order = orderService.createOrder(testCustomer, testOrderItemsList, testOrderType);
	    	order.setOrderStatus(null);
	    	OrderStatus status = orderService.viewOrderStatus(order);
	    }catch (IllegalArgumentException e) {
	    	assertEquals(e.getMessage(),"Order has no status.");
	    }
	}
	
	
	@Test
	public void testPayForOrder() {	 
		GroceryOrder order = null;
	    try{
	    	order = orderService.createOrder(testCustomer, testOrderItemsList, testOrderType);
	    	assertEquals(order.getOrderStatus(), OrderStatus.Received); //when we create order, status = received    	
	    	order = orderService.payForOrder("12345678123456781234123123123", order); //example credit card info -- will be hashed later
	    	assertEquals(order.getOrderStatus(), OrderStatus.Processing); //payment for order should change it from received to processing
	    }catch (IllegalArgumentException e) {
	    	System.out.println(e.getMessage());
	    	fail();
	    }
	}
	
	@Test
	public void testInvalidPayment() {	 
		GroceryOrder order = null;
	    try{
	    	order = orderService.createOrder(testCustomer, testOrderItemsList, testOrderType);
	    	assertEquals(order.getOrderStatus(), OrderStatus.Received); //when we create order, status = received    	
	    	order = orderService.payForOrder("ABCD", order); //example credit card info -- will be hashed later
	    	assertEquals(order.getOrderStatus(), OrderStatus.Processing); //payment for order should change it from received to processing
	    }catch (IllegalArgumentException e) {
	    	assertEquals(e.getMessage(),"Payment information is invalid.");
	    }
	}

     
//    
//-------------------------------------------------ASSERTION METHODS------------------------------------------------------------	
	private void assertOrder(GroceryOrder order, boolean inTown) {
	    assertNotNull(order);
	    assertEquals(order.getOrderStatus(),OrderStatus.Received);
	    if (inTown) {
	    	assertEquals(order.getTotalCost(),testTotalCost);
	    }else {
	    	assertEquals(order.getTotalCost(),testTotalCost+10); //extra fee if out of town
	    }
	    
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
