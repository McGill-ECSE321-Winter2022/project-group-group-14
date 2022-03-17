package ca.mcgill.ecse321.grocerystore.service;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.dao.EmployeeScheduleRepository;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Shift;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Day;


@Service
public class EmployeeScheduleService {

	@Autowired
	EmployeeScheduleRepository employeeScheduleRepo;

	
	/**
	* @author Harry Park
	**/
	/** Creates and saves a EmployeeSchedule to the repository. **/
	@Transactional
	public EmployeeSchedule createEmployeeSchedule(Shift shift, Day day) {
		ServiceHelpers.checkShiftValidity(shift);
		checkDayValidity(day);
		EmployeeSchedule employeeSchedule = new EmployeeSchedule(shift, day);

		employeeScheduleRepo.save(employeeSchedule);
		
		return employeeSchedule;
	}

	/**
	* @author Harry Park 
	**/
	/** Returns all existing EmployeeSchedule objects **/
	@Transactional
	public List<EmployeeSchedule> getAllEmployeeSchedules() {
		return ServiceHelpers.toList(employeeScheduleRepo.findAll());
	}
	
	/**
	* @author Harry Park
	**/
	/** Uses the employeeScheduleId attribute to get an EmployeeSchedule from the repository. **/
	@Transactional
	public EmployeeSchedule getEmployeeScheduleById(int id) {
		return employeeScheduleRepo.findById(id);
	}
	
	/**
	* @author Harry Park
	**/
	/** Uses the shift attribute to get an EmployeeSchedule from the repository. **/
	@Transactional
	public EmployeeSchedule getEmployeeScheduleByShift(Shift shift) {
		return employeeScheduleRepo.findByShift(shift);
	}
	
	/**
	* @author Harry Park
	**/
	/** Uses the day attribute to get an EmployeeSchedule from the repository. **/
	@Transactional
	public EmployeeSchedule getEmployeeScheduleByDay(Day day) {
		return employeeScheduleRepo.findByDay(day);
	}


	/**
	* @author Harry Park
	**/
	/** Updates StoreSchedule information. **/
    @Transactional
    public EmployeeSchedule updateEmmployeeScheduleInfo(EmployeeSchedule employeeSchedule)
    {
    	//check that store schedule times are valid
        ServiceHelpers.checkShiftValidity(employeeSchedule.getShift());
        
    	//check that store schedule day is valid
        EmployeeScheduleService.checkDayValidity(employeeSchedule.getDay());
        
        //
        EmployeeSchedule newEmployeeSchedule = employeeScheduleRepo.findById(employeeSchedule.getId());
        if (newEmployeeSchedule == null) throw new IllegalArgumentException("This Store Schedule does not exist.");
        newEmployeeSchedule.setShift(employeeSchedule.getShift());
        newEmployeeSchedule.setDay(employeeSchedule.getDay());
        
        //save new changes to the repository
        employeeScheduleRepo.save(newEmployeeSchedule);
        
        return employeeSchedule;
    }
	
	/**
	* @author Harry Park 
	**/
    /** Deletes EmployeeSchedule from the database. **/
	@Transactional
	public EmployeeSchedule deleteEmployeeSchedule(EmployeeSchedule employeeSchedule) {
		employeeScheduleRepo.delete(employeeSchedule);
		return employeeSchedule;
	}
	
	public static void checkDayValidity(Day day) 
    {
    	if (day == null)
    			throw new IllegalArgumentException("Please input a valid day");
    	
    	boolean isValidDay = false;
    	for (Day listedDay : Day.values()) {
    		if (listedDay.equals(day)) {
    			isValidDay = true;
    			break;
    		}
    	}
    	if(!isValidDay)
    		throw new IllegalArgumentException("Please input a valid day");
    }

}

