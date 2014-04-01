package com.springapp.mvc.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springapp.mvc.dao.AppointmentDAO;
import com.springapp.mvc.dao.DoctorDAO;
import com.springapp.mvc.dao.PatientDAO;
import com.springapp.mvc.dao.PermissionDAO;
import com.springapp.mvc.dao.PersonDAO;
import com.springapp.mvc.model.Doctor;
import com.springapp.mvc.model.Patient;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.model.Visit;
import com.springapp.mvc.service.AppointmentService;
import com.springapp.mvc.service.FormatService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	private static final Logger LOG = Logger.getLogger("doctor");

	@Autowired
	DoctorDAO doctorDAO;

	@Autowired
	PatientDAO patientDAO;

	@Autowired
	AppointmentDAO appointmentDAO;
	@Autowired
	PersonDAO personDAO;

	@Autowired
	AppointmentService appointmentService;

	@Autowired
	PermissionDAO permissionDAO;

	@RequestMapping(value = "/{docID}", method = RequestMethod.GET)
	public String getPatientsOfDoctor(@PathVariable int docID, ModelMap model) {
		// get all doctors
		return "redirect:/doctor/dashboard";
	}

	@RequestMapping(value = "/see_visits/{personID}", method = RequestMethod.GET)
	public ModelAndView getVisits(HttpServletRequest request,
			@PathVariable int personID) {

		Person user = (Person) request.getSession(false).getAttribute("user");
		ModelAndView model = new ModelAndView("doctor/index");

		List<Visit> visits = null;
		Person person = new Person();
		String patient_name = "";

		Doctor doc = doctorDAO.getDoctorByPersonID(user.getId());

		if (permissionDAO.hasPermissionForPatient(doc, personID)) {
			visits = appointmentDAO.getRecordsByPatientId(personID);
			person = (Person) personDAO.getPersonById(personID);
			patient_name = person.getNameFirst() + " " + person.getNameLast();
		}

		model.addObject("visits", visits);
		model.addObject("patientName", patient_name);
		model.addObject("content", "see_visits");

		return model;

	}

	@RequestMapping(value = "/grant_permission", method = RequestMethod.GET)
	public ModelAndView grantPermission(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");

		return new ModelAndView("doctor/index", "content", "grant_permission");
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Person user = (Person) session.getAttribute("user");
		Doctor doc = doctorDAO.getDoctorByPersonID(user.getId());
		List<Patient> patients = patientDAO.getAllPatientsInfoOfDoctor(doc
				.getId());

		ModelAndView model = new ModelAndView("doctor/index");
		model.addObject("content", "dashboard");
		model.addObject("user", user);
		model.addObject("patients", patients);
		return model;
	}

	@RequestMapping(value = "/patient/search", method = RequestMethod.POST)
	public ModelAndView searchPatients(
			HttpServletRequest request,
			@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam(value = "patientName", defaultValue = "") String patientName,
			@RequestParam(value = "patientID", defaultValue = "") int patientID) {
		HttpSession session = request.getSession(false);
		Person user = (Person) session.getAttribute("user");

		Doctor doc = doctorDAO.getDoctorByPersonID(user.getId());

		List<Patient> patients = patientDAO.searchPatientsOfDoctor(doc,
				patientID, patientName, patientName, date);

		ModelAndView model = new ModelAndView("doctor/index");
		model.addObject("content", "patient_search");
		model.addObject("user", user);
		model.addObject("patients", patients);
		model.addObject("date", FormatService.formatDate(date));
		model.addObject("patientName", patientName);
		model.addObject("patientID", patientID);
		return model;
	}

	@RequestMapping(value = "/appointment/search", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView searchAppointments(
			HttpServletRequest request,
			@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam(value = "patientName", defaultValue = "") String patientName,
			@RequestParam(value = "diagnosis", defaultValue = "") String diagnosis,
			@RequestParam(value = "comment", defaultValue = "") String comment,
			@RequestParam(value = "prescription", defaultValue = "") String prescription,
			@RequestParam(value = "surgery", defaultValue = "") String surgery) {

		HttpSession session = request.getSession(false);
		Person user = (Person) session.getAttribute("user");

		Doctor doc = doctorDAO.getDoctorByPersonID(user.getId());

		List<Visit> visits = appointmentDAO.searchAppointmentsOfDoctor(doc,
				date, patientName, diagnosis, comment, prescription, surgery);

		ModelAndView model = new ModelAndView("doctor/index");
		model.addObject("content", "appointment_search");
		model.addObject("user", user);
		model.addObject("visits", visits);
		// search parameters to be returned
		model.addObject("date",
				date == null ? date : FormatService.formatDate(date));
		model.addObject("patientName", patientName);
		model.addObject("diagnosis", diagnosis);
		model.addObject("comment", comment);
		model.addObject("prescription", prescription);
		model.addObject("surgery", surgery);
		return model;
	}

	@RequestMapping(value = "/appointment/{appointmentID}", method = RequestMethod.GET)
	public ModelAndView viewAppointment(HttpServletRequest request,
			@PathVariable int appointmentID) {
		HttpSession session = request.getSession(false);
		Person user = (Person) session.getAttribute("user");

		Doctor doc = doctorDAO.getDoctorByPersonID(user.getId());

		Visit visit = appointmentID != 0
				&& permissionDAO
						.hasPermissionForAppointment(doc, appointmentID) ? appointmentDAO
				.getAppointment(appointmentID) : new Visit();

		List<Visit> visits = appointmentDAO.getRelatedAppointments(visit
				.getInitialID());

		ModelAndView model = new ModelAndView("doctor/index");
		model.addObject("content", "appointment");
		model.addObject("user", user);
		model.addObject("visit", visit);
		model.addObject("visits", visits);
		return model;
	}

	@RequestMapping(value = "/patient/{patientID}", method = RequestMethod.GET)
	public ModelAndView viewPatient(HttpServletRequest request,
			@PathVariable int patientID) {
		HttpSession session = request.getSession(false);
		Person user = (Person) session.getAttribute("user");
		Doctor doc = doctorDAO.getDoctorByPersonID(user.getId());

		Patient patient = patientID != 0
				&& permissionDAO.hasPermissionForPatient(doc, patientID) ? patientDAO
				.getPatientsByPatientId(patientID) : new Patient();

		String patientName = patient.getNameFirst() + " "
				+ patient.getNameLast();

		List<Visit> visits = appointmentDAO
				.getAppoinmentsByPatientId(patientID);

		ModelAndView model = new ModelAndView("doctor/index");
		model.addObject("patientName", patientName);
		model.addObject("content", "patient");
		model.addObject("user", user);
		model.addObject("patient", patient);
		model.addObject("visits", visits);
		return model;
	}

	@RequestMapping(value = "/appointment/{appointmentID}/edit", method = RequestMethod.GET)
	public ModelAndView editAppointment(HttpServletRequest request,
			@PathVariable int appointmentID) {
		HttpSession session = request.getSession(false);
		Person user = (Person) session.getAttribute("user");
		Doctor doc = doctorDAO.getDoctorByPersonID(user.getId());

		Visit visit = permissionDAO.hasPermissionForAppointment(doc,
				appointmentID) ? appointmentDAO.getAppointment(appointmentID)
				: new Visit();
		List<Visit> visits = appointmentDAO.getRelatedAppointments(visit
				.getInitialID());

		ModelAndView model = new ModelAndView("doctor/index");
		model.addObject("content", "appointment_edit");
		model.addObject("user", user);
		model.addObject("visit", visit);
		model.addObject("visits", visits);
		return model;
	}

	@RequestMapping(value = "/appointment/{appointmentID}", method = RequestMethod.POST)
	public String updateAppointment(HttpServletRequest request,
			@ModelAttribute Visit visit) {
		Person user = (Person) request.getSession(false).getAttribute("user");
		Doctor doc = doctorDAO.getDoctorByPersonID(user.getId());
		return permissionDAO.hasPermissionForPatient(doc, visit.getPatientId()) ? "redirect:/doctor/appointment/"
				+ appointmentService.updateAppointment(visit).getId()
				: "redirect:/doctor/dashboard";
	}

	@RequestMapping(value = "/appointment", method = RequestMethod.GET)
	public ModelAndView allAppointmentsOfDoctor(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Person user = (Person) session.getAttribute("user");

		Doctor doc = doctorDAO.getDoctorByPersonID(user.getId());
		List<Visit> visits = appointmentDAO
				.getAppointmentsOfDoctor(doc.getId());

		ModelAndView model = new ModelAndView("doctor/index");
		model.addObject("content", "allAppointments");
		model.addObject("user", user);
		model.addObject("visits", visits);
		return model;
	}

	@RequestMapping(value = "/appointment", method = RequestMethod.POST)
	public String newAppointment(HttpServletRequest request,
			@ModelAttribute Visit visit) {
		HttpSession session = request.getSession(false);
		Person user = (Person) session.getAttribute("user");
		if (user.getRoleID() != 2) {
			return "redirect:/";
		}

		return "redirect:/appointment/"
				+ appointmentService.insertAppointment(visit).getId();
	}

	@RequestMapping(value = "/search_patient", method = RequestMethod.GET)
	public ModelAndView searchPatientFirst(
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		return new ModelAndView("doctor/index", "content", "search_patient");
	}

	@RequestMapping(value = "/grant_permission", method = RequestMethod.POST)
	public ModelAndView insertPatientDoctor(
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");

		// list of patient whose defaultdoc it this user
		List<Patient> patients = patientDAO.getAllPatientsOfDefaultDoctor(user
				.getId());

		return new ModelAndView("doctor/index", "content", "search_patient");
	}

	@RequestMapping(value = "/search_patient", method = RequestMethod.POST)
	public ModelAndView getPatients(
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			HttpServletRequest request,
			@RequestParam(value = "searchCriteria", defaultValue = "") String searchCriteria) {
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");

		// if(user.getRoleID() != 3)
		// return new ModelAndView("/InvalidAccess" );

		ModelAndView model = new ModelAndView("doctor/index");

		if (searchCriteria.equals("patientName")) {

			List<Patient> patients = patientDAO.searchPatientByKeyword(keyword);
			model.addObject("patients", patients);
		} else if (searchCriteria.equals("patientId")) {

			List<Patient> patients = patientDAO.searchPatientById(keyword);
			model.addObject("patients", patients);
		} else if (searchCriteria.equals("LastVisit")) {

			// TODO: LAST VISIT...

			List<Patient> patients = patientDAO.searchPatientByDate(keyword);
			model.addObject("patients", patients);
		}

		else {
			// NULL SearchCriteria should be BANNED
		}

		model.addObject("content", "search_patient");

		return model;
	}

}