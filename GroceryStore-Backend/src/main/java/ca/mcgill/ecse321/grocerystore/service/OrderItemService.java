package ca.mcgill.ecse321.grocerystore.service;

import static ca.mcgill.ecse321.grocerystore.service.ServiceHelpers.checkInventoryItemInfoValidity;
import static ca.mcgill.ecse321.grocerystore.service.ServiceHelpers.toList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.grocerystore.dao.OrderItemRepository;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;

@Service
public class OrderItemService {

	/** @author Youssof Mohamed */
    @Autowired
    OrderItemRepository orderItemRepository;

    
    /** @author Youssof Mohamed */
    @Transactional
    public OrderItem createOrderItem(String name, int price, int currentStock)
    {
   
        OrderItem orderItem = new OrderItem();
        orderItem.setName(name);
        orderItem.setPrice(price);
        orderItem.setCurrentStock(currentStock);
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
    public OrderItem getOrderItemByName(String name)
    {
        return (OrderItem) orderItemRepository.findByName(name);
    }

   

    
    /** @author Youssof Mohamed */
    @Transactional
    public OrderItem updateOrderItemInfo(OrderItem orderItem)
    {
    	//check order item has valid info
        checkInventoryItemInfoValidity(orderItem);
        
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
