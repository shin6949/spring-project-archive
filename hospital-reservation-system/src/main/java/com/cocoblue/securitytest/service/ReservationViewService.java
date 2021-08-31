package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.ReservationView;

import java.util.List;

public interface ReservationViewService {
    List<ReservationView> getAllReservationByCno(long cno, int page);
}
