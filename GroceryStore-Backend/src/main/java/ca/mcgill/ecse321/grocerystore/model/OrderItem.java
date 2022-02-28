package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;



// line 55 "model.ump"
// line 186 "model.ump"
@Entity
public class OrderItem extends InventoryItem
{
  private int quantity;
  private GroceryOrder groceryOrder;

//
//  public Item (String aName, int aPrice, int quantity)
//  {
//    super(aName,aPrice);
//    this.setQuantity(quantity);
//  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

@ManyToOne(optional = false)
public GroceryOrder getGroceryOrder() {
	return groceryOrder;
}

public void setGroceryOrder(GroceryOrder groceryOrder) {
	this.groceryOrder = groceryOrder;
}
  
//  
//  public void delete()
//  {
//  }

}