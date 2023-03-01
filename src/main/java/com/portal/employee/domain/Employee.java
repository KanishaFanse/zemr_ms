package com.portal.employee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.domain.CommonDomain;

@Entity
@Table(name = "PER_ALL_PEOPLE_DETAILS")
public class Employee extends CommonDomain {
	

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "EFFECTIVE_START_DATE")
	private Date effectiveStartDate = new Date();
	
	@Column(name = "EFFECTIVE_END_DATE")
	private Date effectiveEndDate;
	
	@Column(name = "BUSINESS_GROUP_ID")
	private Long siteId;
	
	@Column(name = "PERSON_TYPE_ID")
	private Long personType;
	
	@Column(name = "TITLE")
	private Long prefix;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "MIDDLE_NAMES")
	private String middleName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "FULL_NAME")
	private String fullName;
	
	@Column(name = "HIRE_DATE")
	private Date employmentDate;
	
	@Column(name = "DATE_OF_BIRTH")
	private Date dob;
	
	@Column(name = "SEX")
	private Long gender;
	
	@Column(name = "EMAIL_ADDRESS")
	private String email;
	
	@Column(name = "MOBILENO")
	private String mobileNo;
	
	@Column(name = "EMPLOYEE_NUMBER")
	private String empNo;
	
	@Column(name = "QUALIFICATION")
	private String qualification;
	
	@Column(name = "EMP_DESC")
	private String empDesc;
	
	@Column(name = "LICENSE_NO")
	private String licenseNo;
	
	@Column(name = "DEPARTMENT_ID")
	private Long departmentId;
	
	@Column(name = "AWARD_ACHIEVEMENTS")
	private String achievements;
	
	@Column(name = "PRESENT_POSITION")
	private String presentPosition;
	
	@Column(name = "WHATSAPP_NUMBER")
	private String whatsappNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Long getPersonType() {
		return personType;
	}

	public void setPersonType(Long personType) {
		this.personType = personType;
	}

	public Long getPrefix() {
		return prefix;
	}

	public void setPrefix(Long prefix) {
		this.prefix = prefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getEmpDesc() {
		return empDesc;
	}

	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	public String getPresentPosition() {
		return presentPosition;
	}

	public void setPresentPosition(String presentPosition) {
		this.presentPosition = presentPosition;
	}

	public String getWhatsappNo() {
		return whatsappNo;
	}

	public void setWhatsappNo(String whatsappNo) {
		this.whatsappNo = whatsappNo;
	}
	
	
	
}
