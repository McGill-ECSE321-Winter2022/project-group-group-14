package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class InventoryItem
{

  private String name; //type of item
  private int price;	
  private int currentStock;
  private int itemId;
  
  
  //association classes 
  private GroceryStore groceryStore;


 
  public InventoryItem(String aName, int aPrice, int aCurrentStock, GroceryStore aGroceryStore)
  {
    name = aName;
    price = aPrice;
    currentStock = aCurrentStock;
    boolean didAddGroceryStore = setGroceryStore(aGroceryStore);
    if (!didAddGroceryStore)
    {
      throw new RuntimeException("Unable to create account due to groceryStore");
    }
  }
  
  public InventoryItem()
  {
    name = null;
    price = 0;
    currentStock = 0;
    groceryStore = null;
    
  }

  //SETTERS AND GETTERS FOR ATTRIBUTES
  
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  public int getItemId() {
	  return this.itemId;
  }
  public void setItemId(int id) {
	  this.itemId = id ;
  }
  
  public void setAccountId(int aitemId) {
	  this.itemId = aitemId;
  }
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
  
  //SETTERS AND GETTERS FOR ASSOCIATIONS
  @ManyToOne   
  public GroceryStore getGroceryStore() {
	  return groceryStore;
  }
  
  public boolean setGroceryStore(GroceryStore aGroceryStore)
  {
    boolean wasSet = false;
    if (aGroceryStore == null)
    {
      return wasSet;
    }

    GroceryStore existingGroceryStore = groceryStore;
    groceryStore = aGroceryStore;
    if (existingGroceryStore != null && !existingGroceryStore.equals(aGroceryStore))
    {
      existingGroceryStore.removeInventoryItem(this);
    }
    groceryStore.addInventoryItem(this);
    wasSet = true;
    return wasSet;
  }



  public void delete()
  {
    GroceryStore placeholderGroceryStore = groceryStore;
    this.groceryStore = null;
    if(placeholderGroceryStore != null)
    {
      placeholderGroceryStore.removeInventoryItem(this);
    }
  }



  public String toString()
  {
    return super.toString() + "["+
    		"itemId" + ":" + getItemId()+ "," +
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "," +
            "currentStock" + ":" + getCurrentStock()+ "]" + System.getProperties().getProperty("line.separator");
  }
}