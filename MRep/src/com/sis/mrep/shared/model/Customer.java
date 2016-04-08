package com.sis.mrep.shared.model;

import java.io.Serializable;

public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6626346002762705779L;

	public Customer(){
		
	}
	
	public Customer(int id, String name, String phone, String address,
			int tier, int speciality , int brick , int type) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.tier = tier;
		this.speciality = speciality;
		this.brick = brick;
		this.type = type;
		
	}

	private int id;
	private String name;
	private String phone;
	private String address;
	private int tier;
	private int speciality;
	private int brick;
	private int type;
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTier() {
		return tier;
	}
	public void setTier(int tier) {
		this.tier = tier;
	}
	public int getSpeciality() {
		return speciality;
	}
	public void setSpeciality(int speciality) {
		this.speciality = speciality;
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
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
