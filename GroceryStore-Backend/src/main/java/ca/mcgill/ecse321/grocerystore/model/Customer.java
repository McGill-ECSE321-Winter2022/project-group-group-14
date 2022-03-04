package ca.mcgill.ecse321.grocerystore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity//@DiscriminatorValue("Customer")
public class Customer extends Account
{



  //Customer Attributes
  private String address;
  private String phoneNumber;

  //Customer Associations
  private List<GroceryOrder> groceryOrders;



  public Customer(String aEmail, String aUsername, String aPassword,GroceryStore aGroceryStore, String aPhoneNumber, String aAddress)
  {
    super(aEmail, aUsername, aPassword, aGroceryStore);
    this.address = aAddress;
    this.phoneNumber = aPhoneNumber;
    this.groceryOrders = new ArrayList<GroceryOrder>();
  }
  
  public Customer()
  {
    super();
    this.groceryOrders = new ArrayList<GroceryOrder>();
    this.address = null;
    this.phoneNumber = null; 
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
  


  
  @OneToMany
  public List<GroceryOrder> getGroceryOrders()
  {
    return this.groceryOrders;
  }

  public void setOrders(List<GroceryOrder> orders) {
	  this.groceryOrders = orders;
  }
  
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGroceryOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
//  public GroceryOrder addGroceryOrder(GroceryStore aGroceryStore)
//  {
//    return new GroceryOrder(aGroceryStore, this);
//  }

  public boolean addGroceryOrder(GroceryOrder aGroceryOrder)
  {
    boolean wasAdded = false;
    if (groceryOrders.contains(aGroceryOrder)) { return false; }
    Customer existingCustomer = aGroceryOrder.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aGroceryOrder.setCustomer(this);
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
    //Unable to remove aGroceryOrder, as it must always have a customer
    if (!this.equals(aGroceryOrder.getCustomer()))
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
    for(int i=groceryOrders.size(); i > 0; i--)
    {
      GroceryOrder aGroceryOrder = groceryOrders.get(i - 1);
      aGroceryOrder.delete();
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