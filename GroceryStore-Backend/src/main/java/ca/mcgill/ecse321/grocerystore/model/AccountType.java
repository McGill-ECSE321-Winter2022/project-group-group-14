package ca.mcgill.ecse321.grocerystore.model;


import javax.persistence.Id;
import javax.persistence.MappedSuperclass;




@MappedSuperclass
public abstract class AccountType
{
  private String name;
  private String email;
  private String username;
  private String password;

  public AccountType(String aName, String aEmail, String aUsername, String aPassword)
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

   public void delete(){}

   public String toString()
   {
     return super.toString() + "["+
             "name" + ":" + getName()+ "," +
             "email" + ":" + getEmail()+ "," +
             "username" + ":" + getUsername()+ "," +
             "password" + ":" + getPassword()+ "]";
   }

}