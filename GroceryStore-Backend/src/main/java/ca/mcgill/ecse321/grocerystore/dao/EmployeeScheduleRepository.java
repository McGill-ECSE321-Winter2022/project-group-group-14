package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Day;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Shift;

public interface EmployeeScheduleRepository extends CrudRepository <EmployeeSchedule, Integer >{
    EmployeeSchedule findById(int id);
    EmployeeSchedule findByShift(Shift shift);
    EmployeeSchedule findByDay(Day day);
	EmployeeSchedule findByEmployee(Employee employee);
}

