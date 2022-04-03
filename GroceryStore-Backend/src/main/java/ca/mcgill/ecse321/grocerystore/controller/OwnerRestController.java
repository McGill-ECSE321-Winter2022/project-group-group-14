package ca.mcgill.ecse321.grocerystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.OwnerDto;
import ca.mcgill.ecse321.grocerystore.model.Owner;
import ca.mcgill.ecse321.grocerystore.service.OwnerService;

@CrossOrigin(origins = "*")
@RestController
public class OwnerRestController {

	@Autowired
	private OwnerService ownerService;
	
	@PostMapping(value = { "/owners/{email}/{username}/{password}", "/owners/{email}/{username}/{password}/" })
	public ResponseEntity<?> createOwner(@PathVariable("email") String email, @PathVariable("username") String username, @PathVariable("password") String password) throws IllegalArgumentException {
		
		try {
			Owner owner = ownerService.createOwner(email,username,password);
			return ResponseEntity.ok(convertToDto(owner));
		}
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
//	@PutMapping(value = { "/owners/update/{oldEmail}/{email}/{username}/{password}", "/owners/update/{oldEmail}/{email}/{username}/{password}/" })
//	public ResponseEntity<?> updateOwner(@PathVariable("oldEmail") String oldEmail,@PathVariable("email") String email, @PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("phoneNumber") String phoneNumber, @PathVariable("address") String address) throws IllegalArgumentException {
//		
//		try {
//			Owner owner = ownerService.updateOwner(oldEmail,email,username,password);
//			return ResponseEntity.ok(convertToDto(owner));
//		}
//		catch(IllegalArgumentException e){
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
	
	@GetMapping(value = { "/owners/{email}", "/owners/{email}/" })
	public OwnerDto getowner(@PathVariable("email") String email) throws IllegalArgumentException {
		return convertToDto(ownerService.getOwnerByEmail(email));
	}
	
	@DeleteMapping(value = { "/owners/delete/{email}", "/owners/delete/{email}/" })
	public ResponseEntity<?> deleteOwner(@PathVariable("email") String email) throws IllegalArgumentException {
		try {
			return ResponseEntity.ok(ownerService.deleteOwner(ownerService.getOwnerByEmail(email)));
		}
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@GetMapping(value = { "/owners/login/{email}/{password}", "/owners/login/{email}/{password}/"})
	public ResponseEntity<?> loginOwner(@PathVariable("email") String email, @PathVariable("password") String password) throws IllegalArgumentException {
		
		try {
			
			return ResponseEntity.ok(convertToDto(ownerService.login(email, password)));
		}
		
		catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	private OwnerDto convertToDto(Owner owner) {
		if (owner == null) {
			throw new IllegalArgumentException("There is no such owner!");
		}
		OwnerDto ownerDto = new OwnerDto(owner.getEmail(),owner.getUsername(),owner.getPassword(),owner.getAccountId());
		return ownerDto;
	}

}