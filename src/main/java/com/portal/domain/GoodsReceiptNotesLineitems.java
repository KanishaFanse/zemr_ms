package com.portal.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "OUTSOURCE_GRN_LINEITEMS")
public class GoodsReceiptNotesLineitems implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ITEM_ID")
	private String productId;
	
	@Column(name = "ITEM_NAME")
	private String productName;
	
	@Column(name = "HSN_CODE")
	private String hsnCode;
	
	@Column(name = "QTY_PER_BOX")
	private Float qtyPerBox;
	
	@Column(name = "BATCH_NO")
	private String batch;
	
	@Column(name = "QTY")
	private Float qty;
	
	@Column(name = "EXPIRY_DATE")
	private String expiryDate;
	
	@Column(name = "MRP")
	private Double mrp;
	
	@Column(name = "SALES_RATE")
	private Double saleRate;
	
	@Column(name = "ITEM_TOTAL")
	private Float itemTotal;
	
	@Column(name = "GST_CODE")
	private String gstCode;
	
	@Column(name = "CGST_PERCENTAGE")
	private Float cgstPer;
	
	@Column(name = "CGST_AMOUNT")
	private Double cgstAmt;
	
	@Column(name = "SGST_PERCENTAGE")
	private Float sgstPer;
	
	@Column(name = "SGST_AMOUNT")
	private Double sgstAmt;
	
	@Column(name = "IGST_PERCENTAGE")
	private Float igstPer;
	
	@Column(name = "IGST_AMOUNT")
	private Double igstAmt;
	
	@Column(name = "CESS_PERCENTAGE")
	private Float cessPer;
	
	@Column(name = "CESS_AMOUNT")
	private Double cessAmt;
	
	@Column(name = "API_SALE_RATE")
	private Double apiSaleRate;

	public Double getApiSaleRate() {
		return apiSaleRate;
	}

	public void setApiSaleRate(Double apiSaleRate) {
		this.apiSaleRate = apiSaleRate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public Float getQtyPerBox() {
		return qtyPerBox;
	}

	public void setQtyPerBox(Float qtyPerBox) {
		this.qtyPerBox = qtyPerBox;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public Float getQty() {
		return qty;
	}

	public void setQty(Float qty) {
		this.qty = qty;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public Double getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(Double saleRate) {
		this.saleRate = saleRate;
	}

	public Float getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(Float itemTotal) {
		this.itemTotal = itemTotal;
	}

	public String getGstCode() {
		return gstCode;
	}

	public void setGstCode(String gstCode) {
		this.gstCode = gstCode;
	}

	public Float getCgstPer() {
		return cgstPer;
	}

	public void setCgstPer(Float cgstPer) {
		this.cgstPer = cgstPer;
	}

	public Double getCgstAmt() {
		return cgstAmt;
	}

	public void setCgstAmt(Double cgstAmt) {
		this.cgstAmt = cgstAmt;
	}

	public Float getSgstPer() {
		return sgstPer;
	}

	public void setSgstPer(Float sgstPer) {
		this.sgstPer = sgstPer;
	}

	public Double getSgstAmt() {
		return sgstAmt;
	}

	public void setSgstAmt(Double sgstAmt) {
		this.sgstAmt = sgstAmt;
	}

	public Float getIgstPer() {
		return igstPer;
	}

	public void setIgstPer(Float igstPer) {
		this.igstPer = igstPer;
	}

	public Double getIgstAmt() {
		return igstAmt;
	}

	public void setIgstAmt(Double igstAmt) {
		this.igstAmt = igstAmt;
	}

	public Float getCessPer() {
		return cessPer;
	}

	public void setCessPer(Float cessPer) {
		this.cessPer = cessPer;
	}

	public Double getCessAmt() {
		return cessAmt;
	}

	public void setCessAmt(Double cessAmt) {
		this.cessAmt = cessAmt;
	}

}
