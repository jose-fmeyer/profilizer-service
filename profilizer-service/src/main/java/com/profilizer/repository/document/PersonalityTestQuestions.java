package com.profilizer.repository.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personality_test_questions")
public class PersonalityTestQuestions {

	@Id
	private String id;
	private List<Question> questions;
	private List<Category> categories;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
