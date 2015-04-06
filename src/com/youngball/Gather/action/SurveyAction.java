package com.youngball.Gather.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.youngball.Gather.domain.Survey;
import com.youngball.Gather.domain.User;
import com.youngball.Gather.service.SurveyService;
import com.youngball.Gather.util.ValidateUtil;

@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware,ServletContextAware{

	private static final long serialVersionUID = -1416936634856203333L;
	
//	private Map<String, Object> sessionMap;
	
	//接受session的user对象
	private User user;
	
	//注入SurveyService
	@Resource
	private SurveyService surveyService;
	
	private Integer sid;
	
	private File logoPhoto;
	
	private String logoPhotoFileName;
	
	//错误页面
	private String inputPage;
	
	public String getInputPage() {
		return inputPage;
	}

	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}

	public String getLogoPhotoFileName() {
		return logoPhotoFileName;
	}

	public void setLogoPhotoFileName(String logoPhotoFileName) {
		this.logoPhotoFileName = logoPhotoFileName;
	}

	public File getLogoPhoto() {
		return logoPhoto;
	}

	public void setLogoPhoto(File logoPhoto) {
		this.logoPhoto = logoPhoto;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	private List<Survey> mySurveys;

	//接受servletContext对象
	private ServletContext sc;
	
	public List<Survey> getMySurvey() {
		return mySurveys;
	}

	public void setMySurvey(List<Survey> mySurvey) {
		this.mySurveys = mySurvey;
	}

	/**
	 * 新建调查页面
	 * @return
	 */
	public String addSurvey(){
		/*User user = (User) sessionMap.get("user");*/
		this.model = surveyService.addSurvey(user);
		return "addSurveyPage";
	}

	/**
	 * 查询我的调查
	 * @return
	 */
	public String mySurvey(){
		// User user = (User) sessionMap.get("user");
		this.mySurveys = surveyService.findSurveysByUid(user);
		return "mySurveysListPage";
	}
	
	
//	//注入session map,使得耦合度降低
//	public void setSession(Map<String, Object> session) {
//		this.sessionMap = session;
//	}

	//注入用户对象
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 设计调查
	 * @return
	 */
	public String designSurvey(){
		this.model = surveyService.getSurvey(sid);
		return "designSurveyPage";
	}
	
	/**
	 * 编辑调查
	 * @return
	 */
	public String editSurvey(){
		this.model = surveyService.getSurvey(sid);
		return "editSurveyPage";
	}

	/**
	 * 更新调查
	 */
	public String updateSurvey(){
		model.setUser(user);
		surveyService.updateSurvey(model);
		return "designSurveyAction";
	}
	
	/**
	 * 删除调查
	 * @return
	 */
	public String deleteSurvey(){
		surveyService.deleteSurvey(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * 清空答案数据
	 * @return
	 */
	public String clearAnswer(){
		surveyService.clearAnswers(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * 改变状态
	 * @return
	 */
	public String changeStatus(){
		surveyService.changeStatus(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * 增加到达logo页面
	 * @return
	 */
	public String toAddLogoPage(){
		return "addLogoPage";
	}
	
	/**
	 * logo图片上传
	 * @return
	 */
	public String doAddLogo(){
		if(ValidateUtil.isValid(logoPhotoFileName)){
			//上传文件
				//可以使用这样的方法得到upload的真实路径,但是这种代码方式不优雅,解决:实现一个ServletContextAware的接口注入servletcontext对象
				//ServletActionContext.getServletContext().getRealPath("/upload");
			String dir = sc.getRealPath("/upload");
			//按时间命名图片
			long i = System.nanoTime();
			//图片后缀名
			String ext = logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			File newFile = new File(dir, i + ext);
			logoPhoto.renameTo(newFile);
			//更新数据库路径信息
			surveyService.updateLogoPath(sid,"/upload/" + i + ext);
		}
		return "designSurveyAction";
	}
	
	//该方法在doAddLogo之前执行,文件上传拦截器
	public void prepareDoAddLogo(){
		inputPage = "/addLogo.jsp";
	}
	
	public boolean logoIsExists(){
		String path = model.getLogoPath();
		if(ValidateUtil.isValid(path)){
			String realPath = sc.getRealPath(path);
			return new File(realPath).exists();
		}
		return false;
	}
	
	//注入ServletContext对象
	public void setServletContext(ServletContext context) {
		this.sc = context;
	} 
	
	
	
	
	
	
	
	
	
	
}
