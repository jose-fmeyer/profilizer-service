package com.profilizer.integration;

import org.apache.commons.lang3.StringUtils;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.restassured.RestAssured;

public class ITSetupRule implements TestRule {
	
	private static final String DEFAULT_SERVER_PORT = "8080";
	private static final String DEFAULT_SERVER_HOST = "http://localhost";
	private static final String DEFAULT_SERVICE_BASE_PATH = "";
	
	private static final String SERVER_PORT_PARAMETER = "server.port";
	private static final String SERVER_HOST_PARAMETER = "server.host";
	private static final String SERVER_BASE_PATH_PARAMETER = "server.base.path";

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				setup();
				base.evaluate();
			}
		};
	}
			
	private void setup() {
		String port = System.getProperty(SERVER_PORT_PARAMETER);
		if (port == null) {
			port = DEFAULT_SERVER_PORT;
		}
		RestAssured.port = Integer.valueOf(port);
		
		String basePath = System.getProperty(SERVER_BASE_PATH_PARAMETER);
		if (basePath == null) {
			basePath = DEFAULT_SERVICE_BASE_PATH;
		}
		RestAssured.basePath = basePath;
		
		String host = System.getProperty(SERVER_HOST_PARAMETER);
		if (host == null) {
			host = DEFAULT_SERVER_HOST;
		}
		else {
			if (!host.contains("http")) {
				host = StringUtils.join("http://", host);
			}
		}
		RestAssured.baseURI = host;
	}
}
