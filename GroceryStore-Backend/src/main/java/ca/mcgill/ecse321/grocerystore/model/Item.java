package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;


// line 55 "model.ump"
// line 186 "model.ump"
@Entity
public class Item extends InventoryItem
{
  private int quantity;

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
  
//  
//  public void delete()
//  {
//  }

}