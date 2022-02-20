package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;


// line 55 "model.ump"
// line 186 "model.ump"
public class Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Associations
  private Inventory inventory;
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(Inventory aInventory)
  {
    boolean didAddInventory = setInventory(aInventory);
    if (!didAddInventory)
    {
      throw new RuntimeException("Unable to create item due to inventory. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Inventory getInventory()
  {
    return inventory;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }

  public boolean hasOrder()
  {
    boolean has = order != null;
    return has;
  }
  /* Code from template association_SetOneToMany */
  public boolean setInventory(Inventory aInventory)
  {
    boolean wasSet = false;
    if (aInventory == null)
    {
      return wasSet;
    }

    Inventory existingInventory = inventory;
    inventory = aInventory;
    if (existingInventory != null && !existingInventory.equals(aInventory))
    {
      existingInventory.removeItem(this);
    }
    inventory.addItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMandatoryMany */
  public boolean setOrder(Order aOrder)
  {
    //
    // This source of this source generation is association_SetOptionalOneToMandatoryMany.jet
    // This set file assumes the generation of a maximumNumberOfXXX method does not exist because 
    // it's not required (No upper bound)
    //   
    boolean wasSet = false;
    Order existingOrder = order;

    if (existingOrder == null)
    {
      if (aOrder != null)
      {
        if (aOrder.addItem(this))
        {
          existingOrder = aOrder;
          wasSet = true;
        }
      }
    } 
    else if (existingOrder != null)
    {
      if (aOrder == null)
      {
        if (existingOrder.minimumNumberOfItems() < existingOrder.numberOfItems())
        {
          existingOrder.removeItem(this);
          existingOrder = aOrder;  // aOrder == null
          wasSet = true;
        }
      } 
      else
      {
        if (existingOrder.minimumNumberOfItems() < existingOrder.numberOfItems())
        {
          existingOrder.removeItem(this);
          aOrder.addItem(this);
          existingOrder = aOrder;
          wasSet = true;
        }
      }
    }
    if (wasSet)
    {
      order = existingOrder;
    }
    return wasSet;
  }
  
  public void delete()
  {
    Inventory placeholderInventory = inventory;
    this.inventory = null;
    if(placeholderInventory != null)
    {
      placeholderInventory.removeItem(this);
    }
    if (order != null)
    {
      if (order.numberOfItems() <= 1)
      {
        order.delete();
      }
      else
      {
        Order placeholderOrder = order;
        this.order = null;
        placeholderOrder.removeItem(this);
      }
    }
  }

}