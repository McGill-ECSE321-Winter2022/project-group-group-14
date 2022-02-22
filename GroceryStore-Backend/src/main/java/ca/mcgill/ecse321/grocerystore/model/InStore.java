package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;

// line 105 "model.ump"
// line 174 "model.ump"
@Entity
public class InStore extends Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public InStore(int aTotalCost, Item... allItems)
  {
    super(aTotalCost, allItems);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}