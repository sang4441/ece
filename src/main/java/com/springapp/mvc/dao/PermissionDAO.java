package com.springapp.mvc.dao;

import java.util.logging.Logger;

import javax.sql.DataSource;

//import ece356.helpers.ServletHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.springapp.mvc.model.Doctor;
import com.springapp.mvc.model.Patient;
import com.springapp.mvc.model.Visit;

public class PermissionDAO {

	private Logger LOG = Logger.getLogger("DAO");

	@Autowired
	DataSource dataSource;

	public boolean hasPermissionForPatient(Doctor doctor, Patient patient) {

		return hasPermissionForPatient(doctor, patient.getId());
	}

	public boolean hasPermissionForPatient(Doctor doctor, int patientID) {

		String sql = "select count(PatientID)\n" + "from PatientDoctor\n"
				+ "where DoctorID = ?\n" + "and PatientID = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		boolean hasPermission = 0 != jdbcTemplate.queryForInt(sql,
				new Object[] { doctor.getId(), patientID });

		return hasPermission;
	}

	public boolean hasPermissionForAppointment(Doctor doctor, int visitID) {
		String sql = "SELECT COUNT(patients.id) \n"
				+ "FROM visits \n"
				+ "INNER JOIN patients ON patients.id = visits.PatientID\n"
				+ "INNER JOIN doctor ON doctor.id = visits.DoctorID\n"
				+ "INNER JOIN PatientDoctor ON patients.id = PatientDoctor.PatientID AND doctor.id = ?\n"
				+ "WHERE visits.id = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		boolean hasPermission = 0 != jdbcTemplate.queryForInt(sql,
				new Object[] { doctor.getId(), visitID });
		return hasPermission;
	}

	public boolean hasPermissionForAppointment(Doctor doctor, Visit visit) {
		return hasPermissionForAppointment(doctor, visit.getId());
	}

}
