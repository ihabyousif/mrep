package com.sis.mrep.shared.model;

import java.io.Serializable;

public class PlanItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 482307388131366577L;
	
	public PlanItem(){
		
	}
	
	public PlanItem(int id, int planId, int calls, int cusId, String cusName, int cusTier , int cusSpeciality , int brick) {
		super();
		this.id = id;
		this.planId = planId;
		this.calls = calls;
		this.cusId = cusId;
		this.cusName = cusName;
		this.cusTier = cusTier;
		this.cusSpeciality = cusSpeciality;
		this.brick = brick;
	}
	
	private int id;
	private int planId;
	private int calls;
	private int cusId;
	private String cusName;
	private int cusTier;
	private int cusSpeciality;
	private int brick;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getCalls() {
		return calls;
	}
	public void setCalls(int calls) {
		this.calls = calls;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public int getCusTier() {
		return cusTier;
	}

	public void setCusTier(int cusTier) {
		this.cusTier = cusTier;
	}

	public int getCusSpeciality() {
		return cusSpeciality;
	}

	public void setCusSpeciality(int cusSpeciality) {
		this.cusSpeciality = cusSpeciality;
	}
	
	public int getBrick() {
		return brick;
	}

	public void setBrick(int brick) {
		this.brick = brick;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		PlanItem other = (PlanItem) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
