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
		return repository.findAllByOrderByCreationDateDesc();
	}
	
	public PersonalityTest createPersonalityTest(PersonalityTest test) {
		test.setCreationDate(new Date());
		return repository.save(test);
	}
	
	public PersonalityTest updatePercentageCompletion(PersonalityTest test) {
		PersonalityTest dbPersonalityTest = repository.findOne(test.getId());
		dbPersonalityTest.setPercentageCompletion(test.getPercentageCompletion());
		return repository.save(dbPersonalityTest);
	}
}
