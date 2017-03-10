package com.hik;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.Matchers.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.hik.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@AutoConfigureMockMvc
@Transactional
public class MockTest {

	private MockMvc mvc; 
	@Resource 
	private WebApplicationContext webApplicationContext;
	@Before 
    public void setUp() throws Exception { 
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	 @Test 
	 @Rollback
	public void testUserController() throws Exception { 
		 RequestBuilder request = null; 
		 request = get("/user"); 
		 mvc.perform(request)
		 .andExpect(status().isOk())
			.andExpect(content().string(equalTo("[]")));
		 
		 request = post("/user/") 
	                .param("name", "测试大师") 
	                .param("age", "20"); 
		 mvc.perform(request) 
	                .andExpect(status().isOk())
	                .andDo(print());
	 }
}
