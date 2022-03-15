package ca.mcgill.ecse321.grocerystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.dao.GroceryStoreRepository;


@Service
public class GroceryStoreService {

	@Autowired
	private GroceryStoreRepository groceryStoreRepository;

}