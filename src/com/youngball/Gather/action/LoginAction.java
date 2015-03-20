package com.youngball.Gather.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.youngball.Gather.domain.User;
import com.youngball.Gather.service.UserService;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> {

	private static final long serialVersionUID = 8892939882902288426L;

	private User model = new User();
	
	@Resource
	private UserService userService;
	
	public User getModel() {
		return model;
	}
	
	/**
	 * 登录页面
	 * @return
	 */
	public String toLogPage(){
		return "logPage";
	}
	
	public String doLog(){
		return "success";
	}

}
