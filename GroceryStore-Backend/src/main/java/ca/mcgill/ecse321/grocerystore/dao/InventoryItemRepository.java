package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;

public interface InventoryItemRepository extends CrudRepository <InventoryItem, String >{
    InventoryItem findByname(String name);   
}
