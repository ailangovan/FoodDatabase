package edu.northeastern.cs5200.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class PatientLog extends BaseEntity{
	
	@OneToOne(cascade=CascadeType.ALL)
	private Nutritionist nutritionist;

	@OneToMany(mappedBy = "patientLog")
	private List<User> users;

	public Nutritionist getNutritionist() {
		return nutritionist;
	}

	public void setNutritionist(Nutritionist nutritionist) {
		this.nutritionist = nutritionist;
	}

	public List<User> getUsers() {
		if(users == null) {
			users = new ArrayList();
		}
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
		for (User u : users) {
			u.setPatientLog(this);
		}
	}

	public PatientLog(Nutritionist nutritionist, List<User> users) {
		super();
		this.nutritionist = nutritionist;
		this.users = users;
		for (User u : users) {
			if (!u.getPatientLog().equals(this)) {
				u.setPatientLog(this);
			}
		}
	}

	public PatientLog() {
		super();
	}

	public void removeUser(User user) {
		this.users.remove(user);
		user.clearPatientLog();
		// TODO Auto-generated method stub
		
	}
}
