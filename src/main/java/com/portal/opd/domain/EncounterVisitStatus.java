package com.portal.opd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.domain.CommonDomain;

@Entity
@Table(name = "ENCOUNTER_VISIT_STATUS")
public class EncounterVisitStatus extends CommonDomain {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ENCOUNTER_ID")
	private Long encounterId;
	
	@Column(name = "VISIT_ID")
	private Long visitId;
	
	@Column(name = "CONSULTATION_STATUS")
	private String consultStatus;
	
	@Column(name = "MRNO")
	private String mrno;
	
	@Column(name = "BRANCH_CODE")
	private String branchCode;
	
	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;
	
	@Column(name = "VISIT_NO")
	private String visitNo;
	
	@Column(name = "QNO")
	private String qNo;
	
	@Column(name = "EMP_NO")
	private String empNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Long encounterId) {
		this.encounterId = encounterId;
	}

	public Long getVisitId() {
		return visitId;
	}

	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}

	public String getConsultStatus() {
		return consultStatus;
	}

	public void setConsultStatus(String consultStatus) {
		this.consultStatus = consultStatus;
	}

	public String getMrno() {
		return mrno;
	}

	public void setMrno(String mrno) {
		this.mrno = mrno;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}

	public String getqNo() {
		return qNo;
	}

	public void setqNo(String qNo) {
		this.qNo = qNo;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	
}
