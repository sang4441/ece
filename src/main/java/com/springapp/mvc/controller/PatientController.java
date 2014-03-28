package com.springapp.mvc.controller;

import com.springapp.mvc.dao.BasicDAO;
import com.springapp.mvc.dao.PatientDAO;
import com.springapp.mvc.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.springapp.mvc.model.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping(value="/patient")
public class PatientController {

    @Autowired PersonDAO personDAO;
    @Autowired PatientDAO patientDAO;
    @Autowired BasicDAO basicDAO;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView getAllPatients(HttpServletRequest request) {
		// get all patients
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
		List<Visit> appointments = basicDAO.getAppoinmentsByPatientId(user.getId());

        ModelAndView model = new ModelAndView("patient/index");
        model.addObject("content", "dashboard");
        model.addObject("user", user);
		model.addObject("appointments", appointments);
		return model;
	}

    @RequestMapping(value = "/edit_profile", method = RequestMethod.GET)
    public ModelAndView editProfile(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        Patient patient = patientDAO.getPatientsByPersonId(user.getId());
        ModelAndView model = new ModelAndView("patient/index");
        model.addObject("content", "edit_profile");
        model.addObject("user", patient);
        return model;
    }

    @RequestMapping(value = "/edit_profile_action", method = RequestMethod.POST)
    public String editProfileAction(HttpServletRequest request, @ModelAttribute("patient") Patient patient) {
    	HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        patient.setPersonId(user.getId());
        patientDAO.updatePatient(patient);
        return "redirect:/patient/profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView patientProfile(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        Patient patient = patientDAO.getPatientsByPersonId(user.getId());
        ModelAndView model = new ModelAndView("patient/index");
        model.addObject("content", "profile");
        model.addObject("user", patient);
        return model;
    }

}
