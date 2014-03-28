package com.springapp.mvc.model;

import java.util.Date;

public class VisitDate {
    public VisitDate(Date date, String patient){
        this.date = date;
        this.patient = patient;
    }

    private Date date;

    private String patient;

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
