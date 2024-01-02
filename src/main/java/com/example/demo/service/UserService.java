package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private PasswordEncoder passwordencoder;

	public User adduser(User user) {
		user.setPassword(passwordencoder.encode(user.getPassword()));
		if (userrepo.save(user) != null) {
			return user;
		}
		return null;
	}

	public User loginUser(User user) {
		User user1 = userrepo.findByEmail(user.getEmail());
		if (passwordencoder.matches(user.getPassword(), user1.getPassword())) {
			return user;
		}
		return null;
	}

	public User findByEmail(String email) {
		return userrepo.findByEmail(email);
	}

public List<User> getAll(User user) {
		
		
		List<User> all=userrepo.findAll();
		return all;
	}	

public User updateUser(User updatedUser) {
    try {
        User existingUser = userrepo.findByEmail(updatedUser.getEmail());
        if (existingUser != null) {
            // Update user fields as needed
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setMobileNumber(updatedUser.getMobileNumber());

            // Check if the password is not empty and encode it
            String newPassword = updatedUser.getPassword();
            if (newPassword != null && !newPassword.isEmpty()) {
                String encodedPassword = passwordencoder.encode(newPassword);
                existingUser.setPassword(encodedPassword);
            }

            // Update other fields as needed

            // Save the updated user
            return userrepo.save(existingUser);
        } else {
            // Handle the case where existingUser is null (e.g., throw an exception or return a default value)
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    return null; // Handle appropriately based on your use case
}

public String deleteUser(String email) {
	User user1=userrepo.findByEmail(email);
	userrepo.delete(user1);
	return "User Deleted Successfully";
}





}
