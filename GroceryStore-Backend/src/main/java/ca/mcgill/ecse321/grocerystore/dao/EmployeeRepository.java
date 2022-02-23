package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.Employee;

public interface EmployeeRepository extends CrudRepository <Employee, String> {
    Employee findByEmail (String email);
    Employee findByName (String name);                 //why do we need name?
    Employee findByUsername(String username);   
   
}