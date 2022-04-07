package ca.mcgill.ecse321.grocerystore.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;

public interface InventoryItemRepository extends CrudRepository <InventoryItem, Integer >{
    InventoryItem findByItemId(int itemId);
    InventoryItem findByName(String name);
    List<InventoryItem> findByAvailability(boolean availability);
}
