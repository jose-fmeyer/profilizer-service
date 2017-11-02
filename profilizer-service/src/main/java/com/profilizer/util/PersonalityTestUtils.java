package com.profilizer.util;

import com.profilizer.repository.document.PersonalityTest;

public class PersonalityTestUtils {
	
	public static void incrementPercentageCompletionOnNewAnswer(PersonalityTest test) {
		if (test.getPercentageCompletion() == 100) {
			return;
		}
		int stepSize = 100 / test.getNumberOfQuestions();
		if (stepSize > 100) {
			stepSize = 100;
		}
		test.setPercentageCompletion(test.getPercentageCompletion() + stepSize);
	}
}
