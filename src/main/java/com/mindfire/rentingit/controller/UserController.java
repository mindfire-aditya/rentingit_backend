package com.mindfire.rentingit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.rentingit.repository.UserDetailsInfoRepository;
import com.mindfire.rentingit.repository.UserRepository;
import com.mindfire.rentingit.services.AddUsers;
import com.mindfire.rentingit.dto.request.UserDetailsInfoRequest;
import com.mindfire.rentingit.entity.User;
import com.mindfire.rentingit.entity.UserDetailsInfo;
import com.mindfire.rentingit.exception.ResourceNotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rentingIt/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDetailsInfoRepository userDetailsInfoRepository;

	@Autowired
	AddUsers addUser;

	// get all users
	@GetMapping("/all")
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	// get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value = "id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}

	// method for adding extra details for the registered User
	@PostMapping(value = "/add-info/{id}")
	public ResponseEntity<?> addUserInfo(@RequestBody UserDetailsInfoRequest userInfoDetails,
			@PathVariable(value = "id") long userId) {

		return addUser.addingUserDetails(userInfoDetails, userId);
	}

	// update user details
	@PutMapping("/update-info/{id}")
	public UserDetailsInfo userDetailsUpdate(@RequestBody User user, @RequestBody UserDetailsInfo existingUserDetails,
			@PathVariable("id") long userId) {

		return addUser.updateUserInfo(user, existingUserDetails, userId);
	}

	// get user details by id
	@GetMapping("/view-info/{id}")
	public UserDetailsInfo getUserDetailsById(@PathVariable(value = "id") long userId) {
		return this.userDetailsInfoRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}

	// delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}

}
