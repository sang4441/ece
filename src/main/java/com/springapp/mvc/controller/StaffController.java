package com.springapp.mvc.controller;

import com.springapp.mvc.dao.BasicDAO;
import com.springapp.mvc.dao.DoctorDAO;
import com.springapp.mvc.dao.PatientDAO;
import com.springapp.mvc.dao.PersonDAO;
import com.springapp.mvc.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.springapp.mvc.model.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/staff")
public class StaffController {

    @Autowired PersonDAO personDAO;
    @Autowired PatientDAO patientDAO;
    @Autowired BasicDAO basicDAO;
    @Autowired DoctorDAO doctorDAO;
    @Autowired BasicService basicService;

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

    @RequestMapping(value="/create_appointment_form_1", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView createAppointmentFirst(
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {

        ModelAndView model = new ModelAndView("staff/index");
        if (keyword != null) {
            List<Patient> patients = patientDAO.searchPatientByKeyword(keyword);
            model.addObject("patients", patients);
        }
        model.addObject("content", "create_appointment_form_1");

        return model;
    }

    @RequestMapping(value="/create_appointment_form_2/{personId}", method = RequestMethod.GET)
    public ModelAndView createAppointmentTwo(
            @PathVariable int personId) {
        Patient patient = patientDAO.getPatientsByPersonId(personId);
        Doctor doctor = doctorDAO.getDoctorById(patient.getDefaultDoc());
        List<Map<Integer, String>> schedule = basicService.findScheduleByDoctorId(patient.getDefaultDoc());
        ModelAndView model = new ModelAndView("staff/index");
        model.addObject("schedule", schedule);
        model.addObject("patient", patient);
        model.addObject("doctor", doctor);
        model.addObject("content", "create_appointment_form_2");
        return model;
    }

//    @RequestMapping(value = "/patient_search", method = RequestMethod.POST)
//    public ModelAndView patientSearch() {
//
//    }

    @RequestMapping(value = "appointment_form_submit", method = RequestMethod.POST)
    public String appointmentFormSubmit(HttpServletRequest request,
                                        @ModelAttribute("visit") Visit visit,
                                        BindingResult result) throws ParseException {
        //incomplete
        return "redirect:/staff/dashboard";
    }
}




