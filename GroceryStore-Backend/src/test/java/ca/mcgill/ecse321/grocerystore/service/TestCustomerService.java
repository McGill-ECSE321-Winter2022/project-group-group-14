package ca.mcgill.ecse321.grocerystore.service;

import ca.mcgill.ecse321.grocerystore.dao.CustomerRepository;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestCustomerService
{
    @Mock
    private CustomerRepository customerDao;

    @InjectMocks
    private CustomerService service;

    @BeforeEach
    public void setMockOutput()
    {
    	lenient().when(customerDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(1))
            {
                Customer customer = new Customer();
                customer.setEmail("dave2@gmail.com");
                customer.setUsername("dave2");
                customer.setPassword("123Abc");
                customer.setPhoneNumber("5149999999");
                customer.setAddress("555 1st avenue");
                return customer;
            } else
            {
                return null;
            }
        });
        lenient().when(customerDao.findByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
        	if (invocation.getArgument(0).equals("dave2")) {
            
            	Customer customer = new Customer();
                customer.setEmail("dave2@gmail.com");
                customer.setUsername("dave2");
                customer.setPassword("123Abc");
                customer.setPhoneNumber("5149999999");
                customer.setAddress("555 1st avenue");
                return customer;
        	}
        	else {
        		return null;
        	}

        });
        lenient().when(customerDao.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
        	if (invocation.getArgument(0).equals("dave2@gmail.com")) {
            	Customer customer = new Customer();
                customer.setEmail("dave2@gmail.com");
                customer.setUsername("dave2");
                customer.setPassword("123Abc");
                customer.setPhoneNumber("5149999999");
                customer.setAddress("555 1st avenue");
                return customer;
        	}
        	else {
        		return null;
        	}
        });
        lenient().when(customerDao.findByAddress(anyString())).thenAnswer((InvocationOnMock invocation) -> {
        	if (invocation.getArgument(0).equals("555 1st avenue")) {
        		Customer customer = new Customer();
        		customer.setEmail("dave2@gmail.com");
        		customer.setUsername("dave2");
        		customer.setPassword("123Abc");
        		customer.setPhoneNumber("5149999999");
        		customer.setAddress("555 1st avenue");
        		return customer;
        	}
        	else {
        		return null;
        	}
        });
        lenient().when(customerDao.findByPhoneNumber(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            	
            	Customer customer = new Customer();
                customer.setEmail("dave2@gmail.com");
                customer.setUsername("dave2");
                customer.setPassword("123Abc");
                customer.setPhoneNumber("5149999999");
                customer.setAddress("555 1st avenue");
                return customer;
            
        });
        lenient().when(customerDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Customer customer = new Customer();
            customer.setEmail("dave2@gmail.com");
            customer.setUsername("dave2");
            customer.setPassword("123Abc");
            customer.setPhoneNumber("5149999999");
            customer.setAddress("555 1st avenue");
            ArrayList<Customer> list = new ArrayList<>();
            list.add(customer);
            return list;
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(customerDao.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreateCustomer()
    {
        Customer customer = null;
        try
        {
            customer = service.createCustomer("dave@gmail.com","dave","123Abc","5149999999","555 1st avenue");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(customer);
        assertEquals("dave@gmail.com", customer.getEmail());
        assertEquals("dave", customer.getUsername());
        assertEquals("123Abc", customer.getPassword());
        assertEquals("5149999999", customer.getPhoneNumber());
        assertEquals("555 1st avenue", customer.getAddress());
    }

    @Test
    public void testGetAll()
    {
        ArrayList<Customer> customers = null;
        try
        {
            customers = new ArrayList<Customer>(service.getAllCustomers());

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(customers);
        assertEquals(1, customers.size());
        assertEquals("dave2", customers.get(0).getUsername());
    }

    @Test
    public void testGetByWrongID()
    {
        Customer customer = null;
        try
        {
            customer = service.getCustomerByID(420);

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(customer);
    }

    @Test
    public void testGetByEmail()
    {
        Customer customer = null;
        try
        {
            customer = service.getCustomerByEmail("dave2@gmail.com");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(customer);
        assertEquals("dave2@gmail.com", customer.getEmail());
        assertEquals("dave2", customer.getUsername());
        assertEquals("123Abc", customer.getPassword());
        assertEquals("5149999999", customer.getPhoneNumber());
        assertEquals("555 1st avenue", customer.getAddress());
    }

    @Test
    public void testGetByWrongEmail()
    {
        Customer customer = null;
        try
        {
            customer = service.getCustomerByEmail("bob@gmail.com");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(customer);
    }

    @Test
    public void testGetByPhoneNumber()
    {
        Customer customer = null;
        try
        {
            customer = service.getCustomerByPhone("5149999999");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(customer);
        assertEquals("dave2@gmail.com", customer.getEmail());
        assertEquals("dave2", customer.getUsername());
        assertEquals("123Abc", customer.getPassword());
        assertEquals("5149999999", customer.getPhoneNumber());
        assertEquals("555 1st avenue", customer.getAddress());
    }
    
    @Test
    public void testGetByAddress()
    {
    	Customer customer = null;
    	try
    	{
    		customer = service.getCustomerByAddress("555 1st avenue");
    		
    	} catch (IllegalArgumentException e)
    	{
    		fail();
    	}
    	assertNotNull(customer);
    	assertEquals("dave2@gmail.com", customer.getEmail());
    	assertEquals("dave2", customer.getUsername());
    	assertEquals("123Abc", customer.getPassword());
    	assertEquals("5149999999", customer.getPhoneNumber());
    	assertEquals("555 1st avenue", customer.getAddress());
    }

    @Test
    public void testGetByUsername()
    {
        Customer customer = null;
        try
        {
            customer = service.getCustomerByUsername("dave2");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(customer);
        assertEquals("dave2@gmail.com", customer.getEmail());
        assertEquals("dave2", customer.getUsername());
        assertEquals("123Abc", customer.getPassword());
        assertEquals("5149999999", customer.getPhoneNumber());
        assertEquals("555 1st avenue", customer.getAddress());
    }

    @Test
    public void testGetByWrongUsername()
    {
        Customer customer = null;
        try
        {
            customer = service.getCustomerByUsername("");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(customer);
    }

    @Test
    public void testUpdateCustomer()
    {
        Customer before = null;
        Customer after = null;
        try
        {
            before = service.createCustomer("frank@gmail.com", "frank", "456Def", "5148888888", "444 1st avenue");
            
            Customer dummy = new Customer();
            dummy.setAccountId(1);
            dummy.setEmail("dave@gmail.com");
            dummy.setUsername("dave2");
            dummy.setPassword("123Abc");
            dummy.setPhoneNumber("5149999999");
            dummy.setAddress("555 1st avenue");
            after = service.updateCustomerInfo(dummy);
        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(after);

        assertEquals("frank@gmail.com", before.getEmail());
        assertEquals("frank", before.getUsername());
        assertEquals("456Def", before.getPassword());
        assertEquals("5148888888", before.getPhoneNumber());
        assertEquals("444 1st avenue", before.getAddress());
        
        assertEquals("dave@gmail.com", after.getEmail());
        assertEquals("dave2", after.getUsername());
        assertEquals("123Abc", after.getPassword());
        assertEquals("5149999999", after.getPhoneNumber());
        assertEquals("555 1st avenue", after.getAddress());
    }
    
    @Test
    public void testCustomerLogin() {
    	Customer customer = null;
        try
        {
            customer = service.login("dave2@gmail.com", "123Abc");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(customer);
        assertEquals("dave2@gmail.com", customer.getEmail());
        assertEquals("dave2", customer.getUsername());
        assertEquals("123Abc", customer.getPassword());
        assertEquals("5149999999", customer.getPhoneNumber());
        assertEquals("555 1st avenue", customer.getAddress());
    }
    
    @Test
    public void testCustomerLoginUsernameIncorrect() {
    	Customer customer = null;
    	try
    	{
    		customer = service.login("dave3", "123Abc");
    		
    	} catch (IllegalArgumentException e)
    	{
    		assertNull(customer);
    	}
    }
    
    @Test
    public void testCustomerLoginPasswordIncorrect() {
    	Customer customer = null;
    	try
    	{
    		customer = service.login("dave2", "123Abcd");
    		
    	} catch (IllegalArgumentException e)
    	{
    		assertNull(customer);
    	}
    }
    
    
    @Test
    public void testDeleteCustomer()
    {
        Customer customer = null;
        Customer deleted = null;
        try
        {
        	customer = service.createCustomer("frank@gmail.com", "frank", "456Def", "5148888888", "444 1st avenue");
            deleted = service.deleteCustomer(customer);
        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(deleted);
        assertEquals(customer.getEmail(), deleted.getEmail());
        assertEquals(customer.getUsername(), deleted.getUsername());
        assertEquals(customer.getPassword(), deleted.getPassword());
        assertEquals(customer.getAddress(), deleted.getAddress());
        assertEquals(customer.getPhoneNumber(), deleted.getPhoneNumber());

    }

    @Test
    public void testDeleteCustomerNull()
    {
        Customer deleted = null;
        try
        {
            deleted = service.deleteCustomer(null);
        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(deleted);
    }
}