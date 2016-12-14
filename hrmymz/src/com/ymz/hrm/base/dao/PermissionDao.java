package com.ymz.hrm.base.dao;

import java.util.List;

import com.ymz.hrm.base.entity.PermissionEntity;


public interface PermissionDao extends BaseDao<PermissionEntity,Integer>{

	/**根据用户id去拿到用户所有的权限**/
	List<PermissionEntity> findPermissions(Integer id);

	/**根据用户id获取菜单权限**/
	List<PermissionEntity> findMenus(Integer id);

	/**获取所有菜单权限**/
	List<PermissionEntity> findMenus();

}
