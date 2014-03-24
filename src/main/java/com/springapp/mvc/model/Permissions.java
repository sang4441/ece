package com.springapp.mvc.model;

public class Permissions {

	private int id;
	private String description;


	public Permissions() {

	}

	public Permissions(int id, String description){

		this.id = id;
		this.description = description;

	}


	/**
	 * Get the value of salary
	 * 
	 * @return the value of salary
	 */
	public int getID() {
		return id;
	}

	public void setID(int id) {

		this.id = id;

	}

	/**
	 * Get the value of deptID
	 * 
	 * @return the value of deptID
	 */
	public String getDescription() {
		return description;
	}

	public void setDesceiption(String description) {

		this.description = description;

	}



}