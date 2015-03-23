package com.youngball.Gather.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.youngball.Gather.action.BaseAction;
import com.youngball.Gather.action.LoginAction;
import com.youngball.Gather.action.RegAction;
import com.youngball.Gather.domain.User;


/**
 * 登陆拦截器
 * @author lpz
 *
 */
public class LoginInterceptor implements Interceptor  {

	private static final long serialVersionUID = 6586932356990706539L;

	public void destroy() {
	}

	public void init() {
	}

	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation invocation) throws Exception {
		BaseAction action = (BaseAction) invocation.getAction();
		if(action instanceof LoginAction || action instanceof RegAction){
			return invocation.invoke();
		}else{
			HttpSession s = ServletActionContext.getRequest().getSession();
			User user = (User)s.getAttribute("user");
			if(user == null){
				return "toLogPage";
			}else{
				return invocation.invoke();
			}
		}
	}


}
