package com.sis.mrep.shared.model;

import java.io.Serializable;

public class Brick implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Brick(){
		
	}
			
	public Brick(int id, String name, int territory) {
		super();
		this.id = id;
		this.name = name;
		this.territory = territory;
	}
	
	private int id;
	private String name;
	private int territory;
	
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
	public int getTerritory() {
		return territory;
	}
	public void setTerritory(int territory) {
		this.territory = territory;
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
		Brick other = (Brick) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
