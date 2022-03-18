package ca.mcgill.ecse321.grocerystore.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class EmployeeSchedule
{
  //enum
  public enum Shift { Morning, Afternoon, Night }
  public enum Day { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }

  //Attributes
  private Shift shift;
  private Day day;


  private int id;

  //constructors
  public EmployeeSchedule(Shift aShift,Day aDay)
  {
   
	this.day = aDay;
    shift = aShift;
  }

  
  public EmployeeSchedule()
  {
    this.shift = null;
    this.day = null;
    
  }

  //------------------------
  // INTERFACE
  //------------------------

  
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  public int getId() {
	  return this.id;
  }
  public void setId(int id) {
	  this.id = id;
  }
  
  public Shift getShift()
  {
    return shift;
  }
  public void setShift(Shift aShift)
  {
    this.shift = aShift;
  }
  
  public Day getDay()
  {
    return this.day;
  }
  public void setDay(Day aDay)
  {
    this.day = aDay;
  }
  

 

  public String toString()
  {
    return super.toString() + "["+
            "shift" + ":" + getShift()+ "," +
            "day" + ":" + getDay()+ "]" + System.getProperties().getProperty("line.separator");
  }
}