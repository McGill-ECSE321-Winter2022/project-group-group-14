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
    InventoryItemRepository inventoryItemRepository;
    
    /** @author Youssof Mohamed */
    @Transactional
    public OrderItem createOrderItem(String name, int price, int currentStock)
    {
    	if (name.equals("Magazine")) throw new IllegalArgumentException("Order cannot contain a magazine.");
    	if (name.equals("Gift Card")) throw new IllegalArgumentException("Order cannot contain a gift card.");

    	//check order item has valid info
    	checkItemInfoValidity(name,price,currentStock);
    	InventoryItem inventoryItem = inventoryItemRepository.findByName(name);
    	if(inventoryItem==null) throw new IllegalArgumentException("No item exists in inventory named '" + name + "'");
    	if(inventoryItem.getCurrentStock()==0) throw new IllegalArgumentException(name + " item is out of stock");
    	
    	//set order item attributes
    	inventoryItem.setCurrentStock(inventoryItem.getCurrentStock()-1);
        OrderItem orderItem = new OrderItem();
        orderItem.setName(name);
        orderItem.setPrice(price);
        orderItem.setCurrentStock(currentStock);
        
        //save changes to repository
        orderItemRepository.save(orderItem);
        inventoryItemRepository.save(inventoryItem);
        
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
    public OrderItem getOrderItemByName(String name)
    {
        return (OrderItem) orderItemRepository.findByName(name);
    }

   

    
    /** @author Youssof Mohamed */
    @Transactional
    public OrderItem updateOrderItemInfo(OrderItem orderItem)
    {
    	//check order item has valid info
        checkItemInfoValidity(orderItem);
        
        //update existing order item info with the new ones
        OrderItem orderItemToUpdate = orderItemRepository.findByItemId(orderItem.getItemId());
        if (orderItemToUpdate == null) throw new IllegalArgumentException("No such order item exists");
        orderItemToUpdate.setName(orderItem.getName());
        orderItemToUpdate.setPrice(orderItem.getPrice());
        orderItemToUpdate.setCurrentStock(orderItem.getCurrentStock());
        
        //save new changes to the repository
        orderItemRepository.save(orderItemToUpdate);
        
        return orderItem;
    }

    
    /** @author Youssof Mohamed */
    @Transactional
    public OrderItem deleteOrderItem(OrderItem OrderItem)
    {
        orderItemRepository.delete(OrderItem);
        return OrderItem;
    }
}
