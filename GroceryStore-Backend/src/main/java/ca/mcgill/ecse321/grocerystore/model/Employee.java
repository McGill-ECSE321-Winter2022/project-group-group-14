package ca.mcgill.ecse321.grocerystore.model;


import javax.persistence.Entity;


@Entity
public class Employee extends Account
{

  
  public Employee(String aEmail, String aUsername, String aPassword)
  {
    super(aEmail, aUsername, aPassword);
  }

  public Employee()
  {
    super();
    
  }
  public void delete()
  {
    super.delete();
  }

}