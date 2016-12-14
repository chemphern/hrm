package com.ymz.hrm.base.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ymz.hrm.base.config.parsexml.user.UserRootXmlObject;
import com.ymz.hrm.base.config.parsexml.user.UserXmlObject;
import com.ymz.hrm.base.dao.RoleDao;
import com.ymz.hrm.base.dao.UserDao;
import com.ymz.hrm.base.entity.RoleEntity;
import com.ymz.hrm.base.entity.UserEntity;
import com.ymz.hrm.base.entity.UserRoleEntity;
import com.ymz.hrm.base.service.UserService;
import com.ymz.hrm.common.config.Global;
import com.ymz.hrm.common.utils.Digests;
import com.ymz.hrm.common.utils.Encodes;

@Service("userService")
public class UserServiceImpl implements UserService{

	private static final int SALT_SIZE = 8;	//盐长度
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private RoleDao roleDao;
	
	@Override
	public void initAdminuser(UserRootXmlObject users) {
		long count = userDao.count("select count(*) from UserEntity");
		if(count < 1){
			for(UserXmlObject uxo :users.getUserXmlObjectList()){
				String loginName = uxo.getLoginName();
				String name = uxo.getName();
				String password = uxo.getPassword();
				String roles = uxo.getRoles();
				UserEntity user = new UserEntity();
				user.setName(name);
				user.setLoginName(loginName);
				user.setPassword(password);
				entryptPassword(user);
				String [] rolearr = roles.split(",");
				Set<UserRoleEntity> urs = new HashSet<>();
				for(String r:rolearr){
					RoleEntity roleEntity = roleDao.get("from RoleEntity where code = ?", new Object[]{r});
					if(roleEntity != null){
						UserRoleEntity ur = new UserRoleEntity();
						ur.setUserEntity(user);
						ur.setRoleEntity(roleEntity);
						urs.add(ur);
					}
				}
				user.setUserRoles(urs);
				userDao.save(user);
			}
		}
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(UserEntity user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(),salt, Global.HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 验证原密码是否正确
	 * @param user
	 * @param oldPassword
	 * @return
	 */
	public boolean checkPassword(UserEntity user,String oldPassword){
		byte[] salt =Encodes.decodeHex(user.getSalt()) ;
		byte[] hashPassword = Digests.sha1(oldPassword.getBytes(),salt, Global.HASH_INTERATIONS);
		if(user.getPassword().equals(Encodes.encodeHex(hashPassword))){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 获取所有的用户
	 * **/
	@Override
	public List<UserEntity> findUsers(String page,String pageSize) {
		int p = Integer.parseInt(page);
		int ps = Integer.parseInt(pageSize);
		p = (p - 1) * ps;
		List<UserEntity> users = userDao.find("from UserEntity",p,ps);
		return users;
	}

	/**获取用户总数**/
	@Override
	public long getAllCount() {
		return userDao.count("select count(*) from UserEntity");
	}

}
