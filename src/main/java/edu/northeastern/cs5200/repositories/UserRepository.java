package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query("Select p.id from Person p where p.username = :username and p.password = :password")
	int findByCredentials(@Param("username")String username, @Param("password")String password);

}
