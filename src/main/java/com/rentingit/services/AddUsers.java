package com.rentingit.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.rentingit.exception.RepeatedUserDetails;
import com.rentingit.helper.Jwtutil;
import com.rentingit.model.Erole;
import com.rentingit.model.Role;
import com.rentingit.model.User;
import com.rentingit.payload.request.Loginrequest;
import com.rentingit.payload.request.SignupRequest;
import com.rentingit.payload.response.JwtResponse;
import com.rentingit.payload.response.MessageResponse;
import com.rentingit.repository.RoleRepository;
import com.rentingit.repository.UserRepository;
import com.rentingit.exception.RoleNotFound;
import com.rentingit.constants.Message;

@Service
public class AddUsers {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	private Jwtutil jwtUtil;
	@Autowired
	Message msg;
	
	public ResponseEntity<?> authUser(Loginrequest jwtRequest) throws Exception{

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken( jwtRequest.getUsername(),  jwtRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtil.generateJwtToken(authentication);
		
		CustomerUserDetails userDetails = (CustomerUserDetails) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	
	// function for checking alredy existing user as well as adding new user
	public ResponseEntity<?> addingUser(SignupRequest signuprequest) {
		if (userRepository.existsByUsername(signuprequest.getUsername())) {
			return ResponseEntity.ok(new RepeatedUserDetails(msg.USERNAME_TAKEN));
					
					//.badRequest()
					//.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signuprequest.getEmail())) {
			return ResponseEntity.ok(new RepeatedUserDetails(msg.EMAIL_TAKEN));
		}

		// Create new user's account
		User user = new User( 
							 signuprequest.getEmail(),
							 encoder.encode(signuprequest.getPassword()),
							 signuprequest.getUsername());

		Set<String> strRoles = signuprequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(Erole.ROLE_USER)
					.orElseThrow(() -> new RoleNotFound(msg.ROLE_NOT_FOUND));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(Erole.ROLE_ADMIN)
							.orElseThrow(() -> new RoleNotFound(msg.ROLE_NOT_FOUND));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByName(Erole.ROLE_USER)
							.orElseThrow(() -> new RoleNotFound(msg.ROLE_NOT_FOUND));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse(msg.USER_REGISTERED));
	}


}
