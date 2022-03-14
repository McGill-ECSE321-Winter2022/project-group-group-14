package ca.mcgill.ecse321.grocerystore.service;

import static ca.mcgill.ecse321.grocerystore.service.ServiceHelpers.checkInventoryItemInfoValidity;
import static ca.mcgill.ecse321.grocerystore.service.ServiceHelpers.toList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.grocerystore.dao.InventoryItemRepository;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;

@Service
public class InventoryItemService {

	/** @author Youssof Mohamed */
    @Autowired
    InventoryItemRepository inventoryItemRepository;

    
    /** @author Youssof Mohamed */
    @Transactional
    public InventoryItem createInventoryItem(String name, int price, int currentStock)
    {
   
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setName(name);
        inventoryItem.setPrice(price);
        inventoryItem.setCurrentStock(currentStock);
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
    public InventoryItem getInventoryItemByID(int itemId)
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
    public InventoryItem updateInventoryItemInfo(InventoryItem inventoryItem)
    {
    	//check inventory item has valid info
        checkInventoryItemInfoValidity(inventoryItem);
        
        //update existing inventory item info with the new ones
        InventoryItem inventoryItemToUpdate = inventoryItemRepository.findByItemId(inventoryItem.getItemId());
        if (inventoryItemToUpdate == null) throw new IllegalArgumentException("No such inventory item exists");
        inventoryItemToUpdate.setName(inventoryItem.getName());
        inventoryItemToUpdate.setPrice(inventoryItem.getPrice());
        inventoryItemToUpdate.setCurrentStock(inventoryItem.getCurrentStock());
        
        //save new changes to the repository
        inventoryItemRepository.save(inventoryItemToUpdate);
        
        return inventoryItem;
    }

    
    /** @author Youssof Mohamed */
    @Transactional
    public InventoryItem deleteInventoryItem(InventoryItem inventoryItem)
    {
        inventoryItemRepository.delete(inventoryItem);
        return inventoryItem;
    }
}