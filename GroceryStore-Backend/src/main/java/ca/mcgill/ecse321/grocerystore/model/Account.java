package ca.mcgill.ecse321.grocerystore.model;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="AccountType")
public abstract class Account
{
  private int accountId;
  private String email;
  private String username;
  private String password;
  private GroceryStore groceryStore;


  public Account(String aEmail, String aUsername, String aPassword, GroceryStore aGroceryStore)
  {
    email = aEmail;
    username = aUsername;
    password = aPassword;
    boolean didAddGroceryStore = setGroceryStore(aGroceryStore);
    if (!didAddGroceryStore)
    {
      throw new RuntimeException("Unable to create account due to groceryStore");
    }
  }
  
  public Account()
  {
    email = null;
    username = null;
    password = null;
    groceryStore = null;
  }

  //------------------------
  // INTERFACE
  //------------------------

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  public int getAccountId() {
	  return this.accountId;
  }
  public void setAccountId(int accountId) {
	  this.accountId = accountId;
  }
  
  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

//  @Id
//  @Column(name = "email", updatable = false, nullable = false)
  public String getEmail()
  {
    return email;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }


  public String toString()
  {
     return super.toString() + "["+
             "email" + ":" + getEmail()+ "," +
             "username" + ":" + getUsername()+ "," +
             "password" + ":" + getPassword()+ "]";
  }
   
	@ManyToOne   
	public GroceryStore getGroceryStore() {
		return groceryStore;
	}
//	public void setGroceryStore(GroceryStore groceryStore) {
//		this.groceryStore = groceryStore;
//	}

	//UMPLE CODE
	
	  /* Code from template association_SetOneToMany */
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
	      existingGroceryStore.removeAccount(this);
	    }
	    groceryStore.addAccount(this);
	    wasSet = true;
	    return wasSet;
	  }
	
	  public void delete()
	  {
	    GroceryStore placeholderGroceryStore = groceryStore;
	    this.groceryStore = null;
	    if(placeholderGroceryStore != null)
	    {
	      placeholderGroceryStore.removeAccount(this);
	    }
	  }



}