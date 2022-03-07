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
import ca.mcgill.ecse321.grocerystore.model.Customer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCustomerPersistence {
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
	public void testPersistAndLoadCustomer(){
		//create Grocery Store object
//		GroceryStore groceryStore = new GroceryStore();
			
		//set values to Customer attributes
		Customer customer = new Customer();
		
		String email = "customer1@gmail.com";
		String username = "customer1";
		String password = "123abc";
		
		String address = "1000 1st Avenue Qc";
		String phoneNumber = "111-111-1111";
		customer.setEmail(email);
		customer.setUsername(username);
		customer.setPassword(password);
//		customer.setGroceryStore(groceryStore);
		
		customer.setAddress(address);
		customer.setPhoneNumber(phoneNumber);
			
		//save Grocery Store object
//		groceryStoreRepository.save(groceryStore);
			
		//save Customer object
		customerRepository.save(customer);
		
		//test saved Customer object
		Customer savedCustomer = customerRepository.findByAccountId(customer.getAccountId());
		assertNotNull(savedCustomer);
		assertEquals(customer.getEmail(), savedCustomer.getEmail());
		assertEquals(customer.getPassword(), savedCustomer.getPassword());
		assertEquals(customer.getUsername(), savedCustomer.getUsername());
		assertEquals(customer.getAddress(), savedCustomer.getAddress());
		assertEquals(customer.getPhoneNumber(), savedCustomer.getPhoneNumber());
	}


}


