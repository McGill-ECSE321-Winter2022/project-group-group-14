package ca.mcgill.ecse321.grocerystore.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import java.sql.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.grocerystore.dao.CustomerRepository;
import ca.mcgill.ecse321.grocerystore.dao.GroceryStoreRepository;
import ca.mcgill.ecse321.grocerystore.dao.StoreScheduleRepository;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule.Day;


@ExtendWith(MockitoExtension.class)
public class TestGroceryStoreService {
	
	@Mock
	private CustomerRepository customerDao;
	@Mock
	private GroceryStoreRepository groceryStoreDao;
	@Mock 
	private StoreScheduleRepository storeScheduleRepository;


	@InjectMocks
	private CustomerService customerService;
	@InjectMocks 
	private StoreScheduleService storeScheduleService;

	private static final String CUSTOMER_EMAIL = "jim@hotmail.com";
	private static final String NONEXISTING_EMAIL = "NotACustomer";

	@BeforeEach
	public void setMockOutput() {
	    lenient().when(customerDao.findByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(CUSTOMER_EMAIL)) {
	            Customer customer = new Customer();
	            customer.setEmail(CUSTOMER_EMAIL);
	            return customer;
	        } else {
	            return null;
	        }
	    });
	    
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(customerDao.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
//		lenient().when(eventDao.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);
//		lenient().when(registrationDao.save(any(Registration.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateCustomer() {
		assertEquals(0, customerService.getAllCustomers().size());

		String email = "tom@gmail.com";
		String username = "tommy";
		String password = "Nohax69";
		String phonenumber = "5141234567";
		String address = "500 Shopiko Road, Montreal";
		Customer customer = null;
		try {
			customer = customerService.createCustomer(email, username, password, phonenumber, address);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(customer);
		assertEquals(email, customer.getEmail());
		assertEquals(username, customer.getUsername());
		assertEquals(password, customer.getPassword());
		assertEquals(phonenumber, customer.getPhoneNumber());
		assertEquals(address, customer.getAddress());
	}
	
	//@Test
	/* Template for making tests that should not work. You can change the tested values to be for checking more specific stuff. 
	 * Ex: This method fails because CustomerService's createCustomer method does not throw errors for null emails or usernames, etc... */
	/*public void testCreateCustomerNull() {
		String email = null;
		String username = null;
		String password = null;
		String phonenumber = null;
		String address = null;
		Customer customer = null;
		
		String error = null;
		try {
			customer = customerService.createCustomer(email, username, password, phonenumber, address);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		//check error
		assertEquals("Customers must have all fields filled out.", error);
	} */
	
	
	@Test
	public void testGetExistingCustomer() {
		assertEquals(CUSTOMER_EMAIL, customerService.getCustomer(CUSTOMER_EMAIL).getEmail());
	}

	@Test
	public void testGetNonExistingCustomer() {
		assertNull(customerService.getCustomer(NONEXISTING_EMAIL));
	}
	
	/* If we have a method that needs two or more different classes to test a class method, try using this method as a template.
	 * This also means that a Dao needs to be added as a class variable if it's used here (See the variables at the top of this class.)  */
//	@Test
//	public void testRegister() {
//		String nameP = "Oscar2";
//		String nameE = "Soccer Game2";
//		Calendar c = Calendar.getInstance();
//		c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
//		Date eventDate = new Date(c.getTimeInMillis());
//		Time startTime = new Time(c.getTimeInMillis());
//		c.set(2017, Calendar.MARCH, 16, 10, 30, 0);
//		Time endTime = new Time(c.getTimeInMillis());
//		Person person = null;
//		person = service.createPerson(nameP);
//		Event event = null;
//		event = service.createEvent(nameE, eventDate, startTime, endTime);
//		lenient().when(personDao.existsById(anyString())).thenReturn(true);
//		lenient().when(eventDao.existsById(anyString())).thenReturn(true);
//		Registration registration = null;
//		try {
//			registration = service.register(person, event);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		checkResultRegister(registration, nameP, nameE, eventDate, startTime, endTime);
//	}
//
//	private void checkResultRegister(Registration registration, String nameP, String nameE, Date eventDate,
//			Time startTime, Time endTime) {
//		assertNotNull(registration);
//		assertEquals(nameP, registration.getPerson().getName());
//		assertEquals(nameE, registration.getEvent().getName());
//		assertEquals(eventDate.toString(), registration.getEvent().getDate().toString());
//		assertEquals(startTime.toString(), registration.getEvent().getStartTime().toString());
//		assertEquals(endTime.toString(), registration.getEvent().getEndTime().toString());
//	}

}