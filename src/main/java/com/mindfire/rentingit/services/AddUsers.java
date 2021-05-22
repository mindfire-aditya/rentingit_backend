package com.mindfire.rentingit.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mindfire.rentingit.constants.Message;
import com.mindfire.rentingit.dto.request.LoginRequest;
import com.mindfire.rentingit.dto.request.SignupRequest;
import com.mindfire.rentingit.dto.request.UserDetailsInfoRequest;
import com.mindfire.rentingit.dto.response.JwtResponse;
import com.mindfire.rentingit.dto.response.MessageResponse;
import com.mindfire.rentingit.entity.Erole;
import com.mindfire.rentingit.entity.Role;
import com.mindfire.rentingit.entity.User;
import com.mindfire.rentingit.entity.UserDetailsInfo;
import com.mindfire.rentingit.exception.RepeatedUserDetails;
import com.mindfire.rentingit.exception.ResourceNotFoundException;
import com.mindfire.rentingit.exception.RoleNotFound;
import com.mindfire.rentingit.helper.Jwtutil;
import com.mindfire.rentingit.repository.RoleRepository;
import com.mindfire.rentingit.repository.UserDetailsInfoRepository;
import com.mindfire.rentingit.repository.UserRepository;

/**
 * @author ujjwalk
 *
 */

@Service
public class AddUsers {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserDetailsInfoRepository userDetailsInfoRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	private Jwtutil jwtUtil;
	@Autowired
	Message msg;

	public ResponseEntity<?> authUser(LoginRequest jwtRequest) throws Exception {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtil.generateJwtToken(authentication);

		CustomerUserDetails userDetails = (CustomerUserDetails) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	/**
	 * function for checking alredy existing user as well as adding new user
	 * 
	 * @param signuprequest
	 * @return response entity
	 */
	public ResponseEntity<?> addingUser(SignupRequest signuprequest) {

		if (userRepository.existsByUsername(signuprequest.getUsername())) {
			return ResponseEntity.ok(new RepeatedUserDetails(msg.USERNAME_TAKEN));

			// .badRequest()
			// .body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signuprequest.getEmail())) {
			return ResponseEntity.ok(new RepeatedUserDetails(msg.EMAIL_TAKEN));
		}

		// Create new user's account
		User user = new User(signuprequest.getEmail(), encoder.encode(signuprequest.getPassword()),
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

				case "user":
					Role userRole = roleRepository.findByName(Erole.ROLE_USER)
							.orElseThrow(() -> new RoleNotFound(msg.ROLE_NOT_FOUND));
					roles.add(userRole);
					break;
				case "admin":
					Role adminRole = roleRepository.findByName(Erole.ROLE_ADMIN)
							.orElseThrow(() -> new RoleNotFound(msg.ROLE_NOT_FOUND));
					roles.add(adminRole);
					break;
				default:
//					Role userRole = roleRepository.findByName(Erole.ROLE_USER)
//							.orElseThrow(() -> new RoleNotFound(msg.ROLE_NOT_FOUND));
//					roles.add(userRole);
					throw new RoleNotFound(msg.ROLE_NOT_FOUND);
				}

			});
		}
		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse(msg.USER_REGISTERED));
	}

	/**
	 * function for adding the user extra details in DB
	 * 
	 * @param userDetailsInfoRequest
	 * @param userId
	 * @return response entity
	 */
	public ResponseEntity<?> addingUserDetails(UserDetailsInfoRequest userDetailsInfoRequest, long userId) {

		// Create new userDetails info with required details
		UserDetailsInfo userDetails = new UserDetailsInfo(userId, userDetailsInfoRequest.getFirstName(),
				userDetailsInfoRequest.getLastName(), userDetailsInfoRequest.getPhoneNo(),
				userDetailsInfoRequest.getHouseNo(), userDetailsInfoRequest.getStreetNo(),
				userDetailsInfoRequest.getLane(), userDetailsInfoRequest.getDistrict(),
				userDetailsInfoRequest.getState(), userDetailsInfoRequest.getLandmark(),
				userDetailsInfoRequest.getCity(), userDetailsInfoRequest.getPincode(),
				userDetailsInfoRequest.getIdProofType(), userDetailsInfoRequest.getIdNumber());

		userDetailsInfoRepository.save(userDetails);

		return ResponseEntity.ok(new MessageResponse(msg.USER_INFO_ADDED));
	}

	/**
	 * updating user info
	 * 
	 * @param existingUserDetails
	 * @param userId
	 * @return user details
	 */
	public UserDetailsInfo updateUserInfo(UserDetailsInfo existingUserDetails, long userId) {

		UserDetailsInfo existingUserDetail = this.userDetailsInfoRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));

		existingUserDetail.setCity(existingUserDetails.getCity());
		existingUserDetail.setFirstName(existingUserDetails.getFirstName());
		existingUserDetail.setLastName(existingUserDetails.getLastName());
		existingUserDetail.setPhoneNo(existingUserDetail.getPhoneNo());
		existingUserDetail.setHouseNo(existingUserDetails.getHouseNo());
		existingUserDetail.setStreetNo(existingUserDetails.getStreetNo());
		existingUserDetail.setLane(existingUserDetails.getLane());
		existingUserDetail.setDistrict(existingUserDetails.getDistrict());
		existingUserDetail.setState(existingUserDetails.getState());
		existingUserDetails.setLandmark(existingUserDetails.getLandmark());
		existingUserDetail.setIdProofType(existingUserDetails.getIdProofType());
		existingUserDetail.setIdNumber(existingUserDetails.getIdNumber());

		this.userDetailsInfoRepository.save(existingUserDetail);
		return this.userDetailsInfoRepository.save(existingUserDetail);
	}

	/**
	 * updating user credentials
	 * 
	 * @param user
	 * @param userId
	 * @return updated user credentials
	 */
	public User updateUserCredentials(User user, long userId) {

		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));

		// updating details
		existingUser.setUsername(user.getUsername());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());

		return this.userRepository.save(existingUser);

	}

}
