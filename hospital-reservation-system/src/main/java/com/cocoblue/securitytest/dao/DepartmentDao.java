package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.Department;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DepartmentDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Department> rowMapper = BeanPropertyRowMapper.newInstance(Department.class);

    public DepartmentDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Department> getAllDepartment() {
        return jdbc.query(DepartmentDaoSqls.SELECT_ALL, new HashMap<>(), rowMapper);
    }

    public Department getDepartmentByName(String departmentName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", departmentName);

        return jdbc.queryForObject(DepartmentDaoSqls.SELECT_DEPARTMENT_BY_NAME, params, rowMapper);
    }
}
