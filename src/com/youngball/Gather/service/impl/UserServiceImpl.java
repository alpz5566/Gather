package com.youngball.Gather.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youngball.Gather.dao.BaseDao;
import com.youngball.Gather.domain.User;
import com.youngball.Gather.service.UserService;

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
	
	
}
