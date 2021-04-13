package com.mindfire.rentingit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mindfire.rentingit.payload.request.Loginrequest;
import com.mindfire.rentingit.payload.request.SignupRequest;
import com.mindfire.rentingit.repository.RoleRepository;
import com.mindfire.rentingit.repository.UserRepository;
import com.mindfire.rentingit.services.AddUsers;


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

	//method for signin
	@PostMapping(value="/signin")
	public ResponseEntity<?> authenticateUser( @RequestBody Loginrequest jwtRequest ) throws Exception{

		return addUser.authUser(jwtRequest);
		}

	//method for signup 
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signuprequest) {
			
		return addUser.addingUser(signuprequest);	
	}
		
		
}


