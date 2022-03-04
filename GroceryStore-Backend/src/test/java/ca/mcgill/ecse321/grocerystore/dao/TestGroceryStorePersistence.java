package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule;
import ca.mcgill.ecse321.grocerystore.model.GroceryStore;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
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
    

	//test Customer persistence
	@Test
	public void testPersistAndLoadCustomer(){
		Customer customer = new Customer();
		String email = "testEmail";
		customer.setEmail(email);
		customer.setPassword("testPassword");
		customer.setUsername("testUsername");
		customer.setPhoneNumber("testPhoneNumber");
		customer.setAddress("street 1");
		
		customerRepository.save(customer);
		customer=null;
		customer = customerRepository.findByEmail(email);
		assertNotNull(customer);
		assertEquals(email, customer.getEmail());
		
	}


}



