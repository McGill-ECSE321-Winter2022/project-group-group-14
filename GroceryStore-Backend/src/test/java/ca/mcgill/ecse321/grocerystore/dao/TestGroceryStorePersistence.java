package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.GroceryStore;
import ca.mcgill.ecse321.grocerystore.model.Owner;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional //don't need clear database method
public class TestGroceryStorePersistence {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private GroceryStoreRepository groceryStoreRepository;
	@Autowired
	private OwnerRepository ownerRepository;

    
	/**
	 * Test grocery store presistence
	 * @author clarissabaciu
	 */
	@Test
	public void testPersistAndLoadMultipleGroceryStore(){
		GroceryStore store1 = new GroceryStore();
		GroceryStore store2 = new GroceryStore();

		store1 = groceryStoreRepository.save(store1);
		store2 = groceryStoreRepository.save(store2);
		int id1 = store1.getStoreId();
		int id2 = store2.getStoreId();

		store1 = null;
		store2 = null;
		store1 = groceryStoreRepository.findByStoreId(id1);
		store2 = groceryStoreRepository.findByStoreId(id2);

		assertNotNull(store1);
		assertNotNull(store2);
		assertEquals(id1, store1.getStoreId());
		assertEquals(id2, store2.getStoreId());
	}
	
	//test GroceryStore association
	@Test
	public void testPersistAndLoadGroceryStoreWithAccounts(){
		GroceryStore store = new GroceryStore();
		Owner owner = new Owner();
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();
		Employee employee = new Employee();
		
		store = groceryStoreRepository.save(store);	
		int storeId = store.getStoreId();
		owner = ownerRepository.save(owner);
		employee = employeeRepository.save(employee);
		customer1 = customerRepository.save(customer1);
		customer2 = customerRepository.save(customer2);
		
        List<Account> accounts = new ArrayList<Account>(); //adding associations
        accounts.add(owner);
        accounts.add(employee);
        accounts.add(customer1);
        accounts.add(customer2);
        
        store.setAccounts(accounts);
		
        groceryStoreRepository.save(store);	
		ownerRepository.save(owner);
		employeeRepository.save(employee);
		customerRepository.save(customer1);
		customerRepository.save(customer2);
        
		store = null; 
		
		store = groceryStoreRepository.findByStoreId(storeId);
		assertNotNull(store);
        assertFalse(store.getAccounts().isEmpty());
        assertEquals(store.getAccounts().get(0).getAccountId(), owner.getAccountId());
        assertEquals(store.getAccounts().get(1).getAccountId(),employee.getAccountId());
        assertEquals(store.getAccounts().get(2).getAccountId(),customer1.getAccountId());
        assertEquals(store.getAccounts().get(3).getAccountId(),customer2.getAccountId());

		
	}


}



