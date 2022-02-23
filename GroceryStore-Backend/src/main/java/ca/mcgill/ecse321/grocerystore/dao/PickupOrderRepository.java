package ca.mcgill.ecse321.grocerystore.dao;


import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.Item;
import java.util.List;
import ca.mcgill.ecse321.grocerystore.model.PickupOrder;

public interface PickupOrderRepository extends CrudRepository <PickupOrder,Integer >{
    PickupOrder findPickupByOrderNumber(int orderNumber);
    List<PickupOrder> findPickupOrdersByCustomer(Customer customer);
    List<PickupOrder> findPickupOrdersByTimeslots(Item items);
}
