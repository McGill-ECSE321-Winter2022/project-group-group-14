package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.Order;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.Item;

import java.util.List;

public interface OrderRepository extends CrudRepository <Order, Integer>{
    Order findByOrderNumber(int orderNumber);
    List<Order> findOrdersByCustomer(Customer customer);
    List<Order> findOrdersByTimeslots(Item items);
}

