package com.profilizer.repository.document;

import static com.profilizer.repository.validation.ValidationMessages.ANSWER_REQUIRED;
import static com.profilizer.repository.validation.ValidationMessages.QUESTION_REQUIRED;
import static com.profilizer.repository.validation.ValidationMessages.TEST_ID_REQUIRED;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "personality_test_answers")
public class Answer {

	@Id
	@Indexed
	private String id;
	@NotBlank(message = QUESTION_REQUIRED)
	private String question;
	@NotBlank(message = ANSWER_REQUIRED)
	private String answer;
	@Field(value = "creation_date")
	private Date creationDate;
	@NotBlank(message = TEST_ID_REQUIRED)
	private String personalityTestId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getPersonalityTestId() {
		return personalityTestId;
	}

	public void setPersonalityTestId(String personalityTestId) {
		this.personalityTestId = personalityTestId;
	}
}
