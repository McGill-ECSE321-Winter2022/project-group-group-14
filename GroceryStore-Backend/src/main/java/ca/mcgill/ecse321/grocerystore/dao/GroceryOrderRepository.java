package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;

import java.util.List;

public interface GroceryOrderRepository extends CrudRepository <GroceryOrder, Integer>{
    GroceryOrder findByOrderNumber(int orderNumber);
    List<GroceryOrder> findGroceryOrdersByCustomer(Customer customer);
    List<GroceryOrder> findGroceryOrdersByItems(OrderItem items);
}

