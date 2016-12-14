package com.ymz.hrm.base.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="base_dictionary")
public class DictionaryEntity implements Serializable{

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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dictionaryEntity")
	private Set<DictionaryItemEntity> dictionaryItems = new HashSet<DictionaryItemEntity>();

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

	public Set<DictionaryItemEntity> getDictionaryItems() {
		return dictionaryItems;
	}

	public void setDictionaryItems(Set<DictionaryItemEntity> dictionaryItems) {
		this.dictionaryItems = dictionaryItems;
	}
	
	
}
