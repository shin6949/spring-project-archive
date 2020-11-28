package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.Holiday;

import java.time.LocalDate;
import java.util.List;

public interface HolidayService {
    Boolean judgeHoliday(List<LocalDate> holidayList, LocalDate localDate);
    public void updateHolidayImf(Holiday holiday);
    public void insertHoliday(Holiday holiday);
    public List<Holiday> getHolidaysUntilSevenDaysLater();
}
