package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderType;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;

import java.util.List;

public interface GroceryOrderRepository extends CrudRepository <GroceryOrder, Integer>{
    GroceryOrder findByOrderId(int orderId);
    List<GroceryOrder> findByOrderType(OrderType orderType);
//    List<GroceryOrder> findGroceryOrdersByCustomer(Customer customer);
    GroceryOrder findGroceryOrderByOrderItems(OrderItem orderItems);
    //GroceryOrder findGroceryOrderByOrderItems(OrderItem orderItems); //not very important
    //possibly add find order items by boolean if its paied or not
}

