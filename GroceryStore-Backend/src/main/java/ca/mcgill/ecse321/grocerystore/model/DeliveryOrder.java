package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;


@Entity
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

  public DeliveryOrder(int aTotalCost, String aDeliveryAddress, Item... allItems)
  {
    super(aTotalCost, allItems);
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