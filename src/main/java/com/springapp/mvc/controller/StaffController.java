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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
        if(user.getRoleID() != 2){
            return new ModelAndView("staff/index","content", "../InvalidAccess" );
        }
        ModelAndView model = new ModelAndView("staff/index");
        model.addObject("user", user);
        model.addObject("content", "dashboard");
        return model;
    }

    @RequestMapping(value="/create_patient_form", method = RequestMethod.GET)
    public ModelAndView createPatient() {
        return new ModelAndView("staff/index", "content", "create_patient_form");
    }
    
    @RequestMapping(value="/update_patient", method = RequestMethod.GET)
    public ModelAndView updatePatient() {
        return new ModelAndView("staff/index", "content", "update_patient");
    }
    
    @RequestMapping(value = "patient_form_submit", method = RequestMethod.POST)
    public String patientFormSubmit(HttpServletRequest request,
                                    @ModelAttribute("patient") Patient patient,
                                    BindingResult result) throws ParseException {
        //incomplete
        return "redirect:/staff/dashboard";
    }

    // @RequestMapping(value = "", method = RequestMethod.POST)
    // public String patientFormSubmit(HttpServletRequest request,
    //                                 @ModelAttribute("patient") Patient patient,
    //                                 BindingResult result) throws ParseException {
    //     //incomplete
    //     return "redirect:/staff/dashboard";
    // }

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


    @RequestMapping(value="/searchPatient", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView searchPatientFirst(
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {

        ModelAndView model = new ModelAndView("staff/index");
        if (keyword != null) {
            List<Patient> patients = patientDAO.searchPatientByKeyword(keyword);
            model.addObject("patients", patients);
        }
        model.addObject("content", "update_patient");

        return model;
    }

    @RequestMapping(value="/create_appointment_form_2/{personId}", method = RequestMethod.GET)
    public ModelAndView createAppointmentTwo(
            @PathVariable int personId) {
        Patient patient = patientDAO.getPatientsByPersonId(personId);
        Doctor doctor = doctorDAO.getDoctorById(patient.getDefaultDoc());
        List<Map<Integer, Object>> schedule = basicService.findScheduleByDoctorId(patient.getDefaultDoc());
        ModelAndView model = new ModelAndView("staff/index");
        model.addObject("schedule", schedule);
        model.addObject("patient", patient);
        model.addObject("doctor", doctor);
        model.addObject("content", "create_appointment_form_2");
        return model;
    }

    @RequestMapping(value = "appointment_form_submit", method = RequestMethod.POST)
    public String appointmentFormSubmit(HttpServletRequest request,
                                        @RequestParam(value = "time", defaultValue = "") String time,
                                        @ModelAttribute("visit") Visit visit,
                                        BindingResult result) throws ParseException {

        String str = time;
        List<String> timeList = Arrays.asList(str.split(","));
        String timeValue;
        if (timeList.get(1).equals(1)) {timeValue="09:00:00";}
        else if (timeList.get(1).equals(2)) {timeValue="09:30:00";}
        else if (timeList.get(1).equals(3)) {timeValue="09:30:00";}
        else if (timeList.get(1).equals(4)) {timeValue="10:00:00";}
        else if (timeList.get(1).equals(5)) {timeValue="10:30:00";}
        else if (timeList.get(1).equals(6)) {timeValue="11:00:00";}
        else if (timeList.get(1).equals(7)) {timeValue="11:30:00";}
        else if (timeList.get(1).equals(8)) {timeValue="12:00:00";}
        else if (timeList.get(1).equals(9)) {timeValue="12:30:00";}
        else if (timeList.get(1).equals(10)) {timeValue="14:30:00";}
        else if (timeList.get(1).equals(11)) {timeValue="15:00:00";}
        else if (timeList.get(1).equals(12)) {timeValue="15:30:00";}
        else if (timeList.get(1).equals(13)) {timeValue="16:00:00";}
        else {timeValue="16:30:00";}

//        visit.setDate(timeList.get(0));

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        String dateInString = timeList.get(0) + " " + timeValue;
        Date date = sdf.parse(dateInString);
        visit.setDate(date);
            return "redirect:/staff/dashboard";
        }
}




