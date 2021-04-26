/*
 * @author Ujjwal Kumar
 */
package com.mindfire.rentingit.exception;

public class RoleNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public RoleNotFound(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
