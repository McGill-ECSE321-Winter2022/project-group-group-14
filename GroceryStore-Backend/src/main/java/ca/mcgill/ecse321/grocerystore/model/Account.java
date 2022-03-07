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


  public Account(String aEmail, String aUsername, String aPassword)
  {
    email = aEmail;
    username = aUsername;
    password = aPassword;
  }
  
  public Account()
  {
    email = null;
    username = null;
    password = null;
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
  public void setAccountId(int aaccountId) {
	  this.accountId = aaccountId;
  }
  
  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    this.email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    this.username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    this.password = aPassword;
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
   

	  public void delete()
	  {

	  }



}