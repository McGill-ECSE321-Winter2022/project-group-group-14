package ca.mcgill.ecse321.grocerystore.model;


import javax.persistence.Entity;
import javax.persistence.Id;




@Entity 
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
//@DiscriminatorColumn(name = "AccountType")
//@MappedSuperclass
public abstract class Account
{
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
             "email" + ":" + getEmail()+ "," +
             "username" + ":" + getUsername()+ "," +
             "password" + ":" + getPassword()+ "]";
   }

}