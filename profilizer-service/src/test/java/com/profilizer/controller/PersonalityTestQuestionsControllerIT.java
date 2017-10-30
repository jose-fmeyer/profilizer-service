package com.profilizer.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

import org.hamcrest.Matchers;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.profilizer.integration.ITSetupRule;

import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@TestPropertySource(value = "classpath:application.properties")
public class PersonalityTestQuestionsControllerIT {
	
	@ClassRule
	public static ITSetupRule itSetupRule = new ITSetupRule();

	@Value("${web.security.username}")
	private String username;

	@Value("${web.security.password}")
	private String password;
	
	@Test
	public void testGetTestQuestions() {
		given().contentType(ContentType.JSON)
				.auth().preemptive().basic(this.username, this.password)
				.when().get("/tests/questions")
				.then().statusCode(HttpStatus.OK.value())
				.body(not(Matchers.emptyArray()));
	}
}
