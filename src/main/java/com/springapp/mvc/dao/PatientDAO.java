package com.springapp.mvc.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

//import ece356.helpers.ServletHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.springapp.mvc.model.Patient;

public class PatientDAO {
	private Logger LOG = Logger.getLogger("DAO");
	@Autowired
	DataSource dataSource;

	@ModelAttribute("patients")
	public List<Patient> getAllPatients() {
		String sql = "SELECT * FROM patients";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Patient> patients = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper(Patient.class));

		return patients;
	}

	public Patient getPatientsById(int id) {
		String sql = "SELECT * FROM patients where id = ? LIMIT 1";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Patient patient = jdbcTemplate.queryForObject(sql, new Object[] { id },
				Patient.class);

		return patient;
	}

	@ModelAttribute("patientsOfDoctor")
	public List<Patient> getAllPatientsOfDoctor(int id) {
		String sql = "SELECT patients.* " + "FROM patients "
				+ "INNER JOIN PatientDoctor dp ON dp.PatientID = patients.id "
				+ "WHERE dp.DoctorID = ?";
		LOG.info(sql);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Patient> patients = jdbcTemplate.query(sql, new Object[] { id },
				new BeanPropertyRowMapper(Patient.class));

		return patients;
	}
}
