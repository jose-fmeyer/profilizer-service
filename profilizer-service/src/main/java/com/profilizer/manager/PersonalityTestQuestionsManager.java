package com.profilizer.manager;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.profilizer.repository.PersonalityTestQuestionsRepository;
import com.profilizer.repository.document.PersonalityTestQuestions;
import com.profilizer.repository.document.Question;

@Component
public class PersonalityTestQuestionsManager {
	
	@Autowired
	private PersonalityTestQuestionsRepository testQuestionsrepository;
	
	public PersonalityTestQuestions getTestQuestions() {
		return testQuestionsrepository.findAll().get(0);
	}
	
	public PersonalityTestQuestions getTestQuestionByQuestionName(String question) {
		return testQuestionsrepository.findByQuestionsQuestion(question);
	}
	
	public PersonalityTestQuestions updateQuestionWithTestId(String question, String testId) {
		PersonalityTestQuestions personalityTestQuestion = testQuestionsrepository.findByQuestionsQuestion(question);
		if (personalityTestQuestion == null) {
			return null;
		}
		List<Question> questions = personalityTestQuestion.getQuestions().stream()
				.filter(q -> q.getQuestion().equalsIgnoreCase(question))
				.collect(Collectors.toList());
		if (questions.isEmpty()) {
			return personalityTestQuestion;
		}
		questions.get(0).addTestId(testId);
		return testQuestionsrepository.save(personalityTestQuestion);
	}
}
