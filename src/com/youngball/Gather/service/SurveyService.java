package com.youngball.Gather.service;

import java.util.List;

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

}
