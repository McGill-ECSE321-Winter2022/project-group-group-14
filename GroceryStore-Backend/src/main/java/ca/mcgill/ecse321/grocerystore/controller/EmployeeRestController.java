package ca.mcgill.ecse321.grocerystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.EmployeeDto;
import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.service.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeRestController {

	@Autowired
	private EmployeeService EmployeeService;
	
	@PostMapping(value = { "/employees/{email}/{username}/{password}", "/employees/{email}/{username}/{password}/" })
	public ResponseEntity<?> createEmployee(@PathVariable("email") String email, @PathVariable("username") String username, @PathVariable("password") String password) throws IllegalArgumentException {
		
		try {
			Employee employee = EmployeeService.createEmployee(email,username,password);
			return ResponseEntity.ok(convertToDto(employee));
		}
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping(value = { "/employees/update/{oldEmail}/{email}/{username}/{password}", "/employees/update/{oldEmail}/{email}/{username}/{password}/" })
	public ResponseEntity<?> updateEmployee(@PathVariable("oldEmail") String oldEmail,@PathVariable("email") String email, @PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("phoneNumber") String phoneNumber, @PathVariable("address") String address) throws IllegalArgumentException {
		
		try {
			Employee employee = EmployeeService.updateEmployee(oldEmail,email,username,password);
			return ResponseEntity.ok(convertToDto(employee));
		}
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = { "/employees/{email}", "/employees/{email}/" })
	public EmployeeDto getEmployee(@PathVariable("email") String email) throws IllegalArgumentException {
		return convertToDto(EmployeeService.getEmployeeByEmail(email));
	}
	
	@DeleteMapping(value = { "/employees/delete/{email}", "/employees/delete/{email}/" })
	public ResponseEntity<?> deleteEmployee(@PathVariable("email") String email) throws IllegalArgumentException {
		try {
			return ResponseEntity.ok(EmployeeService.deleteEmployee(EmployeeService.getEmployeeByEmail(email)));
		}
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = { "/employees/login/{email}/{password}", "/employees/login/{email}/{password}/"})
	public ResponseEntity<?> loginEmployee(@PathVariable("email") String email, @PathVariable("password") String password) throws IllegalArgumentException {
		
		try {
			
			return ResponseEntity.ok(convertToDto(EmployeeService.login(email, password)));
		}
		
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	private EmployeeDto convertToDto(Employee employee) {
		if (employee == null) {
			throw new IllegalArgumentException("There is no such Employee!");
		}
		EmployeeDto employeeDto = new EmployeeDto(employee.getEmail(),employee.getUsername(),employee.getPassword(),employee.getAccountId());
		return employeeDto;
	}

}