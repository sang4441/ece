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

import com.springapp.mvc.model.Patient;
import com.springapp.mvc.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
//import ece356.helpers.ServletHelper;

public class PatientDAO {
    @Autowired
    DataSource dataSource;

	public List<Patient> getAllPatients() {
        String sql = "SELECT * FROM patients";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Patient> patients  = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper(Patient.class));

        return patients;
	}

	public List<Patient> getAllPatientsById(int id) {
        String sql = "SELECT * FROM patients where id = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Patient> patients  = jdbcTemplate.query(
                sql,
                new Object[] {id},
                new BeanPropertyRowMapper(Patient.class));

        return patients;
	}
//
//	public static ArrayList<Visit> getVisitsByPatientID(int id)
//			throws SQLException {
//		Connection con = null;
//		Statement stmt = null;
//		ArrayList<Visit> visits = new ArrayList<Visit>();
//		try {
//			con = getConnection();
//			stmt = con.createStatement();
//			String query = String.format("SELECT * FROM visits "
//					+ "WHERE visits.PatientID = %d "
//					+ "ORDER BY visits.Date DESC", id);
//			logger.log(Level.INFO, "getByVisitID:" + query);
//
//			ResultSet resultSet = stmt.executeQuery(query);
//			while (resultSet.next()) {
//				Visit visit = new Visit(resultSet.getInt("id"),
//						resultSet.getInt("PatientID"),
////						ServletHelper.toDate(resultSet.getString("dATE")),
//						resultSet.getInt("Length"),
//						resultSet.getString("Prescription"),
//						resultSet.getString("Diagnosis"),
//						resultSet.getInt("DoctorID"),
////						ServletHelper.toDate(resultSet
//								.getString("DateModified")),
//						resultSet.getString("Comment"),
//						resultSet.getInt("InitialID"));
//				visits.add(visit);
//			}
//
//			return visits;
//		} catch (ClassNotFoundException e) {
////			ServletHelper.log(e);
//		} catch (Exception e) {
////			ServletHelper.log(e);
//		} finally {
//			if (stmt != null) {
//				stmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//		}
//		return null;
//	}
//
//	public static ArrayList<Patient> searchPatient(int id, int person_id,
//			int default_doc, String health_card, int sin, String current_health)
//			throws ClassNotFoundException, SQLException {
//
//		Connection con = null;
//		Statement stmt = null;
//		ArrayList<Patient> ret = null;
//		try {
//			con = getConnection();
//			stmt = con.createStatement();
//			String query = String.format("SELECT * " + "FROM patients "
//					+ "WHERE (%d = 0 OR patients.id = %d) AND "
//					+ "		(%d = 0 OR patients.PersonID = %d) AND "
//					+ "		(%d = 0 OR patients.DefaultDoc = %d) AND "
//					+ "		('%s' LIKE '' OR patients.HealthCard LIKE '%s') AND "
//					+ "		(%d = 0 OR patients.SIN = %d) AND "
//					+ "		('%s' LIKE '' OR patients.CurrentHealth LIKE '%s')",
//					id, id, person_id, person_id, default_doc, default_doc,
//					health_card, health_card, sin, sin, current_health,
//					current_health);
//			logger.log(Level.INFO, query);
//
//			ResultSet resultSet = stmt.executeQuery(query);
//			ret = new ArrayList<Patient>();
//			while (resultSet.next()) {
//				Patient e = new Patient(resultSet.getInt("id"),
//						resultSet.getInt("PersonID"),
//						resultSet.getInt("DefaultDoc"),
//						resultSet.getString("HealthCard"),
//						resultSet.getInt("SIN"),
//						resultSet.getString("CurrentHealth"));
//				ret.add(e);
//			}
//
//			return ret;
//		} finally {
//			if (stmt != null) {
//				stmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//		}
//	}
//
//	public static Patient getPatientByPersonID(long personID)
//			throws ClassNotFoundException, SQLException {
//		Connection con = null;
//		Statement stmt = null;
//		try {
//			con = getConnection();
//			stmt = con.createStatement();
//			String query = String.format("SELECT * FROM patients "
//					+ "WHERE patients.PersonID = %d " + "LIMIT 1", personID);
//			logger.log(Level.INFO, query);
//
//			ResultSet resultSet = stmt.executeQuery(query);
//			Patient patient = null;
//			while (resultSet.next()) {
//				patient = new Patient(resultSet.getInt("id"),
//						resultSet.getInt("PersonID"),
//						resultSet.getInt("DefaultDoc"),
//						resultSet.getString("HealthCard"),
//						resultSet.getInt("SIN"),
//						resultSet.getString("CurrentHealth"));
//			}
//
//			return patient;
//		} finally {
//			if (stmt != null) {
//				stmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//		}
//	}
}
