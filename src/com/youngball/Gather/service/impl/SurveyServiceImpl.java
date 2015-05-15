package com.youngball.Gather.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import com.youngball.Gather.util.DataUtil;
import com.youngball.Gather.util.ValidateUtil;

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

	/**
	 * 清除调查
	 */
	public void clearAnswers(Integer sid) {
		String hql = "delete from Answer a where a.surveyId = ?";
		answerDao.batchEntityByHQL(hql, sid);
	}

	/**
	 * 改变状态
	 */
	public void changeStatus(Integer sid) {
		Survey s = this.getSurvey(sid);
		String hql = "update Survey s set s.closed = ? where s.id = ?";
		answerDao.batchEntityByHQL(hql,!s.isClosed() ,sid);
	}

	/**
	 * 更新数据库logopath路径
	 */
	public void updateLogoPath(Integer sid, String path) {
		String hql = "update Survey s set s.logoPath = ? where s.id = ?";
		surveyDao.batchEntityByHQL(hql, path, sid);
	}

	/**
	 * 查询调查,携带page集合
	 */
	public List<Survey> findSurveysWithPage(User user) {
		String hql = "from Survey s where s.user.id = ?";
		List<Survey> list = surveyDao.findEntityByHQL(hql, user.getId());
		//强行初始化页面集合.防止懒加载
		for(Survey s : list){
			s.getPages().size();
		}
		return list;
	}

	/**
	 * 实现移动/复制
	 */
	public void moveOrCopyPage(Integer srcPid, Integer tarPid, int pos) {
		Page srcPage = this.getPage(srcPid);
		Survey srcSurvey = srcPage.getSurvey();
		Page tarPage = this.getPage(tarPid);
		Survey tarSurvey = tarPage.getSurvey();
		
		//比较源页面和目标页面
		//移动
		if(srcSurvey.getId().equals(tarSurvey.getId())){
			setOrderno(srcPage,tarPage,pos);
		}
		//复制
		else{
			//对原页面进行深度复制
			srcPage.getQuestions().size();
			Page copy = (Page) DataUtil.deeplyCopy(srcPage);
			//设置新的关联关系
			copy.setSurvey(tarSurvey);
			//分别保存新的页面和问题
			pageDao.saveEntity(copy);
			for(Question q : copy.getQuestions()){
				questionDao.saveEntity(q);
			}
			setOrderno(copy,tarPage,pos);
		}
	}

	/**
	 * 设置页序
	 * @param copy
	 * @param tarPage
	 * @param pos
	 */
	private void setOrderno(Page srcPage, Page tarPage, int pos) {
		//之前/之后??
		if(pos == 0){
			if(isFirstPage(tarPage)){
				srcPage.setOrderno(srcPage.getOrderno() - 0.01f);
			}else {
				Page prePage = getPrePage(tarPage);
				srcPage.setOrderno((tarPage.getOrderno() + prePage.getOrderno()) / 2);
			}
		}else{
			if(isLastPage(tarPage)){
				srcPage.setOrderno(srcPage.getOrderno() + 0.01f);
			}else {
				Page nextPage = getNextPage(tarPage);
				srcPage.setOrderno((tarPage.getOrderno() + nextPage.getOrderno()) / 2);
			}
		}
	}

	/**
	 * 获取页面所在调查的后一页
	 * @param tarPage
	 * @return
	 */
	private Page getNextPage(Page tarPage) {
		String hql = "from Page p where p.orderno > ? and p.survey.id = ? order by p.orderno asc";
		return pageDao.findEntityByHQL(hql, tarPage.getOrderno(), tarPage.getSurvey().getId()).get(0);
		
	}

	/**
	 * 获取页面所在调查的前一页
	 * @param tarPage
	 * @return
	 */
	private Page getPrePage(Page tarPage) {
		String hql = "from Page p where p.orderno < ? and p.survey.id = ? order by p.orderno desc";
		return pageDao.findEntityByHQL(hql, tarPage.getOrderno(), tarPage.getSurvey().getId()).get(0);
	}

	/**
	 * 判断页面是否是最后一页
	 * @param srcPage
	 * @return
	 */
	private boolean isLastPage(Page tarPage) {
		String hql = "from Page p where p.orderno > ? and p.survey.id = ?";
		List<Page> list = pageDao.findEntityByHQL(hql,tarPage.getOrderno(),tarPage.getSurvey().getId());
		return !ValidateUtil.isvalidate(list);
	}

	/**
	 * 判断页面是否是第一页
	 * @param srcPage
	 * @return
	 */
	private boolean isFirstPage(Page tarPage) {
		String hql = "from Page p where p.orderno < ? and p.survey.id = ?";
		List<Page> list = pageDao.findEntityByHQL(hql,tarPage.getOrderno(),tarPage.getSurvey().getId());
		return !ValidateUtil.isvalidate(list);
	}

	/**
	 * 可参与的调查
	 */
	public List<Survey> findAllAvailableSurveys() {
		String hql = "from Survey s where s.closed = ?";
		return surveyDao.findEntityByHQL(hql, false);
	}

	/**
	 * 找到第一个页面
	 */
	public Page getFirstPage(Integer sid) {
		String hql = "from Page p where p.survey.id = ? order by p.orderno asc";
		//因为是一个页面集合所以会出现懒加载,应初始化
		Page page = pageDao.findEntityByHQL(hql, sid).get(0);
		page.getQuestions().size();
		page.getSurvey().getTitle();
		return page;
	}

	/**
	 * 查询指定页面的上一页
	 */
	public Page getPrePage(Integer currPid) {
		Page p = this.getPage(currPid);
		p = this.getPrePage(p);
		p.getQuestions().size();
		return p;
		
	} 

	/**
	 * 查询指定页面的下一页
	 */
	public Page getNextPage(Integer currPid) {
		Page p = this.getPage(currPid);
		p = this.getNextPage(p);
		p.getQuestions().size();
		return p;
	}

	/**
	 * 保存答案
	 */
	public void saveAnswers(List<Answer> list) {
		String uuid = UUID.randomUUID().toString();
		Date date = new Date();
		for(Answer a : list){
			a.setUuid(uuid);
			a.setAnswerTime(date);
			answerDao.saveEntity(a);
		}
	}
	
}
























