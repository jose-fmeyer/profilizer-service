package com.profilizer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.profilizer.repository.document.Answer;

public interface AnswerRepository extends MongoRepository<Answer, String>{
	
	List<Answer> findByPersonalityTestId(String personalityTestId);
}
