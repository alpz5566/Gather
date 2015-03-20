package com.youngball.Gather.action;

import java.lang.reflect.ParameterizedType;

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
	
	public T model;
	@SuppressWarnings("unchecked")
	public BaseAction(){
		try {
			ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
			model = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public void prepare() throws Exception {
		
	}

	public  T getModel() {
		return model;
	}

}
