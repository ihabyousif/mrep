package com.sis.mrep.shared.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Call implements Serializable{

	/**
	 *call_id, call_date, call_details, pi_id, samples, promMaterials, nph_id, nph_feedback, company_feedback 
	 */
	private static final long serialVersionUID = -2149534785781543309L;
	public Call(){
		prodMap = new HashMap<Integer , String>();
	}
	public Call(int callId, int piId, Date dt, String comment , int samples , int promMaterials, int nphId, String nphFeedback , int companyFeedback) {
		this.callId = callId;
		this.piId = piId;
		this.dt = dt;
		this.comment = comment;
		this.samples = samples;
		this.promMaterials = promMaterials;
		this.nphId = nphId;
		this.nphFeedback = nphFeedback;
		this.companyFeedback = companyFeedback;

	}
	
	private int callId;
	private int piId;
	private Date dt;
	private String comment;
	
	private int samples;
	private int promMaterials;
	private int nphId;
	private String nphFeedback;
	private int companyFeedback;
	private Map<Integer , String> prodMap;
	
	
	public void setProduct(int id , String fb){
		prodMap.put(id, fb);
	}
	
	public int getCallId() {
		return callId;
	}
	
	public void setCallId(int callId) {
		this.callId = callId;
	}
	public int getPiId() {
		return piId;
	}
	public void setPiId(int piId) {
		this.piId = piId;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getSamples() {
		return samples;
	}
	public void setSamples(int samples) {
		this.samples = samples;
	}
	public int getPromMaterials() {
		return promMaterials;
	}
	public void setPromMaterials(int promMaterials) {
		this.promMaterials = promMaterials;
	}
	public int getNphId() {
		return nphId;
	}
	public void setNphId(int nphId) {
		this.nphId = nphId;
	}
	public String getNphFeedback() {
		return nphFeedback;
	}
	public void setNphFeedback(String nphFeedback) {
		this.nphFeedback = nphFeedback;
	}
	public int getCompanyFeedback() {
		return companyFeedback;
	}
	public void setCompanyFeedback(int companyFeedback) {
		this.companyFeedback = companyFeedback;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + callId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Call other = (Call) obj;
		if (callId != other.callId)
			return false;
		return true;
	}
	
}
