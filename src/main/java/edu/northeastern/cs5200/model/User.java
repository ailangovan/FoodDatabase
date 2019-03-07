package edu.northeastern.cs5200.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User extends Person {

	private int weight;
	@OneToOne(cascade= CascadeType.ALL)
	private Diet diet;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Message> messages;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonIgnore
	private PatientLog patientLog;
	
	public PatientLog getPatientLog() {
		return patientLog;
	}

	public void setPatientLog(PatientLog patientLog) {
		this.patientLog = patientLog;
		if (!patientLog.getUsers().contains(this)) {
			patientLog.getUsers().add(this);
		}
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
		// TODO Auto-generated constructor stub
	}

	public User(int weight, List<Message> messages) {
		super();
		this.messages = messages;
		this.weight = weight;
		this.diet = new Diet();
	}
	

	public Diet getDiet() {
		return diet;
	}

	public void setDiet(Diet diet) {
		this.diet = diet;
	}

	public List<Message> getMessages() {
		if (messages == null) {
			return new ArrayList();
		}
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
		for (Message m : messages) {
			m.setUser(this);
		}
	}

	public void clearPatientLog() {
		this.patientLog = null;
		// TODO Auto-generated method stub
		
	}


}
