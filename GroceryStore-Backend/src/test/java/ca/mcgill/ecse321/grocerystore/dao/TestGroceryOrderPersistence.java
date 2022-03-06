package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

	
	public void testPersistAndLoadGroceryOrderWithItems(){
		GroceryOrder order = new GroceryOrder();
        OrderItem item1 = new OrderItem();
        OrderItem item2 = new OrderItem();
		
        OrderType type = OrderType.Delivery;
        int cost = 15;
        order.setOrderType(type);	//adding attributes to groceryOrder
        order.setTotalCost(cost);
        
        order = groceryOrderRepository.save(order);		//saving before associating
        item1 = orderItemRepository.save(item1);
        item2 = orderItemRepository.save(item2);
        
        List<OrderItem> itemList = new ArrayList<OrderItem>(); //adding associations
        itemList.add(item1);
        itemList.add(item2);
        order.setOrderItems(itemList);
        
        
        
        
        
        
        
//      
      List<OrderItem> itemList = new ArrayList<OrderItem>(); //creating new associations
      itemList.add(orderItem1);
      itemList.add(orderItem2);
//      groceryOrder.addOrderItem(orderItem1);
//      groceryOrder.addOrderItem(orderItem2);
      groceryOrder.setOrderItems(itemList);

        order.setCustomer(customer);					//creating associations
        order.setGroceryStore(store);
        
        order = groceryOrderRepository.save(order);		//saving after associations

        
        GroceryOrder orderdb = groceryOrderRepository.findByOrderId(order.getOrderId());	//loading from database
        assertNotNull(orderdb);	
        assertEquals(orderdb.getOrderId(),order.getOrderId());			//verifying attributes
        assertEquals(orderdb.getOrderType(),order.getOrderType());
        assertEquals(orderdb.getTotalCost(),order.getTotalCost());
        
        assertEquals(orderdb.getCustomer().getAccountId(),customer.getAccountId());		//verifying associations
        assertEquals(orderdb.getGroceryStore().getStoreId(),store.getStoreId());  

		
		
	}


}