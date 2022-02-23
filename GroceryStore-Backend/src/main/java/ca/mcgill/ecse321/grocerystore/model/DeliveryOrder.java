package ca.mcgill.ecse321.grocerystore.model;

import java.util.List;


//@Entity
//@Entity
//@DiscriminatorValue("delivery")
public class DeliveryOrder extends GroceryOrder
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DeliveryOrder Attributes
  private String deliveryAddress;

  //------------------------
  // CONSTRUCTOR
  //------------------------
//
  public DeliveryOrder(Integer aOrderNumber, Integer aTotalCost, String aDeliveryAddress, List<Item> allItems)
  {
    super(aOrderNumber, aTotalCost, allItems);
    deliveryAddress = aDeliveryAddress;
  }
  public DeliveryOrder()
  {
    super();
    deliveryAddress=null;
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
//
//  public void delete()
//  {
//    super.delete();
//  }


  public String toString()
  {
    return super.toString() + "["+
            "deliveryAddress" + ":" + getDeliveryAddress()+ "]";
  }
}