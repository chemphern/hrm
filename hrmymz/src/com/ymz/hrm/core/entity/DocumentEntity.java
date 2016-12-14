package com.ymz.hrm.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymz.hrm.base.entity.UserEntity;

@Entity
@Table(name = "core_document")
public class DocumentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //id自动生成策略
	@Column(name = "id", nullable = false, unique = true) //列名为id   不能为空    唯一
	private Integer id;
	
	@Column
	private String title;
	
	@Column
	private String url;
	
	@Column
	private String remark;
	
	@Column
	private Date create_time;
	
	//创建人编号 user_id
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY) //懒加载
	@JoinColumn(name = "user_id", nullable = false)  //列名 user_id  不能为空
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
