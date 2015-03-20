package com.youngball.Gather.service;

import com.youngball.Gather.domain.User;

public interface UserService extends BaseService<User> {

	/**
	 * 判断邮箱是否占用
	 * @param email
	 * @return
	 */
	public boolean isRegisted(String email);

	/**
	 * 验证登陆信息
	 * @param email
	 * @param md5
	 * @return
	 */
	public User validateLoginInfo(String email, String password);

	

}
