package com.profilizer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.mongobee.Mongobee;

@Configuration
public class DatabaseConfiguration {

	@Autowired
	private DatabaseProperties databaseProperties;

	@Bean
	public Mongobee mongobee() {
		String host = databaseProperties.getHost();
		String port = databaseProperties.getPort();
		String dbName = databaseProperties.getDatabase();
		String username = databaseProperties.getUsername();
		String password = databaseProperties.getPassword();
		
		StringBuilder url = new StringBuilder("mongodb://");
		url.append(username).append(":").append(password).append("@")
			.append(host).append(":").append(port).append("/").append(dbName);
		
		Mongobee runner = new Mongobee(url.toString());
		runner.setDbName("profilizer");
		runner.setChangeLogsScanPackage("com.profilizer.changelogs");

		return runner;
	}
}
