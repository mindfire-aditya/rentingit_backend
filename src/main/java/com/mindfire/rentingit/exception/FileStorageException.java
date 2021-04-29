package com.mindfire.rentingit.exception;

public class FileStorageException extends RuntimeException {

	/**
	 * author Ujjwal Kumar
	 */
	private static final long serialVersionUID = 1L;

	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
