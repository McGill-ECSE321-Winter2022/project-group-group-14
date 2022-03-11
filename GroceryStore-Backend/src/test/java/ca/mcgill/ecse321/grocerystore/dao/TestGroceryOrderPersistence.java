package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder;
import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderType;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional //don't need clear database method
public class TestGroceryOrderPersistence {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private GroceryOrderRepository groceryOrderRepository;

    /** @author: Clarissa Baciu */
	@Test
	public void testPersistAndLoadGroceryOrderById() {
        Customer customer = new Customer();
		GroceryOrder order = new GroceryOrder();

        OrderType type = OrderType.Delivery;
        int cost = 15;
        order.setOrderType(type);	//adding attributes to groceryOrder
        order.setTotalCost(cost);

        order = groceryOrderRepository.save(order);		//saving before associating
        customer = customerRepository.save(customer);

        order.setCustomer(customer);					//creating associations

        order = groceryOrderRepository.save(order);		//saving after associations
        customer = customerRepository.save(customer);

        GroceryOrder orderdb = groceryOrderRepository.findByOrderId(order.getOrderId());	//loading from database
        assertNotNull(orderdb);	
        assertEquals(orderdb.getOrderId(),order.getOrderId());			//verifying attributes
        assertEquals(orderdb.getOrderType(),order.getOrderType());
        assertEquals(orderdb.getTotalCost(),order.getTotalCost());

        assertEquals(orderdb.getCustomer().getAccountId(),customer.getAccountId());		//verifying associations 

	}
	 /** @author: Clarissa Baciu */
	@Test
	public void testPersistAndLoadGroceryOrderWithItems(){
		GroceryOrder order = new GroceryOrder();
        OrderItem item1 = new OrderItem();
        OrderItem item2 = new OrderItem();
		
        OrderType type = OrderType.Delivery;
        int cost = 15;
        order.setOrderType(type);	//adding attributes to groceryOrder
        order.setTotalCost(cost);
        
        order = groceryOrderRepository.save(order);		//saving before associating
        item1 = orderItemRepository.save(item1);
        item2 = orderItemRepository.save(item2);
        
        int id1 = item1.getItemId();
        int id2 = item2.getItemId();
        
     
        List<OrderItem> itemList = new ArrayList<OrderItem>(); //adding associations
        itemList.add(item1);
        itemList.add(item2);
        order.setOrderItems(itemList);
        
        groceryOrderRepository.save(order);		//saving after associations
        orderItemRepository.save(item1);
        orderItemRepository.save(item2);
        
        GroceryOrder orderdb = groceryOrderRepository.findByOrderId(order.getOrderId());	//loading from database
        
        assertFalse(orderdb.getOrderItems().isEmpty());
        assertEquals(orderdb.getOrderItems().get(0).getItemId(),id1);
        assertEquals(orderdb.getOrderItems().get(1).getItemId(),id2);
	}
	
    /** @author: Clarissa Baciu */
	@Test
	public void testPersistAndLoadOrder() {
        Customer customer = new Customer();
		GroceryOrder order = new GroceryOrder();
		OrderItem item1 = new OrderItem();
        OrderItem item2 = new OrderItem();

        OrderType type = OrderType.Delivery;
        int cost = 15;
        order.setOrderType(type);	//adding attributes to groceryOrder
        order.setTotalCost(cost);

        customer = customerRepository.save(customer);
        item1 = orderItemRepository.save(item1);
        item2 = orderItemRepository.save(item2);
        int id1 = item1.getItemId();
        int id2 = item2.getItemId();
        
        List<OrderItem> itemList = new ArrayList<OrderItem>(); //adding associations
        itemList.add(item1);
        itemList.add(item2);
		
		order = groceryOrderRepository.save(order);		//saving before associating
        order.setOrderItems(itemList);
        order.setCustomer(customer);					//creating associations

        groceryOrderRepository.save(order);		//saving after associations
        // customerRepository.save(customer);
        // orderItemRepository.save(item1);
        // orderItemRepository.save(item2);

        GroceryOrder orderdb = groceryOrderRepository.findByOrderId(order.getOrderId());	//loading from database
        assertNotNull(orderdb);	
        assertEquals(orderdb.getOrderId(),order.getOrderId());			//verifying attributes
        assertEquals(orderdb.getOrderType(),order.getOrderType());
        assertEquals(orderdb.getTotalCost(),order.getTotalCost());

        assertEquals(orderdb.getCustomer().getAccountId(),customer.getAccountId());		//verifying associations 
        assertFalse(orderdb.getOrderItems().isEmpty());
        assertEquals(orderdb.getOrderItems().get(0).getItemId(),id1);
        assertEquals(orderdb.getOrderItems().get(1).getItemId(),id2);
	}
	
	

	/** @author: Clarissa Baciu */
	@Test
	public void testPersistAndLoadGroceryOrderByOrderType() {
		GroceryOrder order1 = new GroceryOrder(5, OrderType.Delivery);
		GroceryOrder order2 = new GroceryOrder(10, OrderType.Delivery);
		GroceryOrder order3 = new GroceryOrder(15,OrderType.PickUp);

		order1 = groceryOrderRepository.save(order1);		
		order2 = groceryOrderRepository.save(order2);
		order3 = groceryOrderRepository.save(order3);


		List<GroceryOrder> deliveryList = groceryOrderRepository.findByOrderType(OrderType.Delivery);	//loading from database
		List<GroceryOrder> pickupList = groceryOrderRepository.findByOrderType(OrderType.PickUp);	
		assertFalse(deliveryList.isEmpty());
		assertEquals(deliveryList.get(0).getOrderId(),order1.getOrderId());
		assertEquals(deliveryList.get(1).getOrderId(),order2.getOrderId());
		assertFalse(pickupList.isEmpty());
		assertEquals(pickupList.get(0).getOrderId(),order3.getOrderId());
	}

	/** @author: Clarissa Baciu */
	@Test
	public void testPersistAndLoadGroceryOrdersByCustomer() {
		GroceryOrder order1 = new GroceryOrder();
		GroceryOrder order2 = new GroceryOrder();
		Customer customer = new Customer();


		
		order1 = groceryOrderRepository.save(order1);	//saving before associations	
		order2 = groceryOrderRepository.save(order2);
		customer = customerRepository.save(customer);


		order1.setCustomer(customer);	//creating associations
		order2.setCustomer(customer);

		//or 
		//customer.set(orders)


		order1 = groceryOrderRepository.save(order1);	//saving after associations
		order2 = groceryOrderRepository.save(order2);
		customer = customerRepository.save(customer);

		List<GroceryOrder> orderList = groceryOrderRepository.findGroceryOrdersByCustomer(customer);	//loading from database
		assertFalse(orderList.isEmpty());
		assertEquals(orderList.get(0).getOrderId(),order1.getOrderId());
		assertEquals(orderList.get(1).getOrderId(),order2.getOrderId());
	}


} 
