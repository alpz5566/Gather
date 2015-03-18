package com.youngball.Gather.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.youngball.Gather.domain.User;
import com.youngball.Gather.service.UserService;

@Controller
@Scope("prototype")
public class RegAction extends BaseAction<User> {

	private static final long serialVersionUID = 4618881342868567454L;

	//模型对象
	private User model = new User();
	
	//注入UserService
	@Resource
	private UserService userService;
	
	public User getModel() {
		return model;
	}
	
	/**
	 * 到达注册页面
	 * @return
	 */
	public String toRegPage(){
		return "regPage";
	}
}
