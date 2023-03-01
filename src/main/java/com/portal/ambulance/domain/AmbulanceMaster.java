package com.portal.ambulance.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.domain.CommonDomain;

@Entity
@Table(name = "SAAS_AMBULANCE_MASTER")
public class AmbulanceMaster extends CommonDomain {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "REQUEST_ID")
	private Long requestId;
	
	@Column(name = "DEPT_TIME")
	private Date deptTime;
	
	@Column(name = "LOCATION_TIME")
	private Date locationTime;
	
	@Column(name = "ARRIVAL_TIME")
	private Date arrivalTime;
	
	@Column(name = "AMBULANCE_TYPE")
	private String ambulanceType;
	
	@Column(name = "AMBULANCE_NO")
	private String ambulanceNo;
	
	@Column(name = "DRIVER_NAME")
	private String driverName;
	
	@Column(name = "DRIVER_CONTACT_NO")
	private String driverContactNo;
	
	@Column(name = "DOCTOR_ID")
	private Long doctorId;
	
	@Column(name = "NURSE_ID")
	private Long nurseId;
	
	@Column(name = "KM")
	private String km;
	
	@Column(name = "CHARGES")
	private String charges;
	
	@Column(name = "COMPLETE")
	@Type(type = "yes_no")
	private Boolean complete = Boolean.FALSE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Date getDeptTime() {
		return deptTime;
	}

	public void setDeptTime(Date deptTime) {
		this.deptTime = deptTime;
	}

	public Date getLocationTime() {
		return locationTime;
	}

	public void setLocationTime(Date locationTime) {
		this.locationTime = locationTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getAmbulanceType() {
		return ambulanceType;
	}

	public void setAmbulanceType(String ambulanceType) {
		this.ambulanceType = ambulanceType;
	}

	public String getAmbulanceNo() {
		return ambulanceNo;
	}

	public void setAmbulanceNo(String ambulanceNo) {
		this.ambulanceNo = ambulanceNo;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverContactNo() {
		return driverContactNo;
	}

	public void setDriverContactNo(String driverContactNo) {
		this.driverContactNo = driverContactNo;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getNurseId() {
		return nurseId;
	}

	public void setNurseId(Long nurseId) {
		this.nurseId = nurseId;
	}

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}

	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	
	
	
}
