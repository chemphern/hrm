package com.ymz.hrm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.ymz.hrm.base.dao.RoleDao;
import com.ymz.hrm.base.entity.RoleEntity;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<RoleEntity,Integer> implements RoleDao{

}
