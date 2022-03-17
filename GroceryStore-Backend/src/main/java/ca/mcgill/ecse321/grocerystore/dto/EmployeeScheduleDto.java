package ca.mcgill.ecse321.grocerystore.dto;

import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Day;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Shift;

public class EmployeeScheduleDto {
	
	private Shift shift;
	private Day day;
	
	//Empty constructor
	public EmployeeScheduleDto() {
		
	}
	
	public EmployeeScheduleDto(Shift ashift, Day aday) {
		this.shift = ashift;
		this.day = aday;
	}
	
	public Shift getShift() {
		return this.shift;
	}
	
	public Day getDay() {
		return this.day;
	}
	
	public void setShift(Shift shift) {
		this.shift = shift;
	}
	
	public void setDay(Day day) {
		this.day = day;
	}

}



