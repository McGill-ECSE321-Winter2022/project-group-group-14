package ca.mcgill.ecse321.grocerystore.dto;

import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Day;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Shift;

public class EmployeeScheduleDto {
	
	private Shift shift;
	private Day day;
	private Employee employee;
	
	//Empty constructor
	public EmployeeScheduleDto() {
		
	}
	
	public EmployeeScheduleDto(Shift ashift, Day aday) {
		this.shift = ashift;
		this.day = aday;
		this.employee = null;
	}
	
	public EmployeeScheduleDto(Shift ashift, Day aday, Employee aemployee) {
		this.shift = ashift;
		this.day = aday;
		this.employee = aemployee;
	}
	
	public Shift getShift() {
		return this.shift;
	}
	
	public Day getDay() {
		return this.day;
	}
	
	public Employee getEmployee() {
		return this.employee;
	}
	
	public void setShift(Shift shift) {
		this.shift = shift;
	}
	
	public void setDay(Day day) {
		this.day = day;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}



