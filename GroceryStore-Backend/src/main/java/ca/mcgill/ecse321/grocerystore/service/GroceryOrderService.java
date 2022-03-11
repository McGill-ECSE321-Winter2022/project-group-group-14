package ca.mcgill.ecse321.grocerystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.dao.CustomerRepository;
import ca.mcgill.ecse321.grocerystore.dao.GroceryOrderRepository;
import ca.mcgill.ecse321.grocerystore.dao.OrderItemRepository;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderType;

/**
 * make a list of services you want related to groceryorder 
 * - in store order doesnt need customer
 * - 
 */


@Service
public class GroceryOrderService {
    @Autowired
	GroceryOrderRepository groceryOrderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderItemRepository orderItemsRepository;

    @Transactional
    public GroceryOrder createInStoreOrder(int totalCost){ 
        GroceryOrder order = new GroceryOrder();
        //create an order without a customer and without order items
        return null;
    }

    @Transactional
    public GroceryOrder createGroceryOrder(Customer customer, List<OrderItem> orderItems, OrderType orderType){ 
        //create groceryOrder with customer and orderItems
        //calculate total Cost from orderItems
        
        return null;
    }

    @Transactional
    public GroceryOrder getOrderById(int Id){
        //repository findByOrderId(int orderId);
        return null;
    }

    @Transactional
    public List<GroceryOrder> getOrdersByCustomer(Customer customer){ 
        //repository : List<GroceryOrder> findGroceryOrdersByCustomer(Customer customer);
        return null;
    }

    @Transactional
    public List<GroceryOrder> getOrdersByOrderType(OrderType orderType){
        //repository List<GroceryOrder> findByOrderType(OrderType orderType);
        return null;
    }
    
}
