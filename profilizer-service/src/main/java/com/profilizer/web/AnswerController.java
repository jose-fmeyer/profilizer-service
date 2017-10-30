package com.profilizer.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.profilizer.exception.InvalidRequestException;
import com.profilizer.manager.AnswerManager;
import com.profilizer.repository.document.Answer;

@RestController
@RequestMapping("/answers")
public class AnswerController extends AbstractController {
	
	public static final String PARAM_NAME_TEST_ID = "personalityTestId";
	
	@Autowired
	private AnswerManager answerManager;
	
	@RequestMapping(method = RequestMethod.GET, params = PARAM_NAME_TEST_ID)
    public List<Answer> getPersonalityTests(@RequestParam(PARAM_NAME_TEST_ID) String personalityTestId) {
		if (StringUtils.isEmpty(personalityTestId)) {
    		throw new InvalidRequestException("Personality test id is required to execute this operation");
    	}
		return this.answerManager.getAnswersByTestId(personalityTestId);
    }
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
    public Answer createAnswer(@Valid @RequestBody Answer answer) {
		return this.answerManager.createAnswer(answer);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
    public Answer updateAnswer(@Valid @RequestBody Answer answer) {
		return this.answerManager.updateAnswer(answer);
	}
}