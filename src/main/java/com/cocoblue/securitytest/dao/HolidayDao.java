package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.CustomerRole;
import com.cocoblue.securitytest.dto.Holiday;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HolidayDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Holiday> rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
    private final SimpleJdbcInsert insertAction;

    public HolidayDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("holiday");
    }

    public void updateHolidayImf(Holiday holiday) {
        Map<String, Object> param = new HashMap<>();
        param.put("holidayDate", holiday.getHolidayDate());

        jdbc.update(HolidayDaoSqls.UPDATE_HOLIDAY_DATE, param);
    }

    public void insertHoliday(Holiday holiday) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(holiday);
        insertAction.execute(params);
    }

    public List<Holiday> getHolidaysUntilSevenDaysLater() {
        return jdbc.query(HolidayDaoSqls.SELECT_UNTIL_SEVEN_DAYS_LATER, new HashMap<>(), rowMapper);
    }
}
