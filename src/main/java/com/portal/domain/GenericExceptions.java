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
@Table(name="OUTSOURCE_EXCEPTIONS")
public class GenericExceptions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "SOURCE")
	private String source;
	
	@Column(name = "TRIGERRED_URL")
	private String trigerredUrl;
	
	@Column(name = "TRIGERRED_DATE")
	private Date trigerredDate;
	
	@Column(name = "EXCEPTION_MESSAGE")
	private String message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTrigerredUrl() {
		return trigerredUrl;
	}

	public void setTrigerredUrl(String trigerredUrl) {
		this.trigerredUrl = trigerredUrl;
	}

	public Date getTrigerredDate() {
		return trigerredDate;
	}

	public void setTrigerredDate(Date trigerredDate) {
		this.trigerredDate = trigerredDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
