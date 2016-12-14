package com.ymz.hrm.base.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="base_role")
public class RoleEntity implements Serializable{

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
	private String remark;
	
	/**是不是超级管理员**/
	@Column
	@org.hibernate.annotations.Type(type="yes_no")
	private boolean isSuper;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roleEntity")
	private Set<UserRoleEntity> userRoles = new HashSet<UserRoleEntity>();
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "roleEntity")
	private Set<RolePermissionEntity> rolePermissions = new HashSet<RolePermissionEntity>();
	
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean getIsSuper() {
		return isSuper;
	}
	public void setIsSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}
	public Set<UserRoleEntity> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRoleEntity> userRoles) {
		this.userRoles = userRoles;
	}
	public Set<RolePermissionEntity> getRolePermissions() {
		return rolePermissions;
	}
	public void setRolePermissions(Set<RolePermissionEntity> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}
	

}
