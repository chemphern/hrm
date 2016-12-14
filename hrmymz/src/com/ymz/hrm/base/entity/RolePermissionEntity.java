package com.ymz.hrm.base.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="base_role_permission")
public class RolePermissionEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id//id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)//many to one的关系 ，懒加载
	@JoinColumn(name = "role_id", nullable = false) //列名user_id ,不可空
	private RoleEntity roleEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)//many to one的关系 ，懒加载
	@JoinColumn(name = "permission_id", nullable = false) //列名user_id ,不可空
	private PermissionEntity permissionEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}

	public PermissionEntity getPermissionEntity() {
		return permissionEntity;
	}

	public void setPermissionEntity(PermissionEntity permissionEntity) {
		this.permissionEntity = permissionEntity;
	}
	

}
