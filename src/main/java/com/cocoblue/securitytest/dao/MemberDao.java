package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.Member;
import com.cocoblue.securitytest.dto.MemberRole;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Member> rowMapper = BeanPropertyRowMapper.newInstance(Member.class);
    private SimpleJdbcInsert insertAction;
    private SimpleJdbcInsert insertRoleAction;

    public MemberDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");
        this.insertRoleAction = new SimpleJdbcInsert(dataSource)
                .withTableName("member_role")
                .usingGeneratedKeyColumns("id");
    }

    public Member getMemberByEmail(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        return jdbc.queryForObject(MemberDaoSqls.SELECT_ALL_BY_EMAIL, map, rowMapper);
    }

    public void insertUser(Member member) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        long result = insertAction.executeAndReturnKey(params).longValue();
        SqlParameterSource roleParams = new BeanPropertySqlParameterSource(new MemberRole(result, "ROLE_USER"));
        insertRoleAction.execute(roleParams);
    }

    public void insertAdmin(Member member) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        long result = insertAction.executeAndReturnKey(params).longValue();
        SqlParameterSource roleParams = new BeanPropertySqlParameterSource(new MemberRole(result, "ROLE_ADMIN"));
        insertRoleAction.execute(roleParams);
    }
}