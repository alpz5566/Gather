package com.youngball.Gather.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.youngball.Gather.domain.Page;
import com.youngball.Gather.domain.Survey;
import com.youngball.Gather.service.SurveyService;

/**
 * PageAction
 * @author lpz
 */
@Controller
@Scope("prototype")
public class PageAction extends BaseAction<Page>{
	
	private static final long serialVersionUID = -1400218575511026363L;
	
	private Integer sid;
	
	private Integer pid;
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getSid() {
		return sid;
	}
	
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	@Resource
	private SurveyService surveyService;

	public String toAddPage(){
		return "addPagePage";
	}
	
	/**
	 * 保存/更新页面
	 * @return
	 */
	public String saveOrUpdatePage(){
		//设置页面调查关系
		Survey s = new Survey();
		s.setId(sid);
		model.setSurvey(s);
		surveyService.saveOrUpdatePage(model);
		return "designSurveyAction";
	}
	
	/**
	 * 编辑页面标题 
	 * @return
	 */
	public String editPage(){
		this.model = surveyService.getPage(pid);
		return "editPage";
	}
	
	public String deletePage(){
		surveyService.deletePage(pid);
		return "designSurveyAction";
	}
	
	
}
