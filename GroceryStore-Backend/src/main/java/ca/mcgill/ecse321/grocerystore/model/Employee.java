package ca.mcgill.ecse321.grocerystore.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Day;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Shift;

import java.util.ArrayList;


@Entity
@DiscriminatorValue("Employee")
public class Employee extends Account
{
  private List<EmployeeSchedule> employeeSchedules;

  
  public Employee(String aEmail, String aUsername, String aPassword, GroceryStore aGroceryStore)
  {
    super(aEmail, aUsername, aPassword,aGroceryStore);
    employeeSchedules = new ArrayList<EmployeeSchedule>();
    
  }

  public Employee()
  {
    super();
    employeeSchedules = new ArrayList<EmployeeSchedule>(); 
  }
  
  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public EmployeeSchedule getEmployeeSchedule(int index)
  {
    EmployeeSchedule aEmployeeSchedule = employeeSchedules.get(index);
    return aEmployeeSchedule;
  }
  
  @OneToMany(cascade={CascadeType.ALL}, mappedBy = "employee")
  public List<EmployeeSchedule> getEmployeeSchedules()
  {
    return this.employeeSchedules;
  }
  
  public void setEmployeeSchedules(List<EmployeeSchedule> employeeSchedules) {
      this.employeeSchedules = employeeSchedules;
  }

  public int numberOfEmployeeSchedules()
  {
    int number = employeeSchedules.size();
    return number;
  }

  public boolean hasEmployeeSchedules()
  {
    boolean has = employeeSchedules.size() > 0;
    return has;
  }

  public int indexOfEmployeeSchedule(EmployeeSchedule aEmployeeSchedule)
  {
    int index = employeeSchedules.indexOf(aEmployeeSchedule);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEmployeeSchedules()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public EmployeeSchedule addEmployeeSchedule(Shift aShift, Day aDay)
  {
    return new EmployeeSchedule(aShift, aDay, this);
  }

  public boolean addEmployeeSchedule(EmployeeSchedule aEmployeeSchedule)
  {
    boolean wasAdded = false;
    if (employeeSchedules.contains(aEmployeeSchedule)) { return false; }
    Employee existingEmployee = aEmployeeSchedule.getEmployee();
    boolean isNewEmployee = existingEmployee != null && !this.equals(existingEmployee);
    if (isNewEmployee)
    {
      aEmployeeSchedule.setEmployee(this);
    }
    else
    {
      employeeSchedules.add(aEmployeeSchedule);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmployeeSchedule(EmployeeSchedule aEmployeeSchedule)
  {
    boolean wasRemoved = false;
    //Unable to remove aEmployeeSchedule, as it must always have a employee
    if (!this.equals(aEmployeeSchedule.getEmployee()))
    {
      employeeSchedules.remove(aEmployeeSchedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEmployeeScheduleAt(EmployeeSchedule aEmployeeSchedule, int index)
  {  
    boolean wasAdded = false;
    if(addEmployeeSchedule(aEmployeeSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployeeSchedules()) { index = numberOfEmployeeSchedules() - 1; }
      employeeSchedules.remove(aEmployeeSchedule);
      employeeSchedules.add(index, aEmployeeSchedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmployeeScheduleAt(EmployeeSchedule aEmployeeSchedule, int index)
  {
    boolean wasAdded = false;
    if(employeeSchedules.contains(aEmployeeSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployeeSchedules()) { index = numberOfEmployeeSchedules() - 1; }
      employeeSchedules.remove(aEmployeeSchedule);
      employeeSchedules.add(index, aEmployeeSchedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmployeeScheduleAt(aEmployeeSchedule, index);
    }
    return wasAdded;
  }
  
  
  public void delete()
  {
    while (employeeSchedules.size() > 0)
    {
      EmployeeSchedule aEmployeeSchedule = employeeSchedules.get(employeeSchedules.size() - 1);
      aEmployeeSchedule.delete();
      employeeSchedules.remove(aEmployeeSchedule);
    }
    super.delete();
  }
  
  public String toString()
  {
      return super.toString();
  }

}