package com.youngball.Gather.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.youngball.Gather.dao.BaseDao;
import com.youngball.Gather.service.BaseService;

/**
 * 抽象的service实现,专门用于继承
 * @author lpz
 *
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> dao;
	
	//注入dao
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Object... objects) {
		dao.batchEntityByHQL(hql, objects);
	}

	@Override
	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	@Override
	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		return dao.findEntityByHQL(hql, objects);
	}

}
