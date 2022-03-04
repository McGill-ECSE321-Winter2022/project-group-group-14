package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Owner")
public class Owner extends Account
{

  public Owner(String aEmail, String aUsername, String aPassword,GroceryStore aGroceryStore)
  {
    super (aEmail, aUsername, aPassword, aGroceryStore);
  }
  
  public Owner()
  {
    super();
    
  }
  
  public void delete()
  {
    super.delete();
  }
  
  public String toString()
  {
      return super.toString();
  }

}