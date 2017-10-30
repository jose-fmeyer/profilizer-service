package com.profilizer.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.profilizer.controller.AnswerControllerIT;
import com.profilizer.controller.PersonalityTestController;
import com.profilizer.controller.PersonalityTestQuestionsControllerIT;

@RunWith(Suite.class)
@SuiteClasses({ 
	AnswerControllerIT.class,
	PersonalityTestController.class,
	PersonalityTestQuestionsControllerIT.class
})
public class ITSuite {
}
