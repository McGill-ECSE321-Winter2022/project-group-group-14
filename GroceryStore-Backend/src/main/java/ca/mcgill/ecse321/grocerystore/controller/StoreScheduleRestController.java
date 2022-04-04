package ca.mcgill.ecse321.grocerystore.controller;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.StoreScheduleDto;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule.Day;
import ca.mcgill.ecse321.grocerystore.service.StoreScheduleService;

/**
 * 
 * @author Yakir Bender
 *
 */

@CrossOrigin(origins = "*")
@RestController
public class StoreScheduleRestController {

	@Autowired
	private StoreScheduleService service;

	@PostMapping(value = { "/storeSchedules/create/{day}", "/storeSchedules/create/{day}/" })
	public ResponseEntity<?> createStoreSchedule(@PathVariable("day") String day,
	@RequestParam String openingTime, @RequestParam String closingTime)
	throws IllegalArgumentException {
		try {
			
			LocalTime newOpen = convertToLocalTime(openingTime);
			LocalTime newClose = convertToLocalTime(closingTime);
			
			StoreSchedule storeSchedule = service.createStoreSchedule(Time.valueOf(newOpen), Time.valueOf(newClose), Day.valueOf(day));
			return ResponseEntity.ok(convertToDto(storeSchedule));	
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping(value = { "/storeSchedules/{day}", "/storeSchedules/{day}/" })
	public ResponseEntity<?> getStoreSchedule(@PathVariable("day") String day) throws IllegalArgumentException {
		try {
			return ResponseEntity.ok(convertToDto(service.getStoreScheduleByDayOpen(Day.valueOf(day))));
			} catch (IllegalArgumentException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
	}
	
	@GetMapping(value = { "/storeSchedules/get", "/storeSchedules/get/" })
	public ResponseEntity<?> getAllStoreSchedules() {
		try {
			List<StoreScheduleDto> storeScheduleDtos = new ArrayList<>();
			for (StoreSchedule storeSchedule : service.getAllStoreSchedules()) {
				storeScheduleDtos.add(convertToDto(storeSchedule));
			}
			return ResponseEntity.ok(storeScheduleDtos);	
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping(value = { "/storeSchedules/update/{day}", "/storeSchedules/update/{day}/" })
	public ResponseEntity<?> updateStoreSchedule(@PathVariable("day") String day,
			@RequestParam String openingTime, @RequestParam String closingTime) throws IllegalArgumentException  {
		try {
			LocalTime newOpen = convertToLocalTime(openingTime);
			LocalTime newClose = convertToLocalTime(closingTime);
			
			StoreSchedule storeSchedule  = service.updateStoreScheduleInfo(Day.valueOf(day), Time.valueOf(newOpen), Time.valueOf(newClose));
			return ResponseEntity.ok(convertToDto(storeSchedule));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping({ "/storeSchedules/delete/{day}", "/storeSchedules/delete/{day}/" })
	public ResponseEntity<?> deleteStoreSchedule(@PathVariable("day") String day) {
		try {
			StoreSchedule storeSchedule = service.deleteStoreSchedule(service.getStoreScheduleByDayOpen(Day.valueOf(day)));
			return ResponseEntity.ok(convertToDto(storeSchedule));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		}
	
	private StoreScheduleDto convertToDto(StoreSchedule storeSchedule) {
		if (storeSchedule == null) {
			throw new IllegalArgumentException("The provided Schedule does not exist.");
		}
		StoreScheduleDto storeScheduleDto = new StoreScheduleDto(storeSchedule.getOpeningTime(), storeSchedule.getClosingTime(), storeSchedule.getDayOpen());
		return storeScheduleDto;
	}
	
	private LocalTime convertToLocalTime(String time) {
		if (time == null) {
			throw new IllegalArgumentException("The provided time cannot be null.");
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime convertedTime = LocalTime.parse(time, dtf);
		return convertedTime;
		
	}
}