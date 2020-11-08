package com.cocoblue.securitytest.dao;

public class DoctorDaoSqls {
    public static String SELECT_ALL = "SELECT * FROM doctor";
    public static String SELECT_ALL_BY_DEPARTMENT_CODE = "SELECT * FROM doctor WHERE dno = :dno";
    public static String SELECT_ALL_BY_DEPARTMENT_NAME = "SELECT do.doctor_no, do.name, do.dno FROM doctor do JOIN department de ON do.dno = de.dno WHERE de.name = :name";
    public static String SELECT_DOCTOR_BY_DOCTOR_NO = "SELECT * FROM doctor WHERE doctor_no = :doctor_no";
    public static String SELECT_DOCTOR_BY_DOCTOR_NAME = "SELECT * FROM doctor WHERE name = :name";
}
