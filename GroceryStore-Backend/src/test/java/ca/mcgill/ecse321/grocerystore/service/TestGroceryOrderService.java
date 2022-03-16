package ca.mcgill.ecse321.grocerystore.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
	    

	   /**
	    * exists by id and delete?? 
	    * - need exists by id for order, customer, order item
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
	
	
	// test 1 create a normal order
	@Test
	public void testCreateOrder() {
		lenient().when(customerDao.existsById(anyInt())).thenReturn(true);
		lenient().when(orderItemDao.existsById(anyInt())).thenReturn(true);
		GroceryOrder order = null;
	    try{
	    	order = orderService.createOrder(testCustomer, testOrderItemsList, testOrderType);
	    }catch (IllegalArgumentException e) {
	    	System.out.println(e.getMessage());
	    	fail();
	    }
	    assertNotNull(order);
	    assertEquals(order.getOrderType(),testOrderType);
	    assertEquals(order.getOrderStatus(),testOrderStatus);
	    assertEquals(order.getTotalCost(),testTotalCost);
	    assertEquals(order.getCustomer().getAccountId(),testCustomer.getAccountId());
	    assertFalse(order.getOrderItems().isEmpty());
        assertEquals(order.getOrderItems().get(0).getItemId(),testOrderItem1.getItemId());
        assertEquals(order.getOrderItems().get(1).getItemId(),testOrderItem2.getItemId());
	}

	

}
