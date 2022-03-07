package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class OrderItem extends InventoryItem
{




  public OrderItem(String aName, int aPrice, int aCurrentStock, GroceryStore aGroceryStore, GroceryOrder aGroceryOrder)
  {
    super(aName,aPrice, aCurrentStock);
    
  }

  
  public OrderItem()
  {
    super();
    
  }


  
}