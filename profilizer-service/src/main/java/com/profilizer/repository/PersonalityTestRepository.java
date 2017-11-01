package com.profilizer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.profilizer.repository.document.PersonalityTest;

public interface PersonalityTestRepository extends MongoRepository<PersonalityTest, String>{
	
	List<PersonalityTest> findAllByOrderByCreationDateDesc();

}
