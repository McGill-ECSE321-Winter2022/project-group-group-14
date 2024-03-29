package ca.mcgill.ecse321.grocerystore.service;

import static ca.mcgill.ecse321.grocerystore.service.ServiceHelpers.checkItemInfoValidity;
import static ca.mcgill.ecse321.grocerystore.service.ServiceHelpers.toList;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.grocerystore.dao.InventoryItemRepository;
import ca.mcgill.ecse321.grocerystore.dao.OrderItemRepository;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
import ca.mcgill.ecse321.grocerystore.model.OrderItem;

@Service
public class InventoryItemService {

	/** @author Youssof Mohamed */
    @Autowired
    InventoryItemRepository inventoryItemRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    
    /** @author Youssof Mohamed */
    @Transactional
    public InventoryItem createInventoryItem(String name, int price, int currentStock, String image, String availability)
    {
    	
    	InventoryItem inventoryItem = inventoryItemRepository.findByName(name);
        if (inventoryItem != null) throw new IllegalArgumentException("Item: '" + name + "' already exists");
    	//check inventory item has valid info
    	checkItemInfoValidity(name,price,currentStock);
   
    	//set inventory item attributes
        inventoryItem = new InventoryItem();
        inventoryItem.setName(name);
        inventoryItem.setPrice(price);
        inventoryItem.setCurrentStock(currentStock);
        inventoryItem.setImage(image);
        System.out.println(availability);
        if(availability.equals("true")) {
        	inventoryItem.setAvailability(true);
        } else inventoryItem.setAvailability(false);
        
        
        //save changes to repository
        inventoryItemRepository.save(inventoryItem);
        
        return inventoryItem;
    }

    
    /** @author Youssof Mohamed */
    @Transactional
    public List<InventoryItem> getAllInventoryItems()
    {
        return toList(inventoryItemRepository.findAll());
    }

    
    /** @author Youssof Mohamed */
    @Transactional
    public InventoryItem getInventoryItemById(int itemId)
    {
        return inventoryItemRepository.findByItemId(itemId);
    }

    
    /** @author Youssof Mohamed */
    @Transactional
    public InventoryItem getInventoryItemByName(String name)
    {
        return inventoryItemRepository.findByName(name);
    }
    
    /** @author Youssof Mohamed */
    @Transactional
    public List<InventoryItem> getInventoryItemsByAvailability()
    {
    	List<InventoryItem> items = new ArrayList<InventoryItem>();
    	for(InventoryItem item: inventoryItemRepository.findAll()) {
    		if(item.getAvailability()) {
    			items.add(item);
    		}
    	}
        return items;
    }
   

    /** @author Youssof Mohamed */
    @Transactional
    public InventoryItem updateInventoryItemInfo(String name, int price, int currentStock, String image, String availability)
    {
    	//check inventory item has valid info
        checkItemInfoValidity(name,price,currentStock);
        
        //update existing inventory item info with the new ones
        InventoryItem inventoryItemToUpdate = inventoryItemRepository.findByName(name);
        if (inventoryItemToUpdate == null) throw new IllegalArgumentException("No such inventory item exists");
        inventoryItemToUpdate.setPrice(price);
        inventoryItemToUpdate.setCurrentStock(currentStock);
        System.out.println(availability);
        if(availability.equals("true")) {
        	inventoryItemToUpdate.setAvailability(true);
        } else inventoryItemToUpdate.setAvailability(false);
        inventoryItemToUpdate.setImage(image);
        
        //save new changes to the repository
        inventoryItemRepository.save(inventoryItemToUpdate);
        
        return inventoryItemToUpdate;
    }

    
    /** @author Youssof Mohamed */
    @Transactional
    public InventoryItem deleteInventoryItem(String name)
    {
        InventoryItem inventoryItem = inventoryItemRepository.findByName(name);
        if(inventoryItem==null) throw new IllegalArgumentException("No such inventory item exists");
        List<OrderItem> orderItems = orderItemRepository.findByName(inventoryItem.getName());
        if(orderItems!=null) {
    		for(OrderItem orderItem : orderItems) {
            	orderItemRepository.delete(orderItem);
            }
        	
            
        }
        
        inventoryItemRepository.delete(inventoryItem);
        return inventoryItem;
    }
    
    @Transactional
    public InventoryItem toggleInventoryItemAvailability(String name) {
    	InventoryItem item = inventoryItemRepository.findByName(name);
    	if (item == null) throw new IllegalArgumentException("No such inventory item exists");
    	if (item.getAvailability()) {
    		item.setAvailability(false);
    	}else {
    		item.setAvailability(true);
    	}
    	inventoryItemRepository.save(item); 
    	return item;
    }
}