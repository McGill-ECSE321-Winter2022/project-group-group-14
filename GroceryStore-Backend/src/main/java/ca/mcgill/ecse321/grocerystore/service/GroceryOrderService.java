package ca.mcgill.ecse321.grocerystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.dao.CustomerRepository;
import ca.mcgill.ecse321.grocerystore.dao.GroceryOrderRepository;
import ca.mcgill.ecse321.grocerystore.dao.InventoryItemRepository;
import ca.mcgill.ecse321.grocerystore.dao.OrderItemRepository;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderStatus;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderType;
import ca.mcgill.ecse321.grocerystore.model.GroceryStore;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;

import static ca.mcgill.ecse321.grocerystore.service.ServiceHelpers.toList;

/**
 * 
 * @author clarissabaciu
 *
 */

@Service
public class GroceryOrderService {
    @Autowired
	GroceryOrderRepository orderDao;
    @Autowired
    CustomerRepository customerDao;
    @Autowired
    OrderItemRepository orderItemDao;
    @Autowired
    InventoryItemRepository inventoryItemDao;
    
  //-------------------------------------------------------CREATE METHODS------------------------------------------------------------
    
    /**
     * @param customer
     * @param orderItems
     * @param orderType
     * @return 
     */
    @Transactional
    public GroceryOrder createOrder(Customer customer, List<OrderItem> orderItems, OrderType orderType){ 
    	
    	checkOrderValidity(customer,orderItems,orderType);
    	
    	GroceryOrder order = new GroceryOrder();
        order.setOrderType(orderType);
        int totalCost = 0; 
        for (OrderItem oi : orderItems) {			//go through items and calculate total order cost
        	totalCost = totalCost + oi.getPrice();
        }
        
        if (order.getOrderType().equals(OrderType.Delivery)) {			//if order is a delivery and customer is out of town, add extra fee
        	if (customer.getAddress().contains(GroceryStore.town)){
        		totalCost+=GroceryStore.outOfTownFee;
        	}
        }
        order.setTotalCost(totalCost);				
        order = orderDao.save(order);			//save order before setting associations
        order.setOrderItems(orderItems);
        order.setCustomer(customer);	
        order = orderDao.save(order);	
        return order;
    }
    
    /**
     * @param totalCost
     * @return
     */
    @Transactional
    public GroceryOrder createInStoreOrder(List<OrderItem> orderItems){ 
    	checkOrderValidity(orderItems);
        GroceryOrder order = new GroceryOrder();		
        order.setOrderType(OrderType.InStore);
        int totalCost = 0; 
        for (OrderItem oi : orderItems) {					//find total cost based on items contained in order
        	totalCost = totalCost + oi.getPrice();
        }
        order.setTotalCost(totalCost);						
        order.setOrderStatus(OrderStatus.Completed);		//order is automatically completed since it is made in store
        order = orderDao.save(order);	
        order.setOrderItems(orderItems);
        order = orderDao.save(order);	
        return order;
    }
    
//-------------------------------------------------------GET METHODS------------------------------------------------------------
    
    /**
     * @return a list of all orders in system
     */
    @Transactional
    public List<GroceryOrder> getAllOrders(){
    	List<GroceryOrder> allOrders = toList(orderDao.findAll());
    	if (allOrders == null) throw new IllegalArgumentException("List of orders in system is null."); 
    	return allOrders;
    }
  /**
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
     * @param customer
     * @returna a list of orders for this particular customer
     */
    @Transactional
    public List<GroceryOrder> getOrdersByCustomer(Customer customer){ 
    	if (customer == null || !customerDao.existsById(customer.getAccountId())) throw new IllegalArgumentException("Please submit a proper customer.");  
    	List<GroceryOrder> orderList = orderDao.findGroceryOrdersByCustomer(customer);	
    	if (orderList == null) throw new IllegalArgumentException("List of orders for customer " + customer.getUsername() +" is null.");  
        return orderList;
    }
    
    
    /**									
     * @param orderType
     * @return returns a list of orders for a particular orderType
     */
    @Transactional
    public List<GroceryOrder> getOrdersByOrderType(OrderType orderType){
    	if (orderType == null) throw new IllegalArgumentException("Please select a proper order type.");
        List<GroceryOrder> orderList = orderDao.findByOrderType(orderType);
        if (orderList == null) throw new IllegalArgumentException("List of orders for order type " + orderType.toString() +" is null.");  
        return orderList;
    }
    
    /**
     * @param orderStatus
     * @return returns a list of orders for a particular orderStatus
     */
    @Transactional
    public List<GroceryOrder> getOrdersByOrderStatus(OrderStatus orderStatus){
    	if (orderStatus == null) throw new IllegalArgumentException("Please select a proper order status.");
        List<GroceryOrder> orderList = orderDao.findByOrderStatus(orderStatus);
        if (orderList == null) throw new IllegalArgumentException("List of orders for order status " + orderStatus.toString() +" is null.");  
        return orderList;
    }
    
    

  //-------------------------------------------------------UPDATE METHOD------------------------------------------------------------
    
    /**
     * @param newOrder
     * @return modified order
     */
    @Transactional
    public GroceryOrder modifyOrder(GroceryOrder newOrder){ 
    	checkOrderValidity(newOrder.getCustomer(), newOrder.getOrderItems(), newOrder.getOrderType());
    	if (newOrder.getTotalCost() == 0) throw new IllegalArgumentException("New order cannot amount to 0$.");	
    	GroceryOrder oldOrder = orderDao.findByOrderId(newOrder.getOrderId());
    	if (oldOrder == null) throw new IllegalArgumentException("No such order exists in the database. Maybe create new instead");

    	List<OrderItem> oldOrderItems = oldOrder.getOrderItems();    	//update inventory and delete order items accordingly 
     	List<OrderItem> newOrderItems = newOrder.getOrderItems();
     	
		for (OrderItem oldItem : oldOrderItems) {		//remove old order items and add them back to inventory
			if (!newOrderItems.contains(oldItem)) {
				InventoryItem inventoryItem = inventoryItemDao.findByName(oldItem.getName()); 
				if (inventoryItem == null) throw new IllegalArgumentException("Order item that is being removed from order has no corresponding inventory item.");
				inventoryItem.setCurrentStock(inventoryItem.getCurrentStock()+1); 	//return order item to inventory
				inventoryItemDao.save(inventoryItem);
				orderItemDao.delete(oldItem); 	//delete order item that is no longer in the order	
			}
		}
    	oldOrder.setTotalCost(newOrder.getTotalCost());
    	oldOrder.setOrderType(newOrder.getOrderType());	
    	oldOrder.setOrderItems(newOrder.getOrderItems());
    	oldOrder.setCustomer(newOrder.getCustomer());
    	oldOrder = orderDao.save(oldOrder);
    	return oldOrder;
    }
 //-------------------------------------------------------DELETE METHODS------------------------------------------------------------
    
    /**
     * @param groceryOrder
     */
    @Transactional
    public void deleteOrder(GroceryOrder groceryOrder){ 
    	if (groceryOrder == null || !orderDao.existsById(groceryOrder.getOrderId())) throw new IllegalArgumentException("Please submit a valid grocery order.");
    	if (!groceryOrder.getOrderStatus().equals(OrderStatus.Completed))throw new IllegalArgumentException("Are you sure you want to delete an order that has not been completed?");
    	orderDao.delete(groceryOrder);
    } 
    
    /**
     * deletes all orders, done by owner at the end of the month after montly report is generated
     */
    @Transactional
    public void deleteAllOrders(){  
    	if (orderDao.findByOrderStatus(OrderStatus.Completed) == null) {
    		 throw new IllegalArgumentException("List of orders for order status completed is null.");  
    	}
    	for (GroceryOrder order : orderDao.findByOrderStatus(OrderStatus.Completed)) {	 	//only delete complete grocery orders
    		if (order == null || !orderDao.existsById(order.getOrderId())) throw new IllegalArgumentException("Completed grocery order does not exist in database.");
    		orderDao.delete(order);
    	}
    } 
    
 //-------------------------------------------------------EXTRA METHODS------------------------------------------------------------
    
    /**
     * @return total sales of completed orders for monthly report
     */
    @Transactional
    public int viewTotalSales(){ //should only be accessed by owner --> this will be dealt with in the view
    	int sales = 0;
    	if (orderDao.findByOrderStatus(OrderStatus.Completed) == null) {
   		 throw new IllegalArgumentException("List of orders for order status completed is null.");  
   	}
    	for (GroceryOrder order : orderDao.findByOrderStatus(OrderStatus.Completed)) {
    		if (order == null || !orderDao.existsById(order.getOrderId())) throw new IllegalArgumentException("Completed grocery order does not exist in database.");
    		sales += order.getTotalCost();
    	}
    	return sales;
    } 
    

    /**
     * @param Id 
     */  
    @Transactional
    public void updateOrderStatus(int Id){   //should only be done by an employee/owner account --> we will take care of this in the view for the ui
    	GroceryOrder order = orderDao.findByOrderId(Id);
    	if (order == null) throw new IllegalArgumentException("Please submit a valid order ID."); //validation for proper id
    	if (order.getOrderStatus() == null) throw new IllegalArgumentException("Order status of the order is null. Cannot be updated.");
    	if (order.getOrderStatus().equals(OrderStatus.Completed)) {			//if order is already completed, order status no longer needs to be updated
    		 throw new IllegalArgumentException("Order has already been completed. Does not require an update.");  	
    	}
    	if (order.getOrderStatus() == null) {								//if order is already completed, order status no longer needs to be updated
    		 throw new IllegalArgumentException("Order has no status.");  	
    	}
    	if (order.getOrderStatus().equals(OrderStatus.Received)) { 	//update from received --> processing as employee prepares order
    		order.setOrderStatus(OrderStatus.Processing);
    		orderDao.save(order);
    	} else if (order.getOrderStatus().equals(OrderStatus.Processing)) {		// update from processing 
    		if (order.getOrderType() == null) throw new IllegalArgumentException("Order type is not set."); //make sure order has a type
    		if (order.getOrderType().equals(OrderType.Delivery)){
    			order.setOrderStatus(OrderStatus.BeingDelivered);	
        		orderDao.save(order);	
    		}else if (order.getOrderType().equals(OrderType.PickUp)) {
    			order.setOrderStatus(OrderStatus.ReadyForPickUp);
        		orderDao.save(order);
    		}
    	}else{ 	// once order is picked up or delivered, set to completetd
    		order.setOrderStatus(OrderStatus.Completed);
    		orderDao.save(order);
    	}
    } 
    
    /**
     * @param Id
     */
    @Transactional
    public OrderStatus viewOrderStatus(int Id) { //customer should be able to view the status of their orders
    	GroceryOrder order = orderDao.findByOrderId(Id);
    	if (order == null) throw new IllegalArgumentException("Please submit a valid order ID."); 
    	if (order.getOrderStatus() == null) {								//if order is already completed, order status no longer needs to be updated
   		 throw new IllegalArgumentException("Order has no status.");  	
    	}
    	return order.getOrderStatus();
    }
    
    /**
     * @param paymentInfo
     * @param Id
     * 
     * if payment info is valid, change order from completed --> processing
     */
    @Transactional
    public void payForOrder(String paymentInfo, int  Id) { 	//payment info should be hashed
    	if (!isPaymentValid(paymentInfo))throw new IllegalArgumentException("Payment information is invalid");  
    	GroceryOrder order = orderDao.findByOrderId(Id);
    	if (order == null) throw new IllegalArgumentException("Please submit a valid order ID."); 
    	if (!order.getOrderStatus().equals(OrderStatus.Completed)){
    		throw new IllegalArgumentException("Order does not have a completed status, it is" + order.getOrderStatus().toString() + "instead. Cannot submit payment."); 
    	}
    	order.setOrderStatus(OrderStatus.Processing);
    	orderDao.save(order);	
    }
    
  //-------------------------------------------------------VALIDATION------------------------------------------------------------
    
    private boolean isPaymentValid(String paymentInfo) {	//16 credit numbers, 4 expiration, 3 cvv, 6 postal code --> + how to secure/ hash this information
    	if (paymentInfo == null || paymentInfo.length() == 0 || paymentInfo.length()!= 29) {
    		return false;
    	}
    	return true; 
    }
    
    public void checkOrderValidity(Customer customer, List<OrderItem> orderItems, OrderType orderType) {
    	if (customer == null || !customerDao.existsById(customer.getAccountId())) throw new IllegalArgumentException("Please select a proper customer.");  	
    	if (orderType == null) throw new IllegalArgumentException("Please select a proper order type.");
    	if (orderItems.isEmpty()||orderItems == null) throw new IllegalArgumentException("Please submit a list of items that is not empty or null.");
    	for (OrderItem oi : orderItems) {
    		if (oi == null || !orderItemDao.existsById(oi.getItemId())) {
    			throw new IllegalArgumentException("Item "+ oi.getName() + " does not exist in database or is null.");
    		}
    	}
    }
    
    public void checkOrderValidity(List<OrderItem> orderItems){
    	if (orderItems.isEmpty()||orderItems == null) throw new IllegalArgumentException("Please submit a list of items that is not empty or null.");
    	for (OrderItem oi : orderItems) {
    		if (oi == null || !orderItemDao.existsById(oi.getItemId())) {
    			throw new IllegalArgumentException("Item "+ oi.getName() + " does not exist in database or is null.");
    		}
    	}
    }
 
    
}
