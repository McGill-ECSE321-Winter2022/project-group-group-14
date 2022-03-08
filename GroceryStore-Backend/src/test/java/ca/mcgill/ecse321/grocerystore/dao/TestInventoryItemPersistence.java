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

import ca.mcgill.ecse321.grocerystore.model.GroceryStore;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional //don't need clear database method
public class TestInventoryItemPersistence {
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
	public void testPersistAndLoadInvetoryItem(){

		
		//set values to Inventory Item attributes
		InventoryItem inventoryItem = new InventoryItem();
		String name = "btates";
		inventoryItem.setName(name);
		inventoryItem.setPrice(32);
		inventoryItem.setCurrentStock(15);

		
		//save Inventory Item object
		inventoryItemRepository.save(inventoryItem);
		
		//test saved Inventory Item object
		InventoryItem savedInventoryItem = inventoryItemRepository.findByItemId(inventoryItem.getItemId());
		assertNotNull(savedInventoryItem);
		assertEquals(inventoryItem.getName(), savedInventoryItem.getName());
		assertEquals(inventoryItem.getPrice(), savedInventoryItem.getPrice());
		assertEquals(inventoryItem.getCurrentStock(), savedInventoryItem.getCurrentStock());
		
	}


}


