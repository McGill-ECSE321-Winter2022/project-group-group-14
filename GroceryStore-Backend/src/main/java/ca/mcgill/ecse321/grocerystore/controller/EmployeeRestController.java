package ca.mcgill.ecse321.grocerystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public EmployeeDto createEmployee(@PathVariable("email") String email, @PathVariable("username") String username, @PathVariable("password") String password) throws IllegalArgumentException {
		Employee employee = EmployeeService.createEmployee(email,username,password);
		return convertToDto(employee);
	}
	
	@GetMapping(value = { "/employees/{email}", "/employees/{email}/" })
	public EmployeeDto getEmployee(@PathVariable("email") String email) throws IllegalArgumentException {
		return convertToDto(EmployeeService.getEmployeeByEmail(email));
	}
	
	private EmployeeDto convertToDto(Employee employee) {
		if (employee == null) {
			throw new IllegalArgumentException("There is no such Employee!");
		}
		EmployeeDto employeeDto = new EmployeeDto(employee.getEmail(),employee.getUsername(),employee.getPassword(),employee.getAccountId());
		return employeeDto;
	}

}