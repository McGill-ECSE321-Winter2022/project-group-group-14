package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private String name;
  private String email;
  private String username;
  private String password;

  //Account Associations
  private AccountType accountType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account(String aName, String aEmail, String aUsername, String aPassword)
  {
    name = aName;
    email = aEmail;
    username = aUsername;
    password = aPassword;
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

  public String getName()
  {
    return name;
  }
  
  @Id
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
  /* Code from template association_GetOne */
  public AccountType getAccountType()
  {
    return accountType;
  }

  public boolean hasAccountType()
  {
    boolean has = accountType != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setAccountType(AccountType aNewAccountType)
  {
    boolean wasSet = false;
    if (accountType != null && !accountType.equals(aNewAccountType) && equals(accountType.getAccount()))
    {
      //Unable to setAccountType, as existing accountType would become an orphan
      return wasSet;
    }

    accountType = aNewAccountType;
    Account anOldAccount = aNewAccountType != null ? aNewAccountType.getAccount() : null;

    if (!this.equals(anOldAccount))
    {
      if (anOldAccount != null)
      {
        anOldAccount.accountType = null;
      }
      if (accountType != null)
      {
        accountType.setAccount(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    AccountType existingAccountType = accountType;
    accountType = null;
    if (existingAccountType != null)
    {
      existingAccountType.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "email" + ":" + getEmail()+ "," +
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "accountType = "+(getAccountType()!=null?Integer.toHexString(System.identityHashCode(getAccountType())):"null");
  }
}