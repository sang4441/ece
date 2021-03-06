package com.springapp.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.springapp.mvc.model.Doctor;
import com.springapp.mvc.model.Patient;
import com.springapp.mvc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class PersonDAO {
    @Autowired
    DataSource dataSource;

    public Person getPersonWithUsernameAndPassword(String username, String password) {
        String sql = "select * from person " +
                "where person.username = ? and person.password=? Limit 1";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Person person  = (Person)jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new BeanPropertyRowMapper(Person.class));
        return person;
    }

    public Person getPersonById(int id){
        String sql = "select * from person " +
                "where person.id = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Person person  = (Person)jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Person.class));
        return person;
    }

    public List<Person> getDoctorsAsPersonForPatient(int patientId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "select person.*\n" +
                "from PatientDoctor\n" +
                "inner join doctor \n" +
                "on doctor.id = PatientDoctor.doctorID\n" +
                "inner join person\n" +
                "on person.id = doctor.personID\n" +
                "where PatientID = ?";

        List<Person> doctors = jdbcTemplate.query(sql, new Object[]{patientId}, new BeanPropertyRowMapper(Doctor.class));

        return doctors;
    }

    public int checkLogin(String username, String password){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "select count(*)\n" +
                        "from person \n" +
                        "where username = ? and password = ?";

        int check = jdbcTemplate.queryForInt(sql, new Object[]{username, password});

        return check;

    }

}
