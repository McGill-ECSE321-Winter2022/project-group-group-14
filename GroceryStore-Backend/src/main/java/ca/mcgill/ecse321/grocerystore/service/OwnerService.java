package ca.mcgill.ecse321.grocerystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.dao.OwnerRepository;
import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.Owner;

@Service
public class OwnerService {
	
	@Autowired
    OwnerRepository ownerRepository;
	
	
	/** @author Samuel Valentine	 */
	@Transactional
	public Owner createOwner(String aEmail, String aUsername, String aPassword) {
		
		checkAllInputParameters(aEmail, aUsername, aPassword);
		
		Owner owner = new Owner(aEmail, aUsername, aPassword);
		ownerRepository.save(owner);
		return owner;
	}

	/** @author Samuel Valentine	 */
	@Transactional
	public Owner getOwner(String email) {
		Owner owner = ownerRepository.findByEmail(email);
		return owner;
	}
	
	/** @author Samuel Valentine	 */
	@Transactional
    public Owner getOwnerByID(int id)
    {
        return ownerRepository.findByAccountId(id);
    }

	/** @author Samuel Valentine	 */
    @Transactional
    public Owner getOwnerByEmail(String email)
    {
        return ownerRepository.findByEmail(email);
    }

    /** @author Samuel Valentine	 */
    @Transactional
    public Owner getOwnerByUsername(String username)
    {
        return ownerRepository.findByUsername(username);
    }
    
    /** @author Samuel Valentine */
    @Transactional
    public Owner updateOwnerInfo(Owner owner)
    {
    	//check owner has valid info
    	checkAllInputParameters(owner.getEmail(),owner.getUsername(),owner.getPassword());
        
        //update existing owner info with the new ones
        Owner ownerToUpdate = ownerRepository.findByAccountId(owner.getAccountId());
        if (ownerToUpdate == null) throw new IllegalArgumentException("No such owner exists");
        ownerToUpdate.setEmail(owner.getEmail());
        ownerToUpdate.setUsername(owner.getUsername());
        ownerToUpdate.setPassword(owner.getPassword());
        
        //save new changes to the repository
        ownerRepository.save(ownerToUpdate);
        
        return owner;
    }

    /** @author Samuel Valentine	 */
    @Transactional
    public Owner deleteOwner(Owner owner)
    {
        ownerRepository.delete(owner);
        return owner;
    }
    
    /** @author Samuel Valentine	 */
	public boolean checkAllInputParameters(String aEmail, String aUsername, String aPassword) {
		
		ServiceHelpers.checkAccountInfoValidity(aEmail, aUsername, aPassword);
		
		if (checkForEmailUniqueness(aEmail) == false ){
			throw new IllegalArgumentException("An account with email " + aEmail + " already exists.");}
		if (checkForUsernameUniqueness(aUsername) == false) {
			throw new IllegalArgumentException("An account with username " + aUsername + " already exists.");}
		if (checkPasswordValidity(aPassword) == false) {
			throw new IllegalArgumentException("This password does not correspond with the requirements.");}
		return true;
	}
	
	/** @author Samuel Valentine	 */
	public boolean checkForEmailUniqueness(String email) {
		for (Account a :  ServiceHelpers.toList(ownerRepository.findAll())) {
			if (email == a.getEmail()) {
				return false;
			}
		}
		return true;
	}
	
	
	/** @author Samuel Valentine	 */
	public boolean checkForUsernameUniqueness(String username) {
		for (Account a :  ServiceHelpers.toList(ownerRepository.findAll())) {
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
    @Transactional
    public Owner login(String email, String password)
    {
    	Owner owner = ownerRepository.findByEmail(email);
    	if (owner!=null) {
    		if (owner.getPassword().equals(password)) {
    			return owner;
    		}
    		else {
        		throw new IllegalArgumentException("That password is invalid for the owner account " + owner.getEmail());
        	}
    	}
    	else {
    		throw new IllegalArgumentException("That email does not exist in the system.");
    	}
    }
}
