package com.profilizer.controller;

import static com.profilizer.util.TestUtils.PASSWORD;
import static com.profilizer.util.TestUtils.USERNAME;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.profilizer.authentication.AuthenticationHelper;
import com.profilizer.manager.PersonalityTestManager;
import com.profilizer.repository.document.PersonalityTest;
import com.profilizer.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalityTestControllerTest {
	
	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
	
	@Autowired
	public AuthenticationHelper authenticationHelper;
	
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private PersonalityTestManager personalityTestManager;
	
	private RequestPostProcessor basicAuth;
	
	private MockMvc mockMvc;
	
	private List<PersonalityTest> personalityTests;
	
	private PersonalityTest personalityTest;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context) 
				.apply(springSecurity())
				.apply(documentationConfiguration(this.restDocumentation)) 
				.build();
		
		this.basicAuth = authenticationHelper.basicAuth(USERNAME, PASSWORD);
		this.personalityTests = Arrays.asList(TestUtils.createPersonalityTest());
		this.personalityTest = TestUtils.createPersonalityTest();
	}
	
	@Test
	public void testGetPersonalityTests() throws Exception {
		given(this.personalityTestManager.getPersonalityTests()).willReturn(this.personalityTests);
		MockHttpServletResponse response = this.mockMvc.perform(get("/tests").with(this.basicAuth)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(document("personality-tests"))
				.andReturn().getResponse();
		assertNotNull(response.getContentAsString());
	}
	
	@Test
	public void testCreatePersonalityTest() throws Exception {
		given(this.personalityTestManager.createPersonalityTest(any(PersonalityTest.class))).willReturn(this.personalityTest);
		this.mockMvc.perform(post("/tests").with(this.basicAuth)
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.loadPersonalityTestContent()))
				.andExpect(status().isCreated()).andDo(document("create-personality-test"));
	}
	
	@Test
	public void testUpdatePersonalityTest() throws Exception {
		given(this.personalityTestManager.updatePersonalityTest(any(PersonalityTest.class))).willReturn(this.personalityTest);
		this.mockMvc.perform(put("/tests").with(this.basicAuth)
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.loadUpdatePersonalityTestContent()))
				.andExpect(status().isOk()).andDo(document("update-personality-test"));
	}
	
	@Test
	public void testCreatePersonalityTestMissingRequiredField() throws Exception {
		this.mockMvc.perform(post("/tests").with(this.basicAuth)
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.loadAnswerMissingRequiredFieldContent()))
				.andExpect(status().isBadRequest()).andDo(document("error-create-personality-test"));
	}
}
