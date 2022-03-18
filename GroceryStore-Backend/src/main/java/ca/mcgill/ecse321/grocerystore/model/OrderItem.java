package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class OrderItem
{
	private String name; //type of item
	private int price;
	private int itemId;



  public OrderItem(String aName, int aPrice)
  {
	  name = aName;
	  price = aPrice;
    
  }

  
  public OrderItem()
  {
	  name = null;
	  price = 0;
    
  }
  
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  public int getItemId() {
	  return this.itemId;
  }
  public void setItemId(int id) {
	  this.itemId = id ;
  }
  
//  public void setAccountId(int aitemId) {
//	  this.itemId = aitemId;
//  }
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

  public String getName()
  {
    return name;
  }

  public int getPrice()
  {
    return price;
  }

  public String toString()
  {
    return super.toString() + "["+
    		"itemId" + ":" + getItemId()+ "," +
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "," + System.getProperties().getProperty("line.separator");
  }
  
}