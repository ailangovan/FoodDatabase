package edu.northeastern.cs5200.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Message extends BaseEntity {
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Nutritionist nutritionist;
	private String message;
	
	public Message(User user, Nutritionist nutritionist, String message) {
		super();
		this.user = user;
		this.nutritionist = nutritionist;
		this.message = message;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Nutritionist getNutritionist() {
		return nutritionist;
	}

	public void setNutritionist(Nutritionist nutritionist) {
		this.nutritionist = nutritionist;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
