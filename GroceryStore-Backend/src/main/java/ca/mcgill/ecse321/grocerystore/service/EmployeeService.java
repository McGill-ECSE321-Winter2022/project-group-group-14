package ca.mcgill.ecse321.grocerystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	/** @author Samuel Valentine	 */
	@Transactional
	public Employee createEmployee(String aEmail, String aUsername, String aPassword) {
		
		// Check that inputs follow requirements
		checkAllInputParameters(aEmail,aUsername,aPassword);
		
		Employee employee = new Employee(aEmail, aUsername, aPassword);
		employeeRepository.save(employee);
		return employee;
	}

	/** @author Samuel Valentine	 */
	@Transactional
	public Employee getEmployee(String email) {
		Employee employee = employeeRepository.findByEmail(email);
		return employee;
	}
	
	/** @author Samuel Valentine	 */
	@Transactional
    public Employee getEmployeeByID(int id)
    {
        return employeeRepository.findByAccountId(id);
    }

	/** @author Samuel Valentine	 */
    @Transactional
    public Employee getEmployeeByEmail(String email)
    {
        return employeeRepository.findByEmail(email);
    }

    /** @author Samuel Valentine	 */
    @Transactional
    public Employee getEmployeeByUsername(String username)
    {
        return employeeRepository.findByUsername(username);
    }
    
    /** @author Samuel Valentine	 */
	@Transactional
	public List<Employee> getAllEmployees() {
		return ServiceHelpers.toList(employeeRepository.findAll());
	}
    
    @Transactional 
    public Employee updateEmployee(String oldUsername, String newEmail, String newUsername, String newPassword) {
    	
    	Employee employeeToUpdate = employeeRepository.findByUsername(oldUsername);
    	
    	// First check that the employee exists
    	if (employeeToUpdate == null) throw new IllegalArgumentException("No such employee exists");
    	
    	// Set new parameters
    	checkAllInputParameters(newEmail,newUsername,newPassword);
        employeeToUpdate.setEmail(newEmail);
        employeeToUpdate.setUsername(newUsername);
        employeeToUpdate.setPassword(newPassword);
        
        // Check that new info follows requirements
        ServiceHelpers.checkAccountInfoValidity(employeeToUpdate);
        
        //save new changes to the repository
        employeeRepository.save(employeeToUpdate);
        
        return employeeToUpdate;
    }
    
    /** @author Samuel Valentine */
    @Transactional
    public Employee updateEmployeeInfo(Employee employee)
    {
    	//check employee has valid info
    	checkAllInputParameters(employee.getEmail(),employee.getUsername(),employee.getPassword());
        
        //update existing employee info with the new ones
        Employee employeeToUpdate = employeeRepository.findByAccountId(employee.getAccountId());
        if (employeeToUpdate == null) throw new IllegalArgumentException("No such employee exists");
        employeeToUpdate.setEmail(employee.getEmail());
        employeeToUpdate.setUsername(employee.getUsername());
        employeeToUpdate.setPassword(employee.getPassword());
        
        //save new changes to the repository
        employeeRepository.save(employeeToUpdate);
        
        return employee;
    }

    /** @author Samuel Valentine	 */
    @Transactional
    public Employee deleteEmployee(Employee employee)
    {
    	employeeRepository.delete(employee);
        return employee;
    }
    
    /** @author Samuel Valentine	 */
    @Transactional
    public Employee login(String email, String password)
    {
    	Employee employee = employeeRepository.findByEmail(email);
    	
    	// Check that an employee with that email exists in the system
    	if (employee!=null) {
    		
    		// Check that the employee's password matches the email
    		if (employee.getPassword().equals(password)) {
    			return employee;
    		}
    		else {
        		throw new IllegalArgumentException("That password is invalid for the employee account " + employee.getEmail());
        	}
    	}
    	else {
    		throw new IllegalArgumentException("That email does not exist in the system.");
    	}
    }
    
    
    /** @author Samuel Valentine	 */
	public boolean checkAllInputParameters(String aEmail, String aUsername, String aPassword) {
		
		// Check that the inputs are not null
		ServiceHelpers.checkAccountInfoValidity(aEmail, aUsername, aPassword);
		
		// Check standard requirements
		if (checkForEmailUniqueness(aEmail) == false ){
			throw new IllegalArgumentException("An account with email " + aEmail + " already exists.");}
		if (checkForUsernameUniqueness(aUsername) == false) {
			throw new IllegalArgumentException("An account with username " + aUsername + " already exists.");}
		if (checkPasswordValidity(aPassword) == false) {
			throw new IllegalArgumentException("This password does not correspond with the requirements.");}
		
		return true;
	}
	
	/** @author Samuel Valentine	 */
	public boolean checkForEmailUniqueness(String email) {
		
		// Iterate through the list of employees, and see if we can find one with the same email
		for (Account a :  ServiceHelpers.toList(employeeRepository.findAll())) {
			if (email.equals(a.getEmail())) {
				return false;
			}
		}
		return true;
	}
	
	
	/** @author Samuel Valentine	 */
	public boolean checkForUsernameUniqueness(String username) {
		

		// Iterate through the list of employees, and see if we can find one with the same username
		for (Account a :  ServiceHelpers.toList(employeeRepository.findAll())) {
			if (username.equals(a.getUsername())) {
				return false;
			}
		}
		return true;
	}
	
	/** @author Samuel Valentine	 */
	public boolean checkPasswordValidity(String password) {
		
		boolean upperCasePresent = false;
		boolean numberPresent = false;
		
		// Include a capital letter and a number
		// Iterate through the list of characters, and see if we can find an upper case and numerical one
		
		for (int i=0;i < password.length();i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				upperCasePresent = true;
			}
			if (Character.isDigit(password.charAt(i))) {
				numberPresent = true;
			}
		}
		return (upperCasePresent && numberPresent);
		
	}

}
