package com.youngball.Gather.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.youngball.Gather.domain.Page;
import com.youngball.Gather.domain.Question;
import com.youngball.Gather.service.SurveyService;

/**
 * QuestionAction
 * @author lpz
 */
@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question>{

	private static final long serialVersionUID = 7945655187851116539L;
	
	private Integer sid;
	private Integer pid;
	private Integer qid;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	@Resource
	private SurveyService surveyService;
	
	/**
	 * 到达选题型页面
	 * @return
	 */
	public String toSelectQuestionType(){
		return "selectQuestionTypePage";
	}
	
	/**
	 * 到达问题设计页面
	 * @return
	 */
	public String toDesignQuestionPage(){
		//动态接收
		return "" + model.getQuestionType();
	}
	
	/**
	 * 保存更新问题
	 * @return
	 */
	public String saveOrUpdateQuestion(){
		Page p = new Page();
		p.setId(pid);
		model.setPage(p);
		surveyService.saveOrUpdateQuestion(model);
		return "designSurveyAction";
	}
	
	

}
