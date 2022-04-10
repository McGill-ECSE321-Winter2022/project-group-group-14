package ca.mcgill.ecse321.grocerystore.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.grocerystore.dao.EmployeeScheduleRepository;
import ca.mcgill.ecse321.grocerystore.dao.GroceryStoreRepository;
import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Day;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Shift;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeScheduleService {
	
	
	@Mock
	private GroceryStoreRepository groceryStoreDao;
	@Mock 
	private EmployeeScheduleRepository employeeScheduleDao;

	@InjectMocks 
	private EmployeeScheduleService employeeScheduleService;

	private final Day DAY = Day.Monday;
	private final Shift SHIFT = Shift.Morning;
	private final String EMPLOYEE = null;
	

	@BeforeEach
	public void setMockOutput() {
	    lenient().when(employeeScheduleDao.findByShift(any(Shift.class))).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(SHIFT)) {
	            EmployeeSchedule employeeSchedule = new EmployeeSchedule();
	            employeeSchedule.setShift(SHIFT);
	            return employeeSchedule;
	        } else {
	            return null;
	        }
	    });
	    
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(employeeScheduleDao.save(any(EmployeeSchedule.class))).thenAnswer(returnParameterAsAnswer);
	}
	
//	@Test
//	public void testCreateEmployeeSchedule() {
//		Day day = DAY;
//		Shift shift = SHIFT;
//		String employee = "employee";
//		EmployeeSchedule employeeSchedule = null;
//		try {
//			employeeSchedule = employeeScheduleService.createEmployeeSchedule(shift, day, employee);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//		assertNotNull(employeeSchedule);
//		checkResultEmployeeSchedule(employeeSchedule, day, shift);
//	}
//	
//	private void checkResultEmployeeSchedule(EmployeeSchedule employeeSchedule, Day day, Shift shift) {
//		assertNotNull(employeeSchedule);
//		assertEquals(day.toString(), employeeSchedule.getDay().toString());
//		assertEquals(shift.toString(), employeeSchedule.getShift().toString());
//	}
	

//	@Test
//	public void testDayNull() {
//		Day day = null;
//		Shift shift = SHIFT;
//		String employee = "employee";
//		EmployeeSchedule employeeSchedule = null;
//		
//		String error = null;
//		try {
//			employeeSchedule = employeeScheduleService.createEmployeeSchedule(shift, day, employee);
//		} catch (IllegalArgumentException e) {
//			error = e.getMessage();
//		}
//
//		assertNull(employeeSchedule);
//		// check error
//		assertEquals("Please input a valid day", error);
//	}
	
//	@Test
//	public void testShiftNull() {
//		Day day = DAY;
//		Shift shift = null;
//		String employee = "employee";
//		EmployeeSchedule employeeSchedule = null;
//		
//		String error = null;
//		try {
//			employeeSchedule = employeeScheduleService.createEmployeeSchedule(shift, day, employee);
//		} catch (IllegalArgumentException e) {
//			error = e.getMessage();
//		}
//		System.out.println(error);
//
//		assertNull(employeeSchedule);
//		// check error
//		assertEquals("Please input a valid shift.", error);
//	}
//	
    
//    @Test
//    public void testDelete()
//    {
//    	EmployeeSchedule employeeSchedule = employeeScheduleService.createEmployeeSchedule(SHIFT, DAY, EMPLOYEE);
//        EmployeeSchedule deleted = null;
//        try
//        {
//            deleted = employeeScheduleService.deleteEmployeeSchedule(employeeSchedule);
//        } catch (IllegalArgumentException e)
//        {
//            fail();
//        }
//        assertNotNull(deleted);
//        assertEquals(employeeSchedule.getShift(), deleted.getShift());
//        assertEquals(employeeSchedule.getDay(), deleted.getDay());
//    }
    
    @Test
    public void testDeleteNull()
    {

        EmployeeSchedule deleted = null;
        try
        {
            deleted = employeeScheduleService.deleteEmployeeSchedule(null);
        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(deleted);
    }
	
}


