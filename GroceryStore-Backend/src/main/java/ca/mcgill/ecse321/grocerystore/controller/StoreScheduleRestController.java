package ca.mcgill.ecse321.grocerystore.controller;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.StoreScheduleDto;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule.Day;
import ca.mcgill.ecse321.grocerystore.service.StoreScheduleService;

@CrossOrigin(origins = "*")
@RestController
public class StoreScheduleRestController {

	@Autowired
	private StoreScheduleService service;

//	@PostMapping(value = { "/storeSchedules/{day}", "/storeSchedules/{day}/" })
//	public StoreScheduleDto createStoreSchedule(@PathVariable("day") String day,
//	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime openingTime,
//	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime closingTime)
//	throws IllegalArgumentException {
//		StoreSchedule storeSchedule = service.createStoreSchedule(Time.valueOf(openingTime), Time.valueOf(closingTime), Day.valueOf(day));
//		return convertToDto(storeSchedule);
//	}
//
//	@GetMapping(value = { "/events", "/events/" })
//	public List<StoreScheduleDto> getAllEvents() {
//		List<StoreScheduleDto> storeScheduleDtos = new ArrayList<>();
//		for (StoreSchedule storeSchedule : service.getAllStoreSchedules()) {
//			storeScheduleDtos.add(convertToDto(storeSchedule));
//		}
//		return storeScheduleDtos;
//	}
}