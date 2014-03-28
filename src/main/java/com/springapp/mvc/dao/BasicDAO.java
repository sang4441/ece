package com.springapp.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.springapp.mvc.model.Doctor;
import com.springapp.mvc.model.Patient;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class BasicDAO {

    @Autowired
    DataSource dataSource;

    public List<Visit> getAppoinmentsByPatientId(int patientId) {
        String sql = "SELECT * FROM visits where PatientID = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Visit> appointments = jdbcTemplate.query(sql,
                new Object[] { patientId },
                new BeanPropertyRowMapper(Visit.class));

        return appointments;
    }

    public List<Visit> getAppointmentsByDoctorId(int doctorId) {
        String sql = "SELECT * FROM visits where DoctorID = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Visit> appointments = jdbcTemplate.query(sql,
                new Object[] { doctorId },
                new BeanPropertyRowMapper(Visit.class));

        return appointments;
    }

    public void insertAppointment(Visit appointment){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE visits\n" +
                "SET doctorID = ?,\n" +
                "date = ?,\n" +
                "length = ?,\n" +
                "prescription = ?,\n" +
                "diagnosis = ?,\n" +
                "comment = ?,\n" +
                "date_modified = ?,\n" +
                "parentID = ? \n" +
                "initialID = ? \n" +
                "WHERE id= ?";

        jdbcTemplate.update(sql, new Object[]{appointment.getDoctorID(), appointment.getDate(), appointment.getPrescription(),
                appointment.getDiagnosis(), appointment.getComment(), appointment.getDate_modified()
                , appointment.getParentID(), appointment.getInitialID(), appointment.getId()});
    }

}
