package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;


import java.util.*;

// line 105 "model.ump"
// line 174 "model.ump"
public class InStore extends Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public InStore(int aTotalCost, Customer aCustomer, Item... allItems)
  {
    super(aTotalCost, aCustomer, allItems);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}