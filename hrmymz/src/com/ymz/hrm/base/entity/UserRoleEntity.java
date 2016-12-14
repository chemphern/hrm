package com.ymz.hrm.base.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="base_user_role")
public class UserRoleEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id//id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)//many to one的关系 ，懒加载
	@JoinColumn(name = "user_id", nullable = false) //列名user_id ,不可空
	private UserEntity userEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private RoleEntity roleEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}
	
	
}
