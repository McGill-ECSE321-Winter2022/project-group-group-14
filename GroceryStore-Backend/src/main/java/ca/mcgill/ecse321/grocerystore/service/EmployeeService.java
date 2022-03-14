package ca.mcgill.ecse321.grocerystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import ca.mcgill.ecse321.grocerystore.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	/** @author Samuel Valentine	 */
	@Transactional
	public Employee createEmployee(String aEmail, String aUsername, String aPassword) {
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
    
    /** @author Samuel Valentine */
    @Transactional
    public Employee updateEmployeeInfo(Employee employee)
    {
    	//check employee has valid info
    	ServiceHelpers.checkAccountInfoValidity(employee);
        
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
}
