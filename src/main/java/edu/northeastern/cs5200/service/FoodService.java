package edu.northeastern.cs5200.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.model.Admin;
import edu.northeastern.cs5200.model.Diet;
import edu.northeastern.cs5200.model.Food;
import edu.northeastern.cs5200.model.Nutritionist;
import edu.northeastern.cs5200.model.PatientLog;
import edu.northeastern.cs5200.model.User;
import edu.northeastern.cs5200.repositories.AdminRepository;
import edu.northeastern.cs5200.repositories.DietRepository;
import edu.northeastern.cs5200.repositories.FoodRepository;
import edu.northeastern.cs5200.repositories.MessageRepository;
import edu.northeastern.cs5200.repositories.NutritionistRepository;
import edu.northeastern.cs5200.repositories.PatientLogRepository;
import edu.northeastern.cs5200.repositories.PersonRepository;
import edu.northeastern.cs5200.repositories.UserRepository;

@RestController
public class FoodService {

	private PersonRepository personRepository;
	private UserRepository userRepository;
	private NutritionistRepository nutritionistRepository;
	private PatientLogRepository patientLogRepository;
	private FoodRepository foodRepository;
	private DietRepository dietRepository;
	private MessageRepository messageRepository;
	private AdminRepository adminRepository;

	@Autowired
	public FoodService(PersonRepository personRepository, UserRepository userRepository,
			NutritionistRepository nutritionistRepository, PatientLogRepository patientLogRepository,
			FoodRepository foodRepository, DietRepository dietRepository,
			MessageRepository messageRepository, AdminRepository adminRepository) {

		this.personRepository = personRepository;
		this.userRepository = userRepository;
		this.nutritionistRepository = nutritionistRepository;
		this.patientLogRepository = patientLogRepository;
		this.dietRepository = dietRepository;
		this.messageRepository = messageRepository;
		this.adminRepository = adminRepository;
		this.foodRepository = foodRepository;
	}

	@RequestMapping("/user/{username}/{password}")
	public int getUser(@PathVariable("username") String username, @PathVariable("password") String password) {
		return userRepository.findByCredentials(username, password);
	}

	@RequestMapping("/api/admin")
	public List<Admin> getAdmins() {
		List<Admin> admins = (List<Admin>) adminRepository.findAll();
		return admins;
	}

	@PostMapping("/api/admin")
	public Admin addAdmin(@RequestBody Admin admin) {
		adminRepository.save(admin);
		return admin;
	}

	@RequestMapping("/api/admin/{id}")
	public Admin findAdmin(@PathVariable("id") int id) {
		Admin admin = (Admin) this.adminRepository.findOne(id);
		return admin;
	}

	// Update a admin
	@PostMapping("/api/admin/{id}")
	public Admin updateAdmin(@RequestBody User n, @PathVariable("id") int id) {

		Admin current = this.adminRepository.findOne(id);
		current.setFirstName(n.getFirstName());
		current.setLastName(n.getLastName());
		current.setUsername(n.getUsername());
		current.setPassword(n.getPassword());

		this.adminRepository.save(current);
		return current;

	}

	// Delete a admin
	@DeleteMapping("/api/admin/{adminId}")
	public void deleteAdmin(@PathVariable("adminId") int id) {
		adminRepository.delete(id);
	}

	@RequestMapping("/api/user")
	public List<User> getUsers() {
		List<User> users = (List<User>) userRepository.findAll();
		return users;
	}

	@PostMapping("/api/user")
	public User addUser(@RequestBody User user) {
		userRepository.save(user);
		createDiet(user);
		return user;
	}

	public void createDiet(User user) {
		Diet diet = new Diet();
		diet.setUser(user);
		dietRepository.save(diet);
	}

	// Get a user's diet
	@RequestMapping("/api/user/{id}/diet")
	public Diet getUserDiet(@PathVariable("id") int id) {
		Diet diet = (Diet) this.dietRepository.findByUser(id);
		return diet;
	}

	// Get Diet's food
	@RequestMapping("/api/diet/{id}/food")
	public List<Food> findDietFood(@PathVariable("id") int id) {
		Diet diet = this.dietRepository.findOne(id);
		return diet.getFood();
	}

	@RequestMapping("/api/user/{id}")
	public User findUser(@PathVariable("id") int id) {
		User user = (User) this.userRepository.findOne(id);
		return user;
	}

	// Update Diet's food
	@PostMapping("/api/diet/{id}/food")
	public List<Food> updateDietFood(@PathVariable("id") int id, @RequestBody List<Food> food) {
		Diet diet = this.dietRepository.findOne(id);
		diet.setFood(food);
		this.dietRepository.save(diet);
		return diet.getFood();
	}

	// Update a user
	@PostMapping("/api/user/{id}")
	public User updateUser(@RequestBody User n, @PathVariable("id") int id) {

		User current = this.userRepository.findOne(id);
		current.setFirstName(n.getFirstName());
		current.setLastName(n.getLastName());
		current.setWeight(n.getWeight());
		current.setUsername(n.getUsername());
		current.setPassword(n.getPassword());

		this.userRepository.save(current);
		return current;

	}

	// Update a user doctor
	@PostMapping("/api/user/{id}/doctor/{docId}")
	public User setDoctor(@PathVariable("id") int id, @PathVariable("docId") int docId) {

		User current = this.userRepository.findOne(id);
		PatientLog log = this.patientLogRepository.findByNut(docId);
		current.setPatientLog(log);
		this.userRepository.save(current);
		return current;

	}

	// Get a user doctor
	@RequestMapping("/api/user/{id}/doctor")
	public Nutritionist getDoctor(@PathVariable("id") int id) {

		User current = this.userRepository.findOne(id);

		return current.getPatientLog().getNutritionist();

	}

	// Delete a user
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		User user = this.userRepository.findOne(id);
		Diet diet = this.dietRepository.findByUser(id);
		this.dietRepository.delete(diet.getId());
		// this.userRepository.delete(id);
	}

	// Nutritionist Mapping and Functions
	// Get a specific nutritionist by Id
	@RequestMapping("/api/nutritionist/{id}")
	public Nutritionist findNutritionist(@PathVariable("id") int id) {
		Nutritionist nutritionist = (Nutritionist) this.nutritionistRepository.findOne(id);
		return nutritionist;
	}

	// Get a nutritionist patients
	@RequestMapping("/api/nutritionist/{id}/users")
	public List<User> findPatients(@PathVariable("id") int id) {
		PatientLog log = this.patientLogRepository.findByNut(id);
		return log.getUsers();
	}

	// Get a nutritionist patients
	@PostMapping("/api/nutritionist/{id}/users")
	public int deletePatient(@PathVariable("id") int id, @RequestBody User user) {
		PatientLog log = this.patientLogRepository.findByNut(id);
		log.removeUser(user);
		this.userRepository.save(user);
		return 0;
	}

	// Get all nutritionists
	@RequestMapping("/api/nutritionist")
	public List<Nutritionist> getNutritionists() {
		List<Nutritionist> nutritionists = (List<Nutritionist>) this.nutritionistRepository.findAll();
		return nutritionists;
	}

	// Add a nutritionist
	@PostMapping("/api/nutritionist")
	public Nutritionist addNutritionist(@RequestBody Nutritionist nutritionist) {

		PatientLog log = new PatientLog();
		log.setNutritionist(nutritionist);

		this.nutritionistRepository.save(nutritionist);
		this.patientLogRepository.save(log);
		return nutritionist;
	}

	// Update a nutritionist
	@PostMapping("/api/nutritionist/{id}")
	public Nutritionist updateNutritionist(@RequestBody Nutritionist n, @PathVariable("id") int id) {

		Nutritionist current = this.nutritionistRepository.findOne(id);
		current.setFirstName(n.getFirstName());
		current.setLastName(n.getLastName());
		current.setMedicalId(n.getMedicalId());
		current.setUsername(n.getUsername());
		current.setPassword(n.getPassword());

		this.nutritionistRepository.save(current);
		return current;

	}

	// Delete a nutritionist
	@DeleteMapping("/api/nutritionist/{id}")
	public void deleteNutritionist(@PathVariable("id") int id) {

		Nutritionist current = this.nutritionistRepository.findOne(id);
		PatientLog log = this.patientLogRepository.findByNut(id);
		this.patientLogRepository.delete(log.getId());
//		this.nutritionistRepository.delete(current.getId());

	}

	@RequestMapping("api/patient")
	public List<PatientLog> returnAllLogs() {
		List<PatientLog> logs = (List<PatientLog>) this.patientLogRepository.findAll();
		return logs;
	}

	// Add food
	@PostMapping("/api/food")
	public Food addFood(@RequestBody Food food) {
		this.foodRepository.save(food);
		return food;
	}

	// Find food
	@RequestMapping("/api/food")
	public List<Food> getFood() {
		List<Food> foods = (List<Food>) this.foodRepository.findAll();
		return foods;
	}

	// Delete food
	@DeleteMapping("/api/food")
	public void deleteFood(@RequestBody Food food) {
		this.foodRepository.delete(food);
	}

	// Delete food
	@DeleteMapping("/api/food/{id}")
	public void deleteFoodById(@PathVariable("id") int id) {
		this.foodRepository.delete(id);
	}

}
