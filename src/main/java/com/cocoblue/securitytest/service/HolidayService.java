package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.Holiday;

import java.time.LocalDate;
import java.util.List;

public interface HolidayService {
    Boolean judgeHoliday(List<Holiday> holidayList, LocalDate localDate);
    void updateHolidayImf(Holiday holiday);
    void insertHoliday(Holiday holiday);
    List<Holiday> getHolidaysUntilSevenDaysLater();
}
