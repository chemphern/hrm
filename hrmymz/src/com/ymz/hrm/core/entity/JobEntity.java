package com.ymz.hrm.core.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "core_job")
public class JobEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id//id
	//id自动生成策略
	@GeneratedValue(strategy=GenerationType.AUTO)
	//列名为id，唯一，不可空
	@Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String code;
	
	@Column
	private String remark;
	
	//一对多的关系    懒加载 
	@OneToMany(cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY,  //关联操作为all，表示增删改的操作，都会直接影响到userRole这张表的增删改
			mappedBy = "jobEntity")
	private Set<EmployeeEntity> employeeEntity = new HashSet<EmployeeEntity>();

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

	public Set<EmployeeEntity> getEmployeeEntity() {
		return employeeEntity;
	}

	public void setEmployeeEntity(Set<EmployeeEntity> employeeEntity) {
		this.employeeEntity = employeeEntity;
	}
	
	
}
