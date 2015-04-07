package com.youngball.Gather.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 页面实体
 * @author lpz
 *
 */
public class Page implements Serializable{
	
	private static final long serialVersionUID = 5976455485399129071L;
	
	private Integer id;
	private String title = "未命名";
	private String description;
	//页序
	private float orderno;
	
	public float getOrderno() {
		return orderno;
	}
	public void setOrderno(float orderno) {
		this.orderno = orderno;
	}
	//调查 transient虚拟机不再串行化这个对象
	private transient Survey survey;
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
	//默认orderno和id一致
	public void setId(Integer id) {
		this.id = id;
		if(id != null){
			this.orderno = id;
		}
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
