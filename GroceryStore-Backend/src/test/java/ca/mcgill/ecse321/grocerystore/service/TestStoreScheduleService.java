package ca.mcgill.ecse321.grocerystore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

import java.sql.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.grocerystore.dao.GroceryStoreRepository;
import ca.mcgill.ecse321.grocerystore.dao.StoreScheduleRepository;
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
	
	@Test
	public void testCreateStoreSchedule() {
		Time openingTime = OPEN_TIME;
		Time closingTime = CLOSE_TIME;
		Day dayOpen = DAY_OPEN;
		StoreSchedule storeSchedule = null;
		try {
			storeSchedule = storeScheduleService.createStoreSchedule(openingTime, closingTime, dayOpen);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(storeSchedule);
		checkResultStoreSchedule(storeSchedule, openingTime, closingTime, dayOpen);
	}
	
	private void checkResultStoreSchedule(StoreSchedule storeSchedule, Time openingTime, Time closingTime,Day dayOpen) {
		assertNotNull(storeSchedule);
		assertEquals(openingTime.toString(), storeSchedule.getOpeningTime().toString());
		assertEquals(closingTime.toString(), storeSchedule.getClosingTime().toString());
		assertEquals(dayOpen.toString(), storeSchedule.getDayOpen().toString());
	}
	

	@Test
	public void testTimeNull() {
		Time openingTime = null;
		Time closingTime = CLOSE_TIME;
		Day dayOpen = DAY_OPEN;
		StoreSchedule storeSchedule = null;
		
		String error = null;
		try {
			storeSchedule = storeScheduleService.createStoreSchedule(openingTime, closingTime, dayOpen);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(storeSchedule);
		// check error
		assertEquals("Please input a valid start and end time.", error);
	}
	
	@Test
	public void testDayNull() {
		Time openingTime = OPEN_TIME;
		Time closingTime = CLOSE_TIME;
		Day dayOpen = null;
		StoreSchedule storeSchedule = null;
		
		String error = null;
		try {
			storeSchedule = storeScheduleService.createStoreSchedule(openingTime, closingTime, dayOpen);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(storeSchedule);
		// check error
		assertEquals("Please input a valid day.", error);
	}
	
	
	@Test
	public void testClosingTimeBeforeOpeningTime() {
		Time openingTime = CLOSE_TIME;
		Time closingTime = OPEN_TIME;
		Day dayOpen = DAY_OPEN;
		StoreSchedule storeSchedule = null;
		
		String error = null;
		try {
			storeSchedule = storeScheduleService.createStoreSchedule(openingTime, closingTime, dayOpen);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(storeSchedule);
		// check error
		assertEquals("Your start time cannot be after your end time.", error);
	}
	
/*    @Test
    public void testGetAllSchedules()
    {
        ArrayList <StoreSchedule> storeSchedules = null;
        storeScheduleService.createStoreSchedule(OPEN_TIME, CLOSE_TIME, DAY_OPEN);
        
        try
        {
            storeSchedules = new ArrayList<>(storeScheduleService.getAllStoreSchedules());
        } catch (IllegalArgumentException e)
        {
            fail(e.getMessage());
        }
        assertNotNull(storeSchedules);
        assertNotEquals(0, storeSchedules.size());
        assertEquals(OPEN_TIME, storeSchedules.get(0).getOpeningTime());
        assertEquals(CLOSE_TIME, storeSchedules.get(0).getClosingTime());
        assertEquals(DAY_OPEN, storeSchedules.get(0).getDayOpen());
    } 
    */
    
    @Test
    public void testDelete()
    {
    	StoreSchedule storeSchedule = storeScheduleService.createStoreSchedule(OPEN_TIME, CLOSE_TIME, DAY_OPEN);
        StoreSchedule deleted = null;
        try
        {
            deleted = storeScheduleService.deleteStoreSchedule(storeSchedule);
        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNotNull(deleted);
        assertEquals(storeSchedule.getOpeningTime(), deleted.getOpeningTime());
        assertEquals(storeSchedule.getClosingTime(), deleted.getClosingTime());
        assertEquals(storeSchedule.getDayOpen(), deleted.getDayOpen());
    }
    
    @Test
    public void testDeleteNull()
    {

        StoreSchedule deleted = null;
        try
        {
            deleted = storeScheduleService.deleteStoreSchedule(null);
        } catch (IllegalArgumentException e)
        {
            fail();
        }
        assertNull(deleted);
    }
	
}