package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystore.model.GroceryStore;
import ca.mcgill.ecse321.grocerystore.model.Owner;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestOwnerPersistence {
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

	/**
	 * @author Samuel Valentine
	 */
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
    

	/**
	 * @author Samuel Valentine
	 */
	@Test
	public void testPersistAndLoadOwner(){

			
		//set values to Owner attributes
		Owner owner = new Owner();
		
		String email = "owner1@gmail.com";
		String username = "owner1";
		String password = "123abc";
		owner.setUsername(username);
		owner.setPassword(password);
		
			
		//save Owner object
		ownerRepository.save(owner);
		
		//test saved Owner object
		Owner savedOwner = ownerRepository.findByAccountId(owner.getAccountId());
		assertNotNull(savedOwner);
		assertEquals(owner.getEmail(), savedOwner.getEmail());
		assertEquals(owner.getPassword(), savedOwner.getPassword());
		assertEquals(owner.getUsername(), savedOwner.getUsername());
	}


}



