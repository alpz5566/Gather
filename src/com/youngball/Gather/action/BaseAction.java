package com.youngball.Gather.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 抽象的BaseAction超类,专门用于继承	
 * @author lpz
 *
 * @param <T>
 */
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>,
		Preparable {

	//子ID,唯一标识符
	private static final long serialVersionUID = 458432474057225054L;

	public void prepare() throws Exception {
		
	}

	public abstract T getModel() ;

}
