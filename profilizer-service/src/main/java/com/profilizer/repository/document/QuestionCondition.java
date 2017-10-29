package com.profilizer.repository.document;

import org.springframework.data.mongodb.core.mapping.Field;

public class QuestionCondition {

	private ConditionPredicate predicate;
	@Field(value = "if_positive")
	private Question conditionalQuestion;

	public ConditionPredicate getPredicate() {
		return predicate;
	}

	public void setPredicate(ConditionPredicate predicate) {
		this.predicate = predicate;
	}

	public Question getConditionalQuestion() {
		return conditionalQuestion;
	}

	public void setConditionalQuestion(Question conditionalQuestion) {
		this.conditionalQuestion = conditionalQuestion;
	}

	@Override
	public String toString() {
		return "QuestionCondition [predicate=" + predicate + ", conditionalQuestion=" + conditionalQuestion + "]";
	}
}
