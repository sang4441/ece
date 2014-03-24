package com.springapp.mvc.controller;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Controller
public class LoginController {

    @Autowired PersonDAO personDAO;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "index";
	}

    @RequestMapping(value="/login_form", method = RequestMethod.GET)
    public String loginForm() {
        return "login_form";
    }

    @RequestMapping(value="/login_action", method = RequestMethod.GET)
    public String login(
            HttpServletRequest request,
            ModelMap model,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password) {
        model.addAttribute("message", "Hello world!");

        String url;
            // find user/pass combo
            Person user = personDAO.getPersonWithUsernameAndPassword(username, password);

            // set user in session data
            if (user == null) {
                // failed login
                url = "login";

            } else {
                // success
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.getSession().setAttribute("user", user);
                // get homepage
                url = "index";
            }
        return url;
    }

    @RequestMapping(value="/Dashboard", method = RequestMethod.GET)
    public String dashboard() {
        return "index";
    }

    @RequestMapping(value="/create_patient_form", method = RequestMethod.GET)
    public String createPatient() {
        return "create_patient_form";
    }
    @RequestMapping(value = "patient_form_submit", method = RequestMethod.POST)
    public String patientFormSubmit(HttpServletRequest request,
                              @ModelAttribute("patient") Patient patient,
                              BindingResult result) throws ParseException {



        return "redirect:/Dashboard";
    }

    @RequestMapping(value="/create_appointment_form", method = RequestMethod.GET)
    public String createAppointment() {
        return "create_appointment_form";
    }

    @RequestMapping(value = "appointment_form_submit", method = RequestMethod.POST)
    public String appointmentFormSubmit(HttpServletRequest request,
                              @ModelAttribute("visit") Visit visit,
                              BindingResult result) throws ParseException {


        return "redirect:/Dashboard";
    }
}