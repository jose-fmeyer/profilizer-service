package com.profilizer.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.profilizer.integration.ITSetupRule;
import com.profilizer.repository.document.PersonalityTest;
import com.profilizer.util.TestUtils;

import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalityTestControllerIT {
	
	@ClassRule
	public static ITSetupRule itSetupRule = new ITSetupRule();

	@Value("${web.security.username}")
	private String username;

	@Value("${web.security.password}")
	private String password;

	@Test
	public void testCreatePersonalityTest() throws Exception {
		PersonalityTest personalityTest = given().contentType(ContentType.JSON)
				.auth().preemptive().basic(this.username, this.password)
				.body(TestUtils.loadPersonalityTestContent())
				.when()
				.post("/tests").as(PersonalityTest.class);
		assertNotNull(personalityTest);
	}
	
	@Test
	public void testUpdatePersonalityTest() throws Exception {
		PersonalityTest personalityTest = given().contentType(ContentType.JSON)
				.auth().preemptive().basic(this.username, this.password)
				.body(TestUtils.loadPersonalityTestContent())
				.when()
				.post("/tests").as(PersonalityTest.class);
		
		personalityTest.setPercentageCompletion(30);
		
		PersonalityTest updatedPersonalityTest = given().contentType(ContentType.JSON)
			.auth().preemptive().basic(this.username, this.password)
			.body(personalityTest)
			.when()
			.put("/tests")
			.as(PersonalityTest.class);
		assertEquals(30, updatedPersonalityTest.getPercentageCompletion());
	}

	@Test
	public void testGetPersonalityTests() {
		given().contentType(ContentType.JSON)
				.auth().preemptive().basic(this.username, this.password)
				.when().get("/tests").then()
				.statusCode(HttpStatus.OK.value());
	}
}
