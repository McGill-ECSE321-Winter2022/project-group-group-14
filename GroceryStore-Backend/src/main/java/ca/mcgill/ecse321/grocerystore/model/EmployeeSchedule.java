package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;


// line 73 "model.ump"
// line 197 "model.ump"
public class EmployeeSchedule
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Shift { Morning, Afternoon, Night }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EmployeeSchedule Attributes
  private Shift shift;

  //EmployeeSchedule Associations
  private Employee employee;
  private StoreSchedule storeSchedule;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetEmployee;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EmployeeSchedule(Shift aShift, Employee aEmployee)
  {
    cachedHashCode = -1;
    canSetEmployee = true;
    
    shift = aShift;
    if (aEmployee == null || aEmployee.getEmployeeSchedule() != null)
    {
      throw new RuntimeException("Unable to create EmployeeSchedule due to aEmployee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    employee = aEmployee;
    
  }

  public EmployeeSchedule(Shift aShift, Account aAccountForEmployee)
  {
    shift = aShift;
    employee = new Employee(aAccountForEmployee, this);
    
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setShift(Shift aShift)
  {
    boolean wasSet = false;
    shift = aShift;
    wasSet = true;
    return wasSet;
  }

  public Shift getShift()
  {
    return shift;
  }
  /* Code from template association_GetOne */
  public Employee getEmployee()
  {
    return employee;
  }
  /* Code from template association_GetOne */
  public StoreSchedule getStoreSchedule()
  {
    return storeSchedule;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    EmployeeSchedule compareTo = (EmployeeSchedule)obj;
  
    if (getEmployee() == null && compareTo.getEmployee() != null)
    {
      return false;
    }
    else if (getEmployee() != null && !getEmployee().equals(compareTo.getEmployee()))
    {
      return false;
    }

    if (getStoreSchedule() == null && compareTo.getStoreSchedule() != null)
    {
      return false;
    }
    else if (getStoreSchedule() != null && !getStoreSchedule().equals(compareTo.getStoreSchedule()))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (getEmployee() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getEmployee().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getStoreSchedule() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getStoreSchedule().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetEmployee = false;
    return cachedHashCode;
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
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "shift" + "=" + (getShift() != null ? !getShift().equals(this)  ? getShift().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "employee = "+(getEmployee()!=null?Integer.toHexString(System.identityHashCode(getEmployee())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "store = "+(getStoreSchedule()!=null?Integer.toHexString(System.identityHashCode(getStoreSchedule())):"null");
  }
}