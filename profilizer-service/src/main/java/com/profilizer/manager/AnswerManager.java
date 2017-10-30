package com.profilizer.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.profilizer.repository.AnswerRepository;
import com.profilizer.repository.document.Answer;

@Component
public class AnswerManager {

	@Autowired
	private AnswerRepository repository;
	
	public List<Answer> getAnswersByTestId(String personalityTestId) {
		return repository.findByPersonalityTestId(personalityTestId);
	}
	
	public Answer createAnswer(String question, String answer, String personalityTestId) {
		Answer answerDocument = new Answer();
		answerDocument.setQuestion(question);
		answerDocument.setAnswer(answer);
		answerDocument.setPersonalityTestId(personalityTestId);
		answerDocument.setCreationDate(new Date());
		return repository.save(answerDocument);
	}
	
	public Answer updateAnswer(String answerId, String answer) {
		Answer answerDocument = repository.findOne(answerId);
		answerDocument.setAnswer(answer);
		return repository.save(answerDocument);
	}
}
