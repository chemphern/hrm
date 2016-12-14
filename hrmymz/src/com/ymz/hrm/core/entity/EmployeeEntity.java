package com.ymz.hrm.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "core_employee")
public class EmployeeEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	//id自动生成策略
	@GeneratedValue(strategy=GenerationType.AUTO)
	//列名为id，唯一，不可空
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	//部门编号 dept_id
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY) //懒加载
	@JoinColumn(name ="dept_id", nullable = false) //列名dept_id   不能为空
	private DepartmentEntity departmentEntity;
	
	//职位编号 job_id
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY) //赖加载
	@JoinColumn(name = "job_id", nullable = false) //列名 job_id    不能为空
	private JobEntity jobEntity;
	
	//姓名
	@Column
	private String name;
	
	//身份证号码
	@Column
	private String cardid;
	
	//地址
	@Column
	private String address;
	
	//邮政编码
	@Column
	private String postcode;
	
	//电话
	@Column
	private String tel;
	
	//手机号码
	@Column
	private String phone;
	
	//QQ号码
	@Column
	private String qqnum;
	
	//电子邮件
	@Column
	private String email;
	
	//性别
	@Column
	private short sex;
	
	//政治面貌
	@Column
	private String party;
	
	//出生日期
	@Column
	private Date birthday;
	
	//民族
	@Column
	private String race;
	
	//学历
	@Column
	private String education;
	
	//专业
	@Column
	private String speciality;
	
	//相片路径
	@Column
	private String picture;
	
	//爱好
	@Column
	private String hobby;
	
	//备注
	@Column
	private String remark;
	
	//创建时间
	@Column
	private Date create_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DepartmentEntity getDepartmentEntity() {
		return departmentEntity;
	}

	public void setDepartmentEntity(DepartmentEntity departmentEntity) {
		this.departmentEntity = departmentEntity;
	}

	public JobEntity getJobEntity() {
		return jobEntity;
	}

	public void setJobEntity(JobEntity jobEntity) {
		this.jobEntity = jobEntity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQqnum() {
		return qqnum;
	}

	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public short getSex() {
		return sex;
	}

	public void setSex(short sex) {
		this.sex = sex;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}
