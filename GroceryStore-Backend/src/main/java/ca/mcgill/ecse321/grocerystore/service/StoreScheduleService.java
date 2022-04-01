package ca.mcgill.ecse321.grocerystore.service;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.dao.StoreScheduleRepository;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule.Day;


@Service
public class StoreScheduleService {

	@Autowired
	StoreScheduleRepository storeScheduleRepo;

	
	/**
	* @author Yakir Bender 
	**/
	/** Creates and saves a StoreSchedule to the repository. **/
	@Transactional
	public StoreSchedule createStoreSchedule(Time openingTime, Time closingTime, Day dayOpen) {
    	StoreSchedule storeSchedule = storeScheduleRepo.findByDayOpen(dayOpen);
        if (storeSchedule != null) throw new IllegalArgumentException(dayOpen.toString() + " already exists");
		
		ServiceHelpers.checkTimeValidity(openingTime, closingTime);
		ServiceHelpers.checkDayValidity(dayOpen);
		
		storeSchedule = new StoreSchedule(openingTime, closingTime, dayOpen);

		storeScheduleRepo.save(storeSchedule);
		
		return storeSchedule;
	}

	/**
	* @author Yakir Bender 
	**/
	/** Returns all existing StoreSchedule objects **/
	@Transactional
	public List<StoreSchedule> getAllStoreSchedules() {
		return ServiceHelpers.toList(storeScheduleRepo.findAll());
	}
	
	/**
	* @author Yakir Bender 
	**/
	/** Uses the storeScheduleId attribute to get a StoreSchedule from the repository. **/
	@Transactional
	public StoreSchedule getStoreScheduleById(int id) {
		return storeScheduleRepo.findByStoreScheduleId(id);
	}
	
	/**
	* @author Yakir Bender 
	**/
	/** Uses the dayOpen attribute to get a StoreSchedule from the repository. **/
	@Transactional
	public StoreSchedule getStoreScheduleByDayOpen(Day dayOpen) {
		return storeScheduleRepo.findByDayOpen(dayOpen);
	}

	/**
	* @author Yakir Bender 
	**/
	/** Updates StoreSchedule information. **/
    @Transactional
    public StoreSchedule updateStoreScheduleInfo(Day dayOpen, Time openingTime, Time closingTime)
    {
    	//check that store schedule times are valid
        ServiceHelpers.checkTimeValidity(openingTime, closingTime);
        
    	//check that store schedule day is valid
        ServiceHelpers.checkDayValidity(dayOpen);
        
        //update existing inventory item info with the new ones
        StoreSchedule newStoreSchedule = storeScheduleRepo.findByDayOpen(dayOpen);
        if (newStoreSchedule == null) throw new IllegalArgumentException("This Store Schedule does not exist.");
        newStoreSchedule.setOpeningTime(openingTime);
        newStoreSchedule.setClosingTime(closingTime);
        newStoreSchedule.setDayOpen(dayOpen);
        
        //save new changes to the repository
        storeScheduleRepo.save(newStoreSchedule);
        
        return newStoreSchedule;
    }
	
	/**
	* @author Yakir Bender 
	**/
    /** Deletes StoreSchedule from the database. **/
	@Transactional
	public StoreSchedule deleteStoreSchedule(StoreSchedule storeSchedule) {
		storeScheduleRepo.delete(storeSchedule);
		return storeSchedule;
	}

}