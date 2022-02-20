package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;


import java.util.*;

// line 60 "model.ump"
// line 179 "model.ump"
public class Inventory
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Inventory Attributes
  private String name;
  private int price;
  private int currentStock;

  //Inventory Associations
  private List<Item> items;
  private Store store;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Inventory(String aName, int aPrice, int aCurrentStock, Store aStore)
  {
    name = aName;
    price = aPrice;
    currentStock = aCurrentStock;
    items = new ArrayList<Item>();
    boolean didAddStore = setStore(aStore);
    if (!didAddStore)
    {
      throw new RuntimeException("Unable to create inventory due to store. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentStock(int aCurrentStock)
  {
    boolean wasSet = false;
    currentStock = aCurrentStock;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getPrice()
  {
    return price;
  }

  public int getCurrentStock()
  {
    return currentStock;
  }
  /* Code from template association_GetMany */
  public Item getItem(int index)
  {
    Item aItem = items.get(index);
    return aItem;
  }

  public List<Item> getItems()
  {
    List<Item> newItems = Collections.unmodifiableList(items);
    return newItems;
  }

  public int numberOfItems()
  {
    int number = items.size();
    return number;
  }

  public boolean hasItems()
  {
    boolean has = items.size() > 0;
    return has;
  }

  public int indexOfItem(Item aItem)
  {
    int index = items.indexOf(aItem);
    return index;
  }
  /* Code from template association_GetOne */
  public Store getStore()
  {
    return store;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Item addItem()
  {
    return new Item(this);
  }

  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    Inventory existingInventory = aItem.getInventory();
    boolean isNewInventory = existingInventory != null && !this.equals(existingInventory);
    if (isNewInventory)
    {
      aItem.setInventory(this);
    }
    else
    {
      items.add(aItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(Item aItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aItem, as it must always have a inventory
    if (!this.equals(aItem.getInventory()))
    {
      items.remove(aItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemAt(Item aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(Item aItem, int index)
  {
    boolean wasAdded = false;
    if(items.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setStore(Store aStore)
  {
    boolean wasSet = false;
    if (aStore == null)
    {
      return wasSet;
    }

    Store existingStore = store;
    store = aStore;
    if (existingStore != null && !existingStore.equals(aStore))
    {
      existingStore.removeInventory(this);
    }
    store.addInventory(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=items.size(); i > 0; i--)
    {
      Item aItem = items.get(i - 1);
      aItem.delete();
    }
    Store placeholderStore = store;
    this.store = null;
    if(placeholderStore != null)
    {
      placeholderStore.removeInventory(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "," +
            "currentStock" + ":" + getCurrentStock()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "store = "+(getStore()!=null?Integer.toHexString(System.identityHashCode(getStore())):"null");
  }
}