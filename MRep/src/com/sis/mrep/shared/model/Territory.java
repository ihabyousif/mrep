package com.sis.mrep.shared.model;

import java.io.Serializable;

public class Territory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Territory(){
		
	}
			
	public Territory(int id, String name, int zone) {
		super();
		this.id = id;
		this.name = name;
		this.zone = zone;
	}
	
	private int id;
	private String name;
	private int zone;
	
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
	public int getZone() {
		return zone;
	}
	public void setZone(int zone) {
		this.zone = zone;
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
		Territory other = (Territory) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
