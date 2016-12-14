package com.ymz.hrm.base.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymz.hrm.base.dao.PermissionDao;
import com.ymz.hrm.base.dao.UserDao;
import com.ymz.hrm.base.entity.PermissionEntity;
import com.ymz.hrm.base.entity.UserEntity;
import com.ymz.hrm.base.entity.UserRoleEntity;
import com.ymz.hrm.base.shiro.ShiroUser;
import com.ymz.hrm.base.shiro.UserNamePasswordKapchaToken;
import com.ymz.hrm.common.config.Global;
import com.ymz.hrm.common.exception.CaptchaException;
import com.ymz.hrm.common.utils.Encodes;
import com.ymz.hrm.common.utils.StringUtils;

@Service
public class UserRealmImpl extends AuthorizingRealm{

	/**对用户操作的数据访问层dao**/
	@Autowired
	private UserDao userDao;
	
	/**对权限操作的数据访问层dao**/
	@Autowired
	private PermissionDao permissionDao;
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		UserEntity user = userDao.get("from UserEntity where loginName = ?", new Object[]{shiroUser.loginName});
		//把principals放session中 key=前缀 + userId value=principals
		SecurityUtils.getSubject().getSession().setAttribute(Global.SESSION_PRINCIPALS_PREV + String.valueOf(user.getId()),SecurityUtils.getSubject().getPrincipals());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//是否超级管理员
		boolean is_super = false;
		//赋予角色
		for(UserRoleEntity userRole:user.getUserRoles()){
			String role_code = userRole.getRoleEntity().getCode();
			info.addRole(role_code);
			boolean isSuper = userRole.getRoleEntity().getIsSuper();
			if(isSuper){
				is_super = true;
			}
		}
		if(is_super){
			info.addStringPermission("*");
		}else {
			//赋予权限
			for (PermissionEntity permission : (List<PermissionEntity>)permissionDao.findPermissions(user.getId())) {
				if (StringUtils.isNotBlank(permission.getCode()))
					info.addStringPermission(permission.getCode());
			}
		}
		return info;
	}

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		
		UserNamePasswordKapchaToken token = (UserNamePasswordKapchaToken) authcToken;
		//根据用户名查询用户表
		String loginName = token.getUsername();
		UserEntity userEntity = userDao.get("from UserEntity where loginName = ?", new Object[]{loginName});
		//用户表不存在用户名为登录名的数据
		if(userEntity == null){
			return null;
			//否则（数据库有这个登陆名的数据）判断验证码是否正确
		}else if(checkKaptchaIsTrue(token)){
			byte[] salt = Encodes.decodeHex(userEntity.getSalt());
			ShiroUser shiroUser=new ShiroUser(userEntity.getId(), userEntity.getLoginName(), userEntity.getName());
			//设置用户session
			//判断用户是否超级管理员,迭代用户所有的角色，当其中一个角色是超级超级管理员的时候，用户就是超级管理员
			boolean is_super = false;
			for(UserRoleEntity userRole:userEntity.getUserRoles()){
				if(userRole.getRoleEntity().getIsSuper()){
					is_super = true;
					break;
				}
			}
			//设置用户是超级管理员
			userEntity.setSuper(is_super);
			//将用户信息存放到session中
			Session session =SecurityUtils.getSubject().getSession();
			session.setAttribute(Global.SESSION_USER, userEntity);
			return new SimpleAuthenticationInfo(shiroUser,userEntity.getPassword(), ByteSource.Util.bytes(salt), getName());
		}else{
			return null;
		}
	}	
	
	public boolean checkKaptchaIsTrue(UserNamePasswordKapchaToken token){
		//拿到谷歌验证码生成的验证码(放在session里面的)
		String serverkaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//用户输入的验证码
		String userKaptcha = token.getKaptcha();
		//忽略大小字母进行比较
		if(serverkaptcha != null && userKaptcha != null && serverkaptcha.equalsIgnoreCase(userKaptcha)){
			return true;
		}else{
			throw new CaptchaException("验证码错误！");
		}		
	}
	
	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@SuppressWarnings("static-access")
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Global.HASH_ALGORITHM);
		matcher.setHashIterations(Global.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

}
