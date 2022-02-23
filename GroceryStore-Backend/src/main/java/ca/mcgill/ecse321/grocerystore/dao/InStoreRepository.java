package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.InStore;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.Item;
import java.util.List;

public interface InStoreRepository extends CrudRepository <InStore, Integer>{
    InStore findByOrderNumber(int orderNumber);
    List<InStore> findInStoresByCustomer(Customer customer);
    List<InStore> findInStoresByTimeslots(Item items);
    
}
