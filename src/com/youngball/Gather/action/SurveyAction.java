package com.youngball.Gather.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.youngball.Gather.domain.Survey;
import com.youngball.Gather.domain.User;
import com.youngball.Gather.service.SurveyService;

@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware{

	private static final long serialVersionUID = -1416936634856203333L;
	
//	private Map<String, Object> sessionMap;
	
	//接受session的user对象
	private User user;
	
	//注入SurveyService
	@Resource()
	private SurveyService surveyService;
	
	private Integer sid;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	private List<Survey> mySurveys;
	
	public List<Survey> getMySurvey() {
		return mySurveys;
	}

	public void setMySurvey(List<Survey> mySurvey) {
		this.mySurveys = mySurvey;
	}

	/**
	 * 新建调查页面
	 * @return
	 */
	public String addSurvey(){
		/*User user = (User) sessionMap.get("user");*/
		this.model = surveyService.addSurvey(user);
		
		return "addSurveyPage";
	}

	/**
	 * 查询我的调查
	 * @return
	 */
	public String mySurvey(){
		// User user = (User) sessionMap.get("user");
		this.mySurveys = surveyService.findSurveysByUid(user);
		return "mySurveysListPage";
	}
	
	
//	//注入session map,使得耦合度降低
//	public void setSession(Map<String, Object> session) {
//		this.sessionMap = session;
//	}

	//注入用户对象
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 设计调查
	 * @return
	 */
	public String designSurvey(){
		this.model = surveyService.getSurvey(sid);
		return "designSurveyPage";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
