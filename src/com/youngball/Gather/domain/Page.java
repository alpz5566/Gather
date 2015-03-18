package com.youngball.Gather.domain;

/**
 * 页面实体
 * @author lpz
 *
 */
public class Page {
	private Integer id;
	private String title = "未命名";
	private String description;
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
