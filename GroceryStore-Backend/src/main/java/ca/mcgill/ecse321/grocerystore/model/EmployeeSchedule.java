package ca.mcgill.ecse321.grocerystore.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
  private Employee employee;

  


  private int id;

  //constructors
  public EmployeeSchedule(Shift aShift, Day aDay, Employee aEmployee)
  {
	this.day = aDay;
    this.shift = aShift;
    this.employee = aEmployee;
  }

  
  public EmployeeSchedule()
  {
    this.shift = null;
    this.day = null;  
    this.employee = null;
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
  
  @ManyToOne
  public Employee getEmployee()
  {
	  return this.employee;
  }
  public void setEmployee(Employee aEmployee)
  {
	  this.employee = aEmployee;
  }

 

  public String toString()
  {
    return super.toString() + "["+
            "shift" + ":" + getShift()+ "," +
            "day" + ":" + getDay()+ "]" + System.getProperties().getProperty("line.separator");
  }
}