package com.ymz.hrm.base.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ymz.hrm.base.dao.PermissionDao;
import com.ymz.hrm.base.entity.PermissionEntity;

@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl<PermissionEntity,Integer> implements PermissionDao{

	@Override
	public List<PermissionEntity> findPermissions(Integer id) {
		StringBuffer sb=new StringBuffer();
		sb.append("select p from PermissionEntity p ");
		sb.append("inner join p.rolePermissions rp ");
		sb.append("inner join rp.roleEntity r ");
		sb.append("inner join r.userRoles ur ");
		sb.append("inner join ur.userEntity u ");
		sb.append("where u.id= ? order by p.sort ");
		List<PermissionEntity> rp = find(sb.toString(),new Object[]{id});
		return rp;
	}
	
	/**
	 * 将多个permision合并
	 * **/
	public List<PermissionEntity> unionPermission(List<PermissionEntity> ... permissions){
		List<PermissionEntity> resList = new ArrayList<>();
		for(int x = 0;x<permissions.length;x++){
			for(PermissionEntity permission:permissions[x]){
				boolean flag = true;
				for(PermissionEntity p:resList){
					if(permission.getId() == p.getId()){
						flag = false;
						break;
					}
				}
				if(flag){
					resList.add(permission);
				}
			}
		}
		return resList;
	}
	
	/**
     * 映射方法，把平行结构的数据变成树结构的数据
     * 思路：迭代权限，获取所有有子权限的集合
     *       在添加权限集合的过程中删剩父权限集合
     *       再依次附上子权限列表
     * **/
    @SuppressWarnings("unchecked")
	public List<PermissionEntity> mapping(List<PermissionEntity>sources){
        Map<Integer,Object> mapping = new HashMap<>();
        Map<Integer,Object> temp = new HashMap<>();
        Map<Integer,Object> idList = new HashMap<>();
        List<PermissionEntity> targer = new ArrayList<>();
        for(PermissionEntity source :sources){
            //除了功能点外的权限
            if(!source.getType().equals("func")) {
                mapping.put(source.getId(), source);
                temp.put(source.getId(), source);
            }
        }
        for(PermissionEntity source:sources){
            //除了功能点外的权限
            if(!source.getType().equals("func")) {
                Integer id = source.getId();
                Integer pid = source.getPid();
                if (pid != null) {
                    PermissionEntity permission = (PermissionEntity) mapping.get(pid);
                    if (permission != null) {
                        List<PermissionEntity> list = (List<PermissionEntity>) idList.get(pid);
                        if (list == null) {
                            list = new ArrayList<>();
                            idList.put(pid, list);
                        }
                        list.add(source);
                        temp.remove(id);
                    }
                }
            }
        }
        for(Map.Entry<Integer,Object> m:temp.entrySet()){
            PermissionEntity p = (PermissionEntity)m.getValue();
            iteratorChild(p,idList);
            targer.add(p);
       }
        return targer;
    }
    
    /**迭代映射树**/
    @SuppressWarnings("unchecked")
    public void iteratorChild(PermissionEntity p,Map<Integer,Object> idList){
        if (idList.get(p.getId()) != null) {
            List<PermissionEntity> ps = (List<PermissionEntity>) idList.get(p.getId());
            if(ps != null){
                for(PermissionEntity perm:ps){
                    iteratorChild(perm,idList);
                }
                p.setChildPermission(ps);
            }

        }
    }

	@Override
	public List<PermissionEntity> findMenus(Integer id) {
		StringBuffer sb=new StringBuffer();
		sb.append("select p  from PermissionEntity p ");
		sb.append("inner join p.rolePermissions rp ");
		sb.append("inner join rp.roleEntity r ");
		sb.append("inner join r.userRoles ur ");
		sb.append("inner join ur.userEntity u ");
		sb.append("where u.id=? order by p.sort");
		Query query = createQuery(sb.toString());
		query.setParameter(0,id);
		List<PermissionEntity> ps = query.list();
		return mapping(ps);
	}

	@Override
	public List<PermissionEntity> findMenus() {
		StringBuffer sb=new StringBuffer();
		sb.append("select p  from PermissionEntity p ");
		sb.append("where p.type = 'module' order by p.sort");
		List<PermissionEntity> ps = find(sb.toString());
		for(PermissionEntity p:ps){
			setMenuChilds(p);
		}
		return ps;
	}
	
	public void setMenuChilds(PermissionEntity p){
		if(!p.getType().equals("url")) {
			List<PermissionEntity> ps = find("from PermissionEntity p where pid = ?",new Object[]{p.getId()});
			if(ps != null && ps.size() > 0){
				p.setChildPermission(ps);
				for(PermissionEntity i : ps){
					setMenuChilds(i);
				}
			}else {
				p.setChildPermission(null);
			}
		}
	}

}
