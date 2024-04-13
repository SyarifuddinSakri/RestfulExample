package com.syarifuddin.app.rest.Controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.syarifuddin.app.rest.Models.User;
import com.syarifuddin.app.rest.Repo.UserRepo;


@RestController
public class ApiController {
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping(value="/mari")
	public String getPage() {
		return "Welcome";
	}
	
	@GetMapping(value = "/users")
	public List<User> getUsers() {
		  return userRepo.findAll();  
	}
	
	@PostMapping(value = "/save")
	public String saveUser(User user) {
		userRepo.save(user);
		return "Savedd.....";
	}
	
	@PostMapping(value = "/create")
	public User createUser(@RequestBody User user) {
		userRepo.save(user);
		System.out.println("Created new User");
		return user;
	}
	
	@PutMapping(value = "/update/{id}")
	public String updateUser(@PathVariable long id, @RequestBody User user) {
		User updatedUser = userRepo.findById(id).get();
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setAge(user.getAge());
		updatedUser.setOccupation(user.getOccupation());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setJoinDate(user.getJoinDate());
		userRepo.save(updatedUser);
		return "savedd......";
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		userRepo.deleteById(id);
		return "Deleted user "+id;
	}
	
//	this is for the custom getMapping 
	@GetMapping(value = "users/ageRange")
	public List<User> getUserByAgeBetween(@RequestParam int minAge, @RequestParam int maxAge){
		return userRepo.findUsersByAgeBetween(minAge,maxAge);
	}
	
	@GetMapping(value ="users/ageMin")
	public List<User> getUserByAgeMoreThan(@RequestParam int minAge){
		return userRepo.findUsersByAgeMoreThan(minAge);
	}
	
	@GetMapping(value = "users/dateRange")
	public List<User> getUserByDateJoinRange(@RequestParam Date startDate, @RequestParam Date endDate){
		return userRepo.findUserByDateJoinRange(startDate, endDate);
	}
}
