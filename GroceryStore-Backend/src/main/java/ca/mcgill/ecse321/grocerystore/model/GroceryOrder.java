package ca.mcgill.ecse321.grocerystore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class GroceryOrder
{
  public enum OrderType { InStore, PickUp, Delivery }
  public enum OrderStatus { Received, Processing, ReadyForPickUp, BeingDelivered, Completed }

  //Order Attributes
  private int orderId;
  private int totalCost;
  private OrderType orderType;
  private OrderStatus orderStatus;

  //Order Associations
  private List<OrderItem> orderItems;
  private Customer customer;
  

  public GroceryOrder(Integer aTotalCost, OrderType aOrderType, OrderStatus orderStatus, Customer aCustomer )
  {
	this.orderType = aOrderType;
    this.totalCost = aTotalCost;
    this.customer = aCustomer;
    this.orderItems = new ArrayList<OrderItem>();
    this.orderStatus = orderStatus;
  }
  
  public GroceryOrder(Integer aTotalCost, OrderType aOrderType, Customer aCustomer )
  {
	this.orderType = aOrderType;
    this.totalCost = aTotalCost;
    this.customer = aCustomer;
    this.orderItems = new ArrayList<OrderItem>();
    this.orderStatus = OrderStatus.Received;
  }
  
  
  public GroceryOrder(Customer aCustomer )
  {
	totalCost = 0;
	orderType = null;
	this.customer = aCustomer;
    this.orderItems = new ArrayList<OrderItem>();
    this.orderStatus = OrderStatus.Received;
  }
  
  public GroceryOrder(Integer aTotalCost, OrderType aOrderType)
  {
	totalCost = aTotalCost;
	orderType = aOrderType;
	this.customer = null;
    this.orderItems = new ArrayList<OrderItem>();
    this.orderStatus = OrderStatus.Received;
  }
  
  public GroceryOrder()
  {
    totalCost = 0;
    orderItems = new ArrayList<OrderItem>();
    orderType = null;
    this.orderItems = new ArrayList<OrderItem>();
    this.customer = null;
    this.orderStatus = OrderStatus.Received;
  }
  
  
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
	
	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	

	@ManyToOne
	public Customer getCustomer() {
		return customer;
	}
	 
	 @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
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