package com.keymane.web.rest.models;

import java.util.Date;

public class Policy {

	private Long id;
	private String policyNumber;
	private Date startDate;
	private Date endDate;
	private Integer premiumAmount;
	private String policyValue;
	private String policyType;
	private String registationNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(Integer premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getPolicyValue() {
		return policyValue;
	}

	public void setPolicyValue(String policyValue) {
		this.policyValue = policyValue;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getRegistationNumber() {
		return registationNumber;
	}

	public void setRegistationNumber(String registationNumber) {
		this.registationNumber = registationNumber;
	}

}
