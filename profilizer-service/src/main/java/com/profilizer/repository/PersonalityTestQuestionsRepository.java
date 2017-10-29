package com.profilizer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.profilizer.repository.document.PersonalityTestQuestions;

public interface PersonalityTestQuestionsRepository extends MongoRepository<PersonalityTestQuestions, String>{

}
