package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule;

public interface EmployeeScheduleRepository extends CrudRepository <EmployeeSchedule, Integer >{
    EmployeeSchedule findById(int id);
    //List<EmployeeSchedule> findByEmployee(Employee employee);
    //maybe find by day?
}

