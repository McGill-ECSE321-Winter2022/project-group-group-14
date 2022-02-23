package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.Item;
import java.util.List;

public interface DeliveryOrderRepository extends CrudRepository <DeliveryOrder, Integer>{
    DeliveryOrder findByOrderNumber(int orderNumber);
    DeliveryOrder findByDeliveryAddress(String deliveryAddress);
    List<DeliveryOrder> findDeliveryOrdersByCustomer(Customer customer);
    List<DeliveryOrder> findDeliveryOrdersByTimeslots(Item items);
    //existsByItems?

}
