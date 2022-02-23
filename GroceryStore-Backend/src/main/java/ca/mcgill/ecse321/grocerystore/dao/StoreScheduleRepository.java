package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule;

public interface StoreScheduleRepository extends CrudRepository <StoreSchedule, Integer >{
    StoreSchedule findById(int id);   
}

