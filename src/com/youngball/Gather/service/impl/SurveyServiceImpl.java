package com.youngball.Gather.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youngball.Gather.dao.BaseDao;
import com.youngball.Gather.dao.impl.AnswerDaoImpl;
import com.youngball.Gather.domain.Answer;
import com.youngball.Gather.domain.Page;
import com.youngball.Gather.domain.Question;
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
	
	@Resource(name="questionDao")
	private BaseDao<Question> questionDao;
	
	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao;
	
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

	
	public Survey getSurveyWithChildren(Integer sid) {
		return surveyDao.getEntity(sid);
	}

	public Survey getSurvey(Integer sid) {
		Survey s = surveyDao.getEntity(sid);
		//初始化问题集合,页面集合
		for(Page p : s.getPages()){
			p.getQuestions().size();
		}
		return s;
	}

	/**
	 * 更新调查
	 */
	public void updateSurvey(Survey model) {
		surveyDao.updateEntity(model);
	}

	/**
	 * 保存更新页面
	 */
	public void saveOrUpdatePage(Page model) {
		pageDao.saveOrUpdateEntity(model);
	}

	/**
	 * 编辑页面标题 
	 * @return
	 */
	public Page getPage(Integer pid) {
		return pageDao.getEntity(pid);
	}

	/**
	 * 保存更新问题
	 */
	public void saveOrUpdateQuestion(Question model) {
		questionDao.saveOrUpdateEntity(model);
	}

	/**
	 * 删除问题 
	 */
	public void deleteQuestion(Integer qid) {
		String hql = "delete from Answer a where a.questionId = ?";
		answerDao.batchEntityByHQL(hql, qid);
		hql = "delete from Question q where q.id = ?";
		questionDao.batchEntityByHQL(hql, qid);
	}

	/**
	 * 编辑问题
	 */
	public Question getQuestion(Integer qid) {
		return questionDao.getEntity(qid);
	}

	/**
	 * 删除页面
	 */
	public void deletePage(Integer pid) {
		//先删除页面中问题的答案
		String hql = "delete from Answer a where a.questionId in (select q.id from Question q where q.page.id = ?)";
		answerDao.batchEntityByHQL(hql, pid);
		//删问题
		hql = "delete from Question q where q.page.id = ?";
		answerDao.batchEntityByHQL(hql, pid);
		//删页面
		hql = "delete from Page p where p.id = ?";
		answerDao.batchEntityByHQL(hql, pid);
	}

	/**
	 * 删除调查
	 */
	public void deleteSurvey(Integer sid) {
		//delete answer
		String hql = "delete from Answer a where a.surveyId = ?";
		answerDao.batchEntityByHQL(hql, sid);
		//delete question
		//hql = "delete from Question q where q.page.survey.id = ?";   错误原因:hibernate,delte的时候不允许2级以上的的连接,但是查询可以
		hql = "delete from Question q where q.page.id in (select p.id from Page p where p.survey.id = ?)"; 
		answerDao.batchEntityByHQL(hql, sid);
		//delete page
		hql = "delete from Page p where p.survey.id = ?";
		answerDao.batchEntityByHQL(hql, sid);
		//delete survey
		hql = "delete from Survey where id = ?";
		answerDao.batchEntityByHQL(hql, sid);
	}
	
}
























