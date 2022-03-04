package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.GroceryStore;

public interface GroceryStoreRepository extends CrudRepository <GroceryStore, Integer>{
    GroceryStore findByStoreId(int storeId);
}

