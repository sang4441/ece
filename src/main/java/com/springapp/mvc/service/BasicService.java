package com.springapp.mvc.service;

import com.springapp.mvc.dao.BasicDAO;
import com.springapp.mvc.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BasicService {
    @Autowired BasicDAO basicDAO;

//    public List<String> findScheduleByDoctorId(int docId) {
//
//        List<Visit> visits = basicDAO.getAppointmentsByDoctorId(docId);
//
//
//
//        return;
//    }
}
