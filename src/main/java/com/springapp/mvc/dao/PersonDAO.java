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
import com.springapp.mvc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class PersonDAO {
    @Autowired
    DataSource dataSource;

    public Person getPersonWithUsernameAndPassword(String username, String password) {
        String sql = "select * from person" +
                "where person.username = ? and person.password=? Limit 1";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Person person  = (Person)jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new BeanPropertyRowMapper(Person.class));
        return person;
    }

//	public static ArrayList<Patient> searchPatient(int id, int person_id,
//			int default_doc, String health_card, int sin, String current_health)
//			throws ClassNotFoundException, SQLException {
//
//		Connection con = null;
//		Statement stmt = null;
//		ArrayList ret = null;
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
}
