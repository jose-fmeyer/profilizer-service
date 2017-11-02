package com.profilizer.repository.document;

import static com.profilizer.repository.validation.ValidationMessages.OWNER_REQUIRED;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "personality_test")
public class PersonalityTest {

	@Id
	@Indexed
	private String id;
	@NotBlank(message = OWNER_REQUIRED)
	private String owner;
	@Field(value = "number_of_questions")
	private int numberOfQuestions;
	@Field(value = "percentage_completion")
	private int percentageCompletion;
	@Field(value = "creation_date")
	private Date creationDate;
	@Field(value = "answers_id")
	private List<String> answersId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public int getPercentageCompletion() {
		return percentageCompletion;
	}

	public void setPercentageCompletion(int percentageCompletion) {
		this.percentageCompletion = percentageCompletion;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void addAnswersId(String answerId) {
		if (answersId == null) {
			answersId = new ArrayList<>(); 
		}
		answersId.add(answerId);
	}
	
	public List<String> getAnswersId() {
		return answersId;
	}

	public void setAnswersId(List<String> answersId) {
		this.answersId = answersId;
	}
}
