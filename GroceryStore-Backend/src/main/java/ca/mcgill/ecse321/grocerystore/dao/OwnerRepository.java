package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.Owner;

public interface OwnerRepository extends CrudRepository <Owner, String> {
    Owner findByEmail (String email);
    Owner findByName (String name);                 //why do we need name?
    Owner findByUsername(String username);   
}