package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.Customer;

public interface CustomerRepository extends CrudRepository <Customer, String> {
    Customer findByEmail (String email);
    Customer findByName (String name);                 //why do we need name?
    Customer findByUsername(String username);          
    Customer findByAddress (String address);
    Customer findByPhoneNumber (String phoneNumber);

}
