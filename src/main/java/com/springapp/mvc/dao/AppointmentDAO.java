package com.springapp.mvc.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.springapp.mvc.model.Visit;
import com.springapp.mvc.service.FormatService;

public class AppointmentDAO {

	Logger LOG = Logger.getLogger("AppointmentDAO");

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

	public List<Visit> getRecordsByDoctorId(int doctorId) {

		String sql = "select visits.*, CONCAT(pe.NameFirst,' ',pe.NameLast) as patientName\n"
				+ "from visits\n"
				+ "inner join person pe\n"
				+ "on pe.id = visits.patientID\n"
				+ "where doctorId = ?\n"
				+ "order by patientName asc, date desc";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Visit> records = jdbcTemplate.query(sql,
				new Object[] { doctorId }, new BeanPropertyRowMapper(
						Visit.class));

		return records;
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

	public List<Visit> getAppointmentsByPatientId(int patientId, String today,
			String lastDay) {
		String sql = "SELECT visits.*, CONCAT(person.NameFirst,' ',person.NameLast) as patientName FROM visits \n"
				+ "            left join patients on patients.id = visits.PatientID\n"
				+ "            left join person on person.id = patients.PersonID\n"
				+ "            where patients.id = ? \n "
				+ "and Date >= ? and Date <= ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Visit> appointments = jdbcTemplate.query(sql, new Object[] {
				patientId, today, lastDay }, new BeanPropertyRowMapper(
				Visit.class));

		return appointments;
	}

	public void updateAppointment(Visit appointment) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "UPDATE visits\n" + "SET PatientID = ?,\n"
				+ "Date = ?,\n" + "dateCode = ?,\n"
				+ "Length = ?,\n" + "Prescription = ?,\n" + "Diagnosis = ?,\n"
				+ "Comment = ?, \n" + "DateModified = ?, \n" + "InitialID = ? \n"
				+ "WHERE id= ?";

		jdbcTemplate.update(
				sql,
				new Object[] { appointment.getPatientId(),
						appointment.getDate(),
						appointment.getDateCode(), appointment.getLength(),
						appointment.getPrescription(),
						appointment.getDiagnosis(), appointment.getComment(),
						appointment.getDate_modified(),
						appointment.getInitialID(), appointment.getId() });
	}

	public int insertAppointment(Visit appointment) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from visits\n"
				+ "where PatientID = ? and DoctorID = ? and dateCode = ? and DATE(Date) = ?";
		List<Visit> appointments = jdbcTemplate.query(sql, new Object[] {
				appointment.getPatientId(), appointment.getDoctorId(),
				appointment.getDateCode(), appointment.getDate() },
				new BeanPropertyRowMapper(Visit.class));

		sql = "INSERT INTO visits(PatientID, DoctorID, Date, dateCode, Length, Prescription, Diagnosis, Comment, DateModified)"
				+ "VALUES (?,\n"
				+ "?,\n"
				+ "?,\n"
				+ "?,\n"
				+ "?,\n"
				+ "?,\n"
				+ "?,\n" + "?,\n" + "?)";

		jdbcTemplate.update(
				sql,
				new Object[] { appointment.getPatientId(),
						appointment.getDoctorId(), appointment.getDate(),
						appointment.getDateCode(), appointment.getLength(),
						appointment.getPrescription(),
						appointment.getDiagnosis(), appointment.getComment(),
						appointment.getDate_modified() });


		sql = "select max(id) from visits";

		java.lang.Integer currentId = jdbcTemplate.queryForObject(sql,
				java.lang.Integer.class);

		appointment.setInitialID(currentId);

		sql = "UPDATE visits SET initialID = ? WHERE id = ?";


        jdbcTemplate.update(
                sql,
                new Object[] { appointment.getInitialID(), appointment.getInitialID() });
        return appointment.getPatientId();
	}

	public void deleteAppointment(int appointmentId) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "DELETE FROM visits\n WHERE id= ?";

		jdbcTemplate.update(sql, new Object[] { appointmentId});
	}

	public List<Visit> searchAppointments(Date date, String patientName,
			String diagnosis, String comment, String prescription,
			String surgery) {
		String sql = "SELECT visits.*, CONCAT(person.NameLast,', ',person.NameFirst) as patientName, \n"
				+ "		CONCAT(personDoc.NameLast,', ',personDoc.NameFirst) as doctorName \n"
				+ "FROM visits \n"
				+ "            LEFT JOIN patients on patients.id = visits.PatientID\n"
				+ "			   LEFT JOIN doctor on doctor.id = visits.DoctorID\n"
				+ "			   LEFT JOIN person personDoc on personDoc.id = doctor.PersonID\n"
				+ "            LEFT JOIN person on person.id = patients.PersonID\n"
				+ "			   LEFT JOIN doctor on visits.DoctorID = doctor.id\n"
				+ "			   LEFT JOIN person personDoc ON personDoc.id = doctor.PersonID\n"
				+ "            WHERE (? = '1900-01-01' OR DAY(visits.Date) = DAY(?)) AND \n "
				+ "					(? LIKE '' OR person.NameFirst LIKE ?) AND \n "
				+ "					(? LIKE '' OR person.NameLast LIKE ?) AND \n "
				+ "					(? LIKE '' OR visits.Diagnosis LIKE ?) AND \n "
				+ "					(? LIKE '' OR visits.Comment LIKE ?) AND \n "
				+ "					(? LIKE '' OR visits.Prescription LIKE ?) AND \n "
				+ "					(? LIKE '' OR visits.Surgery LIKE ?)\n"
				+ "			   ORDER BY visits.InitialID DESC, visits.DateModified DESC";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		LOG.info(sql);
		LOG.info("\tdate: " + FormatService.formatDate(date));
		List<Visit> appointments = jdbcTemplate.query(sql, new Object[] {
				FormatService.formatDate(date), FormatService.formatDate(date),
				patientName, FormatService.searchString(patientName),
				patientName, FormatService.searchString(patientName),
				diagnosis, FormatService.searchString(diagnosis), comment,
				FormatService.searchString(comment), prescription,
				FormatService.searchString(prescription), surgery,
				FormatService.searchString(surgery) },
				new BeanPropertyRowMapper(Visit.class));

		return appointments;
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

	public List<Visit> getRelatedAppointments(int initialID) {
		String sql = "SELECT visits.*, CONCAT(person.NameLast,', ',person.NameFirst) as patientName FROM visits \n"
				+ "            left join patients on patients.id = visits.PatientID\n"
				+ "            left join person on person.id = patients.PersonID\n"
				+ "            where visits.InitialID = ?\n"
				+ "			   order by visits.id DESC";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Visit> appointments = jdbcTemplate.query(sql,
				new Object[] { initialID }, new BeanPropertyRowMapper(
						Visit.class));

		return appointments;
	}
}
