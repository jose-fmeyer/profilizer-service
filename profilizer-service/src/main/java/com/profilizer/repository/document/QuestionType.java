package com.profilizer.repository.document;

import java.util.Set;

public class QuestionType {

	private String type;
	private Set<String> options;
	private QuestionCondition condition;
	private QuestionRange range;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<String> getOptions() {
		return options;
	}

	public void setOptions(Set<String> options) {
		this.options = options;
	}

	public QuestionCondition getCondition() {
		return condition;
	}

	public void setCondition(QuestionCondition condition) {
		this.condition = condition;
	}

	public QuestionRange getRange() {
		return range;
	}

	public void setRange(QuestionRange range) {
		this.range = range;
	}

	@Override
	public String toString() {
		return "QuestionType [type=" + type + ", options=" + options + ", condition=" + condition + ", range=" + range
				+ "]";
	}
}
