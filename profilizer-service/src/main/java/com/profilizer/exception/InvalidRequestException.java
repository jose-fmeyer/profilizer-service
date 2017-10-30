package com.profilizer.exception;

public class InvalidRequestException extends RuntimeException {

	private static final long serialVersionUID = -5452136627161111131L;
	
	public InvalidRequestException() {
	}

	public InvalidRequestException(String message) {
		super(message);
	}
}
