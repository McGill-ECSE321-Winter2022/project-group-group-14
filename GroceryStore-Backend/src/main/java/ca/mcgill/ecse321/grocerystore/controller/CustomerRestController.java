package ca.mcgill.ecse321.grocerystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.CustomerDto;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService CustomerService;
	
	@PostMapping(value = { "/Customers/{email}", "/Customers/{email}/" })
	public CustomerDto createCustomer(@PathVariable("email") String email, @RequestParam String username, @RequestParam String password, @RequestParam String phoneNumber, @RequestParam String address) throws IllegalArgumentException {
		Customer customer = CustomerService.createCustomer(email,username,password,phoneNumber,address);
		return convertToDto(customer);
	}
	@GetMapping(value = { "/Customers/{name}", "/Customers/{name}/" })
	public CustomerDto getCustomer(@PathVariable("email") String email) throws IllegalArgumentException {
		return convertToDto(CustomerService.getCustomerByEmail(email));
	}
	
	private CustomerDto convertToDto(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("There is no such Customer!");
		}
		CustomerDto customerDto = new CustomerDto(customer.getEmail(),customer.getUsername(),customer.getPassword(),customer.getAccountId(),customer.getPhoneNumber(),customer.getAddress());
		return customerDto;
	}

}