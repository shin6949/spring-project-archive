package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.Reservation;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReservationDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Reservation> rowMapper = BeanPropertyRowMapper.newInstance(Reservation.class);
    private final SimpleJdbcInsert reservationInsertAction;

    public ReservationDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.reservationInsertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation")
                .usingGeneratedKeyColumns("rno");
    }

    public List<Reservation> getAllConfirmedReservation() {
        return jdbc.query(ReservationDaoSqls.SELECT_ALL_CONFIRMED_RESERVATION, new HashMap<String, Object>(), rowMapper);
    }

    public List<Reservation> getAllConfirmedReservationByDoctorNo(long doctorNo, LocalDateTime localDateTime) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("doctorNo", doctorNo);
        params.put("today", localDateTime);


        return jdbc.query(ReservationDaoSqls.SELECT_ALL_CONFIRMED_RESERVATION_BY_DOCTOR_NO, params, rowMapper);
    }


    public Boolean makeReservation(Reservation reservation) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(reservation);

        // Insert가 완료되면 true
        return reservationInsertAction.execute(params) > 0;
    }

    public Boolean cancelReservation(long rno) {
        Map<String, Object> map = new HashMap<>();
        map.put("rno", rno);

        // Update가 완료되면 true
        return jdbc.update(ReservationDaoSqls.UPDATE_RESERVATION_CONFIRMED_FALSE, map) > 0;
    }

    public long getReservationCount(long cno){
        Map<String, Object> map = new HashMap<>();
        map.put("cno",cno);
        return (long)jdbc.queryForMap(ReservationDaoSqls.SELECT_COUNT_ALL_RESERVATION_BY_CNO,map).get("reservation_count");
    }
}