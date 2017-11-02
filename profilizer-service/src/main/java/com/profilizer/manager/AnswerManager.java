package com.profilizer.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.profilizer.repository.AnswerRepository;
import com.profilizer.repository.document.Answer;
import com.profilizer.repository.document.PersonalityTest;
import com.profilizer.repository.document.PersonalityTestQuestions;
import com.profilizer.util.PersonalityTestUtils;

@Component
public class AnswerManager {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private PersonalityTestManager testManager;
	
	@Autowired
	private PersonalityTestQuestionsManager testQuestionsManager;
	
	public List<Answer> getAnswersByTestId(String personalityTestId) {
		return answerRepository.findByPersonalityTestId(personalityTestId);
	}
	
	public Answer createAnswer(Answer answer) {
		answer.setCreationDate(new Date());
		Answer dbAnswer = answerRepository.save(answer);
		PersonalityTest test = testManager.getPersonalityTest(dbAnswer.getPersonalityTestId());
		test.addAnswersId(dbAnswer.getId());
		PersonalityTestQuestions testQuestions = testQuestionsManager.getTestQuestions();
		test.setNumberOfQuestions(testQuestions.getQuestions().size());
		PersonalityTestUtils.incrementPercentageCompletionOnNewAnswer(test);
		testManager.updatePersonalityTest(test);
		testQuestionsManager.updateQuestionWithTestId(answer.getQuestion(), test.getId());
		return dbAnswer;
	}
	
	public Answer updateAnswer(Answer answer) {
		Answer dbAnswer = answerRepository.findOne(answer.getId());
		dbAnswer.setAnswer(answer.getAnswer());
		return answerRepository.save(dbAnswer);
	}
}
