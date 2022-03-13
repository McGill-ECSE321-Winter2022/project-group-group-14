package ca.mcgill.ecse321.grocerystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.OwnerDto;
import ca.mcgill.ecse321.grocerystore.model.Owner;
import ca.mcgill.ecse321.grocerystore.service.OwnerService;

@CrossOrigin(origins = "*")
@RestController
public class OwnerRestController {

	@Autowired
	private OwnerService ownerService;
	
	@PostMapping(value = { "/owners/{email}", "/owners/{email}/" })
	public OwnerDto createOwner(@PathVariable("email") String email, @RequestParam String username, @RequestParam String password, @RequestParam String phoneNumber, @RequestParam String address) throws IllegalArgumentException {
		Owner owner = ownerService.createOwner(email,username,password);
		return convertToDto(owner);
	}
	@GetMapping(value = { "/owners/{name}", "/owners/{name}/" })
	public OwnerDto getowner(@PathVariable("email") String email) throws IllegalArgumentException {
		return convertToDto(ownerService.getOwnerByEmail(email));
	}
	
	private OwnerDto convertToDto(Owner owner) {
		if (owner == null) {
			throw new IllegalArgumentException("There is no such owner!");
		}
		OwnerDto ownerDto = new OwnerDto(owner.getEmail(),owner.getUsername(),owner.getPassword(),owner.getAccountId());
		return ownerDto;
	}

}