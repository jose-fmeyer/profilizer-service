package com.profilizer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class DocumentInvalidException extends RuntimeException {

	private static final long serialVersionUID = -7260898154670663371L;
	
	public DocumentInvalidException() {
	}
	
	public DocumentInvalidException(String message) {
		super(message);
	}
	
	public DocumentInvalidException(Throwable inner) {
		super(inner);
	}
}
