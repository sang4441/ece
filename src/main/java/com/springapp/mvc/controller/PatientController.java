package com.springapp.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springapp.mvc.dao.AppointmentDAO;
import com.springapp.mvc.dao.DoctorDAO;
import com.springapp.mvc.dao.PatientDAO;
import com.springapp.mvc.dao.PersonDAO;
import com.springapp.mvc.model.Doctor;
import com.springapp.mvc.model.Patient;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.model.Visit;

@Controller
@RequestMapping(value = "/patient")
public class PatientController {

	@Autowired
	PersonDAO personDAO;
	@Autowired
	PatientDAO patientDAO;
	@Autowired
	AppointmentDAO appointmentDAO;
	@Autowired
	DoctorDAO doctorDAO;

	// Basic dashboard for the patient
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView getAllPatients(HttpServletRequest request) {
		// get all patients
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
        int session_role = ((Person) session.getAttribute("user")).getRoleID();
        int personId = user.getId();

        Patient patient = patientDAO.getPatientsByPersonId(personId);
        int patientId = patientDAO.getPatientsIdByPersonId(personId);
        patient.setId(patientId);
        if(session_role != 1){
                return new ModelAndView("/InvalidAccess");
        }

		List<Visit> appointments = appointmentDAO
				.getAppoinmentsByPatientId(patient.getId());

        for (Visit appointment : appointments) {
            Doctor doctorInfo = doctorDAO.getDoctorInfoById(appointment.getDoctorId());
            appointment.setDoctorName(doctorInfo.getNameFirst() + " " + doctorInfo.getNameLast());
        }

		ModelAndView model = new ModelAndView("patient/index");
		model.addObject("content", "dashboard");
		model.addObject("user", patient);
		model.addObject("appointments", appointments);
		model.addObject("personId", personId);
        model.addObject("role", session_role);
		return model;
	}

	// This function retrieves particular patient's info in a editable form
	@RequestMapping(value = "/edit_profile/{personId}", method = RequestMethod.GET)
	public ModelAndView editProfile(HttpServletRequest request,
			@PathVariable int personId) {

		HttpSession session = request.getSession();
		int session_role = ((Person) session.getAttribute("user")).getRoleID();
		int person_id = personId;

        //if user is a patient, check if personId is same
        if(session_role == 1){

            Person user = (Person) session.getAttribute("user");
            int user_id = user.getId();
            if(user_id!=personId)
                return new ModelAndView("/InvalidAccess");
        }
        if(session_role == 2 || session_role == 4)
            return new ModelAndView("/InvalidAccess");

		List<Doctor> doctors = doctorDAO.getAllDoctors();
		Patient patient = patientDAO.getPatientsByPersonId(personId);

		ModelAndView model = new ModelAndView("patient/index");
		model.addObject("content", "edit_profile");
		model.addObject("doctors", doctors);
		model.addObject("user", patient);
		model.addObject("role", session_role);
		model.addObject("personId", person_id);
		return model;
	}

	// This function only involves in changing patient info
	@RequestMapping(value = "/edit_profile_action", method = RequestMethod.POST)
	public String editProfileAction(HttpServletRequest request,
			@ModelAttribute("patient") Patient patient) {
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		if (user.getRoleID() == 1) {
			patientDAO.updatePatient(patient);
		} else if(user.getRoleID() == 2 || user.getRoleID() == 4) {
            return "redirect:/InvalidAccess";
        } else {
			patientDAO.updatePatientAsStaff(patient);
		}

		int personId = patient.getPersonId();
		return "redirect:/patient/profile/" + personId;
	}

	// This function retrieves particular patient's profile
	@RequestMapping(value = "/profile/{personId}", method = RequestMethod.GET)
	public ModelAndView patientProfile(HttpServletRequest request,
			@PathVariable int personId) {

		HttpSession session = request.getSession();
		int session_role = ((Person) session.getAttribute("user")).getRoleID();

        //if user is a patient, check if personId is same
        if(session_role == 1){
            Person user = (Person) session.getAttribute("user");
            int user_id = user.getId();
            if(user_id!=personId)
                return new ModelAndView("/InvalidAccess");
        }
        if(session_role==2 || session_role == 4)
            return new ModelAndView("/InvalidAccess");

		Patient patient = patientDAO.getPatientsByPersonId(personId);
        int patientId = patientDAO.getPatientsIdByPersonId(personId);
        patient.setId(patientId);
		String doctor = doctorDAO.getDefaultDoctorByPersonId(personId);

		List<Visit> visits = appointmentDAO.getRecordsByPatientId(patient
				.getId());

		ModelAndView model = new ModelAndView("patient/index");
		model.addObject("content", "profile");
		model.addObject("user", patient);
		model.addObject("role", session_role);
		model.addObject("doctor", doctor);
		model.addObject("visits", visits);
		return model;
	}
    @RequestMapping(value = "/visit_record/{personId}", method = RequestMethod.GET)
    public ModelAndView visitRecord(HttpServletRequest request,
                                    @PathVariable int personId) {

        HttpSession session = request.getSession();
        int session_role = ((Person) session.getAttribute("user")).getRoleID();

        //if user is a patient, check if personId is same
        if(session_role == 1){
            Person user = (Person) session.getAttribute("user");
            int user_id = user.getId();
            if(user_id!=personId)
                return new ModelAndView("/InvalidAccess");
        }
        if(session_role==2 || session_role== 4)
            return new ModelAndView("/InvalidAccess");

        Patient patient = patientDAO.getPatientsByPersonId(personId);
        int patientId = patientDAO.getPatientsIdByPersonId(personId);
        patient.setId(patientId);

        List<Visit> visits = appointmentDAO.getRecordsByPatientId(patient
                .getId());

        for (Visit visit : visits) {
            Doctor doctorInfo = doctorDAO.getDoctorInfoById(visit.getDoctorId());
            visit.setDoctorName(doctorInfo.getNameFirst() + " " + doctorInfo.getNameLast());
        }
        ModelAndView model = new ModelAndView("patient/index");
        model.addObject("content", "visit_record");
        model.addObject("user", patient);
        model.addObject("role", session_role);
        model.addObject("visits", visits);
        return model;
    }

}
