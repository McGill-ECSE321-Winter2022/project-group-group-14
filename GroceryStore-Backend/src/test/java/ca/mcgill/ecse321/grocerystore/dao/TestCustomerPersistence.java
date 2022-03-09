package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.model.Customer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional //don't need clear database method
public class TestCustomerPersistence {
	@Autowired
	private CustomerRepository customerRepository;

    
	/**
	 * @author Samuel Valentine
	 */
	@Test
	public void testPersistAndLoadCustomer(){
			
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
		
		customer.setAddress(address);
		customer.setPhoneNumber(phoneNumber);
		
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


