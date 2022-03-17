package ca.mcgill.ecse321.grocerystore.service;

import static ca.mcgill.ecse321.grocerystore.service.ServiceHelpers.toList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.grocerystore.dao.InventoryItemRepository;
import ca.mcgill.ecse321.grocerystore.dao.OrderItemRepository;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;

@Service
public class OrderItemService {

	/** @author Youssof Mohamed */
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    InventoryItemRepository inventoryItemRepository;
    
    /** @author Youssof Mohamed */
    @Transactional
    public OrderItem createOrderItem(String name)
    {
    	//check order item has valid info
    	if(inventoryItemRepository == null) throw new IllegalArgumentException("No items in inventory");
    	InventoryItem inventoryItem = inventoryItemRepository.findByName(name);
    	if(inventoryItem==null) throw new IllegalArgumentException("No item exists in inventory named '" + name + "'");
    	if(inventoryItem.getCurrentStock()==0) throw new IllegalArgumentException(name + " item is out of stock");
    	if(!inventoryItem.getAvailability()) throw new IllegalArgumentException(name + " item is not available for order");
    	
    	//set order item attributes
    	inventoryItem.setCurrentStock(inventoryItem.getCurrentStock()-1);
        OrderItem orderItem = new OrderItem();
        orderItem.setName(name);
        orderItem.setPrice(inventoryItem.getPrice());
        
        //save changes to repository
        inventoryItemRepository.save(inventoryItem);
        orderItemRepository.save(orderItem);
        
        
        return orderItem;
    }

    
    /** @author Youssof Mohamed */
    @Transactional
    public List<OrderItem> getAllOrderItems()
    {
        return toList(orderItemRepository.findAll());
    }

    
    /** @author Youssof Mohamed */
    @Transactional
    public OrderItem getOrderItemByID(int itemId)
    {
        return orderItemRepository.findByItemId(itemId);
    }

    
    /** @author Youssof Mohamed */
    @Transactional
    public List<OrderItem> getOrderItemsByName(String name)
    {
        return orderItemRepository.findByName(name);
    }

    
//    /** @author Youssof Mohamed */
//    @Transactional
//    public OrderItem updateOrderItemInfo(OrderItem orderItem)
//    {
//    	//check order item has valid info
//        checkItemInfoValidity(orderItem);
//        
//        //update existing order item info with the new ones
//        OrderItem orderItemToUpdate = orderItemRepository.findByItemId(orderItem.getItemId());
//        if (orderItemToUpdate == null) throw new IllegalArgumentException("No such order item exists");
//        orderItemToUpdate.setName(orderItem.getName());
//        orderItemToUpdate.setPrice(orderItem.getPrice());
//        orderItemToUpdate.setCurrentStock(orderItem.getCurrentStock());
//        
//        //save new changes to the repository
//        orderItemRepository.save(orderItemToUpdate);
//        
//        return orderItem;
//    }
    
    /** @author Youssof Mohamed */
    @Transactional
    public void deleteOrderItem(String name)
    {
    	List<OrderItem> orderItems = orderItemRepository.findByName(name);
        orderItemRepository.deleteById(orderItems.get(0).getItemId());
    }
}
