package ca.mcgill.ecse321.grocerystore.model;

import java.util.List;

//@Entity
//@DiscriminatorValue("pickup")
public class PickupOrder extends GroceryOrder
{
  public PickupOrder(Integer aOrderNumber, Integer aTotalCost, List<Item> allItems)
  {
    super(aOrderNumber, aTotalCost, allItems);
  }
  public PickupOrder()
  {
    super();
  }

//  public void delete()
//  {
//    super.delete();
//  }


}