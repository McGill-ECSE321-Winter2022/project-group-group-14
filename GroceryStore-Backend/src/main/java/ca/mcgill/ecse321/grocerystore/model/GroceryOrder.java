package ca.mcgill.ecse321.grocerystore.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Shift;


@Entity
public class GroceryOrder
{
  public enum OrderType { InStore, PickUp, Delivery }


  
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private int orderId;
  private int totalCost;
  private OrderType orderType;


  //Order Associations
  private List<OrderItem> orderItems;
  private Customer customer;
  private GroceryStore groceryStore;
  

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GroceryOrder(Integer aTotalCost, OrderType aOrderType, GroceryStore aGroceryStore,Customer aCustomer )
  {
	orderType = aOrderType;
    totalCost = aTotalCost;
    boolean didAddGroceryStore = setGroceryStore(aGroceryStore);
	if (!didAddGroceryStore)
	{
	  throw new RuntimeException("Unable to create groceryOrder due to groceryStore");
	}
	boolean didAddCustomer = setCustomer(aCustomer);
	if (!didAddCustomer)
	{
	  throw new RuntimeException("Unable to create groceryOrder due to customer.");
	}
    this.orderItems = new ArrayList<OrderItem>();
  }
  
  public GroceryOrder(Integer aTotalCost, OrderType aOrderType)
  {
	orderType = aOrderType;
    totalCost = aTotalCost;
    groceryStore = null;
    customer = null;
    this.orderItems = new ArrayList<OrderItem>();
  }
  
  public GroceryOrder(OrderType aOrderType, GroceryStore aGroceryStore,Customer aCustomer )
  {
	totalCost = 0;
	orderType = aOrderType;
    boolean didAddGroceryStore = setGroceryStore(aGroceryStore);
	if (!didAddGroceryStore)
	{
	  throw new RuntimeException("Unable to create groceryOrder due to groceryStore");
	}
	boolean didAddCustomer = setCustomer(aCustomer);
	if (!didAddCustomer)
	{
	  throw new RuntimeException("Unable to create groceryOrder due to customer.");
	}
    this.orderItems = new ArrayList<OrderItem>();
  }
  

  public GroceryOrder()
  {
    this.totalCost = 0;
    this.orderType = null;
    this.orderItems = new ArrayList<OrderItem>();
    this.customer = null;
    this.groceryStore = null;
  }
  
  //GETTERS AND SETTERS FOR ATTRIBUTES
  
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")  
  public Integer getOrderId() {
      return this.orderId;
  }
  
  public void setOrderId(int num) {
      this.orderId=num;
  }
  
  public Integer getTotalCost()
  {
    return totalCost;
  }
  public void setTotalCost(Integer aTotalCost)
  {
    totalCost = aTotalCost;
  }
  
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

 //GETTERS AND SETTERS FOR ASSOCIATIONS

	@ManyToOne
	public GroceryStore getGroceryStore()
	{
		return groceryStore;
	}
	 
	@ManyToOne
	public Customer getCustomer() {
		return customer;
	}
	 
	  /**
	   * paymentType needed. Cash can only be used for pickups
	   * and InPerson.
	   */
	
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "groceryOrder")
	 public List<OrderItem> getOrderItems() 
	 {
		 return this.orderItems;
	 }
	  
	  public boolean setCustomer(Customer aCustomer)
	  {
	    boolean wasSet = false;
	    if (aCustomer == null)
	    {
	      return wasSet;
	    }

	    Customer existingCustomer = customer;
	    customer = aCustomer;
	    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
	    {
	      existingCustomer.removeGroceryOrder(this);
	    }
	    customer.addGroceryOrder(this);
	    wasSet = true;
	    return wasSet;
	  }
	 
	 public boolean setGroceryStore(GroceryStore aGroceryStore)
	  {
	    boolean wasSet = false;
	    if (aGroceryStore == null)
	    {
	      return wasSet;
	    }

	    GroceryStore existingGroceryStore = groceryStore;
	    groceryStore = aGroceryStore;
	    if (existingGroceryStore != null && !existingGroceryStore.equals(aGroceryStore))
	    {
	      existingGroceryStore.removeGroceryOrder(this);
	    }
	    groceryStore.addGroceryOrder(this);
	    wasSet = true;
	    return wasSet;
	  }
	 
	 public void setOrderItems(List<OrderItem> aOrderItems) {
		 this.orderItems = aOrderItems;
//		 for (OrderItem oi: aOrderItems) {
//			 oi.setGroceryOrder(this);
//		 }
	 }

	 
	 
	 //DELETE AND TO STRING METHOD



	 
	 
  public String toString()
  {
	  return super.toString() + "[" + "orderId" + ":" + getOrderId() + "," + "totalCost" + ":"
              + getTotalCost() + "," + "orderType" + ":" + getOrderType() + ","
              + System.getProperties().getProperty("line.separator") + "  " + "customer = "
              + (getCustomer() != null ? Integer.toHexString(System.identityHashCode(getCustomer())) : "null")
              + System.getProperties().getProperty("line.separator") + "  " + "grocerystore = "
              + (getGroceryStore() != null ? Integer.toHexString(System.identityHashCode(getGroceryStore())) : "null")
              + System.getProperties().getProperty("line.separator") + "  " + "OrderItems="
              +(getOrderItems() != null ? Integer.toHexString(System.identityHashCode(getOrderItems())) : "null") 
              + "]";
  }

  public void delete()
  {
    GroceryStore placeholderGroceryStore = groceryStore;
    this.groceryStore = null;
    if(placeholderGroceryStore != null)
    {
      placeholderGroceryStore.removeGroceryOrder(this);
    }
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeGroceryOrder(this);
    }
    while (orderItems.size() > 0)
    {
      OrderItem aOrderItem = orderItems.get(orderItems.size() - 1);
      aOrderItem.delete();
      orderItems.remove(aOrderItem);
    }
    
  }	



	
//UMPLE GENERATED CODE	


	  public OrderItem getOrderItem(int index)
	  {
	    OrderItem aOrderItem = orderItems.get(index);
	    return aOrderItem;
	  }

	  public int numberOfOrderItems()
	  {
	    int number = orderItems.size();
	    return number;
	  }

	  public boolean hasOrderItems()
	  {
	    boolean has = orderItems.size() > 0;
	    return has;
	  }

	  public int indexOfOrderItem(OrderItem aOrderItem)
	  {
	    int index = orderItems.indexOf(aOrderItem);
	    return index;
	  }

	  public static int minimumNumberOfOrderItems()
	  {
	    return 0;
	  }
	  
	  public OrderItem addOrderItem(String aName, int aPrice, int aCurrentStock, GroceryStore aGroceryStore, int quantity)
	  {
	    return new OrderItem(aName, aPrice, aCurrentStock, aGroceryStore, this);
	    
	  }

	  public boolean addOrderItem(OrderItem aOrderItem)
	  {
	    boolean wasAdded = false;
	    if (orderItems.contains(aOrderItem)) { return false; }
	    GroceryOrder existingGroceryOrder = aOrderItem.getGroceryOrder();
	    boolean isNewGroceryOrder = existingGroceryOrder != null && !this.equals(existingGroceryOrder);
	    if (isNewGroceryOrder)
	    {
	      aOrderItem.setGroceryOrder(this);
	    }
	    else
	    {
	      orderItems.add(aOrderItem);
	    }
	    wasAdded = true;
	    return wasAdded;
	  }

	  public boolean removeOrderItem(OrderItem aOrderItem)
	  {
	    boolean wasRemoved = false;
	    //Unable to remove aOrderItem, as it must always have a groceryOrder
	    if (!this.equals(aOrderItem.getGroceryOrder()))
	    {
	      orderItems.remove(aOrderItem);
	      wasRemoved = true;
	    }
	    return wasRemoved;
	  }
	  /* Code from template association_AddIndexControlFunctions */
	  public boolean addOrderItemAt(OrderItem aOrderItem, int index)
	  {  
	    boolean wasAdded = false;
	    if(addOrderItem(aOrderItem))
	    {
	      if(index < 0 ) { index = 0; }
	      if(index > numberOfOrderItems()) { index = numberOfOrderItems() - 1; }
	      orderItems.remove(aOrderItem);
	      orderItems.add(index, aOrderItem);
	      wasAdded = true;
	    }
	    return wasAdded;
	  }

	  public boolean addOrMoveOrderItemAt(OrderItem aOrderItem, int index)
	  {
	    boolean wasAdded = false;
	    if(orderItems.contains(aOrderItem))
	    {
	      if(index < 0 ) { index = 0; }
	      if(index > numberOfOrderItems()) { index = numberOfOrderItems() - 1; }
	      orderItems.remove(aOrderItem);
	      orderItems.add(index, aOrderItem);
	      wasAdded = true;
	    } 
	    else 
	    {
	      wasAdded = addOrderItemAt(aOrderItem, index);
	    }
	    return wasAdded;
	  }

}