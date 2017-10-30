package com.profilizer.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.profilizer.repository.PersonalityTestQuestionsRepository;
import com.profilizer.repository.document.PersonalityTestQuestions;

@Component
public class PersonalityTestQuestionsManager {
	
	@Autowired
	private PersonalityTestQuestionsRepository repository;
	
	public List<PersonalityTestQuestions> getTestQuestions() {
		return repository.findAll();
	}
}
