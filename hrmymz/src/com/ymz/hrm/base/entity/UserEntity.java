package com.ymz.hrm.base.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymz.hrm.core.entity.DocumentEntity;
import com.ymz.hrm.core.entity.NoticeEntity;

@Entity
@Table(name="base_user")
public class UserEntity implements Serializable{

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	
	@Id//id
    @GeneratedValue(strategy= GenerationType.AUTO)//id自动生成策略
	//列名为id，唯一，不可空
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@Column(length=50)
	private String name;
	
	@Column(name = "login_name")
	private String loginName;
	
	@Column(name = "password")
	private String password;
	
	@Column
	private String salt;
	
	@Column
	private short sex;
	
	/**不被hibernate识别**/
	@Transient
	@JsonIgnore
	private boolean isSuper;
	
	@JsonIgnore//转化成json的时候，会忽略该字段
	//one to manry关系   关联操作为all，表示增删改的操作，都会直接影响到userRole这张表的增删改
	//懒加载 关联
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userEntity")
	private Set<UserRoleEntity> userRoles = new HashSet<UserRoleEntity>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userEntity")
	private Set<NoticeEntity> noticeEntity = new HashSet<NoticeEntity>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userEntity")
	private Set<DocumentEntity> documentEntity = new HashSet<DocumentEntity>();

	public Set<NoticeEntity> getNoticeEntity() {
		return noticeEntity;
	}

	public void setNoticeEntity(Set<NoticeEntity> noticeEntity) {
		this.noticeEntity = noticeEntity;
	}

	public Set<DocumentEntity> getDocumentEntity() {
		return documentEntity;
	}

	public void setDocumentEntity(Set<DocumentEntity> documentEntity) {
		this.documentEntity = documentEntity;
	}

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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public short getSex() {
		return sex;
	}

	public void setSex(short sex) {
		this.sex = sex;
	}

	public Set<UserRoleEntity> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoleEntity> userRoles) {
		this.userRoles = userRoles;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean isSuper() {
		return isSuper;
	}

	public void setSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}
	
	
}
