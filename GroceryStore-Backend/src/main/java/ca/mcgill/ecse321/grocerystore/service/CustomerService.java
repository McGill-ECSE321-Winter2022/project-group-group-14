package ca.mcgill.ecse321.grocerystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.dao.CustomerRepository;
import ca.mcgill.ecse321.grocerystore.model.Customer;

@Service
public class CustomerService {
	
	@Autowired
    CustomerRepository customerRepository;
	
	/** @author Samuel Valentine	 */
	@Transactional
	public Customer createCustomer(String aEmail, String aUsername, String aPassword, String aPhoneNumber, String aAddress) {
		
		// Check input parameters to ensure they're following requirements
		checkAllInputParameters(aEmail, aUsername, aPassword, aPhoneNumber, aAddress);
		
		Customer customer = new Customer(aEmail, aUsername, aPassword, aPhoneNumber, aAddress);
		customerRepository.save(customer);
		return customer;
	}
	
	/** @author Samuel Valentine	 
	 * Description: A method that checks for any invalid input information for a customer*/
	public boolean checkAllInputParameters(String aEmail, String aUsername, String aPassword, String aPhoneNumber, String aAddress) {
		
		// Check that inputs are not null
		ServiceHelpers.checkCustomerInfoValidity(aEmail, aUsername, aPassword, aPhoneNumber, aAddress);
		
		// To understand what these do, read the exception messages.
		if (checkForEmailUniqueness(aEmail) == false ){
			throw new IllegalArgumentException("An account with email " + aEmail + " already exists.");}
		if (checkForUsernameUniqueness(aUsername) == false) {
			throw new IllegalArgumentException("An account with username " + aUsername + " already exists.");}
		if (checkPasswordValidity(aPassword) == false) {
			throw new IllegalArgumentException("This password does not correspond with the requirements.");}
		if (checkPhoneNumberValidity(aPhoneNumber) == false) {
			throw new IllegalArgumentException("This phone-number does not follow the standard phone-number format ###-###-####.");}
		if (checkAddressValidity(aAddress) == false) {
			throw new IllegalArgumentException("This address does not follow the standard address format <House# streetname town>");}
		return true;
	}
	
	/** @author Samuel Valentine	 */
	public boolean checkForEmailUniqueness(String email) {
		
		// If the DAO returns null on this query, then we know that there are no accounts with this email, and thus the email is indeed unique
		if (customerRepository.findByEmail(email) == null) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	
	/** @author Samuel Valentine	 */
	public boolean checkForUsernameUniqueness(String username) {
		
		// If the DAO returns null on this query, then we know that there are no accounts with this username, and thus the username is indeed unique
		if (customerRepository.findByUsername(username) == null) {
			return true;
		}
		
		else { 
			return false;
		}
	}
	
	/** @author Samuel Valentine	 */
	public boolean checkPasswordValidity(String password) {
		
		boolean upperCasePresent = false;
		boolean numberPresent = false;
		
		// We have to include a capital letter and a number
		// Therefore this method iterates through all characters and checks if we have at least one of each
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
	
	/** @author Samuel Valentine	 */
	public boolean checkPhoneNumberValidity(String phoneNumber) {
		
		// Format: <### ### ####>, without spaces: we're looking for a number that is 10 digits long
		// (More accurately, <##########>)
		
		if (phoneNumber.length() != 10) {
			return false;
		}
		try {
			Double.parseDouble(phoneNumber); // If there are only numerical characters present, this should work
		}
		catch(NumberFormatException e){ 
			return false;
		}
		return true;
	}
	
	/** @author Samuel Valentine	 */
	public boolean checkAddressValidity(String address) {
		
		// Format: ###-StreetName-Town	
		// No obvious checks to perform
		return true;
	}
	
	/** @author Samuel Valentine	 */
	@Transactional
	public Customer getCustomer(String email) {
		Customer customer = customerRepository.findByEmail(email);
		return customer;
	}

	/** @author Samuel Valentine	 */
	@Transactional
	public List<Customer> getAllCustomers() {
		return ServiceHelpers.toList(customerRepository.findAll());
	}
	
	/** @author Samuel Valentine	 */
	@Transactional
    public Customer getCustomerByID(int id)
    {
        return customerRepository.findByAccountId(id);
    }

	/** @author Samuel Valentine	 */
    @Transactional
    public Customer getCustomerByEmail(String email)
    {
        return customerRepository.findByEmail(email);
    }

    /** @author Samuel Valentine	 */
    @Transactional
    public Customer getCustomerByUsername(String username)
    {
        return customerRepository.findByUsername(username);
    }

    /** @author Samuel Valentine	 */
    @Transactional
    public Customer getCustomerByPhone(String phoneNumber)
    {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }
    
    /** @author Samuel Valentine	 */
    @Transactional
    public Customer getCustomerByAddress(String address)
    {
    	return customerRepository.findByAddress(address);
    }
    
    @Transactional 
    public Customer updateCustomer(String oldUsername, String newEmail, String newUsername, String newPassword, String newPhoneNumber, String newAddress) {
    	
    	Customer customerToUpdate = customerRepository.findByUsername(oldUsername);
    	
    	// Must perform check to ensure that the customer exists
    	if (customerToUpdate == null) throw new IllegalArgumentException("No such customer exists");
    	
    	// Update info
        customerToUpdate.setEmail(newEmail);
        customerToUpdate.setUsername(newUsername);
        customerToUpdate.setPassword(newPassword);
        customerToUpdate.setPhoneNumber(newPhoneNumber);
        customerToUpdate.setAddress(newAddress);
        
        // Before saving, we need to ensure the new information follows requirements
        ServiceHelpers.checkAccountInfoValidity(customerToUpdate);
        
        //save new changes to the repository
        customerRepository.save(customerToUpdate);
        
        return customerToUpdate;
    }
    
    /** @author Samuel Valentine */
    @Transactional
    public Customer updateCustomerInfo(Customer customer)
    {
    	//check customer has valid info
    	ServiceHelpers.checkAccountInfoValidity(customer);
        
        //update existing customer info with the new ones
        Customer customerToUpdate = customerRepository.findByEmail(customer.getEmail());
        if (customerToUpdate == null) throw new IllegalArgumentException("No such customer exists");
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setUsername(customer.getUsername());
        customerToUpdate.setPassword(customer.getPassword());
        customerToUpdate.setPhoneNumber(customer.getPhoneNumber());
        customerToUpdate.setAddress(customer.getAddress());
        
        //save new changes to the repository
        customerRepository.save(customerToUpdate);
        
        return customer;
    }

    /** @author Samuel Valentine	 */
    @Transactional
    public Customer deleteCustomer(Customer customer)
    {
        customerRepository.delete(customer);
        return customer;
    }
    
    /** @author Samuel Valentine	 */
    @Transactional
    public Customer login(String email, String password)
    {
    	// Get the customer
    	Customer customer = customerRepository.findByEmail(email); 
    	
    	// Check that the customer exists (i.e. is not null, which would mean the user inputed an invalid email)
    	if (customer !=null) {
    		
    		// Check that the password matches the email
    		if (customer.getPassword().equals(password)) {
    			return customer;
    		}
    		else {
        		throw new IllegalArgumentException("That password is invalid for the customer account " + customer.getEmail());
        	}
    	}
    	else {
    		throw new IllegalArgumentException("That email does not exist in the system.");
    	}
    }
}
