package com.youngball.Gather.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youngball.Gather.dao.BaseDao;
import com.youngball.Gather.domain.User;
import com.youngball.Gather.service.UserService;
import com.youngball.Gather.util.ValidateUtil;

/**
 * userServiceImpl
 * @author lpz
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	/**
	 * 重写该方法,目的覆盖注解
	 */
	@Resource(name="userDao")
	public void setDao(BaseDao<User> dao) {
		super.setDao(dao);
	}

	@Override
	public boolean isRegisted(String email) {
		String hql = "FROM User u where u.email = ?" ;
		List<User> list = this.findEntityByHQL(hql, email);
		return ValidateUtil.isvalidate(list);
	}
	
	
}
