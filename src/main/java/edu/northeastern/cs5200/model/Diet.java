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
public class Diet extends BaseEntity {

	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Food> food;
	@OneToOne(cascade= CascadeType.ALL)
	private User user;

	public Diet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Diet(List<Food> food, User user) {
		super();
		this.food = food;
		this.user = user;
	}

	public List<Food> getFood() {
		if (food == null) {
			return new ArrayList();
		}
		return food;
	}

	public void setFood(List<Food> food) {
		this.food = food;
		for (Food f : food) {
			if (!f.getDiets().contains(this)) {
				f.getDiets().add(this);
			}
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void removeFood(Food food2) {
		// TODO Auto-generated method stub
		if (food.contains(food2)) {
			this.food.remove(food2);
			food2.getDiets().remove(this);
		}
		
	}

}
