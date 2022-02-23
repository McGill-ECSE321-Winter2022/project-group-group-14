package ca.mcgill.ecse321.grocerystore.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// line 105 "model.ump"
// line 174 "model.ump"
//@Entity
//@Entity
//@DiscriminatorValue("in-store")
public class InStore extends GroceryOrder
{


  public InStore(Integer aOrderNumber, Integer aTotalCost, List<Item> allItems)
  {
    super(aOrderNumber, aTotalCost, allItems);
  }
  public InStore()
  {
    super();
  }



  public void delete()
  {
    super.delete();
  }


}