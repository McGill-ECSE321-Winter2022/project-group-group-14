package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;



@Entity
public class Owner extends Account
{

  public Owner(String aName, String aEmail, String aUsername, String aPassword)
  {
    super (aName, aEmail, aUsername, aPassword);
  }
  
  public void delete()
  {
    super.delete();
  }

}