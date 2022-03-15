package ca.mcgill.ecse321.grocerystore.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.grocerystore.dao.CustomerRepository;
import ca.mcgill.ecse321.grocerystore.dao.GroceryStoreRepository;
import ca.mcgill.ecse321.grocerystore.dao.StoreScheduleRepository;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.GroceryStore;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule.Day;


@ExtendWith(MockitoExtension.class)
public class TestGroceryStoreService {
	
	@Mock
	private CustomerRepository customerDao;
	@Mock
	private GroceryStoreRepository groceryStoreDao;
	@Mock
	private StoreScheduleRepository storeScheduleDao;

//	@InjectMocks
//	private GroceryStoreService service;
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
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreateStoreSchedule() {
		Time openingTime = new Time(8, 30, 0);
		Time closingTime = new Time(18, 0, 0);
		Day dayOpen = Day.Monday;
		StoreSchedule storeSchedule = null;
		try {
			storeSchedule = storeScheduleService.createStoreSchedule(openingTime, closingTime, dayOpen);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(storeSchedule);
		checkResultStoreSchedule(storeSchedule, openingTime, closingTime, dayOpen);
	}
	
	private void checkResultStoreSchedule(StoreSchedule storeSchedule, Time openingTime, Time closingTime,Day dayOpen) {
		assertNotNull(storeSchedule);
		assertEquals(openingTime.toString(), storeSchedule.getOpeningTime().toString());
		assertEquals(closingTime.toString(), storeSchedule.getClosingTime().toString());
		assertEquals(dayOpen.toString(), storeSchedule.getDayOpen().toString());
	}
	
	@Test
	public void testCreateCustomer() {
		assertEquals(0, customerService.getAllCustomers().size());

		String email = "tom@gmail.com";
		String username = "tommy";
		String password = "nohax";
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
	
	@Test
	/* Template for making tests that should not work. You can change the tested values to be for checking more specific stuff. */
	public void testCreateCustomerNull() {
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
	}
	
	
	@Test
	public void testGetExistingCustomer() {
		assertEquals(CUSTOMER_EMAIL, customerService.getCustomer(CUSTOMER_EMAIL).getEmail());
	}

	@Test
	public void testGetNonExistingCustomer() {
		assertNull(customerService.getCustomer(NONEXISTING_EMAIL));
	}
	

}