package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("Customer")
public class Customer extends Account
{


  //Customer Attributes
  private String address;
  private String phoneNumber;




  public Customer(String aEmail, String aUsername, String aPassword, String aPhoneNumber, String aAddress)
  {
	super(aEmail, aUsername, aPassword);
    this.address = aAddress;
    this.phoneNumber = aPhoneNumber;
  }
  
  public Customer()
  {
	super();
    this.address = null;
    this.phoneNumber = null; 
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    this.address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    this.phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getAddress()
  {
    return address;
  }
  
  public String getPhoneNumber()
  {
    return phoneNumber;
  }
 

  public String toString()
  {
    return super.toString() + "["+
            "address" + ":" + getAddress()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]";
  }
}