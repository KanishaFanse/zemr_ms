package com.portal.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="OUTSOURCE_GRN")
public class GoodsReceiptNotes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TRANSACTION_NO")
	private String tranNo;
	
	@Column(name = "BILL_NO")
	private String billNo;
	
	@Column(name = "CR_DATE")
	private String date;
	
	@Column(name = "REFERENCE_NO")
	private String refNo;
	
	@Column(name = "REFERENCE_DATE")
	private String refDate;
	
	@Column(name = "GRN_TOTAL")
	private Double docTotal;
	
	@Column(name = "FROM_GST_NO")
	private String fromGstNo;
	
	@Column(name = "TO_GST_NO")
	private String toGstNo;
	
	@Column(name = "POST")
	private String post;
	
	@Column(name = "CGST_AMOUNT")
	private Double cgstAmt;
	
	@Column(name = "SGST_AMOUNT")
	private Double sgstAmt;
	
	@Column(name = "IGST_AMOUNT")
	private Double igstAmt;
	
	@Column(name = "CESS_AMOUNT")
	private Double cessAmt;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "OUTSOURCE_GRN")
	private List<GoodsReceiptNotesLineitems> lineItems = new ArrayList<GoodsReceiptNotesLineitems>();
	
	@Column(name = "IS_POSTED_TO_HINAI")
	private Boolean isPostedToHinai = Boolean.FALSE;
	
	@Column(name = "STORE_CODE")
	private String storeCode;
	
	@Column(name = "CRAETED_DATE")
	private Date createdDate;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public Boolean getIsPostedToHinai() {
		return isPostedToHinai;
	}

	public void setIsPostedToHinai(Boolean isPostedToHinai) {
		this.isPostedToHinai = isPostedToHinai;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTranNo() {
		return tranNo;
	}

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getRefDate() {
		return refDate;
	}

	public void setRefDate(String refDate) {
		this.refDate = refDate;
	}

	public Double getDocTotal() {
		return docTotal;
	}

	public void setDocTotal(Double docTotal) {
		this.docTotal = docTotal;
	}

	public String getFromGstNo() {
		return fromGstNo;
	}

	public void setFromGstNo(String fromGstNo) {
		this.fromGstNo = fromGstNo;
	}

	public String getToGstNo() {
		return toGstNo;
	}

	public void setToGstNo(String toGstNo) {
		this.toGstNo = toGstNo;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Double getCgstAmt() {
		return cgstAmt;
	}

	public void setCgstAmt(Double cgstAmt) {
		this.cgstAmt = cgstAmt;
	}

	public Double getSgstAmt() {
		return sgstAmt;
	}

	public void setSgstAmt(Double sgstAmt) {
		this.sgstAmt = sgstAmt;
	}

	public Double getIgstAmt() {
		return igstAmt;
	}

	public void setIgstAmt(Double igstAmt) {
		this.igstAmt = igstAmt;
	}

	public Double getCessAmt() {
		return cessAmt;
	}

	public void setCessAmt(Double cessAmt) {
		this.cessAmt = cessAmt;
	}

	public List<GoodsReceiptNotesLineitems> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<GoodsReceiptNotesLineitems> lineItems) {
		this.lineItems = lineItems;
	}
	
	

}
