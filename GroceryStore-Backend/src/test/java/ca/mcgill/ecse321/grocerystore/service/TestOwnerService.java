package ca.mcgill.ecse321.grocerystore.service;

import ca.mcgill.ecse321.grocerystore.dao.OwnerRepository;
import ca.mcgill.ecse321.grocerystore.model.Owner;
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
public class TestOwnerService
{
    @Mock
    private OwnerRepository ownerDao;

    @InjectMocks
    private OwnerService service;


    @BeforeEach
    public void setMockOutput()
    {
    	lenient().when(ownerDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(1))
            {
                Owner owner = new Owner();
                owner.setEmail("dave2@gmail.com");
                owner.setUsername("dave2");
                owner.setPassword("123Abc");
                return owner;
            } else
            {
                return null;
            }
        });
        lenient().when(ownerDao.findByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
        	if (invocation.getArgument(0).equals("dave2")) {
            
            	Owner owner = new Owner();
                owner.setEmail("dave2@gmail.com");
                owner.setUsername("dave2");
                owner.setPassword("123Abc");
                return owner;
        	}
        	else {
        		return null;
        	}

        });
        lenient().when(ownerDao.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
        	if (invocation.getArgument(0).equals("dave2@gmail.com")) {
            	Owner owner = new Owner();
                owner.setEmail("dave2@gmail.com");
                owner.setUsername("dave2");
                owner.setPassword("123Abc");
                return owner;
        	}
        	else {
        		return null;
        	}
        });
        lenient().when(ownerDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Owner owner = new Owner();
            owner.setEmail("dave2@gmail.com");
            owner.setUsername("dave2");
            owner.setPassword("123Abc");
            ArrayList<Owner> list = new ArrayList<>();
            list.add(owner);
            return list;
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(ownerDao.save(any(Owner.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreateOwner()
    {
        Owner owner = null;
        try
        {
            owner = service.createOwner("dave@gmail.com","dave","123Abc");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(owner);
        assertEquals("dave@gmail.com", owner.getEmail());
        assertEquals("dave", owner.getUsername());
        assertEquals("123Abc", owner.getPassword());
    }

//    @Test
//    public void testGetAll()
//    {
//        ArrayList<Owner> owners = null;
//        try
//        {
//            owners = new ArrayList<Owner>(service.getAllOwners());
//
//        } catch (IllegalArgumentException e)
//        {
//            fail();
//        }
//        assertNotNull(owners);
//        assertEquals(1, owners.size());
//        assertEquals("dave2", owners.get(0).getUsername());
//    }

    @Test
    public void testGetByWrongID()
    {
        Owner owner = null;
        try
        {
            owner = service.getOwnerByID(420);

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(owner);
    }

    @Test
    public void testGetByEmail()
    {
        Owner owner = null;
        try
        {
            owner = service.getOwnerByEmail("dave2@gmail.com");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(owner);
        assertEquals("dave2@gmail.com", owner.getEmail());
        assertEquals("dave2", owner.getUsername());
        assertEquals("123Abc", owner.getPassword());
    }

    @Test
    public void testGetByWrongEmail()
    {
        Owner owner = null;
        try
        {
            owner = service.getOwnerByEmail("bob@gmail.com");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(owner);
    }

    @Test
    public void testGetByUsername()
    {
        Owner owner = null;
        try
        {
            owner = service.getOwnerByUsername("dave2");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(owner);
        assertEquals("dave2@gmail.com", owner.getEmail());
        assertEquals("dave2", owner.getUsername());
        assertEquals("123Abc", owner.getPassword());
    }

    @Test
    public void testGetByWrongUsername()
    {
        Owner owner = null;
        try
        {
            owner = service.getOwnerByUsername("");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(owner);
    }

//    @Test
//    public void testUpdateOwner()
//    {
//        Owner before = null;
//        Owner after = null;
//        try
//        {
//            before = service.createOwner("frank@gmail.com", "frank", "456Def");
//            
//            Owner dummy = new Owner();
//            dummy.setEmail("dave@gmail.com");
//            dummy.setUsername("frank");
//            dummy.setPassword("123Abc");
//            after = service.updateOwnerInfo(dummy);
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
    public void testOwnerLogin() {
    	Owner owner = null;
        try
        {
            owner = service.login("dave2@gmail.com", "123Abc");

        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(owner);
        assertEquals("dave2@gmail.com", owner.getEmail());
        assertEquals("dave2", owner.getUsername());
        assertEquals("123Abc", owner.getPassword());
    }
    
    @Test
    public void testOwnerLoginUsernameIncorrect() {
    	Owner owner = null;
    	try
    	{
    		owner = service.login("dave3", "123Abc");
    		
    	} catch (IllegalArgumentException e)
    	{
    		assertNull(owner);
    	}
    }
    
    @Test
    public void testOwnerLoginPasswordIncorrect() {
    	Owner owner = null;
    	try
    	{
    		owner = service.login("dave2", "123Abcd");
    		
    	} catch (IllegalArgumentException e)
    	{
    		assertNull(owner);
    	}
    }
    
    
    @Test
    public void testDeleteOwner()
    {
        Owner owner = null;
        Owner deleted = null;
        try
        {
        	owner = service.createOwner("frank@gmail.com", "frank", "456Def");
            deleted = service.deleteOwner(owner);
        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(deleted);
        assertEquals(owner.getEmail(), deleted.getEmail());
        assertEquals(owner.getUsername(), deleted.getUsername());
        assertEquals(owner.getPassword(), deleted.getPassword());

    }

    @Test
    public void testDeleteOwnerNull()
    {
        Owner deleted = null;
        try
        {
            deleted = service.deleteOwner(null);
        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(deleted);
    }
}