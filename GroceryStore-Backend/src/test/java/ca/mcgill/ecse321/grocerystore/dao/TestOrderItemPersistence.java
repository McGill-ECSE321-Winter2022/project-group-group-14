package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
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
	/**@author Youssof Mohamed */
	@Test
	public void testPersistAndLoadOrderItem(){

				
		//set values to the first Order Item attributes
		OrderItem orderItem = new OrderItem();
		String name = "batates";
		orderItem.setName(name);
		orderItem.setPrice(32);
		orderItem.setCurrentStock(15);
		
		//set values to the second Order Item attributes
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setName("tamatem");
		orderItem2.setPrice(123);
		orderItem2.setCurrentStock(74);
		
		//set values to the second Order Item attributes (same name as in second order item)
		OrderItem orderItem3 = new OrderItem();
		orderItem3.setName("tamatem");
		orderItem3.setPrice(123);
		orderItem3.setCurrentStock(73);
		
		//save Order Item objects
		orderItemRepository.save(orderItem);
		orderItemRepository.save(orderItem2);
		orderItemRepository.save(orderItem3);
	
		//test findByItemId
		OrderItem savedOrderItem = orderItemRepository.findByItemId(orderItem.getItemId());
		assertNotNull(savedOrderItem);
		assertEquals(orderItem.getName(), savedOrderItem.getName());
		assertEquals(orderItem.getPrice(), savedOrderItem.getPrice());
		assertEquals(orderItem.getCurrentStock(), savedOrderItem.getCurrentStock());
		
		//test findByName
		List<OrderItem> savedOrderItems = orderItemRepository.findByName(orderItem2.getName());
		assertNotNull(savedOrderItems);
		assertEquals(orderItem2.getItemId(), savedOrderItems.get(0).getItemId());
		assertEquals(orderItem2.getPrice(), savedOrderItems.get(0).getPrice());
		assertEquals(orderItem2.getCurrentStock(), savedOrderItems.get(0).getCurrentStock());
		assertEquals(orderItem3.getItemId(), savedOrderItems.get(1).getItemId());
		assertEquals(orderItem3.getPrice(), savedOrderItems.get(1).getPrice());
		assertEquals(orderItem3.getCurrentStock(), savedOrderItems.get(1).getCurrentStock());
		
	}

}


