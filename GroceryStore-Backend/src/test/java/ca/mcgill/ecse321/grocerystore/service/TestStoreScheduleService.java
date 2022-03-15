package ca.mcgill.ecse321.grocerystore.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.grocerystore.dao.CustomerRepository;
import ca.mcgill.ecse321.grocerystore.dao.GroceryStoreRepository;
import ca.mcgill.ecse321.grocerystore.dao.StoreScheduleRepository;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule.Day;


@ExtendWith(MockitoExtension.class)
public class TestStoreScheduleService {
	
	@Mock
	private GroceryStoreRepository groceryStoreDao;
	@Mock 
	private StoreScheduleRepository storeScheduleDao;

	@InjectMocks 
	private StoreScheduleService storeScheduleService;

	private static final Day DAY_OPEN = Day.Monday;
	@SuppressWarnings("deprecation")
	private static final Time OPEN_TIME = new Time(8, 30, 0);
	@SuppressWarnings("deprecation")
	private static final Time CLOSE_TIME = new Time(18, 0, 0);
	
	private static final String NONEXISTING_EMAIL = "NotACustomer";

	@BeforeEach
	public void setMockOutput() {
	    lenient().when(storeScheduleDao.findByDayOpen(any(Day.class))).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(DAY_OPEN)) {
	            StoreSchedule storeSchedule = new StoreSchedule();
	            storeSchedule.setDayOpen(DAY_OPEN);
	            return storeSchedule;
	        } else {
	            return null;
	        }
	    });
	    
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(storeScheduleDao.save(any(StoreSchedule.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	
}