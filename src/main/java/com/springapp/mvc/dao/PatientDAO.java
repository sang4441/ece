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

	public Patient getPatientsByPersonId(int id) {
		String sql = "SELECT * FROM patients " +
                "inner join person " +
                "on patients.PersonId = person.id " +
                "where person.id = ? LIMIT 1";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Patient patient = (Patient)jdbcTemplate.queryForObject(sql, new Object[] { id },
                new BeanPropertyRowMapper(Patient.class));

		return patient;
	}

    public void updatePatient(Patient patient) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE person\n" +
                "SET NameLast = ?,\n" +
                "NameFirst = ?,\n" +
                "Phone = ?,\n" +
                "username = ?,\n" +
                "password = ?,\n" +
                "street = ?,\n" +
                "City = ?,\n" +
                "Province = ?,\n" +
                "PostalCode = ? \n" +
                "WHERE id= ?";

        jdbcTemplate.update(sql, new Object[]{patient.getNameLast(), patient.getNameFirst(), patient.getPhone(), patient.getUsername(), patient.getPassword(), patient.getStreet(), patient.getCity()
                , patient.getProvince(), patient.getPostalCode(), patient.getPersonId()});

        sql = "UPDATE patients\n" +
                "SET DefaultDoc = ?,\n" +
                "HealthCard = ?,\n" +
                "SIN = ?,\n" +
                "CurrentHealth = ?\n" +
                "WHERE PersonID= ?";

        jdbcTemplate.update(sql, new Object[]{patient.getDefaultDoc(), patient.getHealthCard(), patient.getSIN(), patient.getCurrentHealth(), patient.getPersonId()});
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
