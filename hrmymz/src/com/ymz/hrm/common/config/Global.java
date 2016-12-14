package com.ymz.hrm.common.config;

/**用于存放整个项目公共的全局变量定义**/
public class Global {

	public static final String SESSION_USER = "hrm_session_user";
	
    public static final String SESSION_PRINCIPALS_PREV = "HRM_SESSION_PRINCIPALS_";

    public static final String CONTEXT_SYSTEM_CONFIG = "HRM_CONTEXT_SYSTEM_CONFIG";

    /**加密方法*/
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;

    public static final String LOGIN_CAPTCHA = "captcha";

	public static final String LOGIN_REMEMBERME = "rememberme";
}
