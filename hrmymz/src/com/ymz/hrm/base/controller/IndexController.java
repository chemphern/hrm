package com.ymz.hrm.base.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ymz.hrm.base.entity.PermissionEntity;
import com.ymz.hrm.base.entity.UserEntity;
import com.ymz.hrm.base.service.PermissionService;
import com.ymz.hrm.common.config.Global;

@Controller
public class IndexController {
	
	@Resource
	private PermissionService permissionService;
	
	@RequestMapping("/index")
	public String index(Model model,HttpSession session){
		UserEntity user = (UserEntity) session.getAttribute(Global.SESSION_USER);
		List<PermissionEntity> p = null;
		if(user.isSuper()){
			p = permissionService.getMenus();
		}else{
			p = permissionService.getMenus(user.getId());
		}
		model.addAttribute("index_permissions",p);
		return "/main/index";
	}
	
	@RequestMapping("/welcome")
	public String welcome(){
		return "/main/welcome";
	}
}
