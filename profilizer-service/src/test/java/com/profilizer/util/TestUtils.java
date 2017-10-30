package com.profilizer.util;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.profilizer.repository.document.Answer;
import com.profilizer.repository.document.PersonalityTest;

public class TestUtils {
	
	public static final String ANSWER_ID = "abc1234";
	public static final String QUESTION = "What is your gender?";
	public static final String ANSWER = "male";
	public static final String ANSWER_UPDATE = "female";
	public static final String PERSONALITY_TEST_ID = "12345abc";
	public static final String OWNER = "User1";
	
	public static Answer createAnswer() {
		Answer answer = new Answer();
		answer.setId(ANSWER_ID);
		answer.setQuestion(QUESTION);
		answer.setAnswer(ANSWER);
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
	
	public static <T extends Object> String getConstraintViolationMessage(Set<ConstraintViolation<T>> violations) {
		ConstraintViolation<T> violation = violations.iterator().next();
		return violation.getMessage();
	}
}
