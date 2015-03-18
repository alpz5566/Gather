package com.youngball.Gather.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.youngball.Gather.domain.User;
import com.youngball.Gather.service.UserService;

/**
 * service测试类
 * @author lpz
 *
 */
public class TestUserService {
	private static ApplicationContext ac = null;
	
	@BeforeClass
	public static void iniAC(){
		ac = new ClassPathXmlApplicationContext("beans.xml");
	}
	
	@Test
	public void insertUser(){
		UserService us = (UserService)ac.getBean("userService");
		User u  = new User();
		u.setEmail("123@qq.com");
		u.setPassword("12121");
		u.setNickname("lpz");
		us.saveEntity(u);
	}
	
}
