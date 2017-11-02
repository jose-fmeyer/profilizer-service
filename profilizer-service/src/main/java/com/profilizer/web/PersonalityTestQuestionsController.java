package com.profilizer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.profilizer.manager.PersonalityTestQuestionsManager;
import com.profilizer.repository.document.PersonalityTestQuestions;

@RestController
@RequestMapping("/tests")
public class PersonalityTestQuestionsController extends AbstractController {
		
	@Autowired
	private PersonalityTestQuestionsManager questionsManager;
	
	@RequestMapping(path = "/questions", method = RequestMethod.GET)
    public PersonalityTestQuestions getTestQuestions() {
		return this.questionsManager.getTestQuestions();
    }
}
