package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class GroceryStore {
    private int id;

    private Set<Account> accounts; 
    private Set<Customer> customers;
    private Set<Employee> employees;
    private Owner owner;

    private Set<InventoryItem> inventoryItems; 
    private Set<OrderItem> orderItems;

    private Set<GroceryOrder> orders;
//    private Set<InStore> inStoreOrders;
//    private Set<DeliveryOrder> deliveryOrders;
//    private Set<PickupOrder> pickupOrders;

    private StoreSchedule storeSchedule; 
    private Set<EmployeeSchedule> employeeSchedules;

    @Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @OneToMany(cascade={CascadeType.ALL})
    public Set<EmployeeSchedule> getEmployeeSchedules() {
        return employeeSchedules;
    }
    public void setEmployeeSchedules(Set<EmployeeSchedule> employeeSchedules) {
        this.employeeSchedules = employeeSchedules;
    }
    @OneToOne(cascade={CascadeType.ALL})
    public StoreSchedule getStoreSchedule() {
        return storeSchedule;
    }
    public void setStoreSchedule(StoreSchedule storeSchedule) {
        this.storeSchedule = storeSchedule;
    }
//    @OneToMany(cascade={CascadeType.ALL})
//    public Set<PickupOrder> getPickupOrders() {
//        return pickupOrders;
//    }
//    public void setPickupOrders(Set<PickupOrder> pickupOrders) {
//        this.pickupOrders = pickupOrders;
//    }
//    @OneToMany(cascade={CascadeType.ALL})
//    public Set<DeliveryOrder> getDeliveryOrders() {
//        return deliveryOrders;
//    }
//    public void setDeliveryOrders(Set<DeliveryOrder> deliveryOrders) {
//        this.deliveryOrders = deliveryOrders;
//    }
//    @OneToMany(cascade={CascadeType.ALL})
//    public Set<InStore> getInStoreOrders() {
//        return inStoreOrders;
//    }
//    public void setInStoreOrders(Set<InStore> inStoreOrders) {
//        this.inStoreOrders = inStoreOrders;
//    }
    @OneToMany(cascade={CascadeType.ALL})
    public Set<GroceryOrder> getOrders() {
        return orders;
    }
    public void setOrders(Set<GroceryOrder> orders) {
        this.orders = orders;
    }
//    @OneToMany(cascade={CascadeType.ALL})
//    public Set<Item> getOrderItems() {
//        return orderItems;
//    }
//    public void setOrderItems(Set<Item> orderItems) {
//        this.orderItems = orderItems;
//    }
    @OneToMany(cascade={CascadeType.ALL})
    public Set<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }
    public void setInventoryItems(Set<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }
//
//    @OneToOne(cascade={CascadeType.ALL})
//    public Owner getOwner() {
//        return owner;
//    }
//    public void setOwner(Owner owner) {
//        this.owner = owner;
//    }
//    @OneToMany(cascade={CascadeType.ALL})
//    public Set<Employee> getEmployees() {
//        return employees;
//    }
//    public void setEmployees(Set<Employee> employees) {
//        this.employees = employees;
//    }
//    @OneToMany(cascade={CascadeType.ALL})
//    public Set<Customer> getCustomers() {
//        return customers;
//    }
//    public void setCustomers(Set<Customer> customers) {
//        this.customers = customers;
//    }
    @OneToMany(cascade={CascadeType.ALL})
    public Set<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    

    
}
