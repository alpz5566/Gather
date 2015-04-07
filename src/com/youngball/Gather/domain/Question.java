package com.youngball.Gather.domain;

import java.io.Serializable;

import com.youngball.Gather.util.StringUtil;
import com.youngball.Gather.util.ValidateUtil;

/**
 * 问题实体
 * @author lpz
 *
 */
public class Question implements Serializable{
	private static String rn = "\r\n"; 
	private static final long serialVersionUID = 2333625411756778441L;
	private Integer id;
	private int questionType; 
	private String title;
	private String options;
	private String[] optionArr;
	private boolean other;

	private int otherStyle;
	private String otherSelectOptions;
	private String[] otherSelectOptionsArr; 

	//矩阵行标题集
	private String matrixRowTitles;
	private String[] matrixRowTitlesArr;
	//矩阵列标题集
	private String matrixColTitles;
	private String[] matrixColTitlesArr;
	//矩阵下拉选项集
	private String matrixSelectOptions;
	private String[] matrixSelectOptionsArr;
	
	//建立问题到页面之间多对一的关系
	private Page page ;

	
	public String[] getMatrixRowTitlesArr() {
		return matrixRowTitlesArr;
	}

	public void setMatrixRowTitlesArr(String[] matrixRowTitlesArr) {
		this.matrixRowTitlesArr = matrixRowTitlesArr;
	}

	public String[] getMatrixColTitlesArr() {
		return matrixColTitlesArr;
	}

	public void setMatrixColTitlesArr(String[] matrixColTitlesArr) {
		this.matrixColTitlesArr = matrixColTitlesArr;
	}

	public String[] getMatrixSelectOptionsArr() {
		return matrixSelectOptionsArr;
	}

	public void setMatrixSelectOptionsArr(String[] matrixSelectOptionsArr) {
		this.matrixSelectOptionsArr = matrixSelectOptionsArr;
	}

	public String[] getOptionArr() {
		return optionArr;
	}

	public void setOptionArr(String[] optionArr) {
		this.optionArr = optionArr;
	}

	public String[] getOtherSelectOptionsArr() {
		return otherSelectOptionsArr;
	}

	public void setOtherSelectOptionsArr(String[] otherSelectOptionsArr) {
		this.otherSelectOptionsArr = otherSelectOptionsArr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
		this.optionArr = StringUtil.strArr(options, rn);
	}

	public boolean isOther() {
		return other;
	}

	public void setOther(boolean other) {
		this.other = other;
	}

	public int getOtherStyle() {
		return otherStyle;
	}

	public void setOtherStyle(int otherStyle) {
		this.otherStyle = otherStyle;
	}

	public String getOtherSelectOptions() {
		return otherSelectOptions;
	}

	public void setOtherSelectOptions(String otherSelectOptions) {
		this.otherSelectOptions = otherSelectOptions;
		this.otherSelectOptionsArr = StringUtil.strArr(otherSelectOptions, rn);
	}

	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}

	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = matrixRowTitles;
		this.matrixRowTitlesArr = StringUtil.strArr(matrixRowTitles, rn);
	}

	public String getMatrixColTitles() {
		return matrixColTitles;
	}

	public void setMatrixColTitles(String matrixColTitles) {
		this.matrixColTitles = matrixColTitles;
		this.matrixColTitlesArr = StringUtil.strArr(matrixColTitles, rn);
	}

	public String getMatrixSelectOptions() {
		return matrixSelectOptions;
	}

	public void setMatrixSelectOptions(String matrixSelectOptions) {
		this.matrixSelectOptions = matrixSelectOptions;
		this.matrixSelectOptionsArr = StringUtil.strArr(matrixSelectOptions, rn);
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
