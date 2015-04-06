package com.youngball.Gather.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.youngball.Gather.domain.Page;
import com.youngball.Gather.domain.Survey;
import com.youngball.Gather.domain.User;
import com.youngball.Gather.service.SurveyService;

/**
 * MoveOrCopyPageAction
 * @author lpz
 */
@Controller
@Scope("prototype")
public class MoveOrCopyPageAction extends BaseAction<Page> implements UserAware{

	private static final long serialVersionUID = -5381037185688360148L;
	
	private List<Survey> surveys;
	private Integer srcPid;
	
	//接收user 登陆拦截器里面注入
	private User user;
	
	@Resource
	private SurveyService surveyService;
	
	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}

	public Integer getSrcPid() {
		return srcPid;
	}

	public void setSrcPid(Integer srcPid) {
		this.srcPid = srcPid; 
	}

	/**
	 * 到达选择目标页面
	 * @return
	 */
	public String toSelectTargetPage(){
		this.surveys = surveyService.findSurveysWithPage(user);
		return "moveOrCopyPageListPage";
	}

	/**
	 * 注入user
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
