package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;


import ca.mcgill.ecse321.grocerystore.model.Employee;

public interface EmployeeRepository extends CrudRepository <Employee, Integer> {
	Employee findByAccountId (int accountId);
	Employee findByEmail (String email);
    Employee findByUsername(String username);
    
   
}
