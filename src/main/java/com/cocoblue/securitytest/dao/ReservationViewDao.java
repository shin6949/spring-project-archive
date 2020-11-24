package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.Reservation;
import com.cocoblue.securitytest.dto.ReservationView;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReservationViewDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<ReservationView> rowMapper = BeanPropertyRowMapper.newInstance(ReservationView.class);

    public ReservationViewDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<ReservationView> getAllConfiremdReservationByCno(long cno){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cno",cno);
        return jdbc.query(ReservationViewDaoSqls.SELECT_ALL_RESERVATION_BY_CNO,params,rowMapper);
    }

}
