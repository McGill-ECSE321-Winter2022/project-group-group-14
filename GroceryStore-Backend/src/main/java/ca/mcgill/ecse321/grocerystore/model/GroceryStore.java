package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import ca.mcgill.ecse321.grocerystore.model.GroceryOrder.OrderType;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule.Day;

import javax.persistence.Id;

@Entity
public class GroceryStore {
    private int storeId;
    private List<Account> accounts; 
    private List<InventoryItem> inventoryItems;
    private List<StoreSchedule> storeSchedules; 
    private List<GroceryOrder> groceryOrders;
    
    public GroceryStore() {
    	this.accounts = new ArrayList<Account>();
    	this.inventoryItems =  new ArrayList<InventoryItem>();
    	this.storeSchedules = new ArrayList<StoreSchedule>();
    	this.groceryOrders = new ArrayList<GroceryOrder>();
    }

	@Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getStoreId() {
        return storeId;
    }
    public void setStoreId(int id) {
        this.storeId = id;
    }

    @OneToMany(cascade={CascadeType.ALL})
    public List<StoreSchedule> getStoreSchedules() {
        return storeSchedules;
    }
    public void setStoreSchedules(List<StoreSchedule> storeSchedules) {
        this.storeSchedules = storeSchedules;
    }
    @OneToMany(cascade={CascadeType.ALL})
    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }
    public void setInventoryItems(List<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }
    @OneToMany(cascade={CascadeType.ALL})
    public List<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    @OneToMany(cascade={CascadeType.ALL})
    public List<GroceryOrder> getGroceryOrders()
    {
      return this.groceryOrders;
    }
    public void setGroceryOrders(List<GroceryOrder> groceryOrders) {
        this.groceryOrders = groceryOrders;
    }
    
   
    

    

    
}
