package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Time;

import ca.mcgill.ecse321.grocerystore.model.StoreSchedule.Day;

public class StoreScheduleDto {
	
	private Time openingTime;
	private Time closingTime;
	private Day dayOpen;
	
	public StoreScheduleDto() {
		
	}
	
	public StoreScheduleDto(Time openingTime, Time closingTime, Day dayOpen) {
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.dayOpen = dayOpen;
	}
	
	public Time getOpeningTime() {
		return openingTime;
	}

	public Time getClosingTime() {
		return closingTime;
	}

	public Day getDayOpen() {
		return dayOpen;
	}
	
	public void setOpeningTime(Time openingTime) {
		this.openingTime = openingTime;
	}

	public void setClosingTime(Time closingTime) {
		this.closingTime = closingTime;
	}

	public void setDayOpen(Day dayOpen) {
		this.dayOpen = dayOpen;
	}
	
}