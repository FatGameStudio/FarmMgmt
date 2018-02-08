package no.lebegue.christophe.FarmMgmt.Entity;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EventRepository extends CrudRepository<Event, Integer> {
	
	@Query("select b from Event b " +
	         "where b.start between ?1 and ?2 and b.end between ?1 and ?2")
	 List<Event> findByDatesBetween(Date start, Date end);
	
}