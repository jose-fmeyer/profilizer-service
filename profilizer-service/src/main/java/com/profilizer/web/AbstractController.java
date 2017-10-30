package com.profilizer.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.profilizer.exception.DocumentInvalidException;
import com.profilizer.exception.InvalidRequestException;
import com.profilizer.exception.InvalidResponseException;
import com.profilizer.util.MessageCodes;
import com.profilizer.util.MessageSourceUtils;

@RestController
public abstract class AbstractController {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(DocumentInvalidException.class)
    public ResponseEntity<Error> handleEntityInvalid(DocumentInvalidException exception) {
		Throwable cause = exception.getCause();
		if (cause instanceof NestedRuntimeException) {
			cause = ((NestedRuntimeException)cause).getRootCause();
		}
		
		if (cause instanceof ConstraintViolationException) {
			List<String> messages = new ArrayList<String>();
			for (ConstraintViolation<?> violation : ((ConstraintViolationException)cause).getConstraintViolations()) {
				String message = String.format(MessageSourceUtils.getMessage(this.messageSource, MessageCodes.CONSTRAINT_VIOLATION, violation.getPropertyPath().toString(), violation.getMessage()));
				messages.add(message);
			}
			
			return handleError(HttpStatus.BAD_REQUEST, messages.toArray(new String[messages.size()]));
		}
		else {
			return handleException(HttpStatus.BAD_REQUEST, exception);
		}
    }
    
    @ExceptionHandler(InvalidResponseException.class)
    public ResponseEntity<Error> handleInvalidResponse(InvalidResponseException exception) {
    	return handleException(HttpStatus.BAD_REQUEST, exception, MessageSourceUtils.getMessage(this.messageSource, MessageCodes.INVALID_RESPONSE));
    }
    
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Error> handleInvalidRequest(InvalidRequestException exception) {
    	return handleException(HttpStatus.BAD_REQUEST, exception, MessageSourceUtils.getMessage(this.messageSource, MessageCodes.INVALID_REQUEST));
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Error> handleAccessDenied(AccessDeniedException exception) {
    	return handleException(HttpStatus.UNAUTHORIZED, exception, MessageSourceUtils.getMessage(this.messageSource, MessageCodes.AUTHENTICATION_REQUIRED));
    }
    
    protected ResponseEntity<Error> handleException(HttpStatus status, Exception exception) {
    	return handleException(status, exception, exception.getMessage());
    }
    
    protected ResponseEntity<Error> handleException(HttpStatus status, Exception exception, String message) {
    	return handleError(status, message);
    }
    
    protected ResponseEntity<Error> handleError(HttpStatus status, String... messages) {
    	return new ResponseEntity<Error>(new Error(status, messages), status);
    }
}
