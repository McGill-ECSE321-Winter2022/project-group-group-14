package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;



@Entity
public class Owner extends Account
{

  public Owner(String aEmail, String aUsername, String aPassword)
  {
    super (aEmail, aUsername, aPassword);
  }
  
  public Owner()
  {
    super();
    
  }
  
  public void delete()
  {
    super.delete();
  }

}