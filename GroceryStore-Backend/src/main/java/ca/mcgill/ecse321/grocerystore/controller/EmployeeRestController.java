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
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.CustomerDto;
import ca.mcgill.ecse321.grocerystore.dto.EmployeeDto;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.service.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeRestController {

	@Autowired
	private EmployeeService EmployeeService;
	
	/*
	 * Author: Samuel Valentine
	 * Description: Controller method to create an employee
	 */
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
	
	/*
	 * Author: Samuel Valentine
	 * Description: Controller method to update an employee
	 */
	@PutMapping(value = { "/employees/update/{oldEmail}/{email}/{username}/{password}", "/employees/update/{oldEmail}/{email}/{username}/{password}/" })
	public ResponseEntity<?> updateEmployee(@PathVariable("oldEmail") String oldEmail,@PathVariable("email") String email, @PathVariable("username") String username, @PathVariable("password") String password) throws IllegalArgumentException {
		
		try {
			Employee employee = EmployeeService.updateEmployee(oldEmail,email,username,password);
			return ResponseEntity.ok(convertToDto(employee));
		}
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/*
	 * Author: Samuel Valentine
	 * Description: Controller method to get an employee by their email
	 */
	@GetMapping(value = { "/employees/{email}", "/employees/{email}/" })
	public EmployeeDto getEmployee(@PathVariable("email") String email) throws IllegalArgumentException {
		return convertToDto(EmployeeService.getEmployeeByEmail(email));
	}
	
	/*
	 * Author: Samuel Valentine
	 * Description: Controller method to delete an employee
	 */
	@DeleteMapping(value = { "/employees/delete/{email}", "/employees/delete/{email}/" })
	public ResponseEntity<?> deleteEmployee(@PathVariable("email") String email) throws IllegalArgumentException {
		try {
			return ResponseEntity.ok(EmployeeService.deleteEmployee(EmployeeService.getEmployeeByEmail(email)));
		}
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/*
	 * Author: Samuel Valentine
	 * Description: Controller method to test employee's login credentials
	 */
	@GetMapping(value = { "/employees/login/{email}/{password}", "/employees/login/{email}/{password}/"})
	public ResponseEntity<?> loginEmployee(@PathVariable("email") String email, @PathVariable("password") String password) throws IllegalArgumentException {
		
		try {
			
			return ResponseEntity.ok(convertToDto(EmployeeService.login(email, password)));
		}
		
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/*
	 * Author: Samuel Valentine
	 * Description: Controller method to get all employees
	 */
	@GetMapping(value = { "/employees/login/getAll", "/employees/login/getAll/"})
	public ResponseEntity<?> getAllEmployees() throws IllegalArgumentException {
		
		try {
			
			return ResponseEntity.ok(convertToDto(EmployeeService.getAllEmployees()));
		}
		
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/*
	 * Author: Samuel Valentine
	 * Description: Helper method to convert an object from the model to a DTO
	 */
	private EmployeeDto convertToDto(Employee employee) {
		if (employee == null) {
			throw new IllegalArgumentException("There is no such Employee!");
		}
		EmployeeDto employeeDto = new EmployeeDto(employee.getEmail(),employee.getUsername(),employee.getPassword(),employee.getAccountId());
		return employeeDto;
	}
	
	/*
	 * Author: Samuel Valentine
	 * Description: Helper method to convert a list of objects from the model to a list of DTO's
	 */
	private List<EmployeeDto> convertToDto(List<Employee> employees) {
		List<EmployeeDto> employeesDto = new ArrayList<EmployeeDto>(employees.size());
		
		for(Employee employee : employees) {
			
			employeesDto.add(new EmployeeDto(employee.getUsername(),employee.getPassword(),employee.getEmail(),employee.getAccountId()));
			}
		
		return employeesDto;
	}

}