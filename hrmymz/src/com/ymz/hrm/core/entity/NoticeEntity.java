package com.ymz.hrm.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymz.hrm.base.entity.UserEntity;

@Entity
@Table(name = "core_notice")
public class NoticeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)   //id自动生成策略
	@Column(name = "id", unique = true, nullable = false) //列名 id   唯一   不能为空
	private Integer id;
	
	//公告标题
	@Column
	private String title;
	
	//公告内容
	@Column
	private String content;

	//创建时间
	@Column
	private Date create_time;
	
	//创建人编号 user_id
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY) //懒加载
	@JoinColumn(name = "user_id", nullable = false)   //列名 user_id   不能为空
	private UserEntity userEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	
	
}
