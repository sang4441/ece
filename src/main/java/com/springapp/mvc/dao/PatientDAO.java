package com.springapp.mvc.dao;

import java.util.Date;
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
		String sql = "select patients.*, pe.nameFirst, pe.nameLast\n" +
                "from patients\n" +
                "inner join person pe\n" +
                "on pe.id = patients.personID";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Patient> patients = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper(Patient.class));

		return patients;
	}

    //returns list of all patients by first name asc
    public List<Patient> getAllPatientsSortedByName() {
        String sql = "select patients.*, pe.nameFirst, pe.nameLast\n" +
                "from patients\n" +
                "inner join person pe\n" +
                "on pe.id = patients.personID\n" +
                "order by pe.nameFirst asc";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Patient> patients = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Patient.class));


        return patients;
    }


	public Patient getPatientsByPatientId(int id) {
		String sql = "SELECT * FROM patients " + "inner join person "
				+ "on patients.PersonId = person.id "
				+ "where patients.id = ? LIMIT 1";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Patient patient = (Patient) jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new BeanPropertyRowMapper(Patient.class));

		return patient;
	}

	public List<Patient> searchPatients(int id, String nameFirst,
			String nameLast, Date date) {
		String sql = "SELECT * FROM patients \n "
				+ "INNER JOIN person ON patients.PersonId = person.id \n "
				+ "WHERE (? = 0 OR patients.id = ?) AND \n "
				+ "		 (? LIKE '' OR person.NameFirst LIKE ?) AND \n "
				+ "		 (? LIKE '' OR person.NameLast LIKE ?) AND \n "
				+ "		 (? = '' OR visit.Date = ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Patient> patients = (List<Patient>) jdbcTemplate.query(sql,
				new Object[] { id, searchString(nameFirst),
						searchString(nameLast), date },
				new BeanPropertyRowMapper(Patient.class));

		return patients;
	}

	public Patient getPatientsByPersonId(int id) {
		String sql = "SELECT * FROM patients " + "inner join person "
				+ "on patients.PersonId = person.id "
				+ "where person.id = ? LIMIT 1";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Patient patient = (Patient) jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new BeanPropertyRowMapper(Patient.class));

		return patient;
	}

    public int getPatientsIdByPersonId(int id) {
        String sql = "SELECT patients.id FROM patients " + "inner join person "
                + "on patients.PersonId = person.id "
                + "where person.id = ? LIMIT 1";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        java.lang.Integer patientId = jdbcTemplate.queryForObject(sql,
                new Object[] { id }, java.lang.Integer.class);

        return patientId;
    }

	public void updatePatient(Patient patient) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "UPDATE person\n" + "SET NameLast = ?,\n"
				+ "NameFirst = ?,\n" + "Phone = ?,\n" + "username = ?,\n"
				+ "password = ?,\n" + "street = ?,\n" + "City = ?,\n"
				+ "Province = ?,\n" + "PostalCode = ? \n" + "WHERE id= ?";

		jdbcTemplate.update(
				sql,
				new Object[] { patient.getNameLast(), patient.getNameFirst(),
						patient.getPhone(), patient.getUsername(),
						patient.getPassword(), patient.getStreet(),
						patient.getCity(), patient.getProvince(),
						patient.getPostalCode(), patient.getPersonId() });

		sql = "UPDATE patients\n" + "SET DefaultDoc = ?,\n"
				+ "HealthCard = ?,\n" + "SIN = ?,\n" + "CurrentHealth = ?\n"
				+ "WHERE PersonID= ?";

		jdbcTemplate.update(
				sql,
				new Object[] { patient.getDefaultDoc(),
						patient.getHealthCard(), patient.getSIN(),
						patient.getCurrentHealth(), patient.getPersonId() });
	}

    public void updatePatientAsStaff(Patient patient) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE person\n" + "SET NameLast = ?,\n"
                + "NameFirst = ?,\n" + "Phone = ?,\n" + "username = ?,\n"
                + "street = ?,\n" + "City = ?,\n"
                + "Province = ?,\n" + "PostalCode = ? \n" + "WHERE id= ?";

        jdbcTemplate.update(
                sql,
                new Object[] { patient.getNameLast(), patient.getNameFirst(),
                        patient.getPhone(), patient.getUsername(), patient.getStreet(),
                        patient.getCity(), patient.getProvince(),
                        patient.getPostalCode(), patient.getPersonId() });

        sql = "UPDATE patients\n" + "SET DefaultDoc = ?,\n"
                + "HealthCard = ?,\n" + "SIN = ?,\n" + "CurrentHealth = ?\n"
                + "WHERE PersonID= ?";

        jdbcTemplate.update(
                sql,
                new Object[] { patient.getDefaultDoc(),
                        patient.getHealthCard(), patient.getSIN(),
                        patient.getCurrentHealth(), patient.getPersonId() });
    }

	public List<Patient> searchPatientByKeyword(String keyword) {
		String sql = "SELECT * FROM patients \n" + "left join person\n"
				+ "on patients.PersonId = person.id\n"
				+ "where CONCAT(person.NameFirst,person.NameLast) like ?;";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Patient> patients = jdbcTemplate.query(sql, new Object[] { "%"
				+ keyword + "%" }, new BeanPropertyRowMapper(Patient.class));

		return patients;
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

    public List<Patient> getAllPatientsInfoOfDoctor(int id) {
        String sql = "SELECT * " + "FROM patients "
                + "INNER JOIN PatientDoctor dp ON dp.PatientID = patients.id "
                + "INNER JOIN person p ON p.id = patients.PersonID WHERE dp.DoctorID = ?";
        LOG.info(sql);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Patient> patients = jdbcTemplate.query(sql, new Object[] { id },
                new BeanPropertyRowMapper(Patient.class));

        return patients;
    }

	public int insertPatient(Patient patient) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO person\n (NameLast, NameFirst, Phone, username, password, street, City, Province, PostalCode, RoleID)"
				+ "VALUES (?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?)";

		jdbcTemplate.update(
				sql,
				new Object[] { patient.getNameLast(), patient.getNameFirst(),
						patient.getPhone(), patient.getUsername(),
						patient.getPassword(), patient.getStreet(),
						patient.getCity(), patient.getProvince(),
						patient.getPostalCode(), 1 });

		sql = "select id from person \n" + "order by id DESC\n" + "limit 1;";

		java.lang.Integer personId = jdbcTemplate.queryForObject(sql,
				java.lang.Integer.class);

		sql = "INSERT INTO patients\n (PersonID, DefaultDoc, HealthCard, SIN, CurrentHealth)"
				+ "VALUES (?,\n?,\n?,\n?,\n?)";

		jdbcTemplate.update(
				sql,
				new Object[] { personId, patient.getDefaultDoc(),
						patient.getHealthCard(), patient.getSIN(),
						patient.getCurrentHealth() });

		return personId;
	}

	public String searchString(String query) {
		// TODO: sanitize inputs
		return "%" + query + "%";
	}
}
