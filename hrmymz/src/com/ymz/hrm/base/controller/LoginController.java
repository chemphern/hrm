package com.ymz.hrm.base.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ymz.hrm.common.config.Global;

/**
 * springMVC登陆控制层
 * **/
@Controller
@RequestMapping("/")
public class LoginController {
	
	@RequestMapping(value="login",method = RequestMethod.GET)
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()||subject.isRemembered()){
            return "redirect:/logout";
        }
        return "/login";
    }
    /**
     * 登录失败
     * @param userName
     * @param model
     * @return
     */
    @RequestMapping(value="login",method = RequestMethod.POST)
    public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
        System.out.println("登录失败！");
        return "/login";
    }

    /**
     * 登出
     * @param model
     * @return
     */
    @RequestMapping(value="logout")
    public String logout(Model model, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        session.removeAttribute(Global.SESSION_USER);
        subject.logout();
        return "/login";
    }
	
}
