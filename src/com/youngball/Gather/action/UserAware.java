package com.youngball.Gather.action;

import com.youngball.Gather.domain.User;

/**
 * 用户关注接口 源自SessionAware
 * @author lpz
 *
 */
public interface UserAware {
	public void setUser(User user);
}
