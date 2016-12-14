package com.ymz.hrm.base;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.ymz.hrm.base.config.parsexml.permission.PermissionXmlObject;
import com.ymz.hrm.base.config.parsexml.role.RoleRootXmlObject;
import com.ymz.hrm.base.config.parsexml.user.UserRootXmlObject;
import com.ymz.hrm.base.service.PermissionService;
import com.ymz.hrm.base.service.RoleService;
import com.ymz.hrm.base.service.UserService;
import com.ymz.hrm.common.utils.XmlParseObjectUtil;

import java.util.Properties;

/**
 * 
 * 类名称：Initialization <br />
 * 类描述：initDatabase 初始化系统参数 <br />
 * 
 * @version
 */
@Component
public class Initialization implements ServletContextAware {
	
	ServletContext context;
	@Autowired
	UserService userService;

	@Autowired
	PermissionService permissionService;

	@Autowired
	RoleService roleService;


	//@Autowired
	//DictService dictService;
	@Resource(name="config")
	private Properties config;

	private static final Logger logger = LoggerFactory.getLogger(Initialization.class);

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	@PostConstruct
	public void init() {
		logger.info("========================初始化数据库开始==================================");
		initDatabase();
		logger.info("========================初始化数据库完成==================================");
	}

	/**
	 * 初始化数据库
	 */
	public void initDatabase() {
		try {
		String ab_path = Initialization.class.getClassLoader().getResource("").getPath();
		String userPath = ab_path + "/data/init/user.xml";
		String rolePath = ab_path + "/data/init/role.xml";
		String permPath = ab_path + "/data/init/permission.xml";
		String encoding = "UTF-8";
		RoleRootXmlObject  roles = XmlParseObjectUtil.getXmlObjectByFile(rolePath,encoding,RoleRootXmlObject.class);
		UserRootXmlObject users = XmlParseObjectUtil.getXmlObjectByFile(userPath,encoding,UserRootXmlObject.class);
		PermissionXmlObject perms = XmlParseObjectUtil.getXmlObjectByFile(permPath,encoding,PermissionXmlObject.class);
		//初始化角色
		roleService.initRole(roles);
		//初始管理员
		userService.initAdminuser(users);
		//初始化权限配置（只有当权限表为空时才会进行）
		permissionService.initPermission(perms);
		//初始化数据字典
		//dictService.initDictionary();
		}catch (Exception e){
			e.printStackTrace();
			logger.info("==================初始化数据库出现异常=====================");
		}
	}
}
