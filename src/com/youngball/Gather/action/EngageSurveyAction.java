package com.youngball.Gather.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.youngball.Gather.domain.Page;
import com.youngball.Gather.domain.Survey;
import com.youngball.Gather.domain.User;
import com.youngball.Gather.service.SurveyService;
import com.youngball.Gather.util.ValidateUtil;

/**
 * 参与调查
 * @author lpz
 */
@Controller
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements UserAware,ServletContextAware,SessionAware{

	private static final long serialVersionUID = -4668343023359766668L;

	private User user;
	private ServletContext sc;
	private List<Survey> surveys;
	private Integer sid;
	private Page currPage;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Page getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Page currPage) {
		this.currPage = currPage;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}

	@Resource
	private SurveyService surveyService;

	//接收sessionMap
	private Map<String, Object> sessionMap;
	
	public void setUser(User user) {
		this.user = user;
	}

	public void setServletContext(ServletContext context) {
		this.sc = context;
	}
	
	/**
	 * 查找可以参与的surveys
	 * @return
	 */
	public String findAllAvailableSurveys(){
		this.surveys = surveyService.findAllAvailableSurveys();
		return "engageSurveyListPage";
	}
	
	/**
	 * 得到图片路径
	 * @param logoPath
	 * @return
	 */
	public String getImageUrl(String logoPath){
		if(ValidateUtil.isValid(logoPath)){
			String realPath = sc.getRealPath(logoPath);
			if(new File(realPath).exists()){
				return sc.getContextPath() + logoPath;
			}
		}
		return sc.getContextPath() + "/question.bmp";
	}
	
	/**
	 * 参与调查的入口方法
	 * @return
	 */
	public String entry(){
		this.currPage = surveyService.getFirstPage(sid);
		//current_survey.minOrderno
		
		sessionMap.put("current_survey", currPage.getSurvey());
		return "engageSurveyPage";
	}

	//注入sessionMap
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}

}
