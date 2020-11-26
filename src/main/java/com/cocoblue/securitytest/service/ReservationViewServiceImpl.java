package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dao.ReservationViewDao;
import com.cocoblue.securitytest.dto.ReservationView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationViewServiceImpl implements ReservationViewService {
    private final ReservationViewDao reservationviewDao;

    public ReservationViewServiceImpl(ReservationViewDao reservationviewDao) {
        this.reservationviewDao = reservationviewDao;
    }

    @Override
    public List<ReservationView> getAllConfiremdReservationByCno(long cno) {return reservationviewDao.getAllConfiremdReservationByCno(cno);}

}
