package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Customer;

public interface CustomerRepository extends CrudRepository <Customer, Integer> {
	Customer findByAccountId (int accountId);
	Customer findByEmail (String email);
    Customer findByUsername(String username);          
    Customer findByAddress (String address);
    Customer findByPhoneNumber (String phoneNumber);

}
