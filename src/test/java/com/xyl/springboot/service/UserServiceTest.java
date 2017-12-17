package com.xyl.springboot.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyl.springboot.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService userSerivce;
	

	@Before
	public void setUp() {
		// 准备，清空user表
//		userSerivce.deleteAllUsers();
	}

	@Test
	public void test() throws Exception {
		// 插入5个用户
		userSerivce.create("a", 1);
		userSerivce.create("b", 2);
		userSerivce.create("c", 3);
		userSerivce.create("d", 4);
		userSerivce.create("e", 5);
		
		
		
		User user = userSerivce.getUserByName("e");
		System.out.println("before update:"+user);
		
		userSerivce.updateById(user.getId(), "5e", 15);
		user = userSerivce.getUserByName("5e");
		System.out.println("after update:"+user);
		Assert.assertEquals("5e", user.getName());
		
		// 查数据库，应该有5个用户
		Assert.assertEquals(5, userSerivce.getAllUserNum().intValue());

		// 删除两个用户
		userSerivce.deleteByName("a");
		userSerivce.deleteByName("b");

		// 查数据库，应该有5个用户
		Assert.assertEquals(3, userSerivce.getAllUserNum().intValue());

	}
	
	@Test
	public void testUpdate(){
		
		User user = userSerivce.getUserByName("5e");
		System.out.println("before update:"+user);
		
		userSerivce.updateById(user.getId(), "张三", 18);
		
		user = userSerivce.getUserByName("张三");
		
		System.out.println("after update:"+user);
	}
	
}
