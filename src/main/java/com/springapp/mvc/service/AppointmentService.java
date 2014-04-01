package com.springapp.mvc.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springapp.mvc.dao.AppointmentDAO;
import com.springapp.mvc.model.Visit;

@Service
public class AppointmentService {
	@Autowired
	AppointmentDAO appointmentDAO;

	public Visit updateAppointment(Visit visit) {
		if (visit.getDateModified() == null) {
			visit.setDateModified(Calendar.getInstance().getTime());
			appointmentDAO.updateAppointment(visit);
		} else {
			visit.setDateModified(Calendar.getInstance().getTime());
			visit.setId(0);
			visit = insertAppointment(visit);
		}

		return visit;
	}

	public Visit insertAppointment(Visit visit) {
		if (visit.getInitialID() == 0) {
			visit.setInitialID(appointmentDAO.insertAppointment(visit));
			visit = appointmentDAO.setInitialID(visit);
		} else {
			visit.setId(appointmentDAO.insertAppointment(visit));
		}
		return visit;
	}

}
