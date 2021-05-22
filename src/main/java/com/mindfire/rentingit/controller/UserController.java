package com.mindfire.rentingit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.rentingit.dto.request.UserDetailsInfoRequest;
import com.mindfire.rentingit.entity.User;
import com.mindfire.rentingit.entity.UserDetailsInfo;
import com.mindfire.rentingit.exception.ResourceNotFoundException;
import com.mindfire.rentingit.repository.UserDetailsInfoRepository;
import com.mindfire.rentingit.repository.UserRepository;
import com.mindfire.rentingit.services.AddUsers;

/**
 * @author ujjwalk
 *
 */
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

	/**
	 * get all users
	 * 
	 * @return all user
	 */
	@GetMapping("/all")
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	/**
	 * get user by id
	 * 
	 * @param userId
	 * @return user
	 */
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value = "id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}

	/**
	 * method for adding extra details for the registered User
	 * 
	 * @param userInfoDetails
	 * @param userId
	 * @return response entity
	 */
	@PostMapping(value = "/add-info/{id}")
	public ResponseEntity<?> addUserInfo(@RequestBody UserDetailsInfoRequest userInfoDetails,
			@PathVariable(value = "id") long userId) {

		return addUser.addingUserDetails(userInfoDetails, userId);
	}

	/**
	 * update user details
	 * 
	 * @param existingUserDetails
	 * @param userId
	 * @return user details
	 */
	@PutMapping("/update-info/{id}")
	public UserDetailsInfo userDetailsUpdate(@RequestBody UserDetailsInfo existingUserDetails,
			@PathVariable("id") long userId) {
		return addUser.updateUserInfo(existingUserDetails, userId);
	}

	/**
	 * update user details
	 * 
	 * @param user
	 * @param userId
	 * @return user
	 */
	@PutMapping("/update-credentials/{id}")
	public User userCredentialsUpdate(@RequestBody User user, @PathVariable("id") long userId) {
		return addUser.updateUserCredentials(user, userId);
	}

	/**
	 * get user details by id
	 * 
	 * @param userId
	 * @return user details
	 */
	@GetMapping("/view-info/{id}")
	public UserDetailsInfo getUserDetailsById(@PathVariable(value = "id") long userId) {
		return this.userDetailsInfoRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}

}
