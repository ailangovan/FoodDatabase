package edu.northeastern.cs5200.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.model.Diet;
import edu.northeastern.cs5200.model.Food;

public interface FoodRepository extends CrudRepository<Food, Integer> {
	
	@Query("Select d from Food d")
	List<Food> findAllFood();
	
}
