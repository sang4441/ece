package com.springapp.mvc.model;

import java.util.Date;

public class Visit {

	private int id;
	private int patient_id;
	private Date date;
	private int length;
	private String prescription;
	private String diagnosis;
	private int doctor_id;
	private Date date_modified;
	private String comment;
	private int initial_id;

	public Visit() {

	}

	public Visit(int id, int patient_id, Date date, int length,
			String prescription, String diagnosis, int doctor_id,
			Date date_modified, String comment, int initial_id) {
		this.id = id;
		this.patient_id = patient_id;
		this.date = date;
		this.length = length;
		this.prescription = prescription;
		this.diagnosis = diagnosis;
		this.doctor_id = doctor_id;
		this.date_modified = date_modified;
		this.comment = comment;
		this.initial_id = initial_id;
	}

	/**
	 * Getters and Setters
	 * 
	 */

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the patient_id
	 */
	public int getPatient_id() {
		return patient_id;
	}

	/**
	 * @param patient_id
	 *            the patient_id to set
	 */
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the prescription
	 */
	public String getPrescription() {
		return prescription;
	}

	/**
	 * @param prescription
	 *            the prescription to set
	 */
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	/**
	 * @return the diagnosis
	 */
	public String getDiagnosis() {
		return diagnosis;
	}

	/**
	 * @param diagnosis
	 *            the diagnosis to set
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
	 * @return the doctor_id
	 */
	public int getDoctor_id() {
		return doctor_id;
	}

	/**
	 * @param doctor_id
	 *            the doctor_id to set
	 */
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	/**
	 * @return the date_modified
	 */
	public Date getDate_modified() {
		return date_modified;
	}

	/**
	 * @param date_modified
	 *            the date_modified to set
	 */
	public void setDate_modified(Date date_modified) {
		this.date_modified = date_modified;
	}

	/**
	 * @return the initial_id
	 */
	public int getInitial_id() {
		return initial_id;
	}

	/**
	 * @param initial_id
	 *            the initial_id to set
	 */
	public void setInitial_id(int initial_id) {
		this.initial_id = initial_id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}