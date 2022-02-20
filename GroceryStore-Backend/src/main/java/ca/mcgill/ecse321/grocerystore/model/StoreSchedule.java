package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;


// line 45 "model.ump"
// line 191 "model.ump"
public class StoreSchedule
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Day { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StoreSchedule Attributes
  private String openingTime;
  private String closingTime;
  private Day daysOpen;

  //StoreSchedule Associations
  private Store store;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StoreSchedule(String aOpeningTime, String aClosingTime, Day aDaysOpen, Store aStore)
  {
    openingTime = aOpeningTime;
    closingTime = aClosingTime;
    daysOpen = aDaysOpen;
    if (aStore == null || aStore.getStoreSchedule() != null)
    {
      throw new RuntimeException("Unable to create StoreSchedule due to aStore. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    store = aStore;
  }

  public StoreSchedule(String aOpeningTime, String aClosingTime, Day aDaysOpen, EmployeeSchedule aEmployeeScheduleForStore)
  {
    openingTime = aOpeningTime;
    closingTime = aClosingTime;
    daysOpen = aDaysOpen;
    store = new Store(this, aEmployeeScheduleForStore);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOpeningTime(String aOpeningTime)
  {
    boolean wasSet = false;
    openingTime = aOpeningTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setClosingTime(String aClosingTime)
  {
    boolean wasSet = false;
    closingTime = aClosingTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDaysOpen(Day aDaysOpen)
  {
    boolean wasSet = false;
    daysOpen = aDaysOpen;
    wasSet = true;
    return wasSet;
  }

  public String getOpeningTime()
  {
    return openingTime;
  }

  public String getClosingTime()
  {
    return closingTime;
  }

  public Day getDaysOpen()
  {
    return daysOpen;
  }
  /* Code from template association_GetOne */
  public Store getStore()
  {
    return store;
  }

  public void delete()
  {
    Store existingStore = store;
    store = null;
    if (existingStore != null)
    {
      existingStore.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "openingTime" + ":" + getOpeningTime()+ "," +
            "closingTime" + ":" + getClosingTime()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "daysOpen" + "=" + (getDaysOpen() != null ? !getDaysOpen().equals(this)  ? getDaysOpen().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "store = "+(getStore()!=null?Integer.toHexString(System.identityHashCode(getStore())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 52 "model.ump"
  1 -- 1 ;

  
}