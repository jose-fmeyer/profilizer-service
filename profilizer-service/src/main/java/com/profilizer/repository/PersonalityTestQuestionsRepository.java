package com.profilizer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.profilizer.repository.document.PersonalityTestQuestions;

public interface PersonalityTestQuestionsRepository extends MongoRepository<PersonalityTestQuestions, String>{
	
    PersonalityTestQuestions findByQuestionsQuestion(String question);
    
    List<PersonalityTestQuestions> findByQuestionsAnswersIdIn(List<String> answersId);
}
