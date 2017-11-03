package com.profilizer.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.profilizer.integration.ITSetupRule;
import com.profilizer.repository.document.PersonalityTestQuestions;

import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalityTestQuestionsControllerIT {
	
	@ClassRule
	public static ITSetupRule itSetupRule = new ITSetupRule();

	@Value("${web.security.username}")
	private String username;

	@Value("${web.security.password}")
	private String password;
	
	@Test
	public void testGetTestQuestions() {
		PersonalityTestQuestions testQuestions = given().contentType(ContentType.JSON)
				.auth().preemptive().basic(this.username, this.password)
				.when().get("/tests/questions")
				.as(PersonalityTestQuestions.class);
		assertNotNull(testQuestions);
	}
}
