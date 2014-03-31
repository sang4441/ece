package com.springapp.mvc.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.springapp.mvc.model.Visit;

public class AppointmentDAO {

	@Autowired
	DataSource dataSource;

	public List<Visit> getAppoinmentsByPatientId(int patientId) {
		String sql = "SELECT * FROM visits where PatientID = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Visit> appointments = jdbcTemplate.query(sql,
				new Object[] { patientId }, new BeanPropertyRowMapper(
						Visit.class));

		return appointments;
	}

	public List<Visit> getAppoinmentsByPatientName(String patientName) {
		String sql = "SELECT visits.*, CONCAT(person.NameFirst,person.NameLast) as patientName FROM visits \n"
				+ "left join patients on patients.id = visits.PatientID \n"
				+ "left join person \n"
				+ "on patients.PersonId = person.id \n"
				+ "where CONCAT(person.NameFirst,person.NameLast) like ?;";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Visit> appointments = jdbcTemplate.query(sql, new Object[] { "%"
				+ patientName + "%" }, new BeanPropertyRowMapper(Visit.class));

		return appointments;
	}

	public List<Visit> getAppointmentsByDoctorId(int doctorId, String today,
			String lastDay) {
		String sql = "SELECT visits.*, CONCAT(person.NameFirst,' ',person.NameLast) as patientName FROM visits \n"
				+ "            left join patients on patients.id = visits.PatientID\n"
				+ "            left join person on person.id = patients.PersonID\n"
				+ "            where visits.DoctorID = ? \n "
				+ "and Date >= ? and Date <= ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Visit> appointments = jdbcTemplate.query(sql, new Object[] {
				doctorId, today, lastDay }, new BeanPropertyRowMapper(
				Visit.class));

		return appointments;
	}

	public void updateAppointment(Visit appointment) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "UPDATE visits\n" + "SET PatientID = ?,\n"
				+ "DoctorID = ?,\n" + "Date = ?,\n" + "dateCode = ?,\n"
				+ "Length = ?,\n" + "Prescription = ?,\n" + "Diagnosis = ?,\n"
				+ "Comment = ? \n" + "DateModified = ? \n" + "InitialID = ? \n"
				+ "WHERE id= ?";

		jdbcTemplate.update(
				sql,
				new Object[] { appointment.getParentID(),
						appointment.getDoctorId(), appointment.getDate(),
						appointment.getDateCode(), appointment.getLength(),
						appointment.getPrescription(),
						appointment.getDiagnosis(), appointment.getComment(),
						appointment.getDate_modified(),
						appointment.getInitialID(), appointment.getId() });
	}

	public void insertAppointment(Visit appointment) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO visits(PatientID, DoctorID, Date, dateCode, Length, Prescription, Diagnosis, Comment, DateModified, InitialID)"
				+ "VALUES (?,\n"
				+ "?,\n"
				+ "?,\n"
				+ "?,\n"
				+ "?,\n"
				+ "?,\n"
				+ "?,\n" + "?,\n" + "?,\n" + "?)";

		jdbcTemplate.update(
				sql,
				new Object[] { appointment.getPatientId(),
						appointment.getDoctorId(), appointment.getDate(),
						appointment.getDateCode(), appointment.getLength(),
						appointment.getPrescription(),
						appointment.getDiagnosis(), appointment.getComment(),
						appointment.getDate_modified(),
						appointment.getInitialID() });
	}

	public void deleteAppointment(Visit appointment) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "DELETE FROM visits\n WHERE id= ?";

		jdbcTemplate.update(sql, new Object[] { appointment.getId() });
	}

	public List<Visit> searchAppointments(Date date, String patientName,
			String diagnosis, String comment, String prescription,
			String surgery) {
		String sql = "SELECT visits.*, CONCAT(person.NameFirst,' ',person.NameLast) as patientName FROM visits \n"
				+ "            LEFT JOIN patients on patients.id = visits.PatientID\n"
				+ "            LEFT JOIN person on person.id = patients.PersonID\n"
				+ "            WHERE (? = '' OR visits.Date = ?) AND \n "
				+ "					(? LIKE '' OR person.NameFirst LIKE ?) AND \n "
				+ "					(? LIKE '' OR person.NameLast LIKE ?) AND \n "
				+ "					(? LIKE '' OR visits.diagnosis LIKE ?) AND \n "
				+ "					(? LIKE '' OR visits.comment LIKE ?) AND \n "
				+ "					(? LIKE '' OR visits.prescription LIKE ?) AND \n "
				+ "					(? LIKE '' OR visits.surgery LIKE ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Visit> appointments = jdbcTemplate
				.query(sql, new Object[] { date, searchString(patientName),
						searchString(diagnosis), searchString(comment),
						searchString(prescription), searchString(surgery) },
						new BeanPropertyRowMapper(Visit.class));

		return appointments;
	}

	public String searchString(String query) {
		// TODO: sanitize inputs
		return "%" + query + "%";
	}

	public Visit getAppointment(int appointmentID) {
		String sql = "SELECT visits.*, CONCAT(person.NameFirst,' ',person.NameLast) as patientName FROM visits \n"
				+ "            LEFT JOIN patients on patients.id = visits.PatientID\n"
				+ "            LEFT JOIN person on person.id = patients.PersonID\n"
				+ "            WHERE visits.id = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Visit visit = (Visit) jdbcTemplate.queryForObject(sql,
				new Object[] { appointmentID }, new BeanPropertyRowMapper(
						Visit.class));

		return visit;
	}
}