package com.ymz.hrm.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymz.hrm.base.entity.UserEntity;
import com.ymz.hrm.base.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/list")
	public String list(){
		return "/user/list";
	}
	
	@RequestMapping("listData")
	@ResponseBody
	public Map<String,Object> listData(String page,String pageSize){
		Map<String,Object> res = new HashMap<>();
		//从后台获取用户列表
		List<UserEntity> datas = userService.findUsers(page,pageSize);
		long count = userService.getAllCount();
		res.put("data", datas);
		res.put("count", count);
		return res;
	}
}
