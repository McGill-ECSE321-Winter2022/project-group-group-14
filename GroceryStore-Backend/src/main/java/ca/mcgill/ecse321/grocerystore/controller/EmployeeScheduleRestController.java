package ca.mcgill.ecse321.grocerystore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin(origins = "*")
@RestController
public class EmployeeScheduleRestController {

	@Autowired
	private EmployeeScheduleService service;
	
	@PostMapping(value = {"/employeeSchedules/{shift}/{day}", "/employeeSchedules/{shift}/{day}"})
	public EmployeeScheduleDto createEmployeeSchedule(@PathVariable("day") String day, @PathVariable("shift") String shift)
	throws IllegalArgumentException {
		
		if (service.getEmployeeScheduleByDay(Day.valueOf(day)) != null) {
			
		}
		EmployeeSchedule employeeSchedule = service.createEmployeeSchedule(Shift.valueOf(shift), Day.valueOf(day));
		return convertToDto(employeeSchedule);
	}
	

	
	@GetMapping(value = { "/employeeSchedules/{day}", "employeeSchedules/{day}/" })
	public EmployeeScheduleDto getEmployeeSchedule(@PathVariable("day") String day) throws IllegalArgumentException {
		return convertToDto(service.getEmployeeScheduleByDay(Day.valueOf(day)));
	}
	
	@GetMapping(value = { "/employeeSchedules", "/employeeSchedules/" })
	public List<EmployeeScheduleDto> getAllEmployeeSchedules() {
		List<EmployeeScheduleDto> employeeScheduleDtos = new ArrayList<>();
		for (EmployeeSchedule employeeSchedule : service.getAllEmployeeSchedules()) {
			employeeScheduleDtos.add(convertToDto(employeeSchedule));
		}
		return employeeScheduleDtos;
	}
	
	@PutMapping(value = { "/employeeSchedules/{day}", "/employeeSchedules/{day}/" })
	public EmployeeScheduleDto updateEmployeeSchedule(@PathVariable("day") String day, @RequestParam ("shift") String shift) throws IllegalArgumentException  {
		EmployeeSchedule newSchedule = service.getEmployeeScheduleByDay(Day.valueOf(day));
		newSchedule.setShift(Shift.valueOf(shift));
		
		EmployeeSchedule employeeSchedule  = service.updateEmmployeeScheduleInfo(newSchedule);
		return convertToDto(employeeSchedule);
	}
	
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
	
	
	@DeleteMapping({ "/employeeSchedules/{day}", "/employeeSchedules/{day}/" })
	public EmployeeScheduleDto deleteEmployeeScheduleByDay(@PathVariable("day") String day) {
		EmployeeSchedule employeeSchedule = service.deleteEmployeeSchedule(service.getEmployeeScheduleByDay(Day.valueOf(day)));
		return convertToDto(employeeSchedule);
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
		EmployeeScheduleDto employeeScheduleDto = new EmployeeScheduleDto(employeeSchedule.getShift(), employeeSchedule.getDay());
		return employeeScheduleDto;
	}
	
}
