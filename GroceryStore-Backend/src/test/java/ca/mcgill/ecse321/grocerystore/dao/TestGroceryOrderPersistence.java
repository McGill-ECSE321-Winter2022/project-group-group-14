package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderType;
import ca.mcgill.ecse321.grocerystore.model.GroceryStore;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGroceryOrderPersistence {
    @Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeScheduleRepository employeeScheduleRepository;
	@Autowired
	private GroceryStoreRepository groceryStoreRepository;
	@Autowired
	private InventoryItemRepository inventoryItemRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private GroceryOrderRepository groceryOrderRepository;
	@Autowired
	private OwnerRepository ownerRepository;
	@Autowired
	private StoreScheduleRepository storeSchedule;


	@AfterEach
	public void clearDatabase() {
		// clear to avoid exceptions due to inconsistencies (dependencies)
		orderItemRepository.deleteAll();
		groceryOrderRepository.deleteAll();
		employeeScheduleRepository.deleteAll();
		customerRepository.deleteAll();
		employeeRepository.deleteAll();
		ownerRepository.deleteAll();
		accountRepository.deleteAll();
		inventoryItemRepository.deleteAll();
		storeSchedule.deleteAll();
		groceryStoreRepository.deleteAll();
	}
    

    /** @author: Clarissa Baciu */
	@Test
	public void testPersistAndLoadGroceryOrderById() {
        GroceryStore store = new GroceryStore(); //creating association classes
        Customer customer = new Customer();
		GroceryOrder order = new GroceryOrder();
		
        OrderType type = OrderType.Delivery;
        int cost = 15;
        order.setOrderType(type);	//adding attributes to groceryOrder
        order.setTotalCost(cost);
        
        order = groceryOrderRepository.save(order);		//saving before associating
        customer = customerRepository.save(customer);
        store = groceryStoreRepository.save(store);
        
        order.setCustomer(customer);					//creating associations
        order.setGroceryStore(store);
        
        order = groceryOrderRepository.save(order);		//saving after associations
        customer = customerRepository.save(customer);
        store = groceryStoreRepository.save(store);
        
        GroceryOrder orderdb = groceryOrderRepository.findByOrderId(order.getOrderId());	//loading from database
        assertNotNull(orderdb);	
        assertEquals(orderdb.getOrderId(),order.getOrderId());			//verifying attributes
        assertEquals(orderdb.getOrderType(),order.getOrderType());
        assertEquals(orderdb.getTotalCost(),order.getTotalCost());
        
        assertEquals(orderdb.getCustomer().getAccountId(),customer.getAccountId());		//verifying associations
        assertEquals(orderdb.getGroceryStore().getStoreId(),store.getStoreId());  
		
	}

//	@Test
//	public void testPersistAndLoadGroceryOrderWithItems(){
//		GroceryOrder order = new GroceryOrder();
//        OrderItem item1 = new OrderItem();
//        OrderItem item2 = new OrderItem();
//		
//        OrderType type = OrderType.Delivery;
//        int cost = 15;
//        order.setOrderType(type);	//adding attributes to groceryOrder
//        order.setTotalCost(cost);
//        
//        order = groceryOrderRepository.save(order);		//saving before associating
//        item1 = orderItemRepository.save(item1);
//        item2 = orderItemRepository.save(item2);
//        
//     
//        List<OrderItem> itemList = new ArrayList<OrderItem>(); //adding associations
//        itemList.add(item1);
//        itemList.add(item2);
//        order.setOrderItems(itemList);
//        
//        groceryOrderRepository.save(order);		//saving after associations
//        orderItemRepository.save(item1);
//        orderItemRepository.save(item2);
//        
//        GroceryOrder orderdb = groceryOrderRepository.findByOrderId(order.getOrderId());	//loading from database
//        
//        assertFalse(orderdb.getOrderItems().isEmpty());
//        assertTrue(orderdb.hasOrderItems());
//        assertTrue(orderdb.getOrderItems().contains(item1));
//        assertTrue(orderdb.getOrderItems().contains(item2));	
//	}
	
	   /** @author: Clarissa Baciu */
		@Test
		public void testPersistAndLoadGroceryOrderByOrderType() {
			GroceryOrder order1 = new GroceryOrder(5, OrderType.Delivery);
			GroceryOrder order2 = new GroceryOrder(10, OrderType.Delivery);
			GroceryOrder order3 = new GroceryOrder(15,OrderType.PickUp);
	        
	        order1 = groceryOrderRepository.save(order1);		
	        order2 = groceryOrderRepository.save(order2);
	        order3 = groceryOrderRepository.save(order3);
	        
	        
	        List<GroceryOrder> deliveryList = groceryOrderRepository.findByOrderType(OrderType.Delivery);	//loading from database
	        List<GroceryOrder> pickupList = groceryOrderRepository.findByOrderType(OrderType.PickUp);	
	        assertFalse(deliveryList.isEmpty());
	        assertEquals(deliveryList.get(0).getOrderId(),order1.getOrderId());
	        assertEquals(deliveryList.get(1).getOrderId(),order2.getOrderId());
	        assertFalse(pickupList.isEmpty());
	        assertEquals(pickupList.get(0).getOrderId(),order3.getOrderId());
		}
		
		   /** @author: Clarissa Baciu */
			@Test
			public void testPersistAndLoadGroceryOrderByCustomer() {
//				GroceryOrder order1 = new GroceryOrder();
//				GroceryOrder order2 = new GroceryOrder();
//				Customer customer = new Customer();
//				GroceryStore store = new GroceryStore(); 
//				
//
//				customer = customerRepository.save(customer);
//		        order1 = groceryOrderRepository.save(order1);	//saving before associations	
//		        order2 = groceryOrderRepository.save(order2);
//		        customer = customerRepository.save(customer);
//		        store = groceryStoreRepository.save(store);
//		        
//		        
//		        order1.setCustomer(customer);	//creating associations
//		        order2.setCustomer(customer);
//		        order1.setGroceryStore(store);
//		        order2.setGroceryStore(store);
//		        
//		        //or 
//		        //customer.set(orders)
//		        
//		        
//		        order1 = groceryOrderRepository.save(order1);	//saving after associations
//		        order2 = groceryOrderRepository.save(order2);
//		        customer = customerRepository.save(customer);
//		        store = groceryStoreRepository.save(store);
//		        
//		        List<GroceryOrder> orderList = groceryOrderRepository.findGroceryOrdersByCustomer(customer);	//loading from database
//		        assertFalse(orderList.isEmpty());
//		        System.out.println(orderList);
////		        assertEquals(orderList.get(0).getOrderId(),order1.getOrderId());
////		        assertEquals(orderList.get(1).getOrderId(),order2.getOrderId());
		        
		        GroceryStore store = new GroceryStore(); //creating association classes
		        Customer customer = new Customer();
				GroceryOrder order = new GroceryOrder();
				
		        OrderType type = OrderType.Delivery;
		        int cost = 15;
		        order.setOrderType(type);	//adding attributes to groceryOrder
		        order.setTotalCost(cost);
		        
		        order = groceryOrderRepository.save(order);		//saving before associating
		        customer = customerRepository.save(customer);
		        store = groceryStoreRepository.save(store);
		        
		        order.setCustomer(customer);					//creating associations
		        order.setGroceryStore(store);
		        
		        order = groceryOrderRepository.save(order);		//saving after associations
		        customer = customerRepository.save(customer);
		        store = groceryStoreRepository.save(store);
		        
		        GroceryOrder orderdb = groceryOrderRepository.findByOrderId(order.getOrderId());	//loading from database
		        assertNotNull(orderdb);	
		        assertEquals(orderdb.getOrderId(),order.getOrderId());			//verifying attributes
		        assertEquals(orderdb.getOrderType(),order.getOrderType());
		        assertEquals(orderdb.getTotalCost(),order.getTotalCost());
		        
		        assertEquals(orderdb.getCustomer().getAccountId(),customer.getAccountId());		//verifying associations
		        assertEquals(orderdb.getGroceryStore().getStoreId(),store.getStoreId());  
		        
			}
	
		
}