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
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService CustomerService;
	
	/*
	 * Author: Samuel Valentine
	 * Description: Controller method to create a customer
	 */
	@PostMapping(value = { "/customers/{email}/{username}/{password}/{phoneNumber}/{address}", "/customers/{email}/{username}/{password}/{phoneNumber}/{address}/" })
	public ResponseEntity<?> createCustomer(@PathVariable("email") String email, @PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("phoneNumber") String phoneNumber, @PathVariable("address") String address) throws IllegalArgumentException {
		
		try {
			Customer customer = CustomerService.createCustomer(email,username,password,phoneNumber,address);
			return ResponseEntity.ok(convertToDto(customer));
		}
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/*
	 * Author: Samuel Valentine
	 * Description: Controller method to update a customer
	 */
	@PutMapping(value = { "/customers/update/{oldEmail}/{email}/{username}/{password}/{phoneNumber}/{address}", "/customers/update/{oldEmail}/{email}/{username}/{password}/{phoneNumber}/{address}/" })
	public ResponseEntity<?> updateCustomer(@PathVariable("oldEmail") String oldEmail,@PathVariable("email") String email, @PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("phoneNumber") String phoneNumber, @PathVariable("address") String address) throws IllegalArgumentException {
		
		try {
			Customer customer = CustomerService.updateCustomer(oldEmail,email,username,password,phoneNumber,address);
			return ResponseEntity.ok(convertToDto(customer));
		}
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/*
	 * Author: Samuel Valentine
	 * Description: Controller method to get a customer by their email
	 */
	@GetMapping(value = { "/customers/{email}", "/customers/{email}/" })
	public CustomerDto getCustomer(@PathVariable("email") String email) throws IllegalArgumentException {
		return convertToDto(CustomerService.getCustomerByEmail(email));
	}
	
	/*
	 * Author: Samuel Valentine
	 * Description: Controller method to delete a customer
	 */
	@DeleteMapping(value = { "/customers/delete/{email}", "/customers/delete/{email}/" })
	public ResponseEntity<?> deleteCustomer(@PathVariable("email") String email) throws IllegalArgumentException {
		try {
			return ResponseEntity.ok(CustomerService.deleteCustomer(CustomerService.getCustomerByEmail(email)));
		}
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/*
	 * Author: Samuel Valentine
	 * Description: Controller method to check if the login information is correct
	 */
	@GetMapping(value = { "/customers/login/{email}/{password}", "/customers/login/{email}/{password}/"})
	public ResponseEntity<?> loginCustomer(@PathVariable("email") String email, @PathVariable("password") String password) throws IllegalArgumentException {
		
		try {
			
			return ResponseEntity.ok(convertToDto(CustomerService.login(email, password)));
		}
		
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/*
	 * Author: Samuel Valentine
	 * Description: Controller method to get all customers
	 */
	@GetMapping(value = { "/customers/login/getAll", "/customers/login/getAll/"})
	public ResponseEntity<?> getAllCustomers() throws IllegalArgumentException {
		
		try {
			
			return ResponseEntity.ok(convertToDto(CustomerService.getAllCustomers()));
		}
		
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	/*
	 * Author: Samuel Valentine
	 * Description: Helper method to convert an object from the model to a DTO
	 */
	private CustomerDto convertToDto(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("There is no such Customer!");
		}
		CustomerDto customerDto = new CustomerDto(customer.getUsername(),customer.getPassword(),customer.getEmail(),customer.getAccountId(),customer.getPhoneNumber(),customer.getAddress());
		return customerDto;
	}
	
	/*
	 * Author: Samuel Valentine
	 * Description: Helper method to convert a list of objects from the model to a list of DTO's
	 */
	private List<CustomerDto> convertToDto(List<Customer> customers) {
		List<CustomerDto> customersDto = new ArrayList<CustomerDto>(customers.size());
		
		for(Customer customer : customers) {
			
			customersDto.add(new CustomerDto(customer.getUsername(),customer.getPassword(),customer.getEmail(),customer.getAccountId(),customer.getPhoneNumber(),customer.getAddress()));
			}
		
		return customersDto;
	}
	
	

}