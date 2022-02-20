package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;


// line 26 "model.ump"
// line 136 "model.ump"
public class Employee extends AccountType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Associations
  private EmployeeSchedule employeeSchedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(Account aAccount, EmployeeSchedule aEmployeeSchedule)
  {
    super(aAccount);
    if (aEmployeeSchedule == null || aEmployeeSchedule.getEmployee() != null)
    {
      throw new RuntimeException("Unable to create Employee due to aEmployeeSchedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    employeeSchedule = aEmployeeSchedule;
  }

  public Employee(Account aAccount, Shift aShiftForEmployeeSchedule, Store aStoreForEmployeeSchedule)
  {
    super(aAccount);
    employeeSchedule = new EmployeeSchedule(aShiftForEmployeeSchedule, this, aStoreForEmployeeSchedule);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public EmployeeSchedule getEmployeeSchedule()
  {
    return employeeSchedule;
  }

  public void delete()
  {
    EmployeeSchedule existingEmployeeSchedule = employeeSchedule;
    employeeSchedule = null;
    if (existingEmployeeSchedule != null)
    {
      existingEmployeeSchedule.delete();
    }
    super.delete();
  }

}