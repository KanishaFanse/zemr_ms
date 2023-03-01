package com.portal.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OrderBy;

@MappedSuperclass
public class CommonDomain {

	private static final long serialVersionUID = 1L;

	@Column(name="CREATED_BY", nullable=false)
	private Long createdBy;

	@Column(name="CREATEDAT", nullable=false)
	@OrderBy(value="desc")
	private Date createdDatetime;
	
	@Column(name="UPDATED_BY")
	private Long updatedBy;

	@Column(name="UPDATEDAT")
	private Date updatedDatetime;
	
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(Date updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}
	
	public CommonDomain() {
		
	}
	
}
