package edu.northeastern.cs5200.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Food extends BaseEntity {

	private String name;

	@ManyToMany(mappedBy = "food")
	private List<Diet> diets;

	private String calories;
	private String fat;
	private String sugar;
	private String carbohydrates;

	public Food(String calories, String fat, String sugar, String carbohydrates) {
		super();
		this.calories = calories;
		this.fat = fat;
		this.sugar = sugar;
		this.carbohydrates = carbohydrates;
	}

	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getFat() {
		return fat;
	}

	public void setFat(String fat) {
		this.fat = fat;
	}

	public String getSugar() {
		return sugar;
	}

	public void setSugar(String sugar) {
		this.sugar = sugar;
	}

	public String getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(String carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public List<Diet> getDiets() {
		if (diets == null) {
			return new ArrayList();
		}
		return diets;
	}

	public void setDiets(List<Diet> diets) {
		this.diets = diets;
		for (Diet d : diets) {
			if (!d.getFood().contains(this)) {
				d.getFood().add(this);
			}
		}
	}

	public void removeDiet(Diet diet) {
		// TODO Auto-generated method stub
		if (diets.contains(diet)) {
			this.diets.remove(diet);
			diet.removeFood(this);
		}
	}
}
