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
	
	public PersonalityTest createPersonalityTest(PersonalityTest personalityTest) {
		personalityTest.setCreationDate(new Date());
		return repository.save(personalityTest);
	}
	
	public PersonalityTest updatePercentageCompletion(PersonalityTest personalityTest) {
		PersonalityTest dbPersonalityTest = repository.findOne(personalityTest.getId());
		dbPersonalityTest.setPercentageCompletion(personalityTest.getPercentageCompletion());
		return repository.save(dbPersonalityTest);
	}
}
