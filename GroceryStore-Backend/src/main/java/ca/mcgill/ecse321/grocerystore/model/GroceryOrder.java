package ca.mcgill.ecse321.grocerystore.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Shift;


@Entity
public class GroceryOrder
{
  public enum OrderType { InStore, PickUp, Delivery }


  private OrderType orderType;
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private Integer totalCost;
  private Integer orderNumber;

  //Order Associations
  private List<OrderItem> orderItems;
  private Customer customer;
  

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GroceryOrder(Integer aOrderNumber, Integer aTotalCost, List<OrderItem> allItems, OrderType aOrderType)
  {
	orderType = aOrderType;
	orderNumber = aOrderNumber;
    totalCost = aTotalCost;
    orderItems = allItems;
    
  }
  public GroceryOrder()
  {
    totalCost = null;
    orderNumber = null;
    orderItems = new ArrayList<OrderItem>();
    orderType = null;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTotalCost(Integer aTotalCost)
  {
    boolean wasSet = false;
    totalCost = aTotalCost;
    wasSet = true;
    return wasSet;
  }



  public void setOrderNumber(int num) {
      this.orderNumber=num;
  }

  @Id
  public Integer getOrderNumber() {
      return this.orderNumber;
  }
  
  public Integer getTotalCost()
  {
    return totalCost;
  }
  /* Code from template association_GetMany */
  public OrderItem getItem(int index)
  {
    OrderItem aItem = orderItems.get(index);
    return aItem;
  }

  /**
   * paymentType needed. Cash can only be used for pickups
   * and InPerson.
   */
  @OneToMany(cascade = CascadeType.ALL)
  public List<OrderItem> getItems() 
  {
    List<OrderItem> newItems = Collections.unmodifiableList(orderItems);
    return newItems;
  }



//  public int numberOfItems()
//  {
//    int number = items.size();
//    return number;
//  }
//
//  public boolean hasItems()
//  {
//    boolean has = items.size() > 0;
//    return has;
//  }
//
//  public int indexOfItem(Item aItem)
//  {
//    int index = items.indexOf(aItem);
//    return index;
//  }


  /* Code from template association_MinimumNumberOfMethod */

//  public static int minimumNumberOfItems()
//  {
//    return 1;
//  }
//  /* Code from template association_AddMNToOptionalOne */
//  public boolean addItem(Item aItem)
//  {
//    boolean wasAdded = false;
//    items.add(aItem);
//    setOrder(aItem,this);
//    wasAdded = true;
//    return wasAdded;
//  }
//
//  public boolean removeItem(Item aItem)
//  {
//    boolean wasRemoved = false;
//    if (items.contains(aItem) && numberOfItems() > minimumNumberOfItems())
//    {
//      items.remove(aItem);
//      setOrder(aItem,null);
//      wasRemoved = true;
//    }
//    return wasRemoved;
//  }
  /* Code from template association_SetMNToOptionalOne */

  public boolean setItems(OrderItem... newItems)
  {
    boolean wasSet = false;

//    if (newItems.length < minimumNumberOfItems())
//    {
//      return wasSet;
//    }


    ArrayList<OrderItem> checkNewItems = new ArrayList<OrderItem>();
//    HashMap<Order,Integer> orderToNewItems = new HashMap<Order,Integer>();
    for (OrderItem aItem : newItems)
    {
      if (checkNewItems.contains(aItem))
      {
        return wasSet;
      }
      checkNewItems.add(aItem);
    }

    orderItems.removeAll(checkNewItems);


//    for (Item orphan : items)
//    {
//      setOrder(orphan, null);
//    }
//    items.clear();
//    for (Item aItem : newItems)
//    {
//      setOrder(aItem, this);
//      items.add(aItem);
//    }

    wasSet = true;
    return wasSet;
  }

  /* Code from template association_GetPrivate */
//  private void setOrder(Item aItem, Order aOrder)
//  {
//    try
//    {
//      java.lang.reflect.Field mentorField = aItem.getClass().getDeclaredField("order");
//      mentorField.setAccessible(true);
//      mentorField.set(aItem, aOrder);
//    }
//    catch (Exception e)
//    {
//      throw new RuntimeException("Issue internally setting aOrder to aItem", e);
//    }
//  }

  /* Code from template association_AddIndexControlFunctions */

//  public boolean addItemAt(Item aItem, int index)
//  {  
//    boolean wasAdded = false;
//    if(addItem(aItem))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfItems()) { index = numberOfItems() - 1; }
//      items.remove(aItem);
//      items.add(index, aItem);
//      wasAdded = true;
//    }
//    return wasAdded;
//  }
//
//  public boolean addOrMoveItemAt(Item aItem, int index)
//  {
//    boolean wasAdded = false;
//    if(items.contains(aItem))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfItems()) { index = numberOfItems() - 1; }
//      items.remove(aItem);
//      items.add(index, aItem);
//      wasAdded = true;
//    } 
//    else 
//    {
//      wasAdded = addItemAt(aItem, index);
//    }
//    return wasAdded;
//  }

  

  public void delete()
  {
//    for(Item aItem : items)
//    {
//      setOrder(aItem,null);
//    }
    orderItems.clear();
  }



  public String toString()
  {
    return super.toString() + "["+
            "totalCost" + ":" + getTotalCost()+ "]";
  }
public OrderType getOrderType() {
	return orderType;
}
public void setOrderType(OrderType orderType) {
	this.orderType = orderType;
}

@ManyToOne(optional=false)
public Customer getCustomer() {
	return customer;
}
public void setCustomer(Customer customer) {
	this.customer = customer;
}
}