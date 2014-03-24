package com.springapp.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.springapp.mvc.model.Patient;
import com.springapp.mvc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class BasicDAO {

    @Autowired
    DataSource dataSource;



	// public static ArrayList<Patient> query8(int empID, String empName,
	// String job, int deptID, int salary) throws ClassNotFoundException,
	// SQLException {
	//
	// Connection con = null;
	// Statement stmt = null;
	// ArrayList ret = null;
	// try {
	// con = getConnection();
	// stmt = con.createStatement();
	// ResultSet resultSet = stmt
	// .executeQuery(String
	// .format("SELECT * "
	// + "FROM Employee "
	// + "WHERE (%d = -1 OR Employee.empID = %d) AND "
	// + "		('%s' LIKE '' OR Employee.empName LIKE '%s') AND "
	// + "		('%s' LIKE '' OR Employee.job LIKE '%s') AND "
	// + "		(%d = -1 OR Employee.deptID = %d) AND "
	// + "		(%d = -1 OR Employee.salary = %d)",
	// empID, empID, empName, empName, job, job,
	// deptID, deptID, salary, salary));
	// ret = new ArrayList<Patient>();
	// while (resultSet.next()) {
	// Patient e = new Patient(resultSet.getInt("empID"),
	// resultSet.getString("empName"),
	// resultSet.getString("job"), resultSet.getInt("deptID"),
	// resultSet.getInt("salary"));
	// ret.add(e);
	// }
	// return ret;
	// } finally {
	// if (stmt != null) {
	// stmt.close();
	// }
	// if (con != null) {
	// con.close();
	// }
	// }
	// }
}
