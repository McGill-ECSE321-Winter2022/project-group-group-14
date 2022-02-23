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
import ca.mcgill.ecse321.grocerystore.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule;
import ca.mcgill.ecse321.grocerystore.model.GroceryStore;
import ca.mcgill.ecse321.grocerystore.model.InStore;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
import ca.mcgill.ecse321.grocerystore.model.Item;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.Owner;
import ca.mcgill.ecse321.grocerystore.model.PickupOrder;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGroceryStorePersistence {
    @Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DeliveryOrderRepository deliveryOrderRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeScheduleRepository employeeScheduleRepository;
	@Autowired
	private GroceryStoreRepository groceryStoreRepository;
	@Autowired
	private InStoreRepository inStoreRepository;
	@Autowired
	private InventoryItemRepository inventoryItemRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private GroceryOrderRepository groceryOrderRepository;
	@Autowired
	private OwnerRepository ownerRepository;
	@Autowired
	private PickupOrderRepository pickupOrderRepository;
	@Autowired
	private StoreScheduleRepository storeSchedule;


	// @AfterEach
	// public void clearDatabase() {
	// 	// clear to avoid exceptions due to inconsistencies (dependencies)
	// 	itemRepository.deleteAll();
	// 	deliveryOrderRepository.deleteAll();
	// 	inStoreRepository.deleteAll();
	// 	pickupOrderRepository.deleteAll();
	// 	orderRepository.deleteAll();
	// 	employeeScheduleRepository.deleteAll();
	// 	customerRepository.deleteAll();
	// 	employeeRepository.deleteAll();
	// 	ownerRepository.deleteAll();
	// 	accountRepository.deleteAll();
	// 	inventoryItemRepository.deleteAll();
	// 	storeSchedule.deleteAll();
	// 	groceryStoreRepository.deleteAll();
	// }
    
	// //test persistence of GroceryStore class
	

	// @Test
	// public void testPersistAndLoadPerson() {
	// 	String name = "TestPerson";
	// 	// First example for object save/load
	// 	Person person = new Person();
	// 	// First example for attribute save/load
	// 	person.setName(name);
	// 	personRepository.save(person);

	// 	person = null;

	// 	person = personRepository.findPersonByName(name);
	// 	assertNotNull(person);
	// 	assertEquals(name, person.getName());
	// }

	// @Test
	// public void testPersistAndLoadEvent() {
	// 	String name = "ECSE321 Tutorial";
	// 	Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
	// 	Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
	// 	Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
	// 	Event event = new Event();
	// 	event.setName(name);
	// 	event.setDate(date);
	// 	event.setStartTime(startTime);
	// 	event.setEndTime(endTime);
	// 	eventRepository.save(event);

	// 	event = null;

	// 	event = eventRepository.findEventByName(name);

	// 	assertNotNull(event);
	// 	assertEquals(name, event.getName());
	// 	assertEquals(date, event.getDate());
	// 	assertEquals(startTime, event.getStartTime());
	// 	assertEquals(endTime, event.getEndTime());
	// }

	// @Test
	// public void testPersistAndLoadRegistration() {
	// 	String personName = "TestPerson";
	// 	Person person = new Person();
	// 	person.setName(personName);
	// 	personRepository.save(person);

	// 	String eventName = "ECSE321 Tutorial";
	// 	Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
	// 	Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
	// 	Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
	// 	Event event = new Event();
	// 	event.setName(eventName);
	// 	event.setDate(date);
	// 	event.setStartTime(startTime);
	// 	event.setEndTime(endTime);
	// 	eventRepository.save(event);

	// 	Registration reg = new Registration();
	// 	int regId = 1;
	// 	// First example for reference save/load
	// 	reg.setId(regId);
	// 	reg.setPerson(person);
	// 	reg.setEvent(event);
	// 	registrationRepository.save(reg);

	// 	reg = null;

	// 	reg = registrationRepository.findByPersonAndEvent(person, event);
	// 	assertNotNull(reg);
	// 	assertEquals(regId, reg.getId());
	// 	// Comparing by keys
	// 	assertEquals(person.getName(), reg.getPerson().getName());
	// 	assertEquals(event.getName(), reg.getEvent().getName());
	// }

}



