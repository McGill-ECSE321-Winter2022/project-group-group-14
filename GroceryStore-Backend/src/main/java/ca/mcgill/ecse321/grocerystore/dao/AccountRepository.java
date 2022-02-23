package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.Account;

public interface AccountRepository extends CrudRepository <Account, String> {
    Account findByEmail (String email);
    Account findByName (String name);                 //why do we need name?
    Account findByUsername(String username);   
    
}