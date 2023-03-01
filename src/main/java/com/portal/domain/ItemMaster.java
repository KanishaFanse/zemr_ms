package com.portal.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="OUTSOURCE_ITEM")
public class ItemMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ITEM_CODE")
	private String itemCode;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "GENERIC_NAME")
	private String genericName;
	
	@Column(name = "FORM")
	private String form;
	
	@Column(name = "STRENGTH")
	private String strength;
	
	@Column(name = "LOT_CONTROL")
	private String lotControl = "Y";
	
	@Column(name = "PRIMARY_UOM_CODE")
	private String primaryUomCode;
	
	@Column(name = "PRIMARY_UNIT_OF_MEASURE")
	private String primaryUomName;
	
	@Column(name = "SECONDARY_UOM_CODE")
	private String secondaryUomCode;
	
	@Column(name = "SECONDARY_UNIT_OF_MEASURE")
	private String secondaryUomName;
	
	@Column(name = "ROUTE_OF_ADMINISTRATION")
	private String routeOfAdmission;
	
	@Column(name = "ITEM_SUBCLASS_CODE")
	private String itemSubClassCode;
	
	@Column(name = "ITEM_MANUFACTURE_CODE")
	private String manufactureCode;
	
	@Column(name = "ITEM_MANUFACTURE_DESC")
	private String manufactureDesc;
	
	@Column(name = "CONSIGNED_FLAG")
	private String consolidatedFlag;
	
	@Column(name = "PURCHASE_CATEGORY_SEG1")
	private String purCatOne;
	
	@Column(name = "PURCHASE_CATEGORY_SEG2")
	private String purCatTwo;
	
	@Column(name = "CREATION_DATE")
	private Date createdDate;
	
	@Column(name = "POSTED")
	private String isPosted = "N";
	
	@Column(name = "C_ITEM_CODE")
	private String apiItemCode;
	
	@Column(name = "C_ITEM_NAME")
	private String apiItemName;
	
	@Column(name = "ITEM_GROUP")
	private String apiItemGroup = "Medical Items";
	
	@Column(name = "ITEM_TYPE")
	private String apiItemType = "PHARMACY";
	
	@Column(name = "C_ITEM_CATEGORY_HEAD_NAME")
	private String apiItemCatName;
	
	@Column(name = "C_MFAC_NAME")
	private String apiManufName;
	
	@Column(name = "N_QTY_PER_BOX")
	private Integer apiQtyPerBox;
	
	@Column(name = "C_GROUP_NAME")
	private String apiGroupName;
	
	@Column(name = "C_CONT_NAME")
	private String apiContName;
	
	@Column(name = "C_SCHEDUL_NAME")
	private String apiScheduleName;
	
	@Column(name = "C_PACK_TYPE_NAME")
	private String apiPackTypeName;
	
	@Column(name = "HSN_SAC_CODE")
	private String apiHsnCode;
	
	@Column(name = "GST_PER")
	private Integer apiGstValue;
	
	@Column(name = "C_CATEGORY_HEAD")
	private String apiCategHead;
	
	@Column(name = "C_PACK_NAME")
	private String apiPackName;
	
	@Column(name = "L1_CATEGORY")
	private String l1Category;
	
	@Column(name = "L2_CATEGORY")
	private String l2Category;
	
	@Column(name = "L3_CATEGORY")
	private String l3Category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getLotControl() {
		return lotControl;
	}

	public void setLotControl(String lotControl) {
		this.lotControl = lotControl;
	}

	public String getPrimaryUomCode() {
		return primaryUomCode;
	}

	public void setPrimaryUomCode(String primaryUomCode) {
		this.primaryUomCode = primaryUomCode;
	}

	public String getPrimaryUomName() {
		return primaryUomName;
	}

	public void setPrimaryUomName(String primaryUomName) {
		this.primaryUomName = primaryUomName;
	}

	public String getSecondaryUomCode() {
		return secondaryUomCode;
	}

	public void setSecondaryUomCode(String secondaryUomCode) {
		this.secondaryUomCode = secondaryUomCode;
	}

	public String getSecondaryUomName() {
		return secondaryUomName;
	}

	public void setSecondaryUomName(String secondaryUomName) {
		this.secondaryUomName = secondaryUomName;
	}

	public String getRouteOfAdmission() {
		return routeOfAdmission;
	}

	public void setRouteOfAdmission(String routeOfAdmission) {
		this.routeOfAdmission = routeOfAdmission;
	}

	public String getItemSubClassCode() {
		return itemSubClassCode;
	}

	public void setItemSubClassCode(String itemSubClassCode) {
		this.itemSubClassCode = itemSubClassCode;
	}

	public String getManufactureCode() {
		return manufactureCode;
	}

	public void setManufactureCode(String manufactureCode) {
		this.manufactureCode = manufactureCode;
	}

	public String getManufactureDesc() {
		return manufactureDesc;
	}

	public void setManufactureDesc(String manufactureDesc) {
		this.manufactureDesc = manufactureDesc;
	}

	public String getConsolidatedFlag() {
		return consolidatedFlag;
	}

	public void setConsolidatedFlag(String consolidatedFlag) {
		this.consolidatedFlag = consolidatedFlag;
	}

	public String getPurCatOne() {
		return purCatOne;
	}

	public void setPurCatOne(String purCatOne) {
		this.purCatOne = purCatOne;
	}

	public String getPurCatTwo() {
		return purCatTwo;
	}

	public void setPurCatTwo(String purCatTwo) {
		this.purCatTwo = purCatTwo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getIsPosted() {
		return isPosted;
	}

	public void setIsPosted(String isPosted) {
		this.isPosted = isPosted;
	}

	public String getApiItemCode() {
		return apiItemCode;
	}

	public void setApiItemCode(String apiItemCode) {
		this.apiItemCode = apiItemCode;
	}

	public String getApiItemName() {
		return apiItemName;
	}

	public void setApiItemName(String apiItemName) {
		this.apiItemName = apiItemName;
	}

	public String getApiItemGroup() {
		return apiItemGroup;
	}

	public void setApiItemGroup(String apiItemGroup) {
		this.apiItemGroup = apiItemGroup;
	}

	public String getApiItemType() {
		return apiItemType;
	}

	public void setApiItemType(String apiItemType) {
		this.apiItemType = apiItemType;
	}

	public String getApiItemCatName() {
		return apiItemCatName;
	}

	public void setApiItemCatName(String apiItemCatName) {
		this.apiItemCatName = apiItemCatName;
	}

	public String getApiManufName() {
		return apiManufName;
	}

	public void setApiManufName(String apiManufName) {
		this.apiManufName = apiManufName;
	}

	public Integer getApiQtyPerBox() {
		return apiQtyPerBox;
	}

	public void setApiQtyPerBox(Integer apiQtyPerBox) {
		this.apiQtyPerBox = apiQtyPerBox;
	}

	public String getApiGroupName() {
		return apiGroupName;
	}

	public void setApiGroupName(String apiGroupName) {
		this.apiGroupName = apiGroupName;
	}

	public String getApiContName() {
		return apiContName;
	}

	public void setApiContName(String apiContName) {
		this.apiContName = apiContName;
	}

	public String getApiScheduleName() {
		return apiScheduleName;
	}

	public void setApiScheduleName(String apiScheduleName) {
		this.apiScheduleName = apiScheduleName;
	}

	public String getApiPackTypeName() {
		return apiPackTypeName;
	}

	public void setApiPackTypeName(String apiPackTypeName) {
		this.apiPackTypeName = apiPackTypeName;
	}

	public String getApiHsnCode() {
		return apiHsnCode;
	}

	public void setApiHsnCode(String apiHsnCode) {
		this.apiHsnCode = apiHsnCode;
	}

	public Integer getApiGstValue() {
		return apiGstValue;
	}

	public void setApiGstValue(Integer apiGstValue) {
		this.apiGstValue = apiGstValue;
	}

	public String getApiCategHead() {
		return apiCategHead;
	}

	public void setApiCategHead(String apiCategHead) {
		this.apiCategHead = apiCategHead;
	}

	public String getApiPackName() {
		return apiPackName;
	}

	public void setApiPackName(String apiPackName) {
		this.apiPackName = apiPackName;
	}

	public String getL1Category() {
		return l1Category;
	}

	public void setL1Category(String l1Category) {
		this.l1Category = l1Category;
	}

	public String getL2Category() {
		return l2Category;
	}

	public void setL2Category(String l2Category) {
		this.l2Category = l2Category;
	}

	public String getL3Category() {
		return l3Category;
	}

	public void setL3Category(String l3Category) {
		this.l3Category = l3Category;
	}
	
	
	
	
}
