package com.profilizer.manager;

import static com.profilizer.repository.validation.ValidationMessages.OWNER_REQUIRED;
import static com.profilizer.util.TestUtils.OWNER;
import static com.profilizer.util.TestUtils.PERSONALITY_TEST_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.profilizer.repository.PersonalityTestRepository;
import com.profilizer.repository.document.PersonalityTest;
import com.profilizer.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalityTestManagerTest {

	private Validator validator;

	@MockBean
	private PersonalityTestRepository personalityTestRepository;

	@Autowired
	private PersonalityTestManager personalityTestManager;
	
	private List<PersonalityTest> personalityTests;
	
	private PersonalityTest personalityTest;
	
	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
		
		this.personalityTests = Arrays.asList(TestUtils.createPersonalityTest());
		this.personalityTest = TestUtils.createPersonalityTest();
	}
	
	@Test
	public void testCreatePersonalityTest() {
		given(this.personalityTestRepository.save(any(PersonalityTest.class))).will(returnsFirstArg());
		PersonalityTest personalityTest = this.personalityTestManager.createPersonalityTest(OWNER);
		assertNotNull(personalityTest);
	}
	
	@Test
	public void testCreatePersonalityTestCheckOwner() {
		given(this.personalityTestRepository.save(any(PersonalityTest.class))).will(returnsFirstArg());
		PersonalityTest personalityTest = this.personalityTestManager.createPersonalityTest(OWNER);
		assertEquals(OWNER, personalityTest.getOwner());
	}
	
	@Test
	public void testCreateAnswerWithOwnerNull() {
		given(this.personalityTestRepository.save(any(PersonalityTest.class))).will(returnsFirstArg());
		PersonalityTest personalityTest = this.personalityTestManager.createPersonalityTest(null);
		Set<ConstraintViolation<PersonalityTest>> violations = validator.validate(personalityTest);
		assertEquals(OWNER_REQUIRED, TestUtils.getConstraintViolationMessage(violations));
	}
	
	@Test
	public void testCreateAnswerWithOwnerEmpty() {
		given(this.personalityTestRepository.save(any(PersonalityTest.class))).will(returnsFirstArg());
		PersonalityTest personalityTest = this.personalityTestManager.createPersonalityTest("");
		Set<ConstraintViolation<PersonalityTest>> violations = validator.validate(personalityTest);
		assertEquals(OWNER_REQUIRED, TestUtils.getConstraintViolationMessage(violations));
	}
	
	@Test
	public void testGetPersonalityTests() {
		given(this.personalityTestRepository.findAll()).willReturn(this.personalityTests);
		List<PersonalityTest> personalityTests = this.personalityTestManager.getPersonalityTests();
		assertSame(this.personalityTests, personalityTests);
	}
	
	@Test
	public void testUpdatePercentageCompletion() {
		given(this.personalityTestRepository.findOne(PERSONALITY_TEST_ID)).willReturn(this.personalityTest);
		given(this.personalityTestRepository.save(any(PersonalityTest.class))).will(returnsFirstArg());
		PersonalityTest personalityTestUpdated = this.personalityTestManager.updatePercentageCompletion(PERSONALITY_TEST_ID, 20);
		assertEquals(personalityTestUpdated.getPercentageCompletion(), 20);
	}
}
