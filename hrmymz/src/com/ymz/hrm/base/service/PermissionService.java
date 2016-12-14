package com.ymz.hrm.base.service;

import java.util.List;

import com.ymz.hrm.base.config.parsexml.permission.PermissionXmlObject;
import com.ymz.hrm.base.entity.PermissionEntity;

public interface PermissionService {

	/**初始化权限**/
	void initPermission(PermissionXmlObject perms);

	/**根据用户获取菜单权限**/
	List<PermissionEntity> getMenus(Integer id);

	/**获取所有菜单权限**/
	List<PermissionEntity> getMenus();

}
