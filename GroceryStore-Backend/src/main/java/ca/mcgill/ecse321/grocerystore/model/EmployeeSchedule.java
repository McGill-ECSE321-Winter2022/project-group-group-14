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
  private Store store;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetEmployee;
  private boolean canSetStore;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EmployeeSchedule(Shift aShift, Employee aEmployee, Store aStore)
  {
    cachedHashCode = -1;
    canSetEmployee = true;
    canSetStore = true;
    shift = aShift;
    if (aEmployee == null || aEmployee.getEmployeeSchedule() != null)
    {
      throw new RuntimeException("Unable to create EmployeeSchedule due to aEmployee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    employee = aEmployee;
    if (aStore == null || aStore.getEmployeeSchedule() != null)
    {
      throw new RuntimeException("Unable to create EmployeeSchedule due to aStore. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    store = aStore;
  }

  public EmployeeSchedule(Shift aShift, Account aAccountForEmployee, StoreSchedule aStoreScheduleForStore)
  {
    shift = aShift;
    employee = new Employee(aAccountForEmployee, this);
    store = new Store(aStoreScheduleForStore, this);
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
  public Store getStore()
  {
    return store;
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

    if (getStore() == null && compareTo.getStore() != null)
    {
      return false;
    }
    else if (getStore() != null && !getStore().equals(compareTo.getStore()))
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
    if (getStore() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getStore().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetEmployee = false;
    canSetStore = false;
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
    Store existingStore = store;
    store = null;
    if (existingStore != null)
    {
      existingStore.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "shift" + "=" + (getShift() != null ? !getShift().equals(this)  ? getShift().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "employee = "+(getEmployee()!=null?Integer.toHexString(System.identityHashCode(getEmployee())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "store = "+(getStore()!=null?Integer.toHexString(System.identityHashCode(getStore())):"null");
  }
}