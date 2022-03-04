package ca.mcgill.ecse321.grocerystore.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule.Day;

public interface StoreScheduleRepository extends CrudRepository <StoreSchedule, Integer >{
    StoreSchedule findByStoreScheduleId(int storeScheduleId); 
    StoreSchedule findByDayOpen(Day dayOpen);
}

