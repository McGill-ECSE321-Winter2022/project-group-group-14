package ca.mcgill.ecse321.grocerystore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Customer extends Account
{

  

  //Customer Attributes
  private String address;
  private String phoneNumber;

  //Customer Associations
  private List<GroceryOrder> orders;



  public Customer(String aEmail, String aUsername, String aPassword, String aAddress, String aPhoneNumber)
  {
    super(aEmail, aUsername, aPassword);
    address = aAddress;
    phoneNumber = aPhoneNumber;
    orders = new ArrayList<GroceryOrder>();
  }
  public Customer()
  {
    super();
    
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getAddress()
  {
    return address;
  }
  
  public String getPhoneNumber()
  {
    return phoneNumber;
  }
  /* Code from template association_GetMany */
  

  public GroceryOrder getOrder(int index)
  {
    GroceryOrder aOrder = orders.get(index);
    return aOrder;
  }
  
  @OneToMany
  public List<GroceryOrder> getOrders()
  {
    List<GroceryOrder> newOrders = orders;
    return newOrders;
  }

  
  public void setOrders(List<GroceryOrder> orders) {
	  this.orders = orders;
  }
  


//  public int numberOfOrders()
//  {
//    int number = orders.size();
//    return number;
//  }
//
//  public boolean hasOrders()
//  {
//    boolean has = orders.size() > 0;
//    return has;
//  }
//
//  public int indexOfOrder(Order aOrder)
//  {
//    int index = orders.indexOf(aOrder);
//    return index;
//  }
//  /* Code from template association_MinimumNumberOfMethod */
//  public static int minimumNumberOfOrders()
//  {
//    return 0;
//  }
//  /* Code from template association_AddManyToOne */
//
//
//  public boolean addOrder(Order aOrder)
//  {
//    boolean wasAdded = false;
//    if (orders.contains(aOrder)) { return false; }
//    orders.add(aOrder);
//    wasAdded = true;
//    return wasAdded;
//  }
//
//  public boolean removeOrder(Order aOrder)
//  {
//    boolean wasRemoved = false;
//    if(orders.remove(aOrder)){
//      wasRemoved = true;
//    }
//    return wasRemoved;
//  }
//  /* Code from template association_AddIndexControlFunctions */
//  public boolean addOrderAt(Order aOrder, int index)
//  {  
//    boolean wasAdded = false;
//    if(addOrder(aOrder))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
//      orders.remove(aOrder);
//      orders.add(index, aOrder);
//      wasAdded = true;
//    }
//    return wasAdded;
//  }
//
//  public boolean addOrMoveOrderAt(Order aOrder, int index)
//  {
//    boolean wasAdded = false;
//    if(orders.contains(aOrder))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
//      orders.remove(aOrder);
//      orders.add(index, aOrder);
//      wasAdded = true;
//    } 
//    else 
//    {
//      wasAdded = addOrderAt(aOrder, index);
//    }
//    return wasAdded;
//  }


  public void delete()
  {
    for(int i=orders.size(); i > 0; i--)
    {
      GroceryOrder aOrder = orders.get(i - 1);
      aOrder.delete();
    }
    super.delete();
  }



  public String toString()
  {
    return super.toString() + "["+
            "address" + ":" + getAddress()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]";
  }
}