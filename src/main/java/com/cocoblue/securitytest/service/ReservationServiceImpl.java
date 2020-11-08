package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dao.ReservationDao;
import com.cocoblue.securitytest.dto.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements  ReservationService{
    private final ReservationDao reservationDao;

    public ReservationServiceImpl(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Override
    public List<Reservation> getAllConfirmedReservation() {
        return reservationDao.getAllConfirmedReservation();
    }

    @Override
    public List<Reservation> getAllConfirmedReservationByDoctorNo(long doctorNo) {
        return reservationDao.getAllConfirmedReservationByDoctorNo(doctorNo);
    }

    @Override
    public List<Reservation> getAllReservationByCno(long cno) {
        return reservationDao.getAllReservationByCno(cno);
    }

    @Override
    public Boolean makeReservation(Reservation reservation) {
        return reservationDao.makeReservation(reservation);
    }

    @Override
    public Boolean cancelReservation(long rno) {
        return reservationDao.cancelReservation(rno);
    }
}
