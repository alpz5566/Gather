package com.youngball.Gather.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 页面实体
 * @author lpz
 *
 */
public class Page {
	private Integer id;
	private String title = "未命名";
	private String description;
	
	//调查
	private Survey survey;
	//问题集合
	private Set<Question> questions = new HashSet<Question>(); 
	
	public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	public Set<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
