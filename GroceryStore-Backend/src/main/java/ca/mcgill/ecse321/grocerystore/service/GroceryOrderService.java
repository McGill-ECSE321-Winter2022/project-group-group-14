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
import ca.mcgill.ecse321.grocerystore.model.GroceryStore;

import static ca.mcgill.ecse321.grocerystore.service.ServiceHelpers.toList;


@Service
public class GroceryOrderService {
    @Autowired
	GroceryOrderRepository groceryOrderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderItemRepository orderItemsRepository;
    
    /**
     * @author clarissabaciu
     * @param totalCost
     * @return
     */
    @Transactional
    public GroceryOrder createInStoreOrder(int totalCost){ 
    	if (totalCost == 0) throw new IllegalArgumentException("Order made in store cannot amount to 0$. ");
        GroceryOrder order = new GroceryOrder();
        order.setOrderType(OrderType.InStore);
        order.setTotalCost(totalCost);
        order = groceryOrderRepository.save(order);	
        return order;
    }
    
    /**
     * @author clarissabaciu
     * @param customer
     * @param orderItems
     * @param orderType
     * @return
     */
    @Transactional
    public GroceryOrder createOrder(Customer customer, List<OrderItem> orderItems, OrderType orderType){ 
    	if (customer == null) throw new IllegalArgumentException("Please submit a proper customer.");  	
    	if (orderType == null) throw new IllegalArgumentException("Please select a proper order type.");
    	if (orderItems.isEmpty()) throw new IllegalArgumentException("Please submit a list of items that is not empty.");
  
    	GroceryOrder order = new GroceryOrder();
        order.setOrderType(orderType);
        int totalCost = 0; 
        for (OrderItem oi : orderItems) {			
        	totalCost = totalCost + oi.getPrice();
        }
        
        //check if customer is in town
        if (order.getOrderType().equals(OrderType.Delivery)) {
        	if (customer.getAddress().contains(GroceryStore.town)){
        		totalCost+=GroceryStore.outOfTownFee;
        	}
        }
        order.setTotalCost(totalCost);				//can also add tax
        order = groceryOrderRepository.save(order);	
        order.setOrderItems(orderItems);
        order.setCustomer(customer);	
        order = groceryOrderRepository.save(order);	
        return order;
    }
    
    /**
     * @author clarissabaciu
     * @param Id
     * @return
     */
    @Transactional
    public GroceryOrder getOrderById(int Id){
    	GroceryOrder order = groceryOrderRepository.findByOrderId(Id);
    	if (order == null) throw new IllegalArgumentException("Please submit a valid order ID.");
        return order;
    }
    /**
     * @author clarissabaciu
     * @param customer
     * @return
     */
    @Transactional
    public List<GroceryOrder> getOrdersByCustomer(Customer customer){ 
    	if (customer == null || customerRepository.findById(customer.getAccountId()) == null) throw new IllegalArgumentException("Please submit a proper customer.");  
    	
    	List<GroceryOrder> orderList = groceryOrderRepository.findGroceryOrdersByCustomer(customer);	
        return orderList;
    }
    
    /**
     * @author clarissabaciu
     * @param customer
     * @return
     */
    @Transactional
    public int getOrderAmountByCustomer(Customer customer){ 
    	if (customer == null) throw new IllegalArgumentException("Please submit a proper customer.");  
    	
    	List<GroceryOrder> orderList = groceryOrderRepository.findGroceryOrdersByCustomer(customer);
    	return orderList.size();
    }
    
    /**
     * @author clarissabaciu
     * @param orderType
     * @return
     */
    @Transactional
    public List<GroceryOrder> getOrdersByOrderType(OrderType orderType){
    	if (orderType == null) throw new IllegalArgumentException("Please select a proper order type.");
        List<GroceryOrder> orderList = groceryOrderRepository.findByOrderType(orderType);
        return orderList;
    }
    /**
     * @author clarissabaciu
     * @param orderType
     * @return
     */
    @Transactional
    public int getOrderAmountByOrderType(OrderType orderType){ 
    	if (orderType == null) throw new IllegalArgumentException("Please select a proper order type.");
    	 List<GroceryOrder> orderList = groceryOrderRepository.findByOrderType(orderType);
    	return orderList.size();
    }
    
    /**
     * @author clarissabaciu
     * @param orderType
     * @return
     */
    @Transactional
    public List<GroceryOrder> getAllOrders(){
    	List<GroceryOrder> allOrders = toList(groceryOrderRepository.findAll());
    	return allOrders;
    }
    
    /**
     * @author clarissabaciu
     * @param newOrder
     * @return
     */
    @Transactional
    public GroceryOrder modifyOrder(GroceryOrder newOrder){ 
    	if (newOrder == null)throw new IllegalArgumentException("New order is invalid.");
    	if (newOrder.getOrderType() == null) throw new IllegalArgumentException("New order must have an order type.");
    	if (newOrder.getCustomer() == null) throw new IllegalArgumentException("New order must be associated to a customer.");  
    	if (newOrder.getTotalCost() == 0) throw new IllegalArgumentException("New order cannot amount to 0$.");
    	if (newOrder.getOrderItems().isEmpty()) throw new IllegalArgumentException("New order must contain items.");
    	
    	GroceryOrder oldOrder = groceryOrderRepository.findByOrderId(newOrder.getOrderId());
    	if (oldOrder == null) throw new IllegalArgumentException("No such order exists in the database. Maybe create new instead");
    	
    	oldOrder.setTotalCost(newOrder.getTotalCost());
    	oldOrder.setOrderType(newOrder.getOrderType());	
    	oldOrder.setOrderItems(newOrder.getOrderItems());
    	oldOrder.setCustomer(newOrder.getCustomer());
    	
    	oldOrder = groceryOrderRepository.save(oldOrder);
    	return oldOrder;
    }
    
    /**
     * @author clarissabaciu
     * @param groceryOrder
     * @return
     */
    @Transactional
    public GroceryOrder deleteOrder(GroceryOrder groceryOrder){ 
    	groceryOrderRepository.delete(groceryOrder);
    	return groceryOrder; 
    } 
}
