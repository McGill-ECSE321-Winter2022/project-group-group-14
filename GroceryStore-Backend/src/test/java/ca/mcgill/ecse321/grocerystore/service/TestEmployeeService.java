package ca.mcgill.ecse321.grocerystore.service;

import ca.mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import ca.mcgill.ecse321.grocerystore.model.Employee;
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
public class TestEmployeeService
{
    @Mock
    private EmployeeRepository employeeDao;

    @InjectMocks
    private EmployeeService service;


    @BeforeEach
    public void setMockOutput()
    {
    	lenient().when(employeeDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(1))
            {
                Employee employee = new Employee();
                employee.setEmail("dave2@gmail.com");
                employee.setUsername("dave2");
                employee.setPassword("123Abc");
                return employee;
            } else
            {
                return null;
            }
        });
        lenient().when(employeeDao.findByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
        	if (invocation.getArgument(0).equals("dave2")) {
            
            	Employee employee = new Employee();
                employee.setEmail("dave2@gmail.com");
                employee.setUsername("dave2");
                employee.setPassword("123Abc");
                return employee;
        	}
        	else {
        		return null;
        	}

        });
        lenient().when(employeeDao.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
        	if (invocation.getArgument(0).equals("dave2@gmail.com")) {
            	Employee employee = new Employee();
                employee.setEmail("dave2@gmail.com");
                employee.setUsername("dave2");
                employee.setPassword("123Abc");
                return employee;
        	}
        	else {
        		return null;
        	}
        });
        lenient().when(employeeDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Employee employee = new Employee();
            employee.setEmail("dave2@gmail.com");
            employee.setUsername("dave2");
            employee.setPassword("123Abc");
            ArrayList<Employee> list = new ArrayList<>();
            list.add(employee);
            return list;
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(employeeDao.save(any(Employee.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreateEmployee()
    {
        Employee employee = null;
        try
        {
            employee = service.createEmployee("dave@gmail.com","dave","123Abc");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(employee);
        assertEquals("dave@gmail.com", employee.getEmail());
        assertEquals("dave", employee.getUsername());
        assertEquals("123Abc", employee.getPassword());
    }

//    @Test
//    public void testGetAll()
//    {
//        ArrayList<Employee> employees = null;
//        try
//        {
//            employees = new ArrayList<Employee>(service.getAllEmployees());
//
//        } catch (IllegalArgumentException e)
//        {
//            fail();
//        }
//        assertNotNull(employees);
//        assertEquals(1, employees.size());
//        assertEquals("dave2", employees.get(0).getUsername());
//    }

    @Test
    public void testGetByWrongID()
    {
        Employee employee = null;
        try
        {
            employee = service.getEmployeeByID(420);

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(employee);
    }

    @Test
    public void testGetByEmail()
    {
        Employee employee = null;
        try
        {
            employee = service.getEmployeeByEmail("dave2@gmail.com");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(employee);
        assertEquals("dave2@gmail.com", employee.getEmail());
        assertEquals("dave2", employee.getUsername());
        assertEquals("123Abc", employee.getPassword());
    }

    @Test
    public void testGetByWrongEmail()
    {
        Employee employee = null;
        try
        {
            employee = service.getEmployeeByEmail("bob@gmail.com");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(employee);
    }

    @Test
    public void testGetByUsername()
    {
        Employee employee = null;
        try
        {
            employee = service.getEmployeeByUsername("dave2");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(employee);
        assertEquals("dave2@gmail.com", employee.getEmail());
        assertEquals("dave2", employee.getUsername());
        assertEquals("123Abc", employee.getPassword());
    }

    @Test
    public void testGetByWrongUsername()
    {
        Employee employee = null;
        try
        {
            employee = service.getEmployeeByUsername("");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(employee);
    }

//    @Test
//    public void testUpdateEmployee()
//    {
//        Employee before = null;
//        Employee after = null;
//        try
//        {
//            before = service.createEmployee("frank@gmail.com", "frank", "456Def");
//            
//            Employee dummy = new Employee();
//            dummy.setEmail("dave@gmail.com");
//            dummy.setUsername("frank");
//            dummy.setPassword("123Abc");
//            after = service.updateEmployeeInfo(dummy);
//        } catch (IllegalArgumentException e)
//        {
//            fail();
//        }
//        assertNotNull(after);
//
//        assertEquals("frank@gmail.com", before.getEmail());
//        assertEquals("frank", before.getUsername());
//        assertEquals("456Def", before.getPassword());
//        
//        assertEquals("dave@gmail.com", after.getEmail());
//        assertEquals("frank", after.getUsername());
//        assertEquals("123Abc", after.getPassword());
//    }
    
    @Test
    public void testEmployeeLogin() {
    	Employee employee = null;
        try
        {
            employee = service.login("dave2@gmail.com", "123Abc");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(employee);
        assertEquals("dave2@gmail.com", employee.getEmail());
        assertEquals("dave2", employee.getUsername());
        assertEquals("123Abc", employee.getPassword());
    }
    
    @Test
    public void testEmployeeLoginUsernameIncorrect() {
    	Employee employee = null;
    	try
    	{
    		employee = service.login("dave3", "123Abc");
    		
    	} catch (IllegalArgumentException e)
    	{
    		assertNull(employee);
    	}
    }
    
    @Test
    public void testEmployeeLoginPasswordIncorrect() {
    	Employee employee = null;
    	try
    	{
    		employee = service.login("dave2", "123Abcd");
    		
    	} catch (IllegalArgumentException e)
    	{
    		assertNull(employee);
    	}
    }
    
    
    @Test
    public void testDeleteEmployee()
    {
        Employee employee = null;
        Employee deleted = null;
        try
        {
        	employee = service.createEmployee("frank@gmail.com", "frank", "456Def");
            deleted = service.deleteEmployee(employee);
        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(deleted);
        assertEquals(employee.getEmail(), deleted.getEmail());
        assertEquals(employee.getUsername(), deleted.getUsername());
        assertEquals(employee.getPassword(), deleted.getPassword());

    }

    @Test
    public void testDeleteEmployeeNull()
    {
        Employee deleted = null;
        try
        {
            deleted = service.deleteEmployee(null);
        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(deleted);
    }
}