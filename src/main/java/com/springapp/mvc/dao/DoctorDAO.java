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
		String sql = "SELECT * FROM doctors";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Doctor> doctors = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper(Doctor.class));

		return doctors;
	}

	@ModelAttribute("doctor")
	public Doctor getDoctorById(int id) {
		String sql = "SELECT * FROM doctors where id = ? LIMIT 1";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Doctor doctor = jdbcTemplate.queryForObject(sql, new Object[] { id },
				Doctor.class);

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
