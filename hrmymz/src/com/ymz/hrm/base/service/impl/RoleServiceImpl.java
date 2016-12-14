package com.ymz.hrm.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ymz.hrm.base.config.parsexml.role.RoleRootXmlObject;
import com.ymz.hrm.base.config.parsexml.role.RoleXmlObject;
import com.ymz.hrm.base.dao.RoleDao;
import com.ymz.hrm.base.entity.RoleEntity;
import com.ymz.hrm.base.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleDao roleDao;
	
	/**初始化角色**/
	@Override
	public void initRole(RoleRootXmlObject roles) {
		long count = roleDao.count("select count(*) from RoleEntity");
		if(count < 1){
			for(RoleXmlObject rxo:roles.getRoleXmlObjectList()){
				String code = rxo.getCode();
				String name = rxo.getName();
				String isSuper = rxo.getIsSuper();
				String remark = rxo.getRemark();
				RoleEntity role = new RoleEntity();
				role.setCode(code);
				if(isSuper == null){
					role.setIsSuper(false);
				}else{
					role.setIsSuper(isSuper.equalsIgnoreCase("true"));
				}
				role.setName(name);
				role.setRemark(remark);
				roleDao.save(role);
			}
		}
	}

}
