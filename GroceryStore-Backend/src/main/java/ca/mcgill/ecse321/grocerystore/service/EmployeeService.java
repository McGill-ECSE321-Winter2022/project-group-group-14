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
    
    /** @author Samuel Valentine	 */
    @Transactional
    public Employee updateEmployeeInfo(Employee employee)
    {
        ServiceHelpers.checkAccountInfoValidity(employee);
        if (employee.getPassword() == null || employee.getPassword().trim().length() == 0)
        {
        	employee.setPassword(getEmployeeByID(employee.getAccountId()).getPassword());
        }
        else
        {
        	employee.setPassword(employee.getPassword()); 
//            employee.setPassword(Helper.hash(employee.getPassword())); // some sources recommended using 'hash'
        }
        employeeRepository.save(employee);
        return employee;
    }

    /** @author Samuel Valentine	 */
    @Transactional
    public Employee deleteEmployee(Employee employee)
    {
//        List<Appointment> appts = appointmentRepository.findAppointmentsByEmployee(employee);
//        appointmentRepository.deleteAll(appts);

    	employeeRepository.delete(employee);
        return employee;
    }
}
