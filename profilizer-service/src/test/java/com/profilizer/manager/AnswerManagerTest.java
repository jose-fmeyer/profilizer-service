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
import com.profilizer.repository.document.PersonalityTest;
import com.profilizer.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerManagerTest {
	
	private Validator validator;
	
	@MockBean
	private AnswerRepository answerRepository;
	
	@MockBean
	private PersonalityTestManager testManager;
	
	@MockBean
	private PersonalityTestQuestionsManager testQuestionsManager;

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
		given(this.testManager.getPersonalityTest(any(String.class))).willReturn(TestUtils.createPersonalityTest());
		given(this.testManager.updatePersonalityTest(any(PersonalityTest.class))).will(returnsFirstArg());
		
		given(this.testQuestionsManager.getTestQuestions()).willReturn(TestUtils.createPersonalityTestQuestions());
		given(this.testQuestionsManager.updateQuestionWithTestId(any(String.class), any(String.class))).willReturn(TestUtils.createPersonalityTestQuestions());
		Answer answer = this.answerManager.createAnswer(this.answer);
		assertNotNull(answer);
	}
	
	@Test
	public void testCreateAnswerCheckQuestion() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		given(this.testManager.getPersonalityTest(any(String.class))).willReturn(TestUtils.createPersonalityTest());
		given(this.testManager.updatePersonalityTest(any(PersonalityTest.class))).will(returnsFirstArg());
		
		given(this.testQuestionsManager.getTestQuestions()).willReturn(TestUtils.createPersonalityTestQuestions());
		given(this.testQuestionsManager.updateQuestionWithTestId(any(String.class), any(String.class))).willReturn(TestUtils.createPersonalityTestQuestions());
		
		Answer answer = this.answerManager.createAnswer(this.answer);
		assertEquals(QUESTION, answer.getQuestion());
	}
	
	@Test
	public void testCreateAnswerCheckAnswer() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		given(this.testManager.getPersonalityTest(any(String.class))).willReturn(TestUtils.createPersonalityTest());
		given(this.testManager.updatePersonalityTest(any(PersonalityTest.class))).will(returnsFirstArg());
		
		given(this.testQuestionsManager.getTestQuestions()).willReturn(TestUtils.createPersonalityTestQuestions());
		given(this.testQuestionsManager.updateQuestionWithTestId(any(String.class), any(String.class))).willReturn(TestUtils.createPersonalityTestQuestions());
		
		this.answer.setAnswer(ANSWER);
		Answer answer = this.answerManager.createAnswer(this.answer);
		assertEquals(ANSWER, answer.getAnswer());
	}
	
	@Test
	public void testCreateAnswerCheckPersonalityTest() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		given(this.testManager.getPersonalityTest(any(String.class))).willReturn(TestUtils.createPersonalityTest());
		given(this.testManager.updatePersonalityTest(any(PersonalityTest.class))).will(returnsFirstArg());
		
		given(this.testQuestionsManager.getTestQuestions()).willReturn(TestUtils.createPersonalityTestQuestions());
		given(this.testQuestionsManager.updateQuestionWithTestId(any(String.class), any(String.class))).willReturn(TestUtils.createPersonalityTestQuestions());
		
		Answer answer = this.answerManager.createAnswer(this.answer);
		assertEquals(PERSONALITY_TEST_ID, answer.getPersonalityTestId());
	}
	
	@Test
	public void testCreateAnswerCheckCreationDate() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		given(this.testManager.getPersonalityTest(any(String.class))).willReturn(TestUtils.createPersonalityTest());
		given(this.testManager.updatePersonalityTest(any(PersonalityTest.class))).will(returnsFirstArg());
		
		given(this.testQuestionsManager.getTestQuestions()).willReturn(TestUtils.createPersonalityTestQuestions());
		given(this.testQuestionsManager.updateQuestionWithTestId(any(String.class), any(String.class))).willReturn(TestUtils.createPersonalityTestQuestions());
		
		Answer answer = this.answerManager.createAnswer(this.answer);
		assertNotNull(answer.getCreationDate());
	}
	
	@Test
	public void testCreateAnswerWithQuestionNull() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		given(this.testManager.getPersonalityTest(any(String.class))).willReturn(TestUtils.createPersonalityTest());
		given(this.testManager.updatePersonalityTest(any(PersonalityTest.class))).will(returnsFirstArg());
		
		given(this.testQuestionsManager.getTestQuestions()).willReturn(TestUtils.createPersonalityTestQuestions());
		given(this.testQuestionsManager.updateQuestionWithTestId(any(String.class), any(String.class))).willReturn(TestUtils.createPersonalityTestQuestions());
		
		this.answer.setQuestion(null);
		Answer answer = this.answerManager.createAnswer(this.answer);
		Set<ConstraintViolation<Answer>> violations = validator.validate(answer);
		assertEquals(QUESTION_REQUIRED, TestUtils.getConstraintViolationMessage(violations));
	}
	
	@Test
	public void testCreateAnswerWithQuestionEmpty() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		given(this.testManager.getPersonalityTest(any(String.class))).willReturn(TestUtils.createPersonalityTest());
		given(this.testManager.updatePersonalityTest(any(PersonalityTest.class))).will(returnsFirstArg());
		
		given(this.testQuestionsManager.getTestQuestions()).willReturn(TestUtils.createPersonalityTestQuestions());
		given(this.testQuestionsManager.updateQuestionWithTestId(any(String.class), any(String.class))).willReturn(TestUtils.createPersonalityTestQuestions());
		
		this.answer.setQuestion("");
		Answer answer = this.answerManager.createAnswer(this.answer);
		Set<ConstraintViolation<Answer>> violations = validator.validate(answer);
		assertEquals(QUESTION_REQUIRED, TestUtils.getConstraintViolationMessage(violations));
	}
	
	@Test
	public void testCreateAnswerWithAnswerNull() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		given(this.testManager.getPersonalityTest(any(String.class))).willReturn(TestUtils.createPersonalityTest());
		given(this.testManager.updatePersonalityTest(any(PersonalityTest.class))).will(returnsFirstArg());
		
		given(this.testQuestionsManager.getTestQuestions()).willReturn(TestUtils.createPersonalityTestQuestions());
		given(this.testQuestionsManager.updateQuestionWithTestId(any(String.class), any(String.class))).willReturn(TestUtils.createPersonalityTestQuestions());
		
		this.answer.setAnswer(null);
		Answer answer = this.answerManager.createAnswer(this.answer);
		Set<ConstraintViolation<Answer>> violations = validator.validate(answer);
		assertEquals(ANSWER_REQUIRED, TestUtils.getConstraintViolationMessage(violations));
	}
	
	@Test
	public void testCreateAnswerWithAnswerEmpty() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		given(this.testManager.getPersonalityTest(any(String.class))).willReturn(TestUtils.createPersonalityTest());
		given(this.testManager.updatePersonalityTest(any(PersonalityTest.class))).will(returnsFirstArg());
		
		given(this.testQuestionsManager.getTestQuestions()).willReturn(TestUtils.createPersonalityTestQuestions());
		given(this.testQuestionsManager.updateQuestionWithTestId(any(String.class), any(String.class))).willReturn(TestUtils.createPersonalityTestQuestions());
		
		this.answer.setAnswer("");
		Answer answer = this.answerManager.createAnswer(this.answer);
		Set<ConstraintViolation<Answer>> violations = validator.validate(answer);
		assertEquals(ANSWER_REQUIRED, TestUtils.getConstraintViolationMessage(violations));
	}
	
	@Test
	public void testCreateAnswerWithPersonalityTestIdNull() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		given(this.testManager.getPersonalityTest(any(String.class))).willReturn(TestUtils.createPersonalityTest());
		given(this.testManager.updatePersonalityTest(any(PersonalityTest.class))).will(returnsFirstArg());
		
		given(this.testQuestionsManager.getTestQuestions()).willReturn(TestUtils.createPersonalityTestQuestions());
		given(this.testQuestionsManager.updateQuestionWithTestId(any(String.class), any(String.class))).willReturn(TestUtils.createPersonalityTestQuestions());
		
		this.answer.setPersonalityTestId(null);
		Answer answer = this.answerManager.createAnswer(this.answer);
		Set<ConstraintViolation<Answer>> violations = validator.validate(answer);
		assertEquals(TEST_ID_REQUIRED, TestUtils.getConstraintViolationMessage(violations));
	}
	
	@Test
	public void testCreateAnswerWithPersonalityTestIdEmpty() {
		given(this.answerRepository.save(any(Answer.class))).will(returnsFirstArg());
		given(this.testManager.getPersonalityTest(any(String.class))).willReturn(TestUtils.createPersonalityTest());
		given(this.testManager.updatePersonalityTest(any(PersonalityTest.class))).will(returnsFirstArg());
		
		given(this.testQuestionsManager.getTestQuestions()).willReturn(TestUtils.createPersonalityTestQuestions());
		given(this.testQuestionsManager.updateQuestionWithTestId(any(String.class), any(String.class))).willReturn(TestUtils.createPersonalityTestQuestions());
		
		this.answer.setPersonalityTestId("");
		Answer answer = this.answerManager.createAnswer(this.answer);
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
		this.answer.setAnswer(ANSWER_UPDATE);
		Answer answerUpdated = this.answerManager.updateAnswer(this.answer);
		assertEquals(answerUpdated.getAnswer(), ANSWER_UPDATE);
	}
}
