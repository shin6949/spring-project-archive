package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.AvailableDateDto;
import com.cocoblue.securitytest.dto.Doctor;
import com.cocoblue.securitytest.dto.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<Reservation> getAllConfirmedReservation();
    List<Reservation> getAllConfirmedReservationByDoctorNo(long doctorNo, LocalDateTime localDateTime);
    Boolean makeReservation(Reservation reservation);
    Boolean cancelReservation(long rno);
    List<AvailableDateDto> getAvailableDate();
    List<String> configureAvailableTime(String dateString, Doctor doctor);
    long getReservationCount(long cno);
}
