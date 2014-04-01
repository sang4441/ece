package com.springapp.mvc.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

//import ece356.helpers.ServletHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.springapp.mvc.model.Doctor;

public class DoctorDAO {

	private Logger LOG = Logger.getLogger("DAO");

	@Autowired
	DataSource dataSource;

	@ModelAttribute("doctors")
	public List<Doctor> getAllDoctors() {
		String sql = "select doctor.*, pe.nameFirst, pe.nameLast\n" +
                "from doctor\n" +
                "inner join person pe\n" +
                "on doctor.personID = pe.id";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Doctor> doctors = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper(Doctor.class));

		return doctors;
	}

    public List<Doctor> searchDoctorsByStaffPersonId(int personId){

        String sql = "select id\n" +
                        "from staff\n" +
                        "where personID = ?\n";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int staff_id = jdbcTemplate.queryForInt(sql, new Object[]{personId});

        sql = "select doctor.*, pe.nameFirst, pe.nameLast\n" +
                "from doctor \n" +
                "inner join person pe\n" +
                "on doctor.personID = pe.id\n" +
                "inner join StaffDoctor\n" +
                "on StaffDoctor.doctorID = doctor.id\n" +
                "where StaffDoctor.staffID = ?";

        List<Doctor> doctors = jdbcTemplate.query(sql, new Object[] {staff_id}, new BeanPropertyRowMapper(Doctor.class));

        return doctors;
    }


	public Doctor getDoctorById(int id) {
		String sql = "  SELECT dt.*, pr.street, pr.City, pr.city, pr.Province, pr.PostalCode, pr.NameLast, pr.NameFirst, pr.Phone, pr.username\n" +
                "                FROM doctor as dt \n" +
                "                inner join person as pr \n" +
                "                on dt.PersonId = pr.id \n" +
                "                where dt.id = ? LIMIT 1";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Doctor doctor = (Doctor) jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new BeanPropertyRowMapper(Doctor.class));

		return doctor;
	}

    public Doctor getDoctorInfoById(int id) {
        String sql = "SELECT pt.*, pr.street, pr.City, pr.city, pr.Province, pr.PostalCode, pr.NameLast, pr.NameFirst, pr.Phone, pr.username\n" +
                "FROM doctor as pt inner join person as pr on pt.PersonId = pr.id\n" +
                "where pt.id = ? LIMIT 1";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Doctor doctor = (Doctor) jdbcTemplate.queryForObject(sql,
                new Object[] { id }, new BeanPropertyRowMapper(Doctor.class));

        return doctor;
    }

	public Doctor getDoctorByPersonID(int id) {
		String sql = "select doctor.id FROM doctor " + "inner join person "
				+ "on doctor.PersonId = person.id "
				+ "where person.id = ? LIMIT 1";
		LOG.info(sql);
		LOG.info("id: " + id);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Doctor doctor = (Doctor) jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new BeanPropertyRowMapper(Doctor.class));

		return doctor;
	}

	@ModelAttribute("doctorsOfDoctor")
	public List<Doctor> getAllDoctorsOfDoctor(int id) {
		String sql = "SELECT doctors.* " + "FROM doctors "
				+ "INNER JOIN PatientDoctor dp ON dp.DoctorID = doctors.id "
				+ "WHERE dp.DoctorID = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Doctor> doctors = jdbcTemplate.query(sql, new Object[] { id },
				new BeanPropertyRowMapper(Doctor.class));

		return doctors;
	}

    public void insertDoctorPatient(int patientId, int doctorId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "insert into PatientDoctor(PatientID, DoctorID)\n" +
                "values(?\n" +
                ", ?)";

        jdbcTemplate.update(sql, new Object[]{patientId, doctorId});
    }

    public void deleteDoctorPatient(int patientId, int doctorId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "DELETE from PatientDoctor\n" +
                "where patientID = ? and doctorID = ?";

        jdbcTemplate.update(sql, new Object[]{patientId, doctorId});
    }

    public List<Doctor> getDoctorsForPatient(int patientId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "select doctor.*, person.nameFirst, person.nameLast\n" +
                "from PatientDoctor\n" +
                "inner join doctor\n" +
                "on doctor.id = PatientDoctor.doctorID\n" +
                "inner join person\n" +
                "on person.id = doctor.personID\n" +
                "where PatientDoctor.patientID = ?";

        List<Doctor> doctors = jdbcTemplate.query(sql, new Object[]{patientId}, new BeanPropertyRowMapper(Doctor.class));

        return doctors;
    }

    public List<Doctor> getDoctorsForRevokePatient(int patientId, int doctorId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "select doctor.*, person.nameFirst, person.nameLast\n" +
                "from PatientDoctor\n" +
                "inner join doctor\n" +
                "on doctor.id = PatientDoctor.doctorID\n" +
                "inner join person\n" +
                "on person.id = doctor.personID\n" +
                "where PatientDoctor.patientID = ? and doctor.id !=?";

        List<Doctor> doctors = jdbcTemplate.query(sql, new Object[]{patientId, doctorId}, new BeanPropertyRowMapper(Doctor.class));

        return doctors;
    }

    public List<Doctor> searchDoctorsByKeyword(String keyword) {
        String sql = "SELECT person.*, doctor.* FROM doctor \n" + "left join person\n"
                + "on doctor.PersonId = person.id\n"
                + "where CONCAT(person.NameFirst,person.NameLast) like ?;";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Doctor> doctors = jdbcTemplate.query(sql, new Object[] { "%"
                + keyword + "%" }, new BeanPropertyRowMapper(Doctor.class));

        return doctors;
    }

    public List<Doctor> searchDoctorsForGrantByKeyword(int patientId, String keyword) {
        String sql = "SELECT person.*, doctor.*\n" +
                "FROM doctor\n" +
                "left join person\n" +
                "on doctor.PersonId = person.id\n" +
                "where doctor.id not in (\n" +
                "SELECT doctor.id\n" +
                "FROM doctor\n" +
                "left join person\n" +
                "on doctor.PersonId = person.id\n" +
                "inner join PatientDoctor\n" +
                "on PatientDoctor.doctorID = doctor.id\n" +
                "where PatientDoctor.patientID = ?\n" +
                ") and CONCAT(person.NameFirst,person.NameLast) LIKE ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Doctor> doctors = jdbcTemplate.query(sql, new Object[] { patientId, "%"
                + keyword + "%" }, new BeanPropertyRowMapper(Doctor.class));

        return doctors;
    }

    public String getDefaultDoctorByPersonId(int personId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "select defaultDoc\n" +
                "from patients\n" +
                "where personID = ?";

        int doctor_id = jdbcTemplate.queryForInt(sql, new Object[] { personId });

        sql = "select CONCAT(pe.nameFirst,' ', pe.nameLast) as doctorName\n" +
                "from doctor\n" +
                "inner join person pe\n" +
                "on pe.id = doctor.personID\n" +
                "WHERE doctor.id = ?";

        String doctor = (String)jdbcTemplate.queryForObject(sql,
                new Object[] { doctor_id }, String.class);

        return doctor;
    }
}
