package com.springapp.mvc.dao;

import java.util.List;

import javax.sql.DataSource;

//import ece356.helpers.ServletHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.springapp.mvc.model.Doctor;

public class DoctorDAO {
	@Autowired
	DataSource dataSource;

	@ModelAttribute("doctors")
	public List<Doctor> getAllDoctors() {
		String sql = "SELECT * FROM doctor";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Doctor> doctors = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper(Doctor.class));

		return doctors;
	}

	public Doctor getDoctorById(int id) {
		String sql = "SELECT person.*, doctor.PersonId FROM doctor \n" +
                "left join person\n" +
                "on doctor.PersonId = person.id\n" +
                "where doctor.id = ?;";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Doctor doctor = (Doctor)jdbcTemplate.queryForObject(sql, new Object[] { id },
                new BeanPropertyRowMapper(Doctor.class));

		return doctor;
	}

	@ModelAttribute("doctorsOfDoctor")
	public List<Doctor> getAllDoctorsOfDoctor(int id) {
		String sql = "SELECT doctors.* " + "FROM doctors "
				+ "INNER JOIN PatientDoctor dp ON dp.DoctorID = doctors.id "
				+ "WHERE dp.DoctorID = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Doctor> doctors = jdbcTemplate.query(sql, new Object[] { id },
				new BeanPropertyRowMapper(Doctor.class));

		return doctors;
	}
}
