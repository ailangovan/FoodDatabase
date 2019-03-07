package edu.northeastern.cs5200.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Nutritionist extends Person {

	private String medicalId;
	@OneToOne(cascade= CascadeType.ALL)
	private PatientLog patientLog;
	
	@OneToMany(mappedBy = "nutritionist")
	@JsonIgnore
	private List<Message> messages;

	public PatientLog getPatientLog() {
		return patientLog;
	}

	public void setPatientLog(PatientLog patientLog) {
		this.patientLog = patientLog;
	}

	public Nutritionist(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
		// TODO Auto-generated constructor stub
	}

	public Nutritionist(String medicalId, PatientLog patientLog, List<Message> messages) {
		super();
		this.medicalId = medicalId;
		this.patientLog = patientLog;
		this.messages = messages;
	}

	public List<Message> getMessages() {
		if (messages == null) {
			return new ArrayList();
		}
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
		for(Message m : messages) {
			m.setNutritionist(this);
		}
	}

	public Nutritionist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Nutritionist(String medicalId) {
		super();
		this.medicalId = medicalId;

	}

	public String getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}

}
