package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.GroceryStore;
import ca.mcgill.ecse321.grocerystore.model.Owner;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGroceryStorePersistence {
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
	private StoreScheduleRepository storeScheduleRepository;


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
		storeScheduleRepository.deleteAll();
		groceryStoreRepository.deleteAll();
	}
    

	/**
	 * Test grocery store presistence
	 * @author clarissabaciu
	 */
	@Test
	public void testPersistAndLoadMultipleGroceryStore(){
		GroceryStore store1 = new GroceryStore();
		GroceryStore store2 = new GroceryStore();
		
		store1 = groceryStoreRepository.save(store1);
		store2 = groceryStoreRepository.save(store2);
		int id1 = store1.getStoreId();
		int id2 = store2.getStoreId();
	
		store1 = null;
		store2 = null;
		store1 = groceryStoreRepository.findByStoreId(id1);
		store2 = groceryStoreRepository.findByStoreId(id2);
	
		assertNotNull(store1);
		assertNotNull(store2);
		assertEquals(id1, store1.getStoreId());
		assertEquals(id2, store2.getStoreId());
	}
//	//test GroceryStore association
//	@Test
//	public void testPersistAndLoadGroceryStoreAssociations(){
//		GroceryStore store = new GroceryStore();
//		Owner owner = new Owner();
//		
//		owner.setGroceryStore(store);					//adding associations 
//		
//		store = groceryStoreRepository.save(store);		
//		int storeId = store.getStoreId();
//		owner = ownerRepository.save(owner);
//		int ownerId = owner.getAccountId();
//		
//		store = null;
//		owner = null;
//		
//		store = groceryStoreRepository.findByStoreId(storeId);
//		assertNotNull(store);
//		
//
//		System.out.println(store.getAccount(0));
////		assertNotNull(store.getAccounts().get(0));
//	
//		//assertFalse(store.getAccounts().isEmpty());
//		//assertEquals(store.getAccounts().get(0).getAccountId(), ownerId);
//
//		
//	}


}



