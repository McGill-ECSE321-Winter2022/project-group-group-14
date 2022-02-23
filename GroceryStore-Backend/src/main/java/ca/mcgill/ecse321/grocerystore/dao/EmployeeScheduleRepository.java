package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule;

public interface EmployeeScheduleRepository extends CrudRepository <EmployeeSchedule, Integer >{
    EmployeeSchedule findById(int id);
    EmployeeSchedule findByEmployee (Employee employee);
    //find by employee
}

