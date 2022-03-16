package ca.mcgill.ecse321.grocerystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.dao.CustomerRepository;
import ca.mcgill.ecse321.grocerystore.dao.AccountRepository;

import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.Account;

@Service
public class CustomerService {
	
	@Autowired
    CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	/** @author Samuel Valentine	 */
	@Transactional
	public Customer createCustomer(String aEmail, String aUsername, String aPassword, String aPhoneNumber, String aAddress) {
		
		checkAllInputParameters(aEmail, aUsername, aPassword, aPhoneNumber, aAddress);
		
		Customer customer = new Customer(aEmail, aUsername, aPassword, aPhoneNumber, aAddress);
		customerRepository.save(customer);
		return customer;
	}
	
	/** @author Samuel Valentine	 */
	public boolean checkAllInputParameters(String aEmail, String aUsername, String aPassword, String aPhoneNumber, String aAddress) {
		ServiceHelpers.checkCustomerInfoValidity(aEmail, aUsername, aPassword, aPhoneNumber, aAddress);
		
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
		for (Account a :  ServiceHelpers.toList(accountRepository.findAll())) {
			if (email == a.getEmail()) {
				return false;
			}
		}
		return true;
	}
	
	
	/** @author Samuel Valentine	 */
	public boolean checkForUsernameUniqueness(String username) {
		for (Account a :  ServiceHelpers.toList(accountRepository.findAll())) {
			if (username == a.getUsername()) {
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
		
		if (phoneNumber.length() != 10) {
			return false;
		}
		try {
			Double.parseDouble(phoneNumber);
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
    
    /** @author Samuel Valentine */
    @Transactional
    public Customer updateCustomerInfo(Customer customer)
    {
    	//check customer has valid info
    	ServiceHelpers.checkAccountInfoValidity(customer);
        
        //update existing customer info with the new ones
        Customer customerToUpdate = customerRepository.findByAccountId(customer.getAccountId());
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

}
