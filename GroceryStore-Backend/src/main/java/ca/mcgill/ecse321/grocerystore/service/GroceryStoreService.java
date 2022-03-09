package ca.mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.dao.AccountRepository;
import ca.mcgill.ecse321.grocerystore.dao.CustomerRepository;
import ca.mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import ca.mcgill.ecse321.grocerystore.dao.EmployeeScheduleRepository;
import ca.mcgill.ecse321.grocerystore.dao.GroceryOrderRepository;
import ca.mcgill.ecse321.grocerystore.dao.GroceryStoreRepository;
import ca.mcgill.ecse321.grocerystore.dao.InventoryItemRepository;
import ca.mcgill.ecse321.grocerystore.dao.OrderItemRepository;
import ca.mcgill.ecse321.grocerystore.dao.OwnerRepository;
import ca.mcgill.ecse321.grocerystore.dao.StoreScheduleRepository;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.Owner;
import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.GroceryStore;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;


@Service
public class GroceryStoreService {

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

	
	
//	@Transactional
//	public Person createPerson(String name) {
//		Person person = new Person();
//		person.setName(name);
//		personRepository.save(person);
//		return person;
//	}
//
//	@Transactional
//	public Person getPerson(String name) {
//		Person person = personRepository.findPersonByName(name);
//		return person;
//	}
//
//	@Transactional
//	public List<Person> getAllPersons() {
//		return toList(personRepository.findAll());
//	}
//
//	@Transactional
//	public Event createEvent(String name, Date date, Time startTime, Time endTime) {
//		Event event = new Event();
//		event.setName(name);
//		event.setDate(date);
//		event.setStartTime(startTime);
//		event.setEndTime(endTime);
//		eventRepository.save(event);
//		return event;
//	}
//
//	@Transactional
//	public Event getEvent(String name) {
//		Event event = eventRepository.findEventByName(name);
//		return event;
//	}
//
//	@Transactional
//	public List<Event> getAllEvents() {
//		return toList(eventRepository.findAll());
//	}
//
//	@Transactional
//	public Registration register(Person person, Event event) {
//		Registration registration = new Registration();
//		registration.setId(person.getName().hashCode() * event.getName().hashCode());
//		registration.setPerson(person);
//		registration.setEvent(event);
//
//		registrationRepository.save(registration);
//
//		return registration;
//	}
//
//	@Transactional
//	public List<Registration> getAllRegistrations(){
//		return toList(registrationRepository.findAll());
//	}
//
//	@Transactional
//	public List<Event> getEventsAttendedByPerson(Person person) {
//		List<Event> eventsAttendedByPerson = new ArrayList<>();
//		for (Registration r : registrationRepository.findByPerson(person)) {
//			eventsAttendedByPerson.add(r.getEvent());
//		}
//		return eventsAttendedByPerson;
//	}
//
//	private <T> List<T> toList(Iterable<T> iterable){
//		List<T> resultList = new ArrayList<T>();
//		for (T t : iterable) {
//			resultList.add(t);
//		}
//		return resultList;
//	}

}