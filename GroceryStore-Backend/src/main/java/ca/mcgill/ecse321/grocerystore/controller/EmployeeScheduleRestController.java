package ca.mcgill.ecse321.grocerystore.controller;

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

import ca.mcgill.ecse321.grocerystore.dto.EmployeeScheduleDto;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Shift;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Day;
import ca.mcgill.ecse321.grocerystore.service.EmployeeScheduleService;
import ca.mcgill.ecse321.grocerystore.service.EmployeeService;
import ca.mcgill.ecse321.grocerystore.model.Employee;


@CrossOrigin(origins = "*")
@RestController
public class EmployeeScheduleRestController {

	@Autowired
	private EmployeeScheduleService service;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	
	@PostMapping(value = {"/employeeSchedules/create/{day}", "/employeeSchedules/create/{day}/"})
	public ResponseEntity<?> createEmployeeSchedule(@PathVariable("day") String day, @RequestParam("shift") String shift, @RequestParam("employeeUsername") String employeeUsername)
	throws IllegalArgumentException {
		try {
		
			if (service.getEmployeeScheduleByDay(Day.valueOf(day)) != null) {
				
			}
			EmployeeSchedule employeeSchedule = service.createEmployeeSchedule(Shift.valueOf(shift), Day.valueOf(day), employeeUsername);
			return ResponseEntity.ok(convertToDto(employeeSchedule));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	

	
	@GetMapping(value = { "/employeeSchedules/{day}", "employeeSchedules/{day}/" })
	public ResponseEntity<?> getEmployeeSchedule(@PathVariable("day") String day) throws IllegalArgumentException {
		try {
			return ResponseEntity.ok(convertToDto(service.getEmployeeScheduleByDay(Day.valueOf(day))));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = { "/employeeSchedules", "/employeeSchedules/" })
	public  ResponseEntity<?>  getAllEmployeeSchedules() {
		try {
			List<EmployeeScheduleDto> employeeScheduleDtos = new ArrayList<>();
			for (EmployeeSchedule employeeSchedule : service.getAllEmployeeSchedules()) {
				employeeScheduleDtos.add(convertToDto(employeeSchedule));
			}
			return ResponseEntity.ok(employeeScheduleDtos);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
//	@PutMapping(value = { "/employeeSchedules/update/{day}", "/employeeSchedules/update/{day}/" })
//	public ResponseEntity<?> updateEmployeeSchedule(@PathVariable("day") String day, @RequestParam ("shift") String shift, @RequestParam ("employeeUsername") String employeeUsername) throws IllegalArgumentException  {
//		try {
//		
//		EmployeeSchedule employeeSchedule  = service.updateEmmployeeScheduleInfo(day, shift, employeeUsername);
//		return ResponseEntity.ok(convertToDto(employeeSchedule));
//		} catch (IllegalArgumentException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
	
//	@PutMapping(value = { "/employeeSchedules/{shift}", "/employeeSchedules/{shift}/" })
//	public EmployeeScheduleDto updateEmployeeSchedule(@PathVariable("day") String day,
//			@PathVariable("shift") String shift) throws IllegalArgumentException  {
//		EmployeeSchedule newSchedule = service.getEmployeeScheduleByShift(Shift.valueOf(shift));
//		newSchedule.setShift(Shift.valueOf(shift));
//		
//		EmployeeSchedule employeeSchedule  = service.updateEmmployeeScheduleInfo(newSchedule);
//		return convertToDto(employeeSchedule);
//	}
//	
	
	
	@DeleteMapping({ "/employeeSchedules/delete/{day}", "/employeeSchedules/delete/{day}/" })
	public ResponseEntity<?> deleteEmployeeScheduleByDay(@PathVariable("day") String day) {
		try {
		EmployeeSchedule employeeSchedule = service.deleteEmployeeSchedule(service.getEmployeeScheduleByDay(Day.valueOf(day)));
		return ResponseEntity.ok(convertToDto(employeeSchedule));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		}
	
//	@DeleteMapping({ "/employeeSchedules/{shift}", "/employeeSchedules/{shift}/" })
//	public EmployeeScheduleDto deleteEmployeeScheduleByShift(@PathVariable("shift") String shift) {
//		EmployeeSchedule employeeSchedule = service.deleteEmployeeSchedule(service.getEmployeeScheduleByShift(Shift.valueOf(shift)));
//		return convertToDto(employeeSchedule);
//		}
//	
	private EmployeeScheduleDto convertToDto(EmployeeSchedule employeeSchedule) {
	
		if (employeeSchedule == null) {
			throw new IllegalArgumentException("The provided Schedule does not exist.");
		}
		EmployeeScheduleDto employeeScheduleDto = new EmployeeScheduleDto(employeeSchedule.getShift(), employeeSchedule.getDay(), employeeSchedule.getEmployee());
		return employeeScheduleDto;
		
	}
	
}
