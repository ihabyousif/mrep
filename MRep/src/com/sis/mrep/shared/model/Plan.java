package com.sis.mrep.shared.model;

import java.io.Serializable;
import java.util.Date;

public class Plan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7698711903001601135L;
	public Plan(){
		
	}
	public Plan(int id, String name, Date planDate, int brickId , int repId, int adminId) {
		super();
		this.id = id;
		this.name = name;
		this.planDate = planDate;
		this.brickId = brickId;
		this.repId = repId;
		this.adminId = adminId;
	}
	private int id;
	private String name;
	private Date planDate;
	private int brickId;
	private int repId;
	private int adminId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public int getBrickId() {
		return brickId;
	}
	public void setBrickId(int brickId) {
		this.brickId = brickId;
	}
	public int getRepId() {
		return repId;
	}
	public void setRepId(int repId) {
		this.repId = repId;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
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
		Plan other = (Plan) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
