package com.springapp.mvc.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springapp.mvc.dao.DoctorDAO;
import com.springapp.mvc.model.Doctor;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	private static final Logger LOG = Logger.getLogger("doctor");

	@Autowired
	DoctorDAO doctorDAO;

	@RequestMapping(value = "/{docID}", method = RequestMethod.GET)
	public String getPatientsOfDoctor(@PathVariable int docID, ModelMap model) {
		// get all doctors
		LOG.info("getalldoctors");
		List<Doctor> doctors = doctorDAO.getAllDoctors();
		model.addAttribute("doctors", doctors);
		return "doctor/index";
	}

}