package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.Doctor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DoctorDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Doctor> rowMapper = BeanPropertyRowMapper.newInstance(Doctor.class);

    public DoctorDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Doctor> getAllDoctors() {
        return jdbc.query(DoctorDaoSqls.SELECT_ALL, new HashMap<>(), rowMapper);
    }

    public List<Doctor> getAllDoctorsByDepartCode(long departmentCode) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("dno", departmentCode);

        return jdbc.query(DoctorDaoSqls.SELECT_ALL_BY_DEPARTMENT_CODE, params, rowMapper);
    }

    public List<Doctor> getAllDoctorsByDepartName(String departmentName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", departmentName);

        return jdbc.query(DoctorDaoSqls.SELECT_ALL_BY_DEPARTMENT_NAME, params, rowMapper);
    }

    public Doctor getDoctorByNo(long doctorNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("doctor_no", doctorNo);

        return jdbc.queryForObject(DoctorDaoSqls.SELECT_DOCTOR_BY_DOCTOR_NO, params, rowMapper);
    }

    public Doctor getDoctorByName(String doctorName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", doctorName);

        return jdbc.queryForObject(DoctorDaoSqls.SELECT_DOCTOR_BY_DOCTOR_NAME, params, rowMapper);
    }
}
