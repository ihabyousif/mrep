package com.sis.mrep.shared.model;

import java.io.Serializable;

public class CoverageReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7804665703186254367L;
	
	private String repName;
	private String customerName;
	private String planName;
	public String getRepName() {
		return repName;
	}
	public void setRepName(String repName) {
		this.repName = repName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public int getCalls() {
		return calls;
	}
	public void setCalls(int calls) {
		this.calls = calls;
	}
	public int getCallsDone() {
		return callsDone;
	}
	public void setCallsDone(int callsDone) {
		this.callsDone = callsDone;
	}
	private int calls;
	private int callsDone;
	
}
