package com.profilizer.changelogs;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.profilizer.util.ResourceUtil;

@Configuration
@ChangeLog
public class DatabaseChangelog {
		
	private static final String CATEGORIES_DOCUMENT = "/db/documents/categories.json";
	private static final String CATEGORIES_COLLECTION = "categories";
	private static final String PERSONALITY_TEST_QUESTIONS_DOCUMENT = "/db/documents/personality_test_questions.json";
	private static final String PERSONALITY_TEST_QUESTIONS_COLLECTION = "personality_test_questions";

	@ChangeSet(order = "001", id = "1", author = "admin")
	public void initialDatabaseLoad(DB db) throws IOException {
		insertInitalData(db, CATEGORIES_DOCUMENT, CATEGORIES_COLLECTION);
		insertInitalData(db, PERSONALITY_TEST_QUESTIONS_DOCUMENT, PERSONALITY_TEST_QUESTIONS_COLLECTION);
	}
	
	private void insertInitalData(DB db, String filePath, String collectionName) throws IOException {
		String json = ResourceUtil.loadResource(filePath);
		DBCollection collection = db.getCollection(collectionName);
		DBObject dbObject = (DBObject) JSON.parse(json);
		collection.insert(dbObject);
	}
}
