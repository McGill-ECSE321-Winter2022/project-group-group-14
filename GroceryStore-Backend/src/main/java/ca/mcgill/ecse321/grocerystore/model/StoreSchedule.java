package ca.mcgill.ecse321.grocerystore.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;


@Entity
public class StoreSchedule
{
  // ENUMERATIONS
  
  public enum Day { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }

  private int storeScheduleId;
  private Time openingTime;
  private Time closingTime;
  private Day dayOpen;

  
  public StoreSchedule(Time aOpeningTime, Time aClosingTime, Day aDayOpen)
  {
    this.openingTime = aOpeningTime;
    this.closingTime = aClosingTime;
    this.dayOpen = aDayOpen;
    
  }
  public StoreSchedule()
  {
    this.openingTime = null;
    this.closingTime = null;
    this.dayOpen = null;
  }
  
  // INTERFACE
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  public int getStoreScheduleId() {
	  return this.storeScheduleId;
  }
  
  public void setStoreScheduleId(int id) {
	  this.storeScheduleId=id;
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

  public boolean setDayOpen(Day aDayOpen)
  {
    boolean wasSet = false;
    dayOpen = aDayOpen;
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

  public Day getDayOpen()
  {
    return dayOpen;
  }

  
  public String toString()
  {
    return super.toString() + "["+
            "openingTime" + ":" + getOpeningTime()+ "," +
            "closingTime" + ":" + getClosingTime()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "daysOpen" + "=" + (getDayOpen() != null ? !getDayOpen().equals(this)  ? getDayOpen().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator");
  } 
  
}
