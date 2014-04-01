package com.springapp.mvc.controller;

import com.mysql.jdbc.StringUtils;
import com.springapp.mvc.dao.AppointmentDAO;
import com.springapp.mvc.dao.DoctorDAO;
import com.springapp.mvc.dao.PatientDAO;
import com.springapp.mvc.dao.PersonDAO;
import com.springapp.mvc.service.BasicService;
import com.springapp.mvc.service.FormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.springapp.mvc.model.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value="/staff")
public class StaffController {

    @Autowired PersonDAO personDAO;
    @Autowired PatientDAO patientDAO;
    @Autowired AppointmentDAO appointmentDAO;
    @Autowired DoctorDAO doctorDAO;
    @Autowired BasicService basicService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView getAllPatients(HttpServletRequest request) {
        // get all patients
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3){
            return new ModelAndView("/InvalidAccess" );
        }
        ModelAndView model = new ModelAndView("staff/index");
        model.addObject("user", user);
        model.addObject("content", "dashboard");
        return model;
    }

    @RequestMapping(value="/create_patient_form", method = RequestMethod.GET)
    public ModelAndView createPatient(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3)
            return new ModelAndView("/InvalidAccess" );

        return new ModelAndView("staff/index", "content", "create_patient_form");
    }
    
    @RequestMapping(value="/update_patient", method = RequestMethod.GET)
    public ModelAndView updatePatient(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3)
            return new ModelAndView("/InvalidAccess" );
        return new ModelAndView("staff/index", "content", "update_patient");
    }

    @RequestMapping(value="/review_patient", method = RequestMethod.GET)
    public ModelAndView reviewPatientRecord(HttpServletRequest request) {

//        return new ModelAndView("staff/index", "content", "review_patient");
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        int person_id = user.getId();

        if(user.getRoleID() != 3)
            return new ModelAndView("/InvalidAccess" );

        //NEED TO ACCESS STAFFDOCTOR
        ModelAndView model = new ModelAndView("staff/index");
        List<Doctor> doctors = doctorDAO.searchDoctorsByStaffPersonId(person_id);
        model.addObject("doctors", doctors);
        model.addObject("content", "review_patient");
        return model;
    }

    @RequestMapping(value="/assign_patient", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView assignPatient(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3)
            return new ModelAndView("/InvalidAccess" );

        // get all the doctors in the system
        List<Doctor> doctors = doctorDAO.getAllDoctors();

        ModelAndView model = new ModelAndView("staff/index");

        model.addObject("content","assign_patient");
        model.addObject("doctors", doctors );
        return model;

    }

    @RequestMapping(value="/select_patient/{doctorId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView selectPatient(HttpServletRequest request, @PathVariable int doctorId) {
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3)
            return new ModelAndView("/InvalidAccess" );

        // get all the doctors in the system by firstname alphabetically
        List<Patient> patients = patientDAO.getAllPatientsSortedByName();

        ModelAndView model = new ModelAndView("staff/index");

        model.addObject("content","select_patient");
        model.addObject("patients", patients);
        //save doctorId to assign patients
        model.addObject("doctorId", doctorId);
        return model;

    }

    @RequestMapping(value = "patient_form_submit", method = RequestMethod.POST)
    public String patientFormSubmit(HttpServletRequest request, @ModelAttribute("patient") Patient patient){
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3)
            return "redirect:/InvalidAccess";

        int personId = patientDAO.insertPatient(patient);
        return "redirect:/patient/profile/"+personId;
        //return "redirect:/staff/dashboard";
    }

//    @RequestMapping(value = "/edit_profile_action", method = RequestMethod.POST)
//    public String editProfileAction(HttpServletRequest request, @ModelAttribute("patient") Patient patient) {
//        HttpSession session = request.getSession();
//        Person user = (Person)session.getAttribute("user");
//        patientDAO.updatePatient(patient);
//        int personId = patient.getPersonId();
//        return "redirect:/patient/profile/"+personId;
//    }

    // @RequestMapping(value = "", method = RequestMethod.POST)
    // public String patientFormSubmit(HttpServletRequest request,
    //                                 @ModelAttribute("patient") Patient patient,
    //                                 BindingResult result) throws ParseException {
    //     //incomplete
    //     return "redirect:/staff/dashboard";
    // }

    @RequestMapping(value="/create_appointment_form_1", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView createAppointmentFirst(
            @RequestParam(value = "keyword", defaultValue = "") String keyword, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3)
            return new ModelAndView("/InvalidAccess" );

        ModelAndView model = new ModelAndView("staff/index");
        if (!keyword.equals("")) {
            List<Patient> patients = patientDAO.searchPatientByKeyword(keyword);
            model.addObject("patients", patients);
        }
        model.addObject("content", "create_appointment_form_1");
        return model;
    }
    @RequestMapping(value="/see_appointment", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeAppointment(
            @RequestParam(value = "keyword", defaultValue = "") String keyword, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3)
            return new ModelAndView("/InvalidAccess" );
        ModelAndView model = new ModelAndView("staff/index");
        if (!keyword.equals("")) {
            List<Visit> appointments = appointmentDAO.getAppoinmentsByPatientName(keyword);
            model.addObject("appointments", appointments);
        }
        model.addObject("content", "search_appointment");
        return model;
    }

    @RequestMapping(value="/see_appointment/{patientId}", method = {RequestMethod.GET})
    public ModelAndView seeAppointmentGet(
            @PathVariable(value = "patientId") int patientId) {

        ModelAndView model = new ModelAndView("staff/index");
        List<Visit> appointments = appointmentDAO.getAppoinmentsByPatientId(patientId);
        Patient patient = patientDAO.getPatientsByPatientId(patientId);
        model.addObject("appointments", appointments);
        model.addObject("patient", patient);
        model.addObject("content", "patient_appointment");
        return model;
    }

    @RequestMapping(value="/appointment_schedule/{appointmentId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeAppointmentbyId(
            @PathVariable int appointmentId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3)
            return new ModelAndView("/InvalidAccess" );

        ModelAndView model = new ModelAndView("staff/index");
//        if (!keyword.equals("")) {
//            List<Visit> appointments = appointmentDAO.getAppoinmentsByPatientName(keyword);
//            model.addObject("appointments", appointments);
//        }
        Visit appointment = appointmentDAO.getAppointment(appointmentId);
        List<Map<Integer, Object>> schedule = basicService.findScheduleByVisit(1,appointment.getDoctorId());
        Patient patient = patientDAO.getPatientsByPatientId(appointment.getPatientId());
        model.addObject("schedule", schedule);
        model.addObject("appointment", appointment);
        model.addObject("patient", patient);
        model.addObject("dayDiff", FormatService.findDiffDays(new Date(), appointment.getDate()));
        model.addObject("content", "see_appointment");
        return model;
    }


    @RequestMapping(value="/reschedule/{patientId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView reschedules(@PathVariable int patientId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3)
            return new ModelAndView("/InvalidAccess" );

        ModelAndView model = new ModelAndView("staff/index");
        Patient patient = patientDAO.getPatientsByPatientId(patientId);
        Doctor doctor = doctorDAO.getDoctorById(patient.getDefaultDoc());
        List<Map<Integer, Object>> schedule = basicService.findScheduleByVisit(1, patient.getDefaultDoc());
        model.addObject("schedule", schedule);
        model.addObject("patient", patient);
        model.addObject("doctor", doctor);
        model.addObject("content", "create_appointment_form_2");
        model.addObject("type", "reschedule");
        return model;
    }

    @RequestMapping(value="/appointment_delete/{appointmentId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String appointmentDelete(@PathVariable int appointmentId) {
        Visit apmt = appointmentDAO.getAppointment(appointmentId);
        appointmentDAO.deleteAppointment(appointmentId);
        ModelAndView model = new ModelAndView("staff/index");
        model.addObject("content", "create_appointment_form_2");
        return "redirect:/staff/see_appointment/" + apmt.getPatientId();
    }

    @RequestMapping(value="/searchPatient", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView searchPatientFirst(
            @RequestParam(value = "keyword", defaultValue = "") String keyword, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3)
            return new ModelAndView("/InvalidAccess" );

        ModelAndView model = new ModelAndView("staff/index");
        if (keyword != null) {
            List<Patient> patients = patientDAO.searchPatientByKeyword(keyword);
            model.addObject("patients", patients);
        }
        model.addObject("content", "update_patient");

        return model;
    }

    // This function retrieves particular patient's info in a editable form
    @RequestMapping(value = "/review_records/{doctorId}", method = RequestMethod.GET)
    public ModelAndView retrieveRecords(HttpServletRequest request,@PathVariable int doctorId) {

        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3)
            return new ModelAndView("/InvalidAccess" );

        int session_role = ((Person)session.getAttribute("user")).getRoleID();
        List<Visit> records = appointmentDAO.getRecordsByDoctorId(doctorId);
        Doctor doctor = doctorDAO.getDoctorById(doctorId);

        ModelAndView model = new ModelAndView("staff/index");
        model.addObject("content", "review_records");
        model.addObject("records", records);
        model.addObject("doctor", doctor);
        model.addObject("role", session_role);
        return model;
    }


    @RequestMapping(value="/create_appointment_form_2/{personId}", method = RequestMethod.GET)
    public ModelAndView createAppointmentTwo(
            @PathVariable int personId, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3)
            return new ModelAndView("/InvalidAccess" );

        Patient patient = patientDAO.getPatientsByPersonId(personId);
        int patientID = patientDAO.getPatientsIdByPersonId(personId);
        Doctor doctor = doctorDAO.getDoctorById(patient.getDefaultDoc());
//        List<Visit> visits = appointmentDAO.getAppointmentsByDoctorId(docId, todayString, lastDayString);
        List<Visit> appointments = appointmentDAO.getAppoinmentsByPatientId(patientID);
//        List<Long> dateDiffList = new ArrayList<Long>();
//        for (Visit appointment : appointments) {
//            dateDiffList.add(FormatService.findDiffDays(new Date(), appointment.getDate()));
//        }
        List<Map<Integer, Object>> schedule = basicService.findScheduleByVisit(1, doctor.getId());
        ModelAndView model = new ModelAndView("staff/index");
        model.addObject("schedule", schedule);
        model.addObject("patient", patient);
        model.addObject("patientId", patientID);
        model.addObject("doctor", doctor);
//        model.addObject("dateDiffList", dateDiffList);
        model.addObject("content", "create_appointment_form_2");
        return model;
    }

    @RequestMapping(value = "appointment_form_submit", method = RequestMethod.POST)
    public String appointmentFormSubmit(HttpServletRequest request,
                                        @RequestParam(value = "time", defaultValue = "") String time,
                                        @RequestParam(value = "type", defaultValue = "") String type,
                                        @RequestParam(value = "appointmentId", defaultValue = "") String appointmentId,
                                        @ModelAttribute("visit") Visit visit,
                                        BindingResult result) throws ParseException {

        HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        if(user.getRoleID() != 3)
            return "redirect:/InvalidAccess";

        visit.setDate(basicService.getSchedule(visit, time));
        if (StringUtils.isNullOrEmpty(appointmentId)) {
            return "redirect:/staff/see_appointment/" + appointmentDAO.insertAppointment(visit);
        } else {
            visit.setId(Integer.parseInt(appointmentId));
            visit.setInitialID(Integer.parseInt(appointmentId));
            appointmentDAO.updateAppointment(visit);
        }
        return "redirect:/staff/appointment_schedule/" + appointmentId;
    }
}




