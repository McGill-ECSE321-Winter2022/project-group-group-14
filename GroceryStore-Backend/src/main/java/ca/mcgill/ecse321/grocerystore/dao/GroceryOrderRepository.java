package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.Item;

import java.util.List;

public interface GroceryOrderRepository extends CrudRepository <GroceryOrder, Integer>{
    GroceryOrder findByOrderNumber(int orderNumber);
    List<GroceryOrder> findOrdersByCustomer(Customer customer);
    List<GroceryOrder> findOrdersByTimeslots(Item items);
}

