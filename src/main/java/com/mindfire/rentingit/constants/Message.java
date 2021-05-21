package com.mindfire.rentingit.constants;

import org.springframework.stereotype.Component;

/**
 * @author ujjwalk this file contains the constant messages that are fired
 *         during the exceution of program
 *
 */
@Component
public class Message {

	
	public final String EMAIL_TAKEN = "Error: Email is already taken!!";
	public final String USERNAME_TAKEN = "Error: UserName is already taken!!";
	public final String ROLE_NOT_FOUND = "Error: Role is not found.";
	public final String USER_REGISTERED = "User registered successfully! !";
	public final String USER_INFO_ADDED = "User details are added successfully! !";
	public final String PRODUCTS_ADDED = "Product and its details are added successfully! !";
	public final String PRODUCTS_DELETED = "Product and its details are deleted successfully! !";
	public final String CANT_DELETE_PROD = "Sorry you cant delete this products! !";
	public final String FILE_UPLOADED = "File uploaded successfully !!";
	public final String FILES_UPLOADED = "Files uploaded successfully !!";
	public final String EMPTY_FILE_SELECTED = "Sorry no file is selected!!";
	public final String ORDER_ADDED = "Order added successfully !!";
	public final String CANT_ADDED_ORDER = "Sorry request order FAILED";

}
