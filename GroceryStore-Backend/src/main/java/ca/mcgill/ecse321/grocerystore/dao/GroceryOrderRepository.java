package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderType;
import ca.mcgill.ecse321.grocerystore.model.Customer;

import java.util.List;

public interface GroceryOrderRepository extends CrudRepository <GroceryOrder, Integer>{
    GroceryOrder findByOrderId(int orderId);                        //retrieve grocery order object with specified id 
    List<GroceryOrder> findByOrderType(OrderType orderType);        //retrieve grocery order objects with specified order type
    List<GroceryOrder> findByOrderStatus(OrderStatus orderStatus);  //retrieve grocery order objects with specified order status
    List<GroceryOrder> findGroceryOrdersByCustomer(Customer customer);  //retrieve grocery order objects associated to specified customer object
    boolean existsByCustomer(Customer customer);                        //return true if belongs to specified customer object
}

