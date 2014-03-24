package com.springapp.mvc.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springapp.mvc.dao.PatientDAO;
import com.springapp.mvc.model.Patient;

@Controller
@RequestMapping("/patient")
public class PatientController {
	private static final Logger LOG = Logger.getLogger("patient");

	@Autowired
	PatientDAO patientDAO;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllPatients(ModelMap model) {
		// get all patients
		LOG.info("getallpatients");
		List<Patient> patients = patientDAO.getAllPatients();
		model.addAttribute("patients", patients);
		return "patient/index";
	}

	@RequestMapping(value = "/doctor/{doctorId}/patients", method = RequestMethod.GET)
	public String getAllPatientsOfDoctor(@PathVariable int doctorId,
			ModelMap model) {
		// get all patients of doctor
		LOG.info("getAllPatientsOfDoctor");
		List<Patient> patients = patientDAO.getAllPatientsOfDoctor(doctorId);

		model.addAttribute("patients", patients);
		return "patient/index";
	}
}