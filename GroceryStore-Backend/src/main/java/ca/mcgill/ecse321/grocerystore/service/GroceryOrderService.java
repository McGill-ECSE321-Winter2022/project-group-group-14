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

	// -------------------------------------------------------CREATE
	// METHODS------------------------------------------------------------

	/**
	 * 
	 * @param customer
	 * @return create empty delivery order
	 */
	@Transactional
	public GroceryOrder createDeliveryOrder(Customer customer) {
		ServiceHelpers.checkAccountInfoValidity(customer);
		noCompletedOrders(customer); 			//make sure customer has no active orders
		GroceryOrder order = new GroceryOrder();
		order.setOrderType(OrderType.Delivery);	
		int totalCost = 0;		//total cost of grocery order
		if (!customer.getAddress().contains(GroceryStore.town)) { // if order is a delivery and customer is out of town,
			totalCost += GroceryStore.outOfTownFee;		//add extra fee, set in the grocery store model class 
		}
		order.setTotalCost(totalCost);						
		order.setOrderStatus(OrderStatus.Received);		
		order = orderDao.save(order);					//save order before adding associations
		order.setCustomer(customer);
		order = orderDao.save(order);
		return order;
	}

	/**
	 * helper method
	 * returns true if customer has no completed orders
	 */
	public void noCompletedOrders(Customer customer) {
		for (GroceryOrder order : orderDao.findGroceryOrdersByCustomer(customer)) {
			if (!order.getOrderStatus().equals(OrderStatus.Completed)) {  //if the one of the orders belonging to the customer is not completed, throw illegal argument exception
				throw new IllegalArgumentException("Please complete your order before creating a new one.");
			}
		}
	}

	/**
	 * 
	 * @param customer
	 * @return create empty pick up order
	 */
	@Transactional
	public GroceryOrder createPickupOrder(Customer customer) {
		ServiceHelpers.checkAccountInfoValidity(customer);
		noCompletedOrders(customer); 		//make sure customer has no active orders
		GroceryOrder order = new GroceryOrder();
		order.setOrderType(OrderType.PickUp);
		int totalCost = 0;
		order.setTotalCost(totalCost);
		order.setOrderStatus(OrderStatus.Received);
		order = orderDao.save(order);
		order.setCustomer(customer);
		order = orderDao.save(order);
		return order;
	}

	/**
	 * 
	 * @return create empty in store order done by employee
	 */
	@Transactional
	public GroceryOrder createInStoreOrder() {
		GroceryOrder order = new GroceryOrder();
		order.setOrderType(OrderType.InStore);
		int totalCost = 0;
		order.setTotalCost(totalCost);
		order.setOrderStatus(OrderStatus.Received); // when employee is done adding items, the order will be marked as completed since it does need to go through a preparation process
		order = orderDao.save(order);
		return order;
	}

	/**
	 * 
	 * @param order
	 * @return placed grocery order
	 * @functionality order is to be placed after all items have been placed, should change status from received to processing for delivery/pickup or completed for in store orders
	 */
	@Transactional
	public GroceryOrder placeOrder(GroceryOrder order) { // SHOULD ONLY BE CALLED ONCE WE ARE DONE ADDING ITEMS TO THE COMPLETED ORDER
		checkOrderValidity(order.getCustomer(), order.getOrderItems(), order.getOrderType());
		if (order.getOrderStatus() == null)
			throw new IllegalArgumentException("Order status is null."); 
		if (!(order.getOrderStatus().equals(OrderStatus.Received)))   //can only place an order that is received (initial status)
			throw new IllegalArgumentException("Order has already been placed ");
		if (order.getOrderType().equals(OrderType.InStore)) { // for in store orders automatically set to completed
			order.setOrderStatus(OrderStatus.Completed);
		} else { // for delivery or pick up set to processing, employee will set it to the next
			order.setOrderStatus(OrderStatus.Processing);
		}
		order = orderDao.save(order);
		return order;
	}

	/**
	 * 
	 * @param order
	 * @param list of order items to add to the grocery order
	 * @return gorcery rorder with new items
	 */
	@Transactional
	public GroceryOrder addOrderItems(GroceryOrder order, List<OrderItem> orderItems) {
		if (order == null || orderDao.findByOrderId(order.getOrderId()) == null)
			throw new IllegalArgumentException("Please submit a valid grocery order.");
		if (orderItems == null || orderItems.size() == 0)
			throw new IllegalArgumentException("Please submit a valid list of orderItems");
		if (!(order.getOrderStatus().equals(OrderStatus.Received)))
			throw new IllegalArgumentException("Can only add items to a received order.");
		checkOrderValidity(order.getCustomer(), order.getOrderType());
		//make sure items being ordered are available for delivery and pickup orders
		if (!order.getOrderType().equals(OrderType.InStore) && !inventoryItemDao.findByName(orderItems.get(0).getName()).getAvailability()) throw new IllegalArgumentException("Item is only available in store.");
		int totalItemPrice = 0;
		for (OrderItem oi : orderItems) { // go through items and add to the total cost of the order
			totalItemPrice = totalItemPrice + oi.getPrice();
		}
		for (OrderItem orderItem : order.getOrderItems()) {
			orderItems.add(orderItem);
		}
		order.setTotalCost(order.getTotalCost() + totalItemPrice); // add to the current order price
		order = orderDao.save(order); // save order before setting associations
		order.setOrderItems(orderItems);
		order = orderDao.save(order);
		return order;
	}

//-------------------------------------------------------GET METHODS------------------------------------------------------------
	/**
	 * 
	 * @param order
	 * @return list of order items contained in the order
	 */
	@Transactional
	public List<OrderItem> getOrderItems(GroceryOrder order) {
		if (order == null || orderDao.findByOrderId(order.getOrderId()) == null)
			throw new IllegalArgumentException("Please submit a valid grocery order.");
		List<OrderItem> items = order.getOrderItems();		//query order items
		if (items.isEmpty())
			throw new IllegalArgumentException("There are no items in this order.");
		return items;
	}

	/**
	 * @return a list of all orders in system
	 */
	@Transactional
	public List<GroceryOrder> getAllOrders() {
		List<GroceryOrder> allOrders = toList(orderDao.findAll());
		if (allOrders == null)
			throw new IllegalArgumentException("List of orders in system is null.");
		return allOrders;
	}

	/**
	 * @param Id
	 * @return the order corresponding to the id
	 */
	@Transactional
	public GroceryOrder getOrderById(int Id) {
		GroceryOrder order = orderDao.findByOrderId(Id);
		if (order == null)
			throw new IllegalArgumentException("Please submit a valid order ID.");
		return order;
	}

	/**
	 * @param customer
	 * @return a list of orders for this particular customer
	 */
	@Transactional
	public List<GroceryOrder> getOrdersByCustomer(Customer customer) {
		if (customer == null || !customerDao.existsById(customer.getAccountId()))
			throw new IllegalArgumentException("Please submit a proper customer.");
		List<GroceryOrder> orderList = orderDao.findGroceryOrdersByCustomer(customer);
		if (orderList == null)
			throw new IllegalArgumentException("List of orders for customer " + customer.getUsername() + " is null.");
		return orderList;
	}
	/**
	 * 
	 * @param customer
	 * @return all orders of the customer that have been received
	 */
	@Transactional
	public GroceryOrder getReceivedOrdersByCustomer(Customer customer) {
		if (customer == null || !customerDao.existsById(customer.getAccountId()))
			throw new IllegalArgumentException("Please submit a proper customer.");
		GroceryOrder receivedOrder = null;
		List<GroceryOrder> orderList = orderDao.findGroceryOrdersByCustomer(customer);
		for (GroceryOrder o : orderList) {
			if (o.getOrderStatus().equals(OrderStatus.Received)) {
				receivedOrder = o;
			}

		}
		return receivedOrder;
	}

	/**
	 * @param orderType
	 * @return returns a list of orders for a particular orderType
	 */
	@Transactional
	public List<GroceryOrder> getOrdersByOrderType(OrderType orderType) {
		if (orderType == null)
			throw new IllegalArgumentException("Please select a proper order type.");
		List<GroceryOrder> orderList = orderDao.findByOrderType(orderType);
		if (orderList == null)
			throw new IllegalArgumentException("List of orders for order type " + orderType.toString() + " is null.");
		return orderList;
	}
	
	/**
	 * 
	 * @param customer
	 * @returns the latest order that has been created for this customer
	 */
	@Transactional
	public GroceryOrder getLatestOrderByCustomer(Customer customer) {
		if (customer == null || !customerDao.existsById(customer.getAccountId()))
			throw new IllegalArgumentException("Please submit a proper customer.");
		List<GroceryOrder> orderList = orderDao.findGroceryOrdersByCustomer(customer);
		if (orderList.size() == 0)
			throw new IllegalArgumentException("There are no orders associated to this customer account.");
		return orderList.get(orderList.size() - 1);
	}

	/**
	 * @param orderStatus
	 * @return returns a list of orders for a particular orderStatus
	 */
	@Transactional
	public List<GroceryOrder> getOrdersByOrderStatus(OrderStatus orderStatus) {
		if (orderStatus == null)
			throw new IllegalArgumentException("Please select a proper order status.");
		List<GroceryOrder> orderList = orderDao.findByOrderStatus(orderStatus);
		if (orderList == null)
			throw new IllegalArgumentException(
					"List of orders for order status " + orderStatus.toString() + " is null.");
		return orderList;
	}

	// -------------------------------------------------------UPDATE METHOD------------------------------------------------------------

	// may not be necessary for functionality of the front end

//    /**
//     * @param newOrder
//     * @return modified order
//     */
//    @Transactional
//    public GroceryOrder modifyOrder(GroceryOrder newOrder){ 
//    	checkOrderValidity(newOrder.getCustomer(), newOrder.getOrderItems(), newOrder.getOrderType());
//    	if (newOrder.getTotalCost() == 0) throw new IllegalArgumentException("New order cannot amount to 0$.");	
//    	GroceryOrder oldOrder = orderDao.findByOrderId(newOrder.getOrderId());
//    	if (oldOrder == null) throw new IllegalArgumentException("No such order exists in the database. Maybe create new instead");
//
//    	List<OrderItem> oldOrderItems = oldOrder.getOrderItems();    	//update inventory and delete order items accordingly 
//     	List<OrderItem> newOrderItems = newOrder.getOrderItems();
//     	
//		for (OrderItem oldItem : oldOrderItems) {		//remove old order items and add them back to inventory
//			if (!newOrderItems.contains(oldItem)) {
//				InventoryItem inventoryItem = inventoryItemDao.findByName(oldItem.getName()); 
//				if (inventoryItem == null) throw new IllegalArgumentException("Order item that is being removed from order has no corresponding inventory item.");
//				inventoryItem.setCurrentStock(inventoryItem.getCurrentStock()+1); 	//return order item to inventory
//				inventoryItemDao.save(inventoryItem);
//				orderItemDao.delete(oldItem); 	//delete order item that is no longer in the order	
//			}
//		}
//    	oldOrder.setTotalCost(newOrder.getTotalCost());
//    	oldOrder.setOrderType(newOrder.getOrderType());	
//    	oldOrder.setOrderItems(newOrder.getOrderItems());
//    	oldOrder.setCustomer(newOrder.getCustomer());
//    	oldOrder = orderDao.save(oldOrder);
//    	return oldOrder;
//    }
	// -------------------------------------------------------DELETE METHODS------------------------------------------------------------

	/**
	 * @param grocery order to delete
	 * @return deleted grocery order
	 */
	@Transactional
	public GroceryOrder deleteOrder(GroceryOrder groceryOrder) {
		if (groceryOrder == null || !orderDao.existsById(groceryOrder.getOrderId()))
			throw new IllegalArgumentException("Please submit a valid grocery order.");
		if (!groceryOrder.getOrderStatus().equals(OrderStatus.Completed)) { // if the order has not been completed yet,
																			// items must be returned to inventory
			for (OrderItem oi : groceryOrder.getOrderItems()) {
				if (oi == null)
					continue;
				InventoryItem inventoryItem = inventoryItemDao.findByName(oi.getName());
				if (inventoryItem == null)
					inventoryItem = new InventoryItem(oi.getName(), oi.getPrice(), 0);
				inventoryItem.setCurrentStock(inventoryItem.getCurrentStock() + 1);
				inventoryItemDao.save(inventoryItem);
				orderItemDao.delete(oi);
			}
		}
		orderDao.delete(groceryOrder);
		return groceryOrder;
	}

	/**
	 * 
	 * @param groceryOrder
	 * @param name of the item of which we want to delete a quantity of 1
	 * @return groceryOrder
	 */
	@Transactional
	public GroceryOrder deleteItemFromOrder(GroceryOrder groceryOrder, String itemName) {
		if (groceryOrder == null || !orderDao.existsById(groceryOrder.getOrderId()))
			throw new IllegalArgumentException("Please submit a valid grocery order.");
		if (!groceryOrder.getOrderStatus().equals(OrderStatus.Received))
			throw new IllegalArgumentException("Can only remove items from an order that has not been placed yet!"); //make sure order is not being prepared yet
		InventoryItem inventoryItem = inventoryItemDao.findByName(itemName);
		int currentStock = inventoryItem.getCurrentStock();
		int currentPrice = groceryOrder.getTotalCost();
		boolean found = false;
		for (OrderItem item : groceryOrder.getOrderItems()) {
			if (item.getName().equals(itemName)) { // iterate through all items, if name is reached, delete this name
				found = true;
				currentStock += 1; // add back to stock			//add back to stock for the grocery store inventory items
				currentPrice = currentPrice - item.getPrice();	//set new price for the grocery order
				orderItemDao.delete(item);						 // delete item from database
				groceryOrder.getOrderItems().remove(item);		//remove 1 of this item from the grocery order list of items
				break; 											//break out of the loop once found 
			}
		}
		if (!found){ //throw exception if no such item exists in the order
			throw new IllegalArgumentException("No such item exists in the order.");
		}
		inventoryItem.setCurrentStock(currentStock); // update and save
		groceryOrder.setTotalCost(currentPrice);
		inventoryItemDao.save(inventoryItem);
		groceryOrder = orderDao.save(groceryOrder);
		return groceryOrder;
	}
	
	/**
	 * deletes all orders, done by owner at the end of the month after montly report
	 * is generated
	 */
	@Transactional
	public void deleteAllCompletedOrders() {
		if (orderDao.findByOrderStatus(OrderStatus.Completed) == null) {
			throw new IllegalArgumentException("List of orders for order status completed is null.");
		}
		for (GroceryOrder order : orderDao.findByOrderStatus(OrderStatus.Completed)) { // only delete complete grocery orders
			if (order == null || !orderDao.existsById(order.getOrderId()))
				throw new IllegalArgumentException("Completed grocery order does not exist in database.");
			orderDao.delete(order);
		}
	}

	// -------------------------------------------------------EXTRA METHODS------------------------------------------------------------

	/**
	 * @return total sales of all orders for monthly report
	 */
	@Transactional
	public int viewTotalSales() { // should only be accessed by owner --> this will be dealt with in the view
		int sales = 0;
		for (GroceryOrder order : orderDao.findAll()) {
			sales += order.getTotalCost();
		}
		return sales;
	}

	/**
	 * 
	 * @param order
	 * @return order
	 * @functionality change the order type of the order pickup <-> delivery
	 */
	@Transactional
	public GroceryOrder toggleOrderType(GroceryOrder order) { // should only be accessed by owner --> this will be deal with in the view
		OrderType type = order.getOrderType();
		if (type.equals(OrderType.Delivery)) {	 //if delivery switch to pickup
			if (!order.getCustomer().getAddress().contains(GroceryStore.town)) { // if out of town delivery order remove 10$ when switching
				order.setTotalCost(order.getTotalCost() - GroceryStore.outOfTownFee);
			}
			order.setOrderType(OrderType.PickUp);
		} else {	//if pick up switch to delivery
			if (!order.getCustomer().getAddress().contains(GroceryStore.town)) { // if out of town delivery order add 10$ when switching to delivery
				order.setTotalCost(order.getTotalCost() + GroceryStore.outOfTownFee);
			}
			order.setOrderType(OrderType.Delivery);
		}
		order = orderDao.save(order);
		return order;

	}

	/**
	 * @param Id //updates orderder status to the next one, done by an employee
	 * @return groceryOrder
	 * Pick up : 
	 * Received -> Processing -> readyForPickUp ->Completed
	 * Delivery: 
	 * Received -> Processing -> beingDelivered -> Completed
	 * InStore: 
	 * Received -> Completed 
	 */
	@Transactional
	public GroceryOrder updateOrderStatus(GroceryOrder order) { // should only be done by an employee/owner account --> we will take care of this in the view for the ui
		if (order == null)
			throw new IllegalArgumentException("Please submit a valid order ID."); // validation for proper id
		if (order.getOrderStatus() == null)
			throw new IllegalArgumentException("Order status of the order is null. Cannot be updated.");
		if (order.getOrderStatus().equals(OrderStatus.Completed)) { // if order is already completed, order status no longer needs to be updated
			throw new IllegalArgumentException("Order has already been completed. Does not require an update.");
		}
		if (order.getOrderStatus() == null) { // if order is already completed, order status no longer needs to be pdated
			throw new IllegalArgumentException("Order has no status.");
		}
		if (order.getOrderStatus().equals(OrderStatus.Received)) { // should have already been updated once order had been marked as completed,
			throw new IllegalArgumentException("Customer is still adding items to order. Cannot start preparation.");
		} else if (order.getOrderStatus().equals(OrderStatus.Processing)) { // update from processing
			if (order.getOrderType() == null)
				throw new IllegalArgumentException("Order type is not set."); // make sure order has a type
			if (order.getOrderType().equals(OrderType.Delivery)) {
				order.setOrderStatus(OrderStatus.BeingDelivered);
				order = orderDao.save(order);
				return order;
			} else if (order.getOrderType().equals(OrderType.PickUp)) {	 
				order.setOrderStatus(OrderStatus.ReadyForPickUp);
				order = orderDao.save(order);
				return order;
			}
		} else { // once order is picked up or delivered, set to completetd
			order.setOrderStatus(OrderStatus.Completed);
			order = orderDao.save(order);
			return order;
		}
		return order;
	}

	/**
	 * @param Id
	 * @return the order status of the corresponding order
	 */
	@Transactional
	public OrderStatus viewOrderStatus(GroceryOrder order) { // customer should be able to view the status of their orders
		if (order == null)
			throw new IllegalArgumentException("Please select a valid order.");
		if (order.getOrderStatus() == null) { // if order is already completed, order status no longer needs to be updated
			throw new IllegalArgumentException("Order has no status.");
		}
		return order.getOrderStatus();
	}

	// -------------------------------------------------------VALIDATION------------------------------------------------------------

	public void checkOrderValidity(Customer customer, List<OrderItem> orderItems, OrderType orderType) {
		// if it is instore, order does not need a customer
		if (orderType == null)
			throw new IllegalArgumentException("Please select a proper order type.");
		if (!orderType.equals(OrderType.InStore)
				&& (customer == null || !customerDao.existsById(customer.getAccountId())))
			throw new IllegalArgumentException("Please select a proper customer.");
		if (orderItems.isEmpty() || orderItems == null)
			throw new IllegalArgumentException("Please submit a list of items that is not empty or null.");
		for (OrderItem oi : orderItems) {
			if (oi == null || !orderItemDao.existsById(oi.getItemId())) {
				throw new IllegalArgumentException("Item " + oi.getName() + " does not exist in database or is null.");
			}
		}
	}

	public void checkOrderValidity(Customer customer, OrderType orderType) {
		// if it is instore, order does not need a customer
		if (orderType == null)
			throw new IllegalArgumentException("Please select a proper order type.");
		if (!orderType.equals(OrderType.InStore)
				&& (customer == null || !customerDao.existsById(customer.getAccountId())))
			throw new IllegalArgumentException("Please select a proper customer.");
	}

	public void checkOrderValidity(List<OrderItem> orderItems) {
		if (orderItems.isEmpty() || orderItems == null)
			throw new IllegalArgumentException("Please submit a list of items that is not empty or null.");
		for (OrderItem oi : orderItems) {
			if (oi == null || !orderItemDao.existsById(oi.getItemId())) {
				throw new IllegalArgumentException("Item " + oi.getName() + " does not exist in database or is null.");
			}
		}
	}

}