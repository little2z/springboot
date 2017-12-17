package com.xyl.springboot;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.xyl.springboot.controller.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {
	private MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	@Test
	public void testUserOp() throws Exception {
		RequestBuilder requestBuilder = null;

		// 查询所有
		requestBuilder = get("/users");
		mvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));

		// 增加用户
		requestBuilder = post("/users").param("id", "1").param("name", "张三")
				.param("age", "18");
		mvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		// 再次查询
		requestBuilder = get("/users");
		mvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(
						content()
								.string(equalTo("[{\"id\":1,\"name\":\"张三\",\"age\":18}]")));

		// 修改用户
		requestBuilder = put("/users/1").param("name", "李四").param("age", "28");
		mvc.perform(requestBuilder).andExpect(status().isOk())
		.andExpect(content().string(equalTo("success")));
		
		//查询某个用户
		requestBuilder = get("/users/1");
		mvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(
						content()
								.string(equalTo("{\"id\":1,\"name\":\"李四\",\"age\":28}")));
		
		
		//删除某个用户
		requestBuilder = delete("/users/1");
		mvc.perform(requestBuilder).andExpect(status().isOk())
		.andExpect(content().string(equalTo("success")));
		
		// 查询所有
		requestBuilder = get("/users");
		mvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
	}

}
