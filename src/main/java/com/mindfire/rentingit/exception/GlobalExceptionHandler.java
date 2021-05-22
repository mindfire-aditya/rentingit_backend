package com.mindfire.rentingit.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * @author ujjwalk
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * @param exception
	 * @param request
	 * @return bad request
	 */
	@ExceptionHandler(RepeatedUserDetails.class)
	public ResponseEntity<?> resourceNotFoundHandling(RepeatedUserDetails exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param exception
	 * @param request
	 * @return internal server error msg
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
