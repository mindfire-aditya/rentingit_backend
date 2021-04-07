package com.rentingit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.rentingit.payload.request.Loginrequest;
import com.rentingit.payload.request.SignupRequest;
import com.rentingit.repository.RoleRepository;
import com.rentingit.repository.UserRepository;
import com.rentingit.services.AddUsers;


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

	@PostMapping(value="/signin")
	public ResponseEntity<?> authenticateUser( @RequestBody Loginrequest jwtRequest) throws Exception{

		return addUser.authUser(jwtRequest);
		}

		@PostMapping("/signup")
		public ResponseEntity<?> registerUser(@RequestBody SignupRequest signuprequest) {
			
			return addUser.addingUser(signuprequest);
			
			
		}
		
		
}


