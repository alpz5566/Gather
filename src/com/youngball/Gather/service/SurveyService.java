package com.youngball.Gather.service;

import java.util.List;

import com.youngball.Gather.domain.Page;
import com.youngball.Gather.domain.Question;
import com.youngball.Gather.domain.Survey;
import com.youngball.Gather.domain.User;

public interface SurveyService {

	/**
	 * 新建调查
	 * @param user
	 * @return
	 */
	public Survey addSurvey(User user);

	/**
	 * 查询指定用户的调查集合
	 * @param user
	 * @return
	 */
	public List<Survey> findSurveysByUid(User user);

	/**
	 * 按照id查询调查
	 * @param sid
	 * @return
	 */
	public Survey getSurvey(Integer sid);
	
	public Survey getSurveyWithChildren(Integer sid);

	/**
	 * 更新调查
	 * @param model
	 */
	public void updateSurvey(Survey model);

	/**
	 * 保存,更新页面
	 * @param model
	 */
	public void saveOrUpdatePage(Page model);

	/**
	 * 编辑页面标题 
	 * @return
	 */
	public Page getPage(Integer pid);

	/**
	 * 保存更新问题
	 * @param model
	 */
	public void saveOrUpdateQuestion(Question model);

	/**
	 * 删除问题
	 * @param qid
	 */
	public void deleteQuestion(Integer qid);

	/**
	 * 编辑问题
	 * @param qid
	 * @return
	 */
	public Question getQuestion(Integer qid);

	/**
	 * 删除页面
	 * @param pid
	 */
	public void deletePage(Integer pid);

	/**
	 * 删除调查
	 * @param sid
	 */
	public void deleteSurvey(Integer sid);

	/**
	 * 清除调查
	 * @param sid
	 */
	public void clearAnswers(Integer sid);

	/**
	 * 改变状态
	 * @param sid
	 */
	public void changeStatus(Integer sid);

	/**
	 * 修改logoPath路径
	 * @param sid
	 * @param string
	 */
	public void updateLogoPath(Integer sid, String path);

	/**
	 * 查询调查,携带page集合
	 * @param user
	 * @return
	 */
	public List<Survey> findSurveysWithPage(User user);

	/**
	 * 实现移动/复制
	 * @param srcPid
	 * @param tarPid
	 * @param pos
	 */
	public void moveOrCopyPage(Integer srcPid, Integer tarPid, int pos);

	/**
	 * 可参与的调查
	 * @return
	 */
	public List<Survey> findAllAvailableSurveys();

}
