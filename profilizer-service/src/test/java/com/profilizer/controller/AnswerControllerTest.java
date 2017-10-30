package com.profilizer.controller;

import static com.profilizer.util.TestUtils.PASSWORD;
import static com.profilizer.util.TestUtils.PERSONALITY_TEST_ID;
import static com.profilizer.util.TestUtils.USERNAME;
import static com.profilizer.web.AnswerController.PARAM_NAME_TEST_ID;
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
import com.profilizer.manager.AnswerManager;
import com.profilizer.repository.document.Answer;
import com.profilizer.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerControllerTest {
	
	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
	
	@Autowired
	public AuthenticationHelper authenticationHelper;
	
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private AnswerManager answerManager;
	
	private RequestPostProcessor basicAuth;
	
	private MockMvc mockMvc;
	
	private List<Answer> answers;
	
	private Answer answer;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context) 
				.apply(springSecurity())
				.apply(documentationConfiguration(this.restDocumentation)) 
				.build();
		
		this.basicAuth = authenticationHelper.basicAuth(USERNAME, PASSWORD);
		this.answers = Arrays.asList(TestUtils.createAnswer());
		this.answer = TestUtils.createAnswer();
	}
	
	@Test
	public void testGetTestQuestions() throws Exception {
		given(this.answerManager.getAnswersByTestId(PERSONALITY_TEST_ID)).willReturn(this.answers);
		MockHttpServletResponse response = this.mockMvc.perform(get("/answers").with(this.basicAuth)
				.param(PARAM_NAME_TEST_ID, PERSONALITY_TEST_ID)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(document("personality-test-answers"))
				.andReturn().getResponse();
		assertNotNull(response.getContentAsString());
	}
	
	@Test
	public void testCreateAnswer() throws Exception {
		given(this.answerManager.createAnswer(any(Answer.class))).willReturn(this.answer);
		this.mockMvc.perform(post("/answers").with(this.basicAuth)
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.loadAnswerContent()))
				.andExpect(status().isCreated()).andDo(document("create-answer"));
	}
	
	@Test
	public void testUpdateAnswer() throws Exception {
		given(this.answerManager.updateAnswer(any(Answer.class))).willReturn(this.answer);
		this.mockMvc.perform(put("/answers").with(this.basicAuth)
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.loadUpdateAnswerContent()))
				.andExpect(status().isOk()).andDo(document("update-answer"));
	}
}
