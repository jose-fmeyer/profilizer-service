package com.profilizer.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.profilizer.repository.document.Answer;
import com.profilizer.repository.document.PersonalityTest;
import com.profilizer.repository.document.PersonalityTestQuestions;
import com.profilizer.repository.document.Question;

public class TestUtils {
	
	private static final String RESOURCE_ANSWER = "/answer.json";
	private static final String RESOURCE_UPDATE_ANSWER = "/update_answer.json";
	private static final String RESOURCE_ANSWER_MISSING_REQUIRED_FIELD = "/answer_missing_required_field.json";

	private static final String RESOURCE_PERSONALITY_TEST = "/personality_test.json";
	private static final String RESOURCE_UPDATE_PERSONALITY_TEST = "/update_personality_test.json";
	private static final String RESOURCE_PERSONALITY_TEST_MISSING_REQ_FIELD = "/personality_test_missing_required_field.json";
	
	public static final String USERNAME = "user";
	public static final String PASSWORD = "user123";
	
	public static final String ANSWER_ID = "abc1234";
	public static final String QUESTION = "What is your gender?";
	public static final String ANSWER = "male";
	public static final String ANSWER_UPDATE = "female";
	public static final String PERSONALITY_TEST_ID = "12345abc";
	public static final String OWNER = "User1";
	public static final String CATEGORY = "passion";
	
	public static Answer createAnswer() {
		Answer answer = new Answer();
		answer.setId(ANSWER_ID);
		answer.setQuestion(QUESTION);
		answer.setAnswer(QUESTION);
		answer.setCategory(CATEGORY);
		answer.setPersonalityTestId(PERSONALITY_TEST_ID);
		answer.setCreationDate(new Date());
		return answer;
	}
	
	public static PersonalityTest createPersonalityTest() {
		PersonalityTest personalityTest = new PersonalityTest();
		personalityTest.setId(PERSONALITY_TEST_ID);
		personalityTest.setCreationDate(new Date());
		personalityTest.setOwner(OWNER);
		personalityTest.setPercentageCompletion(10);
		return personalityTest;
	}
	
	public static PersonalityTestQuestions createPersonalityTestQuestions() {
		PersonalityTestQuestions testQuestions = new PersonalityTestQuestions();
		testQuestions.setId(PERSONALITY_TEST_ID);
		testQuestions.setQuestions(Arrays.asList(createQuestion()));
		return testQuestions;
	}
	
	public static Question createQuestion() {
		Question question = new Question();
		question.setCategory(CATEGORY);
		question.setQuestion(QUESTION);
		return question;
	}
	
	public static String loadAnswerContent() throws IOException {
		return ResourceUtil.loadResource(RESOURCE_ANSWER);
	}
	
	public static String loadUpdateAnswerContent() throws IOException {
		return ResourceUtil.loadResource(RESOURCE_UPDATE_ANSWER);
	}
	
	public static String loadAnswerMissingRequiredFieldContent() throws IOException {
		return ResourceUtil.loadResource(RESOURCE_ANSWER_MISSING_REQUIRED_FIELD);
	}
	
	public static String loadPersonalityTestContent() throws IOException {
		return ResourceUtil.loadResource(RESOURCE_PERSONALITY_TEST);
	}
	
	public static String loadUpdatePersonalityTestContent() throws IOException {
		return ResourceUtil.loadResource(RESOURCE_UPDATE_PERSONALITY_TEST);
	}
	
	public static String loadPersonalityTestMissingReqFieldContent() throws IOException {
		return ResourceUtil.loadResource(RESOURCE_PERSONALITY_TEST_MISSING_REQ_FIELD);
	}
	
	public static <T extends Object> String getConstraintViolationMessage(Set<ConstraintViolation<T>> violations) {
		ConstraintViolation<T> violation = violations.iterator().next();
		return violation.getMessage();
	}
}
