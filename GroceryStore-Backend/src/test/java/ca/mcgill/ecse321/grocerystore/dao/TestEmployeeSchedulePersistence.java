package ca.mcgill.ecse321.grocerystore.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Day;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Shift;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional //don't need clear database method
public class TestEmployeeSchedulePersistence {

	@Autowired
	private EmployeeScheduleRepository employeeScheduleRepository;

	
	//test EmployeeSchedule persistence
	@Test
	public void testPersistAndLoadEmployeeSchedule(){
		
		EmployeeSchedule employeeSchedule = new EmployeeSchedule(null, null);
		
		//set employee schedule variables for test
		Shift ashift = Shift.Morning;
		Day aday = Day.Sunday;
		
		//set values to employee schedule
		employeeSchedule.setShift(ashift);
		employeeSchedule.setDay(aday);
		
		//save employee schedule object
		employeeScheduleRepository.save(employeeSchedule);
		
		//test saved Employee Schedule object
		EmployeeSchedule savedEmployeeSchedule = employeeScheduleRepository.findById(employeeSchedule.getId());
		
		assertNotNull(savedEmployeeSchedule);
		assertEquals(employeeSchedule.getShift(), savedEmployeeSchedule.getShift());
		assertEquals(employeeSchedule.getDay(), savedEmployeeSchedule.getDay());
	
	}
	
}	

