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
	
	public Answer createAnswer(Answer answer) {
		answer.setCreationDate(new Date());
		return repository.save(answer);
	}
	
	public Answer updateAnswer(Answer answer) {
		Answer dbAnswer = repository.findOne(answer.getId());
		dbAnswer.setAnswer(answer.getAnswer());
		return repository.save(dbAnswer);
	}
}
