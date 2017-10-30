package com.profilizer.manager;

import static com.profilizer.repository.validation.ValidationMessages.ANSWER_REQUIRED;
import static com.profilizer.repository.validation.ValidationMessages.QUESTION_REQUIRED;
import static com.profilizer.repository.validation.ValidationMessages.TEST_ID_REQUIRED;
import static com.profilizer.util.TestUtils.ANSWER;
import static com.profilizer.util.TestUtils.ANSWER_ID;
import static com.profilizer.util.TestUtils.ANSWER_UPDATE;
import static com.profilizer.util.TestUtils.PERSONALITY_TEST_ID;
import static com.profilizer.util.TestUtils.QUESTION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.profilizer.repository.AnswerRepository;
import com.profilizer.repository.document.Answer;
import com.profilizer.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerManagerTest {
	
	private Validator validator;
	
	@MockBean
	private AnswerRepository answerRepository;

	@Autowired
	private AnswerManager answerManager;
	
	private List<Answer> answers;
	
	private Answer answer;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
		
		this.answers = Arrays.asList(TestUtils.createAnswer());
		this.answer = TestUtils.createAnswer();
	}
	
	@Test
	public void testCreateAnswer() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		Answer answer = this.answerManager.createAnswer(QUESTION, ANSWER, PERSONALITY_TEST_ID);
		assertNotNull(answer);
	}
	
	@Test
	public void testCreateAnswerCheckQuestion() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		Answer answer = this.answerManager.createAnswer(QUESTION, ANSWER, PERSONALITY_TEST_ID);
		assertEquals(QUESTION, answer.getQuestion());
	}
	
	@Test
	public void testCreateAnswerCheckAnswer() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		Answer answer = this.answerManager.createAnswer(QUESTION, ANSWER, PERSONALITY_TEST_ID);
		assertEquals(ANSWER, answer.getAnswer());
	}
	
	@Test
	public void testCreateAnswerCheckPersonalityTest() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		Answer answer = this.answerManager.createAnswer(QUESTION, ANSWER, PERSONALITY_TEST_ID);
		assertEquals(PERSONALITY_TEST_ID, answer.getPersonalityTestId());
	}
	
	@Test
	public void testCreateAnswerCheckCreationDate() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		Answer answer = this.answerManager.createAnswer(QUESTION, ANSWER, PERSONALITY_TEST_ID);
		assertNotNull(answer.getCreationDate());
	}
	
	@Test
	public void testCreateAnswerWithQuestionNull() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		Answer answer = this.answerManager.createAnswer(null, ANSWER, PERSONALITY_TEST_ID);
		Set<ConstraintViolation<Answer>> violations = validator.validate(answer);
		assertEquals(QUESTION_REQUIRED, TestUtils.getConstraintViolationMessage(violations));
	}
	
	@Test
	public void testCreateAnswerWithQuestionEmpty() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		Answer answer = this.answerManager.createAnswer("", ANSWER, PERSONALITY_TEST_ID);
		Set<ConstraintViolation<Answer>> violations = validator.validate(answer);
		assertEquals(QUESTION_REQUIRED, TestUtils.getConstraintViolationMessage(violations));
	}
	
	@Test
	public void testCreateAnswerWithAnswerNull() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		Answer answer = this.answerManager.createAnswer(QUESTION, null, PERSONALITY_TEST_ID);
		Set<ConstraintViolation<Answer>> violations = validator.validate(answer);
		assertEquals(ANSWER_REQUIRED, TestUtils.getConstraintViolationMessage(violations));
	}
	
	@Test
	public void testCreateAnswerWithAnswerEmpty() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		Answer answer = this.answerManager.createAnswer(QUESTION, "", PERSONALITY_TEST_ID);
		Set<ConstraintViolation<Answer>> violations = validator.validate(answer);
		assertEquals(ANSWER_REQUIRED, TestUtils.getConstraintViolationMessage(violations));
	}
	
	@Test
	public void testCreateAnswerWithTestIdNull() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		Answer answer = this.answerManager.createAnswer(QUESTION, ANSWER, null);
		Set<ConstraintViolation<Answer>> violations = validator.validate(answer);
		assertEquals(TEST_ID_REQUIRED, TestUtils.getConstraintViolationMessage(violations));
	}
	
	@Test
	public void testCreateAnswerWithTestIdEmpty() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		Answer answer = this.answerManager.createAnswer(QUESTION, ANSWER, "");
		Set<ConstraintViolation<Answer>> violations = validator.validate(answer);
		assertEquals(TEST_ID_REQUIRED, TestUtils.getConstraintViolationMessage(violations));
	}
	
	@Test
	public void testGetAnswersByTestId() {
		given(this.answerRepository.findByPersonalityTestId(PERSONALITY_TEST_ID)).willReturn(this.answers);
		List<Answer> answers = this.answerManager.getAnswersByTestId(PERSONALITY_TEST_ID);
		assertSame(this.answers, answers);
	}
	
	@Test
	public void testUpdateAnswer() {
		given(this.answerRepository.findOne(ANSWER_ID)).willReturn(this.answer);
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		Answer answerUpdated = this.answerManager.updateAnswer(ANSWER_ID, ANSWER_UPDATE);
		assertEquals(answerUpdated.getAnswer(), ANSWER_UPDATE);
	}
}
