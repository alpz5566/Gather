package com.youngball.Gather.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youngball.Gather.dao.BaseDao;
import com.youngball.Gather.domain.Page;
import com.youngball.Gather.domain.Survey;
import com.youngball.Gather.domain.User;
import com.youngball.Gather.service.SurveyService;

/**
 * serveyService
 * @author lpz
 *
 */
@Service("surveyService")
public class SurveyServiceImpl implements SurveyService{

	@Resource(name="surveyDao")
	private BaseDao<Survey> surveyDao;
	
	@Resource(name="pageDao")
	private BaseDao<Page> pageDao;
	
	/**
	 * 新建调查
	 */
	public Survey addSurvey(User user) {
		Survey survey = new Survey();
		Page page = new Page();
		
		//设置相关关系
		survey.setUser(user);
		page.setSurvey(survey);
		survey.getPages().add(page);
		//保存
		surveyDao.saveEntity(survey);
		pageDao.saveEntity(page);
		return survey;
	}

	/**
	 * 查询指定用户的调查集合
	 */
	public List<Survey> findSurveysByUid(User user) {
		String hql = "from Survey s where s.user.id = ?";
		return surveyDao.findEntityByHQL(hql, user.getId());
	}

	public Survey getSurvey(Integer sid) {
		return surveyDao.getEntity(sid);
	}
	
}
























