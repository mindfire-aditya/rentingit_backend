package com.mindfire.rentingit.constants;

import org.springframework.stereotype.Component;

@Component
public class Message {

	public final String EMAIL_TAKEN = "Error: Email is already taken!!";
	public final String USERNAME_TAKEN =  "Error: UserName is already taken!!";
	public final String ROLE_NOT_FOUND = "Error: Role is not found.";
	public final String USER_REGISTERED = "User registered successfully! !";
}
