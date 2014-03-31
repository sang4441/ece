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
		String sql = "SELECT person.*, doctor.PersonId FROM doctor \n"
				+ "left join person\n" + "on doctor.PersonId = person.id\n"
				+ "where doctor.id = ?;";

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
