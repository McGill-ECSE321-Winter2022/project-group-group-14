package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;



@Entity
public class Owner extends AccountType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Owner(Account aAccount)
  {
    super(aAccount);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}