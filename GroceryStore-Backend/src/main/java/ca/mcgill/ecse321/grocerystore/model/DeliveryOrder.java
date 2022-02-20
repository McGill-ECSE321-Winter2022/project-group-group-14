package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;


import java.util.*;

// line 113 "model.ump"
// line 164 "model.ump"
public class DeliveryOrder extends Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DeliveryOrder Attributes
  private String deliveryAddress;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DeliveryOrder(int aTotalCost, Customer aCustomer, String aDeliveryAddress, Item... allItems)
  {
    super(aTotalCost, aCustomer, allItems);
    deliveryAddress = aDeliveryAddress;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDeliveryAddress(String aDeliveryAddress)
  {
    boolean wasSet = false;
    deliveryAddress = aDeliveryAddress;
    wasSet = true;
    return wasSet;
  }

  public String getDeliveryAddress()
  {
    return deliveryAddress;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "deliveryAddress" + ":" + getDeliveryAddress()+ "]";
  }
}