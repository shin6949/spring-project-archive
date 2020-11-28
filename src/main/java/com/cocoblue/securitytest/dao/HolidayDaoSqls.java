package com.cocoblue.securitytest.dao;

public class HolidayDaoSqls {
    public static String UPDATE_HOLIDAY_DATE = "UPDATE holiday SET holiday_date = :holidayDate, reg_time = now() WHERE holiday_date = :holidayDate";
    public static String INSERT_HOLIDAY = "INSERT INTO holiday VALUES(:holidayDate, 0, now())";
    public static String SELECT_UNTIL_SEVEN_DAYS_LATER = "SELECT * FROM holiday WHERE holiday_date > now() AND holiday_date < DATE_ADD(now(), INTERVAL 7 DAY) ";
}
