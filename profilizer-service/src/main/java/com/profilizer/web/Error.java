package com.profilizer.web;

import org.springframework.http.HttpStatus;

public class Error {

	private HttpStatus status;
	
	private String[] messages;
	
	public Error(HttpStatus status, String... messages) {
		this.status = status;
		this.messages = messages;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}
}
