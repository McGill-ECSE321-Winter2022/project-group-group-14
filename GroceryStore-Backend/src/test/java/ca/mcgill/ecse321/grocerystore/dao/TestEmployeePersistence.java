package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional //don't need clear database method
public class TestEmployeePersistence {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeScheduleRepository employeeScheduleRepository;
    
	/**
	 * @author Samuel Valentine
	 */
	@Test
	public void testPersistAndLoadEmployee(){
			
		//set values to Employee attributes
		Employee employee = new Employee();
		
		String email = "employee1@gmail.com";
		String username = "employee1";
		String password = "123abc";
		EmployeeSchedule employeeSchedule0 = new EmployeeSchedule(EmployeeSchedule.Shift.Afternoon, EmployeeSchedule.Day.Monday, employee);
		EmployeeSchedule employeeSchedule1 = new EmployeeSchedule(EmployeeSchedule.Shift.Morning, EmployeeSchedule.Day.Wednesday, employee);
		
	    employee.setEmail(email);
		employee.setUsername(username);
		employee.setPassword(password);
	
		employee.addEmployeeSchedule(employeeSchedule0);
		employee.addEmployeeSchedule(employeeSchedule1);
			
		//save EmployeeSchedule object
		employeeScheduleRepository.save(employeeSchedule0);
		employeeScheduleRepository.save(employeeSchedule1);
		
		//save Employee object
		employeeRepository.save(employee);
		
		
		//test saved Employee object
		Employee savedEmployee = employeeRepository.findByAccountId(employee.getAccountId());
		EmployeeSchedule savedEmployeeSchedule0 = employeeScheduleRepository.findById(employeeSchedule0.getId());
		EmployeeSchedule savedEmployeeSchedule1 = employeeScheduleRepository.findById(employeeSchedule1.getId());
		assertNotNull(savedEmployee);
		assertEquals(employee.getEmail(), savedEmployee.getEmail());
		assertEquals(employee.getPassword(), savedEmployee.getPassword());
		assertEquals(employee.getUsername(), savedEmployee.getUsername());
		
		//test first EmployeeSchedule Object
//		assertEquals(employee.getEmployeeSchedules().get(0).getDay(), savedEmployeeSchedule0.getDay());
//		assertEquals(employee.getEmployeeSchedules().get(0).getShift(), savedEmployeeSchedule0.getShift());
//		assertEquals(employee.getEmployeeSchedules().get(0).getId(), savedEmployeeSchedule0.getId());
//		
//		//test second EmployeeSchedule Object
//		assertEquals(employee.getEmployeeSchedules().get(1).getDay(), savedEmployeeSchedule1.getDay());
//		assertEquals(employee.getEmployeeSchedules().get(1).getShift(), savedEmployeeSchedule1.getShift());
//		assertEquals(employee.getEmployeeSchedules().get(1).getId(), savedEmployeeSchedule1.getId());
	}


}


