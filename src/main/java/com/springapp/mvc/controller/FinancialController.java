package com.springapp.mvc.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.springapp.mvc.dao.AppointmentDAO;
import com.springapp.mvc.dao.DoctorDAO;
import com.springapp.mvc.dao.PatientDAO;
import com.springapp.mvc.dao.PersonDAO;
import com.springapp.mvc.model.Doctor;
import com.springapp.mvc.model.Patient;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.model.Visit;
import com.springapp.mvc.service.AppointmentService;
import com.springapp.mvc.service.BasicService;
import com.springapp.mvc.service.FormatService;

@Controller
@RequestMapping(value = "/financial")
public class FinancialController {

    @Autowired
    PersonDAO personDAO;
    @Autowired
    PatientDAO patientDAO;
    @Autowired
    AppointmentDAO appointmentDAO;
    @Autowired
    DoctorDAO doctorDAO;
    @Autowired
    BasicService basicService;
    @Autowired
    AppointmentService appointmentService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView getAllPatients(HttpServletRequest request,
                                       @RequestParam(value = "doctorName", defaultValue = "") String doctorName) {
        // get all patients
        HttpSession session = request.getSession();
        Person user = (Person) session.getAttribute("user");
        if (user.getRoleID() != 4) {
            return new ModelAndView("/InvalidAccess");
        }

        List<Doctor> doctors = doctorDAO.getAllDoctors();
        ModelAndView model = new ModelAndView("financial/index");
        model.addObject("doctors", doctors);
        model.addObject("user", user);
        model.addObject("content", "dashboard");
        return model;
    }

    @RequestMapping(value = "/review_records/{doctorId}", method = RequestMethod.GET)
    public ModelAndView retrieveRecords(HttpServletRequest request,
                                        @PathVariable int doctorId) {

        HttpSession session = request.getSession();
        Person user = (Person) session.getAttribute("user");
        if (user.getRoleID() != 4)
            return new ModelAndView("/InvalidAccess");

        int session_role = ((Person) session.getAttribute("user")).getRoleID();
        List<Visit> records = appointmentDAO.getRecordsByDoctorId(doctorId);
        String numberOfPatient = appointmentDAO.getNumOfPatientByDoctorId(doctorId);
        Doctor doctor = doctorDAO.getDoctorById(doctorId);

        ModelAndView model = new ModelAndView("financial/index");
        model.addObject("content", "review_records");
        model.addObject("records", records);
        model.addObject("doctor", doctor);
        model.addObject("numPatient", numberOfPatient);
        model.addObject("role", session_role);
        return model;
    }

}
