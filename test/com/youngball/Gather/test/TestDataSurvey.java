package com.youngball.Gather.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.youngball.Gather.domain.Page;
import com.youngball.Gather.domain.Survey;
import com.youngball.Gather.domain.User;
import com.youngball.Gather.service.SurveyService;

public class TestDataSurvey {
	private static ClassPathXmlApplicationContext ac = null;
	
	@BeforeClass
	public static void iniAC(){
		ac = new ClassPathXmlApplicationContext("beans.xml");
	}
	
	@Test
	public void saveSurvey(){
		SurveyService ss = (SurveyService) ac.getBean("surveyService");
		User u = new User();
		Page p = new Page();
		Survey s = new Survey();
		u.setId(4);
		p.setSurvey(s);
		s.getPages().add(p);
		s.setTitle("4字");
		ss.addSurvey(u);
	}
	
}
