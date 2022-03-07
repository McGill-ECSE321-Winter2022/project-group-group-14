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
  

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GroceryOrder(Integer aTotalCost, OrderType aOrderType, Customer aCustomer )
  {
	this.orderType = aOrderType;
    this.totalCost = aTotalCost;
    this.customer = aCustomer;
    this.orderItems = new ArrayList<OrderItem>();
  }
  
  public GroceryOrder(OrderType aOrderType, GroceryStore aGroceryStore,Customer aCustomer )
  {
	totalCost = 0;
	orderType = aOrderType;
	this.customer = aCustomer;
    this.orderItems = new ArrayList<OrderItem>();
  }
  

  public GroceryOrder()
  {
    totalCost = 0;
    orderItems = new ArrayList<OrderItem>();
    orderType = null;
    this.orderItems = new ArrayList<OrderItem>();
    this.customer = null;
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
	public Customer getCustomer() {
		return customer;
	}
	 
	  /**
	   * paymentType needed. Cash can only be used for pickups
	   * and InPerson.
	   */
	
	 @OneToMany(cascade = CascadeType.ALL)
	 public List<OrderItem> getOrderItems() 
	 {
		 return this.orderItems;
	 }
	  
	  public boolean setCustomer(Customer aCustomer)
	  { 
		  this.customer = aCustomer;
		  return true;
	  } 
	    
	 
	 public void setOrderItems(List<OrderItem> aOrderItems) {
		 this.orderItems = aOrderItems;
	 }

	 
	 
	 //DELETE AND TO STRING METHOD



	 
	 
  public String toString()
  {
	  return super.toString() + "[" + "orderId" + ":" + getOrderId() + "," + "totalCost" + ":"
              + getTotalCost() + "," + "orderType" + ":" + getOrderType() + ","
              + System.getProperties().getProperty("line.separator") + "  " + "customer = "
              + (getCustomer() != null ? Integer.toHexString(System.identityHashCode(getCustomer())) : "null")
              + System.getProperties().getProperty("line.separator") + "  " + "OrderItems="
              +(getOrderItems() != null ? Integer.toHexString(System.identityHashCode(getOrderItems())) : "null") 
              + "]";
  }




}