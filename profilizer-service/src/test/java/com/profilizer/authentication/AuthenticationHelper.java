package com.profilizer.authentication;

import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

@Component
public class AuthenticationHelper {
	
	public RequestPostProcessor basicAuth(final String username, final String password) {
		return mockRequest -> {			
			String value = username + ":" + password;
			mockRequest.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + new String(Base64.encode(value.getBytes())));
			return mockRequest;
		};
	}
}
