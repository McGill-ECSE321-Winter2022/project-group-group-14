package ca.mcgill.ecse321.grocerystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.CustomerDto;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService CustomerService;
	
	@PostMapping(value = { "/customers/{email}/{username}/{password}/{phoneNumber}/{address}", "/customers/{email}/{username}/{password}/{phoneNumber}/{address}/" })
	public CustomerDto createCustomer(@PathVariable("email") String email, @PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("phoneNumber") String phoneNumber, @PathVariable("address") String address) throws IllegalArgumentException {
		Customer customer = CustomerService.createCustomer(email,username,password,phoneNumber,address);
		return convertToDto(customer);
	}
	@GetMapping(value = { "/customers/{email}", "/customers/{email}/" })
	public CustomerDto getCustomer(@PathVariable("email") String email) throws IllegalArgumentException {
		return convertToDto(CustomerService.getCustomerByEmail(email));
	}
	
	@DeleteMapping(value = { "/customers/delete/{email}", "/customers/delete/{email}/" })
	public void deleteCustomer(@PathVariable("email") String email) throws IllegalArgumentException {
		CustomerService.deleteCustomer(CustomerService.getCustomerByEmail(email));
	}
	
	@GetMapping(value = { "/customers/login/{email}/{password}", "/customers/login/{email}/{password}/"})
	public ResponseEntity<?> loginCustomer(@PathVariable("email") String email, @PathVariable("password") String password) throws IllegalArgumentException {
		
		try {
			
			return ResponseEntity.ok(convertToDto(CustomerService.login(email, password)));
		}
		
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
	private CustomerDto convertToDto(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("There is no such Customer!");
		}
		CustomerDto customerDto = new CustomerDto(customer.getEmail(),customer.getUsername(),customer.getPassword(),customer.getAccountId(),customer.getPhoneNumber(),customer.getAddress());
		return customerDto;
	}
	
	

}