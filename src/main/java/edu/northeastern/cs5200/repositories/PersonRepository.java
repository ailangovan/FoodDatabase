package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.*;

import edu.northeastern.cs5200.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{

}
