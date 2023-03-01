package com.portal.servicecenter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.domain.CommonDomain;

@Entity
@Table(name = "SAAS_SERVICECENTERS")
public class ServiceCenter extends CommonDomain {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "SERVICE_CENTER_CODE")
	private String code;
	
	@Column(name = "SERVICE_CENTER_NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "SERVICE_CENTER_TYPE")
	private Long serviceCenterType;
	
	@Column(name = "SITE_ID")
	private Long siteId;
	
	@Column(name = "STORE_ID")
	private Long storeId;
	
	@Column(name = "DEPARTMENT_ID")
	private Long departmentId;
	
	@Column(name = "GROUPS_ID")
	private Long groupId;
	
	@Column(name = "STORE_ATTACHED")
	private String storeAttached;
	
	@Column(name = "ACTIVE")
	private String active;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getServiceCenterType() {
		return serviceCenterType;
	}
	public void setServiceCenterType(Long serviceCenterType) {
		this.serviceCenterType = serviceCenterType;
	}
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getStoreAttached() {
		return storeAttached;
	}
	public void setStoreAttached(String storeAttached) {
		this.storeAttached = storeAttached;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
	
	
}

