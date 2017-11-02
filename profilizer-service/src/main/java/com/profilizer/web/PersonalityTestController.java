package com.profilizer.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.profilizer.manager.PersonalityTestManager;
import com.profilizer.repository.document.PersonalityTest;

@RestController
@RequestMapping("/tests")
public class PersonalityTestController extends AbstractController {
	
	@Autowired
	private PersonalityTestManager personalityTestManager;
	
	@RequestMapping(method = RequestMethod.GET)
    public List<PersonalityTest> getPersonalityTests() {
		return this.personalityTestManager.getPersonalityTests();
    }
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
    public PersonalityTest createPersonalityTest(@Valid @RequestBody PersonalityTest personalityTest) {
		return this.personalityTestManager.createPersonalityTest(personalityTest);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
    public PersonalityTest updatePercentageCompletion(@Valid @RequestBody PersonalityTest personalityTest) {
		return this.personalityTestManager.updatePersonalityTest(personalityTest);
	}
}
