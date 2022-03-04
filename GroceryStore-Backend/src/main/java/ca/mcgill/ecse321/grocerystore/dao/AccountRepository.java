package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.Account;

public interface AccountRepository extends CrudRepository <Account, Integer> {
    Account findByAccountId (int accountId);
    Account findByEmail (String email);
    Account findByUsername(String username);  
    
}
