package ca.mcgill.ecse321.grocerystore.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Day;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Shift;

import java.util.ArrayList;


@Entity
@DiscriminatorValue("Employee")
public class Employee extends Account
{
  private List<EmployeeSchedule> employeeSchedules;

  
  public Employee(String aEmail, String aUsername, String aPassword)
  {
    super(aEmail, aUsername, aPassword);
    employeeSchedules = new ArrayList<EmployeeSchedule>();
    
  }

  public Employee()
  {
    super();
    employeeSchedules = new ArrayList<EmployeeSchedule>(); 
  }
  
  public void addEmployeeSchedule(EmployeeSchedule sch) {
	  this.employeeSchedules.add(sch);
  }
  
  
  
  @OneToMany(cascade={CascadeType.ALL})
  public List<EmployeeSchedule> getEmployeeSchedules()
  {
    return this.employeeSchedules;
  }
  
  public void setEmployeeSchedules(List<EmployeeSchedule> employeeSchedules) {
      this.employeeSchedules = employeeSchedules;
  }

 

  
  public String toString()
  {
      return super.toString();
  }

}