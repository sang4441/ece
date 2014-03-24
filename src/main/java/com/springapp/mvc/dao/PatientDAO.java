package com.springapp.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.springapp.mvc.model.Patient;
import com.springapp.mvc.model.Visit;
//import ece356.helpers.ServletHelper;

public class PatientDAO {
	public static final String url = "jdbc:mysql://eceweb.uwaterloo.ca:3306/";
	private static final String user_id = "gdogrady";
	public static final String user = "user_" + user_id;
	public static final String pwd = "user_" + user_id;
	private static final Logger logger = Logger.getLogger("ece356");

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, pwd);
		Statement stmt = null;
		try {
			con.createStatement();
			stmt = con.createStatement();
			stmt.execute("USE ece356db_" + user_id);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return con;
	}

//	public static ArrayList<Patient> getAllPatients()
//			throws ClassNotFoundException, SQLException {
//		Connection con = null;
//		Statement stmt = null;
//		ArrayList<Patient> ret = null;
//		try {
//			con = getConnection();
//			stmt = con.createStatement();
//			ResultSet resultSet = stmt.executeQuery("SELECT * FROM patients");
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
//	public static Patient getByID(int id) throws SQLException {
//		Connection con = null;
//		Statement stmt = null;
//		Patient patient = null;
//		try {
//			con = getConnection();
//			stmt = con.createStatement();
//			String query = String.format("SELECT * FROM patients "
//					+ "WHERE patients.id = '%d' " + "LIMIT 1", id);
//			logger.log(Level.INFO, "getByID:" + query);
//
//			ResultSet resultSet = stmt.executeQuery(query);
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
//		return patient;
//	}
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
