package com.cocoblue.securitytest.service;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface HolidayService {
    String getItemsFromOpenApi(int year, int month, List<LocalDate> value) throws Exception;
    Boolean judgeHoliday(List<LocalDate> holidayList, LocalDate localDate);
}
