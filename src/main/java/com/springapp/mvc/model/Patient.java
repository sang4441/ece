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
	private int personId;
	private int defaultDoc;
	private String healthCard;
	private int SIN;
	private String currentHealth;

	/** Relations */
	private Person person;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getDefaultDoc() {
		return defaultDoc;
	}

	public void setDefaultDoc(int defaultDoc) {
		this.defaultDoc = defaultDoc;
	}

	public String getHealthCard() {
		return healthCard;
	}

	public void setHealthCard(String healthCard) {
		this.healthCard = healthCard;
	}

	public int getSIN() {
		return SIN;
	}

	public void setSIN(int SIN) {
		this.SIN = SIN;
	}

	public String getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(String currentHealth) {
		this.currentHealth = currentHealth;
	}

}
