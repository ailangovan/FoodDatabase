package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.model.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {

}
