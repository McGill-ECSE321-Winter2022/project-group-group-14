package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystore.model.GroceryStore;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule.Day;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestStoreSchedulePersistence {
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
	
	
    

	//test Customer persistence
	@Test
	public void testPersistAndLoadStoreSchedule(){
		

		//create test Store Schedule
		StoreSchedule storeSchedule = new StoreSchedule();
		
		//set StoreSchedule values to be tested
		@SuppressWarnings("deprecation")
		Time openingTime = new Time(8, 0, 0);
		
		@SuppressWarnings("deprecation")
		Time closingTime = new Time(17, 30, 0);
		
		Day dayOpen = Day.Monday;
		
		//set values to StoreSchedule object
		storeSchedule.setOpeningTime(openingTime);
		storeSchedule.setClosingTime(closingTime);
		storeSchedule.setDayOpen(dayOpen);
		

		
		//save StoreSchedule object
		storeScheduleRepository.save(storeSchedule);
		
		//test saved StoreSchedule object
		StoreSchedule savedStoreSchedule = storeScheduleRepository.findByStoreScheduleId(storeSchedule.getStoreScheduleId());
		
		assertNotNull(savedStoreSchedule);
		assertEquals(storeSchedule.getOpeningTime(), savedStoreSchedule.getOpeningTime());
		assertEquals(storeSchedule.getClosingTime(), savedStoreSchedule.getClosingTime());
		assertEquals(storeSchedule.getDayOpen(), savedStoreSchedule.getDayOpen());

	}


}
