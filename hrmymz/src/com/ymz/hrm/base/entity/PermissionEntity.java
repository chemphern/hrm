package com.ymz.hrm.base.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="base_permission")
public class PermissionEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id//id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@Column
	private String name;

	@Column
	private String code;

	@Column
	private String type;
	
	@Column
	private short level;
	
	@Column
	private Integer pid;
	
	@Column
	private String url;
	
	@Column
	private short sort;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "permissionEntity")
	private Set<RolePermissionEntity> rolePermissions = new HashSet<RolePermissionEntity>();

	@Transient
	@JsonProperty("children")
	private List<PermissionEntity> childPermission;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public short getSort() {
		return sort;
	}

	public void setSort(short sort) {
		this.sort = sort;
	}

	public Set<RolePermissionEntity> getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(Set<RolePermissionEntity> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	/**
	 * 模块权限设置属性
	 * **/
	public void setAuthcModule(String name,short sort,short level){
		this.name = name;
		this.sort = sort;
		this.type = "module";
		this.level = level;
	}
	/**菜单权限设置属性**/
	public void setAuthcMenu(String name,short sort,short level){
		this.name = name;
		this.sort = sort;
		this.type = "menu";
		this.level = level;
	}
	/**链接权限设置属性**/
	public void setAuthcUrl(String name,short sort,short level,String href,String funcs){
		this.name = name;
		this.sort = sort;
		this.type = "url";
		this.level = level;
		this.url = href;
		this.code = funcs;
	}

	/**功能点设置属性**/
	public void setAuthcFunc(String name,short sort,short level,String code){
		this.name = name;
		this.sort = sort;
		this.type = "func";
		this.level = level;
		this.code = code;
	}

	public List<PermissionEntity> getChildPermission() {
		return childPermission;
	}

	public void setChildPermission(List<PermissionEntity> childPermission) {
		this.childPermission = childPermission;
	}
	
}
