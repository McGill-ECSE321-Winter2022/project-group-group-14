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
import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestEmployeePersistence {
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
	public void testPersistAndLoadEmployee(){
		//create Grocery Store object
//		GroceryStore groceryStore = new GroceryStore();
			
		//set values to Employee attributes
		Employee employee = new Employee();
		
		String email = "employee1@gmail.com";
		String username = "employee1";
		String password = "123abc";
		EmployeeSchedule employeeSchedule0 = new EmployeeSchedule(EmployeeSchedule.Shift.Afternoon, EmployeeSchedule.Day.Monday);
		EmployeeSchedule employeeSchedule1 = new EmployeeSchedule(EmployeeSchedule.Shift.Morning, EmployeeSchedule.Day.Wednesday);
		
	    employee.setEmail(email);
		employee.setUsername(username);
		employee.setPassword(password);
	
		employee.addEmployeeSchedule(employeeSchedule0);
		employee.addEmployeeSchedule(employeeSchedule1);
//		employee.setGroceryStore(groceryStore);
		
		//save Grocery Store object
//		groceryStoreRepository.save(groceryStore);
			
		//save Employee object
		employeeRepository.save(employee);
		
		//save EmployeeSchedule object
		employeeScheduleRepository.save(employeeSchedule0);
		employeeScheduleRepository.save(employeeSchedule1);
		
		
		//test saved Employee object
		Employee savedEmployee = employeeRepository.findByAccountId(employee.getAccountId());
		EmployeeSchedule savedEmployeeSchedule0 = employeeScheduleRepository.findById(employeeSchedule0.getId());
		EmployeeSchedule savedEmployeeSchedule1 = employeeScheduleRepository.findById(employeeSchedule1.getId());
		assertNotNull(savedEmployee);
		assertEquals(employee.getEmail(), savedEmployee.getEmail());
		assertEquals(employee.getPassword(), savedEmployee.getPassword());
		assertEquals(employee.getUsername(), savedEmployee.getUsername());
		
		//test first EmployeeSchedule Object
		assertEquals(employee.getEmployeeSchedules().get(0).getDay(), savedEmployeeSchedule0.getDay());
		assertEquals(employee.getEmployeeSchedules().get(0).getShift(), savedEmployeeSchedule0.getShift());
		assertEquals(employee.getEmployeeSchedules().get(0).getId(), savedEmployeeSchedule0.getId());
		
		//test second EmployeeSchedule Object
		assertEquals(employee.getEmployeeSchedules().get(1).getDay(), savedEmployeeSchedule1.getDay());
		assertEquals(employee.getEmployeeSchedules().get(1).getShift(), savedEmployeeSchedule1.getShift());
		assertEquals(employee.getEmployeeSchedules().get(1).getId(), savedEmployeeSchedule1.getId());
	}


}


