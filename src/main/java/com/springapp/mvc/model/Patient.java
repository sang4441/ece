/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springapp.mvc.model;

/**
 * 
 * @author Wojciech Golab
 */
public class Patient {
	private int id;
	private int person_id;
	private int default_doc;
	private String health_card;
	private int sin;
	private String current_health;

	public Patient() {
		this.id = -1;
		this.person_id = -1;
		this.default_doc = -1;
		this.health_card = "";
		this.sin = -1;
		this.current_health = "";
	}

	public Patient(int id, int person_id, int default_doc, String health_card,
			int sin, String current_health) {
		this.id = id;
		this.person_id = person_id;
		this.default_doc = default_doc;
		this.health_card = health_card;
		this.sin = sin;
		this.current_health = current_health;
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
	public int getPersonID() {
		return person_id;
	}

	public void setPersonID(int person_id) {

		this.person_id = person_id;

	}

	/**
	 * Set the value of deptID
	 * 
	 * @param deptID
	 *            new value of deptID
	 */
	public void setSIN(int sin) {
		this.sin = sin;
	}

	/**
	 * Get the value of deptID
	 * 
	 * @param deptID
	 *            new value of deptID
	 */
	public int getSIN() {
		return sin;
	}

	public int getDefaultDoc() {
		return default_doc;
	}

	public void setDefaultDoc(int default_doc) {

		this.default_doc = default_doc;
	}

	public String getCurrentHealth() {
		return current_health;
	}

	public void setCurrentHealth(String current_health) {

		this.current_health = current_health;

	}

	public int getHealthCard() {
		return sin;
	}

	public void setHealthCard(String health_card) {

		this.health_card = health_card;
		
	}

}
