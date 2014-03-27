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
@RequestMapping(value="/staff")
public class StaffController {

    @Autowired PersonDAO personDAO;
    @Autowired PatientDAO patientDAO;
    @Autowired BasicDAO basicDAO;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView getAllPatients(HttpServletRequest request) {
        // get all patients
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        ModelAndView model = new ModelAndView("staff/index");
        model.addObject("content", "dashboard");
        model.addObject("user", user);
        return model;
    }

    @RequestMapping(value="/create_patient_form", method = RequestMethod.GET)
    public ModelAndView createPatient() {
        return new ModelAndView("staff/index", "content", "create_patient_form");
    }
    @RequestMapping(value = "patient_form_submit", method = RequestMethod.POST)
    public String patientFormSubmit(HttpServletRequest request,
                                    @ModelAttribute("patient") Patient patient,
                                    BindingResult result) throws ParseException {
        //incomplete
        return "redirect:/staff/dashboard";
    }

    @RequestMapping(value="/create_appointment_form", method = RequestMethod.GET)
    public ModelAndView createAppointment() {
        return new ModelAndView("staff/index", "content", "create_appointment_form");
    }

    @RequestMapping(value = "appointment_form_submit", method = RequestMethod.POST)
    public String appointmentFormSubmit(HttpServletRequest request,
                                        @ModelAttribute("visit") Visit visit,
                                        BindingResult result) throws ParseException {
        //incomplete
        return "redirect:/staff/dashboard";
    }
}




