package com.ymz.hrm.base.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UserNamePasswordKapchaToken extends UsernamePasswordToken{

	private static final long serialVersionUID = 1L;
	
	/**加上自己的验证码**/
	private String kaptcha;

	/**提供getter setter方法**/
	public String getKaptcha() {
		return kaptcha;
	}

	public void setKaptcha(String kaptcha) {
		this.kaptcha = kaptcha;
	}

	/**构造函数**/
	public UserNamePasswordKapchaToken() {
		super();
	}

	/**有参构造函数**/
	public UserNamePasswordKapchaToken(String username, char[] password,boolean rememberMe, String host, String kaptcha) {
		super(username, password, rememberMe, host);
		this.kaptcha = kaptcha;
	}
	
}
