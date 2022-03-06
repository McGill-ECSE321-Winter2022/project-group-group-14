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

    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "groceryStore")
    public List<StoreSchedule> getStoreSchedules() {
        return storeSchedules;
    }
    public void setStoreSchedules(List<StoreSchedule> storeSchedules) {
        this.storeSchedules = storeSchedules;
//        for (StoreSchedule ss: storeSchedules) {
//        	ss.setGroceryStore(this);
//        }
    }
    @OneToMany(cascade={CascadeType.ALL},mappedBy = "groceryStore")
    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }
    public void setInventoryItems(List<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;
//        for (InventoryItem ii: inventoryItems) {
//        	ii.setGroceryStore(this);
//        }  
    }
    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "groceryStore")
    public List<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
        
//        for (Account a: accounts) {
//        	a.setGroceryStore(this);
//        }
    }
    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "groceryStore")
    public List<GroceryOrder> getGroceryOrders()
    {
      return this.groceryOrders;
    }
    public void setGroceryOrders(List<GroceryOrder> groceryOrders) {
        this.groceryOrders = groceryOrders;
//        for (GroceryOrder go: groceryOrders) {
//        	go.setGroceryStore(this);
//        }
    }
    

    //------------------------
    // INTERFACE
    //------------------------
    /* Code from template association_GetMany */
    
    public GroceryOrder getGroceryOrder(int index)
    {
      GroceryOrder aGroceryOrder = groceryOrders.get(index);
      return aGroceryOrder;
    }


    public int numberOfGroceryOrders()
    {
      int number = groceryOrders.size();
      return number;
    }

    public boolean hasGroceryOrders()
    {
      boolean has = groceryOrders.size() > 0;
      return has;
    }

    public int indexOfGroceryOrder(GroceryOrder aGroceryOrder)
    {
      int index = groceryOrders.indexOf(aGroceryOrder);
      return index;
    }
    public Account getAccount(int index)
    {
      Account aAccount = accounts.get(index);
      return aAccount;
    }

//    public List<Account> getAccounts()
//    {
//      List<Account> newAccounts = Collections.unmodifiableList(accounts);
//      return newAccounts;
//    }

    public int numberOfAccounts()
    {
      int number = accounts.size();
      return number;
    }

    public boolean hasAccounts()
    {
      boolean has = accounts.size() > 0;
      return has;
    }

    public int indexOfAccount(Account aAccount)
    {
      int index = accounts.indexOf(aAccount);
      return index;
    }
    /* Code from template association_GetMany */
    public StoreSchedule getStoreSchedule(int index)
    {
      StoreSchedule aStoreSchedule = storeSchedules.get(index);
      return aStoreSchedule;
    }

//    public List<StoreSchedule> getStoreSchedules()
//    {
//      List<StoreSchedule> newStoreSchedules = Collections.unmodifiableList(storeSchedules);
//      return newStoreSchedules;
//    }

    public int numberOfStoreSchedules()
    {
      int number = storeSchedules.size();
      return number;
    }

    public boolean hasStoreSchedules()
    {
      boolean has = storeSchedules.size() > 0;
      return has;
    }

    public int indexOfStoreSchedule(StoreSchedule aStoreSchedule)
    {
      int index = storeSchedules.indexOf(aStoreSchedule);
      return index;
    }
    /* Code from template association_GetMany */
    public InventoryItem getInventoryItem(int index)
    {
      InventoryItem aInventoryItem = inventoryItems.get(index);
      return aInventoryItem;
    }

//    public List<InventoryItem> getInventoryItems()
//    {
//      List<InventoryItem> newInventoryItems = Collections.unmodifiableList(inventoryItems);
//      return newInventoryItems;
//    }

    public int numberOfInventoryItems()
    {
      int number = inventoryItems.size();
      return number;
    }

    public boolean hasInventoryItems()
    {
      boolean has = inventoryItems.size() > 0;
      return has;
    }

    public int indexOfInventoryItem(InventoryItem aInventoryItem)
    {
      int index = inventoryItems.indexOf(aInventoryItem);
      return index;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfAccounts()
    {
      return 0;
    }

    
    //Because we cant instantiate am abstract class
    public Account addAccount(String aEmail, String aUsername, String aPassword, String accountType)
    {
    	if (accountType == "customer") {
    		Customer customer = new Customer(aEmail, aUsername, aPassword, this , null, null);
    		this.accounts.add(customer);
    		return customer; // will have to set the phone number and address later --> not the best method
    	}else if (accountType == "employee") {
    		Employee employee = new Employee(aEmail, aUsername, aPassword, this);
    		this.accounts.add(employee);
    		return employee;
    	}else if (accountType == "owner") {
    		Owner owner = new Owner(aEmail, aUsername, aPassword, this);
    		this.accounts.add(owner);
    		return owner;
    	}
    	return null;
    }

    public boolean addAccount(Account aAccount)
    {
      boolean wasAdded = false;
      if (accounts.contains(aAccount)) { return false; }
      GroceryStore existingGroceryStore = aAccount.getGroceryStore();
      boolean isNewGroceryStore = existingGroceryStore != null && !this.equals(existingGroceryStore);
      if (isNewGroceryStore)
      {
        aAccount.setGroceryStore(this);
      }
      else
      {
        accounts.add(aAccount);
      }
      wasAdded = true;
      return wasAdded;
    }

    public boolean removeAccount(Account aAccount)
    {
      boolean wasRemoved = false;
      //Unable to remove aAccount, as it must always have a groceryStore
      if (!this.equals(aAccount.getGroceryStore()))
      {
        accounts.remove(aAccount);
        wasRemoved = true;
      }
      return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addAccountAt(Account aAccount, int index)
    {  
      boolean wasAdded = false;
      if(addAccount(aAccount))
      {
        if(index < 0 ) { index = 0; }
        if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
        accounts.remove(aAccount);
        accounts.add(index, aAccount);
        wasAdded = true;
      }
      return wasAdded;
    }

    public boolean addOrMoveAccountAt(Account aAccount, int index)
    {
      boolean wasAdded = false;
      if(accounts.contains(aAccount))
      {
        if(index < 0 ) { index = 0; }
        if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
        accounts.remove(aAccount);
        accounts.add(index, aAccount);
        wasAdded = true;
      } 
      else 
      {
        wasAdded = addAccountAt(aAccount, index);
      }
      return wasAdded;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfStoreSchedules()
    {
      return 0;
    }
    /* Code from template association_AddManyToOne */
    public StoreSchedule addStoreSchedule(Time aOpeningTime, Time aClosingTime, Day aDayOpen)
    {
      return new StoreSchedule(aOpeningTime,aClosingTime,aDayOpen,this);
    }

    public boolean addStoreSchedule(StoreSchedule aStoreSchedule)
    {
      boolean wasAdded = false;
      if (storeSchedules.contains(aStoreSchedule)) { return false; }
      GroceryStore existingGroceryStore = aStoreSchedule.getGroceryStore();
      boolean isNewGroceryStore = existingGroceryStore != null && !this.equals(existingGroceryStore);
      if (isNewGroceryStore)
      {
        aStoreSchedule.setGroceryStore(this);
      }
      else
      {
        storeSchedules.add(aStoreSchedule);
      }
      wasAdded = true;
      return wasAdded;
    }

    public boolean removeStoreSchedule(StoreSchedule aStoreSchedule)
    {
      boolean wasRemoved = false;
      //Unable to remove aStoreSchedule, as it must always have a groceryStore
      if (!this.equals(aStoreSchedule.getGroceryStore()))
      {
        storeSchedules.remove(aStoreSchedule);
        wasRemoved = true;
      }
      return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addStoreScheduleAt(StoreSchedule aStoreSchedule, int index)
    {  
      boolean wasAdded = false;
      if(addStoreSchedule(aStoreSchedule))
      {
        if(index < 0 ) { index = 0; }
        if(index > numberOfStoreSchedules()) { index = numberOfStoreSchedules() - 1; }
        storeSchedules.remove(aStoreSchedule);
        storeSchedules.add(index, aStoreSchedule);
        wasAdded = true;
      }
      return wasAdded;
    }

    public boolean addOrMoveStoreScheduleAt(StoreSchedule aStoreSchedule, int index)
    {
      boolean wasAdded = false;
      if(storeSchedules.contains(aStoreSchedule))
      {
        if(index < 0 ) { index = 0; }
        if(index > numberOfStoreSchedules()) { index = numberOfStoreSchedules() - 1; }
        storeSchedules.remove(aStoreSchedule);
        storeSchedules.add(index, aStoreSchedule);
        wasAdded = true;
      } 
      else 
      {
        wasAdded = addStoreScheduleAt(aStoreSchedule, index);
      }
      return wasAdded;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfInventoryItems()
    {
      return 0;
    }
    /* Code from template association_AddManyToOne */
    public InventoryItem addInventoryItem(String aName, int aPrice, int aCurrentStock)
    {
      return new InventoryItem(aName,aPrice, aCurrentStock, this);
    }

    public boolean addInventoryItem(InventoryItem aInventoryItem)
    {
      boolean wasAdded = false;
      if (inventoryItems.contains(aInventoryItem)) { return false; }
      GroceryStore existingGroceryStore = aInventoryItem.getGroceryStore();
      boolean isNewGroceryStore = existingGroceryStore != null && !this.equals(existingGroceryStore);
      if (isNewGroceryStore)
      {
        aInventoryItem.setGroceryStore(this);
      }
      else
      {
        inventoryItems.add(aInventoryItem);
      }
      wasAdded = true;
      return wasAdded;
    }

    public boolean removeInventoryItem(InventoryItem aInventoryItem)
    {
      boolean wasRemoved = false;
      //Unable to remove aInventoryItem, as it must always have a groceryStore
      if (!this.equals(aInventoryItem.getGroceryStore()))
      {
        inventoryItems.remove(aInventoryItem);
        wasRemoved = true;
      }
      return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addInventoryItemAt(InventoryItem aInventoryItem, int index)
    {  
      boolean wasAdded = false;
      if(addInventoryItem(aInventoryItem))
      {
        if(index < 0 ) { index = 0; }
        if(index > numberOfInventoryItems()) { index = numberOfInventoryItems() - 1; }
        inventoryItems.remove(aInventoryItem);
        inventoryItems.add(index, aInventoryItem);
        wasAdded = true;
      }
      return wasAdded;
    }

    public boolean addOrMoveInventoryItemAt(InventoryItem aInventoryItem, int index)
    {
      boolean wasAdded = false;
      if(inventoryItems.contains(aInventoryItem))
      {
        if(index < 0 ) { index = 0; }
        if(index > numberOfInventoryItems()) { index = numberOfInventoryItems() - 1; }
        inventoryItems.remove(aInventoryItem);
        inventoryItems.add(index, aInventoryItem);
        wasAdded = true;
      } 
      else 
      {
        wasAdded = addInventoryItemAt(aInventoryItem, index);
      }
      return wasAdded;
    }
    
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfGroceryOrders()
    {
      return 0;
    }
    /* Code from template association_AddManyToOne */
    public GroceryOrder addGroceryOrder(Integer aTotalCost, OrderType aOrderType, Customer aCustomer)
    {
      return new GroceryOrder(aTotalCost, aOrderType, this, aCustomer);
    }

    public boolean addGroceryOrder(GroceryOrder aGroceryOrder)
    {
      boolean wasAdded = false;
      if (groceryOrders.contains(aGroceryOrder)) { return false; }
      GroceryStore existingGroceryStore = aGroceryOrder.getGroceryStore();
      boolean isNewGroceryStore = existingGroceryStore != null && !this.equals(existingGroceryStore);
      if (isNewGroceryStore)
      {
        aGroceryOrder.setGroceryStore(this);
      }
      else
      {
        groceryOrders.add(aGroceryOrder);
      }
      wasAdded = true;
      return wasAdded;
    }

    public boolean removeGroceryOrder(GroceryOrder aGroceryOrder)
    {
      boolean wasRemoved = false;
      //Unable to remove aGroceryOrder, as it must always have a groceryStore
      if (!this.equals(aGroceryOrder.getGroceryStore()))
      {
        groceryOrders.remove(aGroceryOrder);
        wasRemoved = true;
      }
      return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addGroceryOrderAt(GroceryOrder aGroceryOrder, int index)
    {  
      boolean wasAdded = false;
      if(addGroceryOrder(aGroceryOrder))
      {
        if(index < 0 ) { index = 0; }
        if(index > numberOfGroceryOrders()) { index = numberOfGroceryOrders() - 1; }
        groceryOrders.remove(aGroceryOrder);
        groceryOrders.add(index, aGroceryOrder);
        wasAdded = true;
      }
      return wasAdded;
    }

    public boolean addOrMoveGroceryOrderAt(GroceryOrder aGroceryOrder, int index)
    {
      boolean wasAdded = false;
      if(groceryOrders.contains(aGroceryOrder))
      {
        if(index < 0 ) { index = 0; }
        if(index > numberOfGroceryOrders()) { index = numberOfGroceryOrders() - 1; }
        groceryOrders.remove(aGroceryOrder);
        groceryOrders.add(index, aGroceryOrder);
        wasAdded = true;
      } 
      else 
      {
        wasAdded = addGroceryOrderAt(aGroceryOrder, index);
      }
      return wasAdded;
    }

    public void delete()
    {
      while (accounts.size() > 0)
      {
        Account aAccount = accounts.get(accounts.size() - 1);
        aAccount.delete();
        accounts.remove(aAccount);
      }
      
      while (storeSchedules.size() > 0)
      {
        StoreSchedule aStoreSchedule = storeSchedules.get(storeSchedules.size() - 1);
        aStoreSchedule.delete();
        storeSchedules.remove(aStoreSchedule);
      }
      
      while (inventoryItems.size() > 0)
      {
        InventoryItem aInventoryItem = inventoryItems.get(inventoryItems.size() - 1);
        aInventoryItem.delete();
        inventoryItems.remove(aInventoryItem);
      }
      while (groceryOrders.size() > 0)
      {
        GroceryOrder aGroceryOrder = groceryOrders.get(groceryOrders.size() - 1);
        aGroceryOrder.delete();
        groceryOrders.remove(aGroceryOrder);
      }
      
    }
    
    

    

    
}
