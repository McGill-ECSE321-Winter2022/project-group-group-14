package ca.mcgill.ecse321.grocerystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.dao.AccountRepository;

public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	 /** @author Samuel Valentine	 */
    @Transactional
    public Account login(String username, String password)
    {
    	Account account = accountRepository.findByUsername(username);
    	if (account!=null) {
    		if (account.getPassword()==password) {
    			return account;
    		}
    		else {
        		throw new IllegalArgumentException("That password is invalid for the account " + account);
        	}
    	}
    	else {
    		throw new IllegalArgumentException("That username does not exist in the system.");
    	}
    }

}
