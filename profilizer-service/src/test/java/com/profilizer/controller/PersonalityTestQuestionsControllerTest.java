package com.profilizer.controller;

import static com.profilizer.util.TestUtils.PASSWORD;
import static com.profilizer.util.TestUtils.USERNAME;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.profilizer.manager.PersonalityTestQuestionsManager;
import com.profilizer.repository.document.PersonalityTestQuestions;
import com.profilizer.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalityTestQuestionsControllerTest {
	
	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
	
	@Autowired
	public AuthenticationHelper authenticationHelper;
	
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private PersonalityTestQuestionsManager questionsManager;
	
	private RequestPostProcessor basicAuth;
	
	private MockMvc mockMvc;
	
	private PersonalityTestQuestions testQuestions;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context) 
				.apply(springSecurity())
				.apply(documentationConfiguration(this.restDocumentation)) 
				.build();
		
		this.basicAuth = authenticationHelper.basicAuth(USERNAME, PASSWORD);
		this.testQuestions = TestUtils.createPersonalityTestQuestions();
	}
	
	@Test
	public void testGetTestQuestions() throws Exception {
		given(this.questionsManager.getTestQuestions()).willReturn(this.testQuestions);
		MockHttpServletResponse response = this.mockMvc.perform(get("/tests/questions").with(this.basicAuth)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(document("personality-test-questions"))
				.andReturn().getResponse();
		assertNotNull(response.getContentAsString());
	}
}
