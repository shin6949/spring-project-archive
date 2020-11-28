package com.cocoblue.securitytest.service;

import java.time.LocalDate;
import java.util.List;

import com.cocoblue.securitytest.dao.HolidayDao;
import com.cocoblue.securitytest.dto.Holiday;
import org.springframework.stereotype.Service;

@Service
public class HolidayServiceImpl implements HolidayService {
    private final HolidayDao holidayDao;

    public HolidayServiceImpl(HolidayDao holidayDao) {
        this.holidayDao = holidayDao;
    }

    @Override
    public Boolean judgeHoliday(List<Holiday> holidayList, LocalDate localDate) {
        // 일요일인지 판단
        if(localDate.getDayOfWeek().getValue() == 7) {
            return true;
        }

        // 만일, 인터넷 등의 문제로 받아오지 못한 경우 일단 평일로 인정함.
        if(holidayList == null) {
            return false;
        }

        for(Holiday holiday : holidayList) {
            if(holiday.getHolidayDate().equals(localDate)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateHolidayImf(Holiday holiday) {
        holidayDao.updateHolidayImf(holiday);
    }

    @Override
    public void insertHoliday(Holiday holiday) {
        holidayDao.insertHoliday(holiday);
    }

    @Override
    public List<Holiday> getHolidaysUntilSevenDaysLater() {
        return holidayDao.getHolidaysUntilSevenDaysLater();
    }
}
