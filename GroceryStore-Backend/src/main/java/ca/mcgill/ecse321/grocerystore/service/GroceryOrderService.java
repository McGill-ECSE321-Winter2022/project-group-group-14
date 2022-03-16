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
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderType;
import ca.mcgill.ecse321.grocerystore.model.GroceryStore;

import static ca.mcgill.ecse321.grocerystore.service.ServiceHelpers.toList;


@Service
public class GroceryOrderService {
    @Autowired
	GroceryOrderRepository orderDao;
    @Autowired
    CustomerRepository customerDao;
    @Autowired
    OrderItemRepository orderItemDao;
    
    /**
     * @author clarissabaciu
     * @param totalCost
     * @return
     */
    @Transactional
    public GroceryOrder createInStoreOrder(List<OrderItem> orderItems){ 
		String error = "";
		if (orderItems == null || orderItems.isEmpty()) {									//check if the orderItems list is null or empty
		    error = "List of order items must be selected to create an order in store.";
		} else {
			for (OrderItem oi : orderItems) {												//for each order item, make sure they exist in the database
				if (!orderItemDao.existsById(oi.getItemId())) {
					error = "Item"+ oi.getName() + "does not exist.";
				}
			}
		}
		if (error.length() > 0) {
		    throw new IllegalArgumentException(error);		//return appropriate error							
		}

//		if (orderDao.existsById))
//			
//			
//	
//		if (registrationRepository.existsByPersonAndEvent(person, event)) {
//		    error = error + "Person is already registered to this event!";
//		}
//
//		

    	
    	
    	
        GroceryOrder order = new GroceryOrder();
        order.setOrderType(OrderType.InStore);
        int totalCost = 0; 
        for (OrderItem oi : orderItems) {			
        	totalCost = totalCost + oi.getPrice();
        }
        order.setTotalCost(totalCost);
        order.setOrderStatus(OrderStatus.Completed);
        order = orderDao.save(order);	
        order.setOrderItems(orderItems);
        order = orderDao.save(order);	
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
        order = orderDao.save(order);	
        order.setOrderItems(orderItems);
        order.setCustomer(customer);	
        order = orderDao.save(order);	
        return order;
    }
    
    /**
     * @author clarissabaciu
     * @param Id
     * @return
     */
    @Transactional
    public GroceryOrder getOrderById(int Id){
    	GroceryOrder order = orderDao.findByOrderId(Id);
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
    	if (customer == null || customerDao.findById(customer.getAccountId()) == null) throw new IllegalArgumentException("Please submit a proper customer.");  
    	
    	List<GroceryOrder> orderList = orderDao.findGroceryOrdersByCustomer(customer);	
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
    	
    	List<GroceryOrder> orderList = orderDao.findGroceryOrdersByCustomer(customer);
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
        List<GroceryOrder> orderList = orderDao.findByOrderType(orderType);
        return orderList;
    }
    
    /**
     * @author clarissabaciu
     * @param orderStatus
     * @return
     */
    @Transactional
    public List<GroceryOrder> getOrdersByOrderStatus(OrderStatus orderStatus){
    	if (orderStatus == null) throw new IllegalArgumentException("Please select a proper order status.");
        List<GroceryOrder> orderList = orderDao.findByOrderStatus(orderStatus);
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
    	 List<GroceryOrder> orderList = orderDao.findByOrderType(orderType);
    	return orderList.size();
    }
    
    /**
     * @author clarissabaciu
     * @param orderType
     * @return
     */
    @Transactional
    public List<GroceryOrder> getAllOrders(){
    	List<GroceryOrder> allOrders = toList(orderDao.findAll());
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
    	
    	GroceryOrder oldOrder = orderDao.findByOrderId(newOrder.getOrderId());
    	if (oldOrder == null) throw new IllegalArgumentException("No such order exists in the database. Maybe create new instead");
    	
    	oldOrder.setTotalCost(newOrder.getTotalCost());
    	oldOrder.setOrderType(newOrder.getOrderType());	
    	oldOrder.setOrderItems(newOrder.getOrderItems());
    	oldOrder.setCustomer(newOrder.getCustomer());
    	
    	oldOrder = orderDao.save(oldOrder);
    	return oldOrder;
    }
    
    /**
     * @author clarissabaciu
     * @param groceryOrder
     * @return
     */
    @Transactional
    public GroceryOrder deleteOrder(GroceryOrder groceryOrder){ 
    	orderDao.delete(groceryOrder);
    	return groceryOrder; 
    } 
    
    /**
     * @author clarissabaciu
     * @return total sales for monthly report
     */
    @Transactional
    public int getTotalSales(){  
    	int sales = 0;
    	for (GroceryOrder order : orderDao.findByOrderStatus(OrderStatus.Completed)) {
    		sales += order.getTotalCost();
    	}
    	return sales;
    } 
    

    /**
     * @author clarissabaciu
     * deletes all orders, done by owner at the end of the month after montly report is generated
     */
    @Transactional
    public void deleteAllOrders(){  
    	for (GroceryOrder order : orderDao.findByOrderStatus(OrderStatus.Completed)) {
    		orderDao.delete(order);
    	}
    } 

    
}
