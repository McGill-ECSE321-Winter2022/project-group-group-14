package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Owner extends AccountType
{

  public Owner(Account aAccount)
  {
    super(aAccount);
  }
  
  public void delete()
  {
    super.delete();
  }

}