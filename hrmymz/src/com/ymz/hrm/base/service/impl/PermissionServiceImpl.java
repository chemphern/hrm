package com.ymz.hrm.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import com.ymz.hrm.base.config.parsexml.permission.FuncXmlObject;
import com.ymz.hrm.base.config.parsexml.permission.MenuXmlObject;
import com.ymz.hrm.base.config.parsexml.permission.ModuleXmlObject;
import com.ymz.hrm.base.config.parsexml.permission.PermissionXmlObject;
import com.ymz.hrm.base.config.parsexml.permission.SystemXmlObject;
import com.ymz.hrm.base.config.parsexml.permission.UrlXmlObject;
import com.ymz.hrm.base.dao.PermissionDao;
import com.ymz.hrm.base.entity.PermissionEntity;
import com.ymz.hrm.base.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService{

	@Resource
	private PermissionDao permissionDao;
	
	@Override
	public void initPermission(PermissionXmlObject perms) {
		long count  = permissionDao.count("select count(*) from PermissionEntity");
		if(count < 1){
			//删除角色权限表
			permissionDao.executeHql("delete from RolePermissionEntity");
			//删除所有该系统权限
			permissionDao.executeHql("delete from PermissionEntity");
			updateSystemFromXmlObject(perms);
		}
	}
	
	public void updateSystemFromXmlObject(PermissionXmlObject obj){
		List<SystemXmlObject> systemList = obj.getSystemXmlObjectList();
		//读取系统
		for(SystemXmlObject system :systemList) {
			//处理系统
			String system_code = system.getCode();
			if (system_code != null) {
				system_code = system_code.trim().replaceAll("\n", "").replaceAll(" ", "");
			}else{
				throw new ServiceException("xml文件格式不正确！");
			}
			//读取模块
			List<ModuleXmlObject> modules = system.getModuleXmlObjectList();
			for (ModuleXmlObject module : modules) {
				String module_name = module.getName();
				String module_sort = module.getSort();
				PermissionEntity module_permission = new PermissionEntity();
				module_permission.setAuthcModule(module_name, Short.parseShort(module_sort), (short) 0);
				//模块保存
				permissionDao.save(module_permission);
				int module_permission_id = module_permission.getId();
				//获取菜单
				List<MenuXmlObject> menus = module.getMenuXmlObject();
				if (menus != null) {
					for (MenuXmlObject menu : menus) {
						String menu_name = menu.getName();
						String menu_sort = menu.getSort();
						PermissionEntity menu_permission = new PermissionEntity();
						menu_permission.setAuthcMenu(menu_name, Short.parseShort(menu_sort), (short) 1);
						menu_permission.setPid(module_permission.getId());
						permissionDao.save(menu_permission);
						int menu_permission_id = menu_permission.getId();
						this.saveUrlPermission(menu_permission, menu.getUrlXmlObjects(), 2);
					}
				}
				//获取链接
				this.saveUrlPermission(module_permission, module.getUrlXmlObject(), 1);
			}
		}
	}
	
	/***
	 * 保存链接权限信息
	 * */
	private void saveUrlPermission(PermissionEntity p,List<UrlXmlObject> urls,int level){
		if(urls != null){
			for(UrlXmlObject url :urls){
				String url_name = url.getName();
				String url_sort = url.getSort();
				String url_func = url.getFuncs();
				String url_href = url.getHref();
				PermissionEntity menu_url_permission = new PermissionEntity();
				menu_url_permission.setAuthcUrl(url_name,Short.parseShort(url_sort),(short)level,url_href,url_func);
				menu_url_permission.setPid(p.getId());
				permissionDao.save(menu_url_permission);
				int menu_url_permission_id = menu_url_permission.getId();
				//获取功能点
				List<FuncXmlObject> url_funcs = url.getFuncXmlObjects();
				if(url_funcs != null){
					for(FuncXmlObject func : url_funcs){
						String func_name = func.getName();
						String func_sort = func.getSort();
						String func_code = func.getCode();
						if(func_code != null){
							func_code = func_code.trim().replaceAll("\n","").replaceAll(" ","");
						}
						PermissionEntity url_func_permission = new PermissionEntity();
						url_func_permission.setAuthcFunc(func_name,Short.parseShort(func_sort),(short)(level+1),func_code);
						url_func_permission.setPid(menu_url_permission.getId());
						permissionDao.save(url_func_permission);
					}
				}
			}
		}
	}

	@Override
	public List<PermissionEntity> getMenus(Integer id) {
		return permissionDao.findMenus(id);
	}

	@Override
	public List<PermissionEntity> getMenus() {
		return permissionDao.findMenus();
	}


}
