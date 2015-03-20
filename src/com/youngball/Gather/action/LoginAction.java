package com.youngball.Gather.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.youngball.Gather.domain.User;
import com.youngball.Gather.service.UserService;
import com.youngball.Gather.util.DataUtil;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware{

	private static final long serialVersionUID = 8892939882902288426L;
	
	@Resource
	private UserService userService;
	
	//SessionAware接口实现,接收session的map集合
	private Map<String, Object> sessionMap;
	
	/**
	 * 登录页面
	 * @return
	 */
	@SkipValidation
	public String toLogPage(){
		return "logPage";
	}
	
	public String doLog(){
		return SUCCESS;
	}
	
	public void validate(){
		User user = userService.validateLoginInfo(model.getEmail(),DataUtil.md5(model.getPassword()));
		if(user == null){
			addActionError("email或密码错误");
		}else{
			sessionMap.put("user", user);
		}
	}

	//注入session的map
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}

}
