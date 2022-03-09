package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.model.Owner;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional //don't need clear database method
public class TestOwnerPersistence {
	@Autowired
	private OwnerRepository ownerRepository;

	/**
	 * @author Samuel Valentine
	 */
	@Test
	public void testPersistAndLoadOwner(){

			
		//set values to Owner attributes
		Owner owner = new Owner();
		
		String email = "owner1@gmail.com";
		String username = "owner1";
		String password = "123abc";
		owner.setUsername(username);
		owner.setPassword(password);
		owner.setPassword(email);
		
		//save Owner object
		ownerRepository.save(owner);
		
		//test saved Owner object
		Owner savedOwner = ownerRepository.findByAccountId(owner.getAccountId());
		assertNotNull(savedOwner);
		assertEquals(owner.getEmail(), savedOwner.getEmail());
		assertEquals(owner.getPassword(), savedOwner.getPassword());
		assertEquals(owner.getUsername(), savedOwner.getUsername());
	}


}


