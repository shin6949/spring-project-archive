package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.config.ApplicationConfig;
import com.cocoblue.securitytest.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class ReservationServiceTest {
    @Autowired
    HolidayService holidayService;

    @Autowired
    ReservationService reservationService;

    @Test
    public void getAvailableDateTest() {
        System.out.println(reservationService.getAvailableDate());
    }
}
