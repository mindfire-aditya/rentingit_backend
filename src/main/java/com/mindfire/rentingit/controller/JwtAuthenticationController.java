package com.mindfire.rentingit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.rentingit.dto.request.LoginRequest;
import com.mindfire.rentingit.dto.request.SignupRequest;
import com.mindfire.rentingit.repository.RoleRepository;
import com.mindfire.rentingit.repository.UserRepository;
import com.mindfire.rentingit.services.AddUsers;

/**
 * @author ujjwalk
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rentingIt/user")
public class JwtAuthenticationController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AddUsers addUser;

	/*
	 * imput- user name and password 
	 * output - response entity with JWWt token
	 */
	
	/**
	 * method for user signin
	 * @param jwtRequest
	 * @return response entity
	 * @throws Exception
	 */
	@PostMapping(value = "/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest jwtRequest) throws Exception {

		return addUser.authUser(jwtRequest);
	}

	/*
	 * imput- user name, password and email 
	 * output - success msg and add user to user table
	 */
	
	/**
	 * method for user signup
	 * @param signuprequest
	 * @return response entity
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signuprequest) {

		return addUser.addingUser(signuprequest);
	}

}
