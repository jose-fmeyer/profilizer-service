package com.profilizer.repository.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

public class Question {
	
	@Indexed
	private String question;
	private String category;
	@Field(value = "question_type")
	private QuestionType questionType;
	@Indexed
	@Field(value = "tests_id")
	private List<String> testsId;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
	
	public void addTestId(String testId) {
		if (testsId == null) {
			testsId = new ArrayList<>(); 
		}
		testsId.add(testId);
	}
	
	public List<String> getTestsId() {
		return testsId;
	}

	public void setAnswersId(List<String> testsId) {
		this.testsId = testsId;
	}

	@Override
	public String toString() {
		return "Question [question=" + question + ", category=" + category + ", questionType=" + questionType + "]";
	}
}
