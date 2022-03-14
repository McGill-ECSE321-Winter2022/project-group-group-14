package ca.mcgill.ecse321.grocerystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.dao.OwnerRepository;
import ca.mcgill.ecse321.grocerystore.model.Owner;

@Service
public class OwnerService {
	
	@Autowired
    OwnerRepository ownerRepository;
	
	/** @author Samuel Valentine	 */
	@Transactional
	public Owner createOwner(String aEmail, String aUsername, String aPassword) {
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
    	ServiceHelpers.checkAccountInfoValidity(owner);
        
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
}
