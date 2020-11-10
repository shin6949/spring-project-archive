package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dao.ReservationDao;
import com.cocoblue.securitytest.dto.Reservation;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements  ReservationService{
    private final ReservationDao reservationDao;
    private final HolidayService holidayService;

    public ReservationServiceImpl(ReservationDao reservationDao, HolidayService holidayService) {
        this.reservationDao = reservationDao;
        this.holidayService = holidayService;
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

    @Override
    public List<String> getAvailableDate() {
        List<LocalDate> holidayList = null;

        try {
            if(holidayService.getItemsFromOpenApi(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue(), holidayList) != null) {
                return configureAvailableDate(null);
            }
            System.out.println(holidayList);

        } catch (Exception e) {
            return configureAvailableDate(null);
        }

        return configureAvailableDate(holidayList);
    }

    private List<String> configureAvailableDate(List<LocalDate> holidayList) {
        List<String> availableDateList = new ArrayList<String>();

        for(int i = 1; i < 8; i++) {
            LocalDate plusDate = LocalDate.now().plusDays(i);
            Boolean judgeResult = holidayService.judgeHoliday(null, plusDate);

            if(!judgeResult) {
                availableDateList.add(plusDate.toString());
            }
        }

        return availableDateList;
    }
}
