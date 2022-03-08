package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.GroceryStore;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional //don't need clear database method
public class TestOrderItemPersistence {
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


//	@AfterEach
//	public void clearDatabase() {
//		// clear to avoid exceptions due to inconsistencies (dependencies)
//		orderItemRepository.deleteAll();
//		groceryOrderRepository.deleteAll();
//		employeeScheduleRepository.deleteAll();
//		customerRepository.deleteAll();
//		employeeRepository.deleteAll();
//		ownerRepository.deleteAll();
//		accountRepository.deleteAll();
//		inventoryItemRepository.deleteAll();
//		storeSchedule.deleteAll();
//		groceryStoreRepository.deleteAll();
//	}
//    

	//test Inventory Item persistence
	@Test
	public void testPersistAndLoadOrderItem(){

		//create Grocery Order object
		GroceryOrder groceryOrder = new GroceryOrder();
		
//		groceryOrder.setCustomer(new Customer());		
		//set values to Order Item attributes
		OrderItem orderItem = new OrderItem();
		String name = "btates";
		orderItem.setName(name);
		orderItem.setPrice(32);
		orderItem.setCurrentStock(15);
		
//		orderItem.setGroceryStore(groceryStore);
//		orderItem.setGroceryOrder(groceryOrder);

		
		
		//save Order Item object
//		orderItemRepository.save(orderItem);
		
		orderItem = orderItemRepository.save(orderItem);
		
		//save Grocery Order object
		groceryOrder=groceryOrderRepository.save(groceryOrder);
		
		
		
		System.out.println(orderItem.getItemId());
		

		
		//save Order Item object
		orderItemRepository.save(orderItem);
		
		//save Grocery Order object
		groceryOrderRepository.save(groceryOrder);
		
			
		
		
		//test saved Order Item object
		OrderItem savedOrderItem = orderItemRepository.findByItemId(orderItem.getItemId());
		assertNotNull(savedOrderItem);
		assertEquals(orderItem.getName(), savedOrderItem.getName());
		assertEquals(orderItem.getPrice(), savedOrderItem.getPrice());
		assertEquals(orderItem.getCurrentStock(), savedOrderItem.getCurrentStock());
		
	}

}


