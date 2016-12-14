package com.ymz.hrm.base.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="base_dictionary_item")
public class DictionaryItemEntity implements Serializable{

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
	
	@Column
	private short sort;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dictionary_id", nullable = false)
	private DictionaryEntity dictionaryEntity;

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

	public short getSort() {
		return sort;
	}

	public void setSort(short sort) {
		this.sort = sort;
	}

	public DictionaryEntity getDictionaryEntity() {
		return dictionaryEntity;
	}

	public void setDictionaryEntity(DictionaryEntity dictionaryEntity) {
		this.dictionaryEntity = dictionaryEntity;
	}
	
	
	
}
