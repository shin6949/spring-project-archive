package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.CustomerRole;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerRoleDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<CustomerRole> rowMapper = BeanPropertyRowMapper.newInstance(CustomerRole.class);

    public CustomerRoleDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<CustomerRole> getRolesByEmail(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        return jdbc.query(CustomerRoleDaoSqls.SELECT_ALL_BY_ID, map, rowMapper);
    }
}
