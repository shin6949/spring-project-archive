package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAllConfirmedReservation();
    List<Reservation> getAllConfirmedReservationByDoctorNo(long doctorNo);
    List<Reservation> getAllReservationByCno(long cno);
    Boolean makeReservation(Reservation reservation);
    Boolean cancelReservation(long rno);
}
