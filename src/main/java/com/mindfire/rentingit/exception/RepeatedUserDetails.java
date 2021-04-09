package com.mindfire.rentingit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RepeatedUserDetails extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;

	public RepeatedUserDetails(String message) {
	    this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}


