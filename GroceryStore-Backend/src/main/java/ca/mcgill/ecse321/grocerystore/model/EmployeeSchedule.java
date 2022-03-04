package ca.mcgill.ecse321.grocerystore.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

  //Associations
  private Employee employee;
  private int id;

  //constructors
  public EmployeeSchedule(Shift aShift,Day aDay, Employee aEmployee)
  {
   
	this.day = aDay;
    shift = aShift;
    if (aEmployee == null)
    {
      throw new RuntimeException("Unable to create EmployeeSchedule due to aEmployee.");
    }
    employee = aEmployee;
  }
  
  public EmployeeSchedule()
  {
    this.shift = null;
    this.employee = null;
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
  
  
  @ManyToOne
  @JoinColumn(name = "employee_id")
  public Employee getEmployee()     
  {
    return employee;
  }

  
  public void setEmployee(Employee employee)     
  {
    this.employee = employee;
  }
  
  public void delete()
  {
    Employee existingEmployee = employee;
    employee = null;
    if (existingEmployee != null)
    {
      existingEmployee.delete();
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "shift" + ":" + getShift()+ "," +
            "day" + ":" + getDay()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "employee = "+(getEmployee()!=null?Integer.toHexString(System.identityHashCode(getEmployee())):"null");
  }
}