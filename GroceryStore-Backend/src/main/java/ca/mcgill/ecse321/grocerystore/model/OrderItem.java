package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class OrderItem extends InventoryItem
{

  private GroceryOrder groceryOrder;


  public OrderItem(String aName, int aPrice, int aCurrentStock, GroceryStore aGroceryStore, GroceryOrder aGroceryOrder)
  {
    super(aName,aPrice, aCurrentStock, aGroceryStore);
    boolean didAddGroceryOrder = setGroceryOrder(aGroceryOrder);
    if (!didAddGroceryOrder)
    {
      throw new RuntimeException("Unable to create account due to groceryStore");
    }
    
  }

  
  public OrderItem()
  {
    super();
    this.groceryOrder = null;
    
  }
  

@ManyToOne
public GroceryOrder getGroceryOrder() {
	return this.groceryOrder;
}

public boolean setGroceryOrder(GroceryOrder aGroceryOrder)
{
  boolean wasSet = false;
  if (aGroceryOrder == null)
  {
    return wasSet;
  }

  GroceryOrder existingGroceryOrder = groceryOrder;
  groceryOrder= aGroceryOrder;
  if (existingGroceryOrder != null && !existingGroceryOrder.equals(aGroceryOrder))
  {
    existingGroceryOrder.removeOrderItem(this);
  }
  groceryOrder.addOrderItem(this);
  wasSet = true;
  return wasSet;
}
  public void delete()
  {
   GroceryOrder placeholderGroceryOrder = groceryOrder;
    this.groceryOrder = null;
    if(placeholderGroceryOrder != null)
    {
      placeholderGroceryOrder.removeOrderItem(this);
    }
    //super.delete();
  }

  
}