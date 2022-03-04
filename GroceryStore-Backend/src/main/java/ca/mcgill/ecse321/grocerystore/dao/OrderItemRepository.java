package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

import ca.mcgill.ecse321.grocerystore.model.OrderItem;

public interface OrderItemRepository extends CrudRepository <OrderItem, Integer>{
	OrderItem findByItemId(int itemId);   
	List<OrderItem> findByName(String name);    
}
