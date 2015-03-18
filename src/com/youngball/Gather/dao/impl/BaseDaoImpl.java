package com.youngball.Gather.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.youngball.Gather.dao.BaseDao;

/**
 * dao基本实现,不实现实务管理
 * @author lpz
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sf;
	
	private Class<T> clazz;
	
	public BaseDaoImpl(){
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	public void saveEntity(T t) {
		sf.getCurrentSession().save(t);
	}

	public void updateEntity(T t) {
		sf.getCurrentSession().update(t);
	}

	public void saveOrUpdateEntity(T t) {
		sf.getCurrentSession().saveOrUpdate(t);
	}

	public void deleteEntity(T t) {
		sf.getCurrentSession().delete(t);
	}

	public void batchEntityByHQL(String hql, Object... objects) {
		Query q = sf.getCurrentSession().createQuery(hql);
		for(int i = 0 ; i < objects.length ; i++){
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}

	//get
	public T getEntity(Integer id) {
		return (T) sf.getCurrentSession().get(clazz, id);
	}

	public T loadEntity(Integer id) {
		return (T) sf.getCurrentSession().load(clazz, id);
	}

	public List<T> findEntityByHQL(String hql, Object... objects) {
		Query q = sf.getCurrentSession().createQuery(hql);
		for(int i = 0 ; i < objects.length ; i++){
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}

}
