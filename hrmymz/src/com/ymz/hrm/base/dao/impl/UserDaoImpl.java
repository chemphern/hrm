package com.ymz.hrm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.ymz.hrm.base.dao.UserDao;
import com.ymz.hrm.base.entity.UserEntity;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UserEntity,Integer> implements UserDao{

}
