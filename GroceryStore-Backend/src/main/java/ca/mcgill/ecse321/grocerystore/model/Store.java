package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;


import java.util.*;

// line 40 "model.ump"
// line 204 "model.ump"
public class Store
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Store Associations
  private StoreSchedule storeSchedule;
  private List<Inventory> inventories;
  private EmployeeSchedule employeeSchedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Store(StoreSchedule aStoreSchedule, EmployeeSchedule aEmployeeSchedule)
  {
    if (aStoreSchedule == null || aStoreSchedule.getStore() != null)
    {
      throw new RuntimeException("Unable to create Store due to aStoreSchedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    storeSchedule = aStoreSchedule;
    inventories = new ArrayList<Inventory>();
    if (aEmployeeSchedule == null || aEmployeeSchedule.getStore() != null)
    {
      throw new RuntimeException("Unable to create Store due to aEmployeeSchedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    employeeSchedule = aEmployeeSchedule;
  }

  public Store(String aOpeningTimeForStoreSchedule, String aClosingTimeForStoreSchedule, Day aDaysOpenForStoreSchedule, Shift aShiftForEmployeeSchedule, Employee aEmployeeForEmployeeSchedule)
  {
    storeSchedule = new StoreSchedule(aOpeningTimeForStoreSchedule, aClosingTimeForStoreSchedule, aDaysOpenForStoreSchedule, this);
    inventories = new ArrayList<Inventory>();
    employeeSchedule = new EmployeeSchedule(aShiftForEmployeeSchedule, aEmployeeForEmployeeSchedule, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public StoreSchedule getStoreSchedule()
  {
    return storeSchedule;
  }
  /* Code from template association_GetMany */
  public Inventory getInventory(int index)
  {
    Inventory aInventory = inventories.get(index);
    return aInventory;
  }

  public List<Inventory> getInventories()
  {
    List<Inventory> newInventories = Collections.unmodifiableList(inventories);
    return newInventories;
  }

  public int numberOfInventories()
  {
    int number = inventories.size();
    return number;
  }

  public boolean hasInventories()
  {
    boolean has = inventories.size() > 0;
    return has;
  }

  public int indexOfInventory(Inventory aInventory)
  {
    int index = inventories.indexOf(aInventory);
    return index;
  }
  /* Code from template association_GetOne */
  public EmployeeSchedule getEmployeeSchedule()
  {
    return employeeSchedule;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfInventories()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Inventory addInventory(String aName, int aPrice, int aCurrentStock)
  {
    return new Inventory(aName, aPrice, aCurrentStock, this);
  }

  public boolean addInventory(Inventory aInventory)
  {
    boolean wasAdded = false;
    if (inventories.contains(aInventory)) { return false; }
    Store existingStore = aInventory.getStore();
    boolean isNewStore = existingStore != null && !this.equals(existingStore);
    if (isNewStore)
    {
      aInventory.setStore(this);
    }
    else
    {
      inventories.add(aInventory);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeInventory(Inventory aInventory)
  {
    boolean wasRemoved = false;
    //Unable to remove aInventory, as it must always have a store
    if (!this.equals(aInventory.getStore()))
    {
      inventories.remove(aInventory);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addInventoryAt(Inventory aInventory, int index)
  {  
    boolean wasAdded = false;
    if(addInventory(aInventory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInventories()) { index = numberOfInventories() - 1; }
      inventories.remove(aInventory);
      inventories.add(index, aInventory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveInventoryAt(Inventory aInventory, int index)
  {
    boolean wasAdded = false;
    if(inventories.contains(aInventory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInventories()) { index = numberOfInventories() - 1; }
      inventories.remove(aInventory);
      inventories.add(index, aInventory);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addInventoryAt(aInventory, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    StoreSchedule existingStoreSchedule = storeSchedule;
    storeSchedule = null;
    if (existingStoreSchedule != null)
    {
      existingStoreSchedule.delete();
    }
    for(int i=inventories.size(); i > 0; i--)
    {
      Inventory aInventory = inventories.get(i - 1);
      aInventory.delete();
    }
    EmployeeSchedule existingEmployeeSchedule = employeeSchedule;
    employeeSchedule = null;
    if (existingEmployeeSchedule != null)
    {
      existingEmployeeSchedule.delete();
    }
  }

}