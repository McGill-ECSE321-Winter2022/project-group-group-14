package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;

@Entity
public class GroceryStore {
    private int storeId;
    private List<Account> accounts; 
    private List<GroceryOrder> orders;
    private List<InventoryItem> inventoryItems;
    private List<StoreSchedule> storeSchedules; 
    private List<EmployeeSchedule> employeeSchedules;
    
    public GroceryStore() {
    	this.accounts = new ArrayList<Account>();
    	this.orders =  new ArrayList<GroceryOrder>();
    	this.inventoryItems =  new ArrayList<InventoryItem>();
    	this.employeeSchedules = new ArrayList<EmployeeSchedule>();
    	this.storeSchedules = new ArrayList<StoreSchedule>();
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
    public List<EmployeeSchedule> getEmployeeSchedules() {
        return employeeSchedules;
    }
    public void setEmployeeSchedules(List<EmployeeSchedule> employeeSchedules) {
        this.employeeSchedules = employeeSchedules;
    }
    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "groceryStore")
    public List<StoreSchedule> getStoreSchedules() {
        return storeSchedules;
    }
    public void setStoreSchedules(List<StoreSchedule> storeSchedules) {
        this.storeSchedules = storeSchedules;
    }
    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "groceryStore")
    public List<GroceryOrder> getOrders() {
        return orders;
    }
    public void setOrders(List<GroceryOrder> orders) {
        this.orders = orders;
    }
    @OneToMany(cascade={CascadeType.ALL},mappedBy = "groceryStore")
    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }
    public void setInventoryItems(List<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }
    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "groceryStore")
    public List<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    

    
}
