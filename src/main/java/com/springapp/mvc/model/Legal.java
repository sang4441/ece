package com.springapp.mvc.model;

public class Legal {

	private int id;
	private int person_id;


	public Legal() {

	}

	public Legal(int id, int person_id){

		this.id = id;
		this.person_id = person_id;

	}


	/**
	 * Get the value of Legal ID
	 * 
	 * @return the value of Legal ID
	 */
	public int getID() {
		return id;
	}

	public void setID(int id) {

		this.id = id;

	}

	/**
	 * Get the value of Person ID
	 * 
	 * @return the value of Person ID
	 */
	public int getPersonID() {
		return person_id;
	}

	public void setPersonID(int person_id) {

		this.person_id = person_id;
		
	}



}