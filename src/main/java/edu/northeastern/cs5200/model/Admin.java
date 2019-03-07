package edu.northeastern.cs5200.model;

import javax.persistence.Entity;

@Entity
public class Admin extends Person {
	
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
		// TODO Auto-generated constructor stub
	}

}
