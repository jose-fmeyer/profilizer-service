package com.profilizer.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.profilizer.repository.PersonalityTestRepository;
import com.profilizer.repository.document.PersonalityTest;

@Component
public class PersonalityTestManager {
	
	@Autowired
	private PersonalityTestRepository repository;
	
	public List<PersonalityTest> getPersonalityTests() {
		return repository.findAll();
	}
	
	public PersonalityTest createPersonalityTest(String owner) {
		PersonalityTest personalityTest = new PersonalityTest();
		personalityTest.setOwner(owner);
		personalityTest.setCreationDate(new Date());
		return repository.save(personalityTest);
	}
	
	public PersonalityTest updatePercentageCompletion(String personalityTestId, int percentageCompletion) {
		PersonalityTest personalityTest = repository.findOne(personalityTestId);
		personalityTest.setPercentageCompletion(percentageCompletion);
		return repository.save(personalityTest);
	}
}
