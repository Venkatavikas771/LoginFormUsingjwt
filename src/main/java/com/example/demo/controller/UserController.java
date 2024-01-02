package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UserAuthenticationProvider;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@CrossOrigin("*")
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserAuthenticationProvider userAuthenticationProvider;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		// System.out.println("This method was hit");
		User user1 = userService.adduser(user);
		user1.setToken(userAuthenticationProvider.createtoken(user.getEmail()));
		return ResponseEntity.ok(user1);

	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		// System.out.println("This method was hit");
		User user1 = userService.loginUser(user);
		user1.setToken(userAuthenticationProvider.createtoken(user.getEmail()));
		return ResponseEntity.ok(user1);

	}

	 @GetMapping("/getAllUsers")
	 public List<User> getAll(User user)
	 {
		List<User> alld= userService.getAll(user);
		return alld;
	 }
	 
	 
	 @PostMapping("/updateUser")
	    public ResponseEntity<User> updateUser( @RequestBody User updatedUser) {
	        User updatedUserData = userService.updateUser(updatedUser);
	 
	        if (updatedUserData != null) {
	            return new ResponseEntity<>(updatedUserData, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 
	 @DeleteMapping("/deleteUser/{email}")
	    public ResponseEntity<String> deleteUser(@PathVariable String email) {
	        String result=userService.deleteUser(email);
	        return ResponseEntity.ok(result);
	    }
	
	 
	 
	 
	 
	 
}
