package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.model.OrderItem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional //don't need clear database method
public class TestOrderItemPersistence {
	@Autowired
	private OrderItemRepository orderItemRepository;

	//test Inventory Item persistence
	/**@author Youssof Mohamed */
	@Test
	public void testPersistAndLoadOrderItem(){

				
		//set values to the first Order Item attributes
		OrderItem orderItem = new OrderItem();
		String name = "batates";
		orderItem.setName(name);
		orderItem.setPrice(32);
		
		//set values to the second Order Item attributes
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setName("tamatem");
		orderItem2.setPrice(123);
		
		//set values to the second Order Item attributes (same name as in second order item)
		OrderItem orderItem3 = new OrderItem();
		orderItem3.setName("tamatem");
		orderItem3.setPrice(123);
		
		//save Order Item objects
		orderItemRepository.save(orderItem);
		orderItemRepository.save(orderItem2);
		orderItemRepository.save(orderItem3);
	
		//test findByItemId
		OrderItem savedOrderItem = orderItemRepository.findByItemId(orderItem.getItemId());
		assertNotNull(savedOrderItem);
		assertEquals(orderItem.getName(), savedOrderItem.getName());
		assertEquals(orderItem.getPrice(), savedOrderItem.getPrice());
		
		//test findByName
		List<OrderItem> savedOrderItems = orderItemRepository.findByName(orderItem2.getName());
		assertNotNull(savedOrderItems);
		assertEquals(orderItem2.getItemId(), savedOrderItems.get(0).getItemId());
		assertEquals(orderItem2.getPrice(), savedOrderItems.get(0).getPrice());
		assertEquals(orderItem3.getItemId(), savedOrderItems.get(1).getItemId());
		assertEquals(orderItem3.getPrice(), savedOrderItems.get(1).getPrice());
		
	}

}


