package com.springapp.mvc.model;

import java.util.Date;

public class Visit {

	private int id;
	private int patientId;
	private String patientName;
	private long dayCode;
	private int dateCode;
	private int parentID;
	private Date date;
	private int length;
	private String prescription;
	private String diagnosis;
	private int doctorId;
	private Date date_modified;
	private String comment;
	private int initialID;

	public Visit() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getDateCode() {
		return dateCode;
	}

	public void setDateCode(int dateCode) {
		this.dateCode = dateCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public Date getDate_modified() {
		return date_modified;
	}

	public void setDate_modified(Date date_modified) {
		this.date_modified = date_modified;
	}

	public int getInitialID() {
		return initialID;
	}

	public void setInitialID(int initialID) {
		this.initialID = initialID;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getDayCode() {
		return dayCode;
	}

	public void setDayCode(long dayCode) {
		this.dayCode = dayCode;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
}