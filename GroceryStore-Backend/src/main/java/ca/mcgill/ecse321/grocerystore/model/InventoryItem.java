package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.*;

// line 60 "model.ump"
// line 179 "model.ump"
@Entity
public class InventoryItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Inventory Attributes
  private String name;
  private int price;
  private int currentStock;



  //------------------------
  // CONSTRUCTOR
  //------------------------

  public InventoryItem(String aName, int aPrice, int aCurrentStock)
  {
    name = aName;
    price = aPrice;
    currentStock = aCurrentStock;
    
  }
  public InventoryItem(String aName, int aPrice)
  {
    name = aName;
    price = aPrice;
    
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

  @Id
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


//  /* Code from template association_MinimumNumberOfMethod */
//  public static int minimumNumberOfItems()
//  {
//    return 0;
//  }
//  

  public void delete()
  {

   
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "," +
            "currentStock" + ":" + getCurrentStock()+ "]" + System.getProperties().getProperty("line.separator");
  }
}