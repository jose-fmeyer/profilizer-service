package com.profilizer.controller;

import static com.profilizer.util.TestUtils.ANSWER_UPDATE;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.profilizer.integration.ITSetupRule;
import com.profilizer.repository.document.Answer;
import com.profilizer.repository.document.PersonalityTest;
import com.profilizer.util.SerializationUtils;
import com.profilizer.util.TestUtils;

import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerControllerIT {

	@ClassRule
	public static ITSetupRule itSetupRule = new ITSetupRule();

	@Value("${web.security.username}")
	private String username;

	@Value("${web.security.password}")
	private String password;

	@Test
	public void testCreateAnswer() throws Exception {
		PersonalityTest personalityTest = given().contentType(ContentType.JSON)
				.auth().preemptive().basic(this.username, this.password)
				.body(TestUtils.loadPersonalityTestContent())
				.when()
				.post("/tests").as(PersonalityTest.class);
		
		Answer answerBody = SerializationUtils.deserialize(TestUtils.loadAnswerContent(), Answer.class);
		answerBody.setPersonalityTestId(personalityTest.getId());
		
		Answer answer = given()
				.contentType(ContentType.JSON)
				.auth().preemptive().basic(this.username, this.password)
				.body(answerBody)
				.when()
				.post("/answers").as(Answer.class);
		assertNotNull(answer);
	}
	
	@Test
	public void testUpdateAnswer() throws Exception {
		PersonalityTest personalityTest = given().contentType(ContentType.JSON)
				.auth().preemptive().basic(this.username, this.password)
				.body(TestUtils.loadPersonalityTestContent())
				.when()
				.post("/tests").as(PersonalityTest.class);
		
		Answer answerBody = SerializationUtils.deserialize(TestUtils.loadAnswerContent(), Answer.class);
		answerBody.setPersonalityTestId(personalityTest.getId());
		
		Answer answer = given()
				.contentType(ContentType.JSON)
				.auth().preemptive().basic(this.username, this.password)
				.body(answerBody)
				.when()
				.post("/answers").as(Answer.class);
		
		answer.setAnswer(ANSWER_UPDATE);
		
		Answer answerUpdated = given()
			.contentType(ContentType.JSON)
			.auth().preemptive().basic(this.username, this.password)
			.body(answer)
			.when()
			.put("/answers")
			.as(Answer.class);
		assertEquals(ANSWER_UPDATE, answerUpdated.getAnswer());
	}

	@Test
	public void testGetAnswersByTestId() throws IOException {
		PersonalityTest personalityTest = given().contentType(ContentType.JSON)
				.auth().preemptive().basic(this.username, this.password)
				.body(TestUtils.loadPersonalityTestContent())
				.when()
				.post("/tests").as(PersonalityTest.class);
		
		Answer answerBody = SerializationUtils.deserialize(TestUtils.loadAnswerContent(), Answer.class);
		answerBody.setPersonalityTestId(personalityTest.getId());
		
		Answer answer = given().contentType(ContentType.JSON)
				.auth().preemptive().basic(this.username, this.password)
				.body(answerBody)
				.when()
				.post("/answers").as(Answer.class);
		
		given().contentType(ContentType.JSON)
				.auth().preemptive().basic(this.username, this.password)
				.pathParam("personalityTestId", answer.getPersonalityTestId())
				.when().get("/answers/{personalityTestId}").then()
				.statusCode(HttpStatus.OK.value());
	}
}
