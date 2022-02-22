package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;

@Entity
public class PickupOrder extends Order
{
  public PickupOrder(int aTotalCost, Customer aCustomer, Item... allItems)
  {
    super(aTotalCost, aCustomer, allItems);
  }

  public void delete()
  {
    super.delete();
  }


}