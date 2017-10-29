package com.profilizer.repository.document;

import org.springframework.data.mongodb.core.mapping.Field;

public class Question {

	private String question;
	private String category;
	@Field(value = "question_type")
	private QuestionType questionType;

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

	@Override
	public String toString() {
		return "Question [question=" + question + ", category=" + category + ", questionType=" + questionType + "]";
	}
}
