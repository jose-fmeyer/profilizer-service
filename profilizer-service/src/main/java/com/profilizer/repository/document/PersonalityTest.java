package com.profilizer.repository.document;

import static com.profilizer.repository.validation.ValidationMessages.OWNER_REQUIRED;

import java.util.Date;

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
	@Field(value = "percentage_completion")
	private int percentageCompletion;
	@Field(value = "creation_date")
	private Date creationDate;

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
}
