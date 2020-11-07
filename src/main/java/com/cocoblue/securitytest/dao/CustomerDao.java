package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.Customer;
import com.cocoblue.securitytest.dto.CustomerRole;
import com.cocoblue.securitytest.service.security.CustomUserDetails;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Customer> rowMapper = BeanPropertyRowMapper.newInstance(Customer.class);
    private final SimpleJdbcInsert insertAction;
    private final SimpleJdbcInsert insertRoleAction;

    public CustomerDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("customer")
                .usingGeneratedKeyColumns("cno");
        this.insertRoleAction = new SimpleJdbcInsert(dataSource)
                .withTableName("customer_role")
                .usingGeneratedKeyColumns("role_id");
    }

    public Customer getCustomerById(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        return jdbc.queryForObject(CustomerDaoSqls.SELECT_ALL_BY_ID, map, rowMapper);
    }

    public Customer getCustomerByCno(long cno) {
        Map<String, Object> map = new HashMap<>();
        map.put("cno", cno);

        return jdbc.queryForObject(CustomerDaoSqls.SELECT_CUSTOMER_BY_CNO, map, rowMapper);
    }

    public void insertUser(Customer customer) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(customer);
        long insertUserId = insertAction.executeAndReturnKey(params).longValue();

        SqlParameterSource roleParams = new BeanPropertySqlParameterSource(new CustomerRole(insertUserId, "ROLE_CUSTOMER"));
        insertRoleAction.execute(roleParams);
    }

    public void insertAdmin(Customer customer) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(customer);
        long insertUserId = insertAction.executeAndReturnKey(params).longValue();
        SqlParameterSource roleParams = new BeanPropertySqlParameterSource(new CustomerRole(insertUserId, "ROLE_ADMIN"));
        insertRoleAction.execute(roleParams);
    }

    public Boolean checkEmail(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        List<Map<String, Object>> queryResult = jdbc.queryForList(CustomerDaoSqls.USER_COUNT_BY_EMAIL, map);

        return queryResult.isEmpty();
    }

    public Customer getLoginUser() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return null;
        }

        return getCustomerByCno(customUserDetails.getCno());
    }
}