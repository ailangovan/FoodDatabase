package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

}
