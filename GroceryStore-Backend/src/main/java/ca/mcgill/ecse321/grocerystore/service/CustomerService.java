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
		Customer customer = new Customer(aEmail, aUsername, aPassword, aPhoneNumber, aAddress);
		customerRepository.save(customer);
		return customer;
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
