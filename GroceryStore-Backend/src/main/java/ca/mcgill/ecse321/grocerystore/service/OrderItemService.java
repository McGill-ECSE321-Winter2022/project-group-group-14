package ca.mcgill.ecse321.grocerystore.service;

import static ca.mcgill.ecse321.grocerystore.service.ServiceHelpers.toList;
import static ca.mcgill.ecse321.grocerystore.service.ServiceHelpers.checkItemInfoValidity;

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
//    	if(inventoryItemRepository == null) throw new IllegalArgumentException("No items in inventory");
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

    
    /** @author Youssof Mohamed */
    @Transactional
    public OrderItem updateOrderItemInfo(String name, int price)
    {
    	//check order item has valid info
        checkItemInfoValidity(name, price, 15);
        
        //update existing order item info with the new ones
        List<OrderItem> orderItemToUpdate = orderItemRepository.findByName(name);
        if (orderItemToUpdate == null) throw new IllegalArgumentException("No such order item exists");
        if (orderItemToUpdate.get(0) == null) throw new IllegalArgumentException("No such order item exists");
        orderItemToUpdate.get(0).setName(name);
        orderItemToUpdate.get(0).setPrice(price);
        
        //save new changes to the repository
        orderItemRepository.save(orderItemToUpdate.get(0));
        
        return orderItemToUpdate.get(0);
    }
    
    /** @author Youssof Mohamed */
    @Transactional
    public void deleteOrderItem(String name)
    {
    	List<OrderItem> orderItems = orderItemRepository.findByName(name);
        orderItemRepository.deleteById(orderItems.get(0).getItemId());
    }
}