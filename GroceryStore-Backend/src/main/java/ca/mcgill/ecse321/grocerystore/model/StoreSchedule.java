package ca.mcgill.ecse321.grocerystore.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;


@Entity
public class StoreSchedule
{

	
  // ENUMERATIONS
  
  public enum Day { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }

  
  // MEMBER VARIABLES

  //StoreSchedule Attributes
  private Time openingTime;
  private Time closingTime;
  private Day daysOpen;
  private int id;

  
  // CONSTRUCTOR
  
  public StoreSchedule(Time aOpeningTime, Time aClosingTime, Day aDaysOpen)
  {
    openingTime = aOpeningTime;
    closingTime = aClosingTime;
    daysOpen = aDaysOpen;
    
  }


  // INTERFACE
  @Id
  public int getId() {
	  return this.id;
  }


  public boolean setOpeningTime(Time aOpeningTime)
  {
    boolean wasSet = false;
    openingTime = aOpeningTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setClosingTime(Time aClosingTime)
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

  public Time getOpeningTime()
  {
    return openingTime;
  }

  public Time getClosingTime()
  {
    return closingTime;
  }

  public Day getDaysOpen()
  {
    return daysOpen;
  }
  
  
  public void delete()
  {
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "openingTime" + ":" + getOpeningTime()+ "," +
            "closingTime" + ":" + getClosingTime()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "daysOpen" + "=" + (getDaysOpen() != null ? !getDaysOpen().equals(this)  ? getDaysOpen().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator");
  } 
  
}