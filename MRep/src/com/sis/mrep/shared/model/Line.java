package com.sis.mrep.shared.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class Line implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Line() {
		
	}
	public Line(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	private int id;
	@Size(min = 4)
	private String name;
	
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
		Line other = (Line) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
