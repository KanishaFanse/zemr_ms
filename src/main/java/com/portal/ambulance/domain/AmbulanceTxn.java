package com.portal.ambulance.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.domain.CommonDomain;

@Entity
@Table(name = "TXN_AMBUALNCE_DETS")
public class AmbulanceTxn extends CommonDomain {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "MRN")
	private String mrn;
	
	@Column(name = "PATIENT_NAME")
	private String ptnName;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "CONTACT_NO")
	private String contactNo;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "STATE_ID")
	private Long stateId;
	
	@Column(name = "DISTRICT_ID")
	private Long distId;
	
	@Column(name = "CITY_ID")
	private Long cityId;
	
	@Column(name = "IS_CANCEL")
	@Type(type = "yes_no")
	private Boolean complete = Boolean.FALSE;
	
	@Column(name = "CANCEL_REMARK")
	private String cancelRemark;
	
	@Column(name = "INFORM_TO")
	private String informTo;
	
	@Column(name = "EXPECTED_TIME")
	private Date expTime;
	
	@Column(name = "ATTENDENT")
	private String attendent;
	
	@Column(name = "SITE_ID")
	private Long siteId;
	
	@Column(name = "VISIT_TYPE")
	private String visittype;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "REMARKS1")
	private String remarks1;
	
	@Column(name = "P_CONDITION")
	private String PCondition;
	
	@Column(name = "CHEST_PAIN")
	private String chestPain;
	
	@Column(name = "STROKE")
	private String stroke;
	
	@Transient
	private String expectedTime;

	public String getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public String getPtnName() {
		return ptnName;
	}

	public void setPtnName(String ptnName) {
		this.ptnName = ptnName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getDistId() {
		return distId;
	}

	public void setDistId(Long distId) {
		this.distId = distId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}

	public String getInformTo() {
		return informTo;
	}

	public void setInformTo(String informTo) {
		this.informTo = informTo;
	}

	public Date getExpTime() {
		return expTime;
	}

	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}

	public String getAttendent() {
		return attendent;
	}

	public void setAttendent(String attendent) {
		this.attendent = attendent;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getVisittype() {
		return visittype;
	}

	public void setVisittype(String visittype) {
		this.visittype = visittype;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}

	public String getPCondition() {
		return PCondition;
	}

	public void setPCondition(String pCondition) {
		PCondition = pCondition;
	}

	public String getChestPain() {
		return chestPain;
	}

	public void setChestPain(String chestPain) {
		this.chestPain = chestPain;
	}

	public String getStroke() {
		return stroke;
	}

	public void setStroke(String stroke) {
		this.stroke = stroke;
	}
	
}
