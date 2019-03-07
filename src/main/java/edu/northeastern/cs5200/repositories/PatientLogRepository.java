package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.model.Diet;
import edu.northeastern.cs5200.model.PatientLog;

public interface PatientLogRepository extends CrudRepository<PatientLog, Integer>{
	
	@Query("Select l from PatientLog l where l.nutritionist.id = :id")
	PatientLog findByNut(@Param("id")int id);

//	@Query("INSERT INTO diet (user_id) VALUES (:userId)")
//	void addUser(@Param("userId") int userId);
//	@Query("Select d from Diet d where d.user.id = :id")
//	Diet findByUser(@Param("id")int id);
}
