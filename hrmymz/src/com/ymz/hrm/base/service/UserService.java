package com.ymz.hrm.base.service;

import java.util.List;

import com.ymz.hrm.base.config.parsexml.user.UserRootXmlObject;
import com.ymz.hrm.base.entity.UserEntity;

public interface UserService {

	/**初始化管理员**/
	void initAdminuser(UserRootXmlObject users);

	/**获取所有的用户**/
	List<UserEntity> findUsers(String page,String pageSize);

	/**获取用户表的总数**/
	long getAllCount();

}
