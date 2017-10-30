package com.profilizer.exception;

public class InvalidResponseException extends RuntimeException {

	private static final long serialVersionUID = -21952647614613661L;

	public InvalidResponseException(String message) {
		super(message);
	}
	
	public InvalidResponseException(String message, Throwable exception) {
		super(message, exception);
	}
}
