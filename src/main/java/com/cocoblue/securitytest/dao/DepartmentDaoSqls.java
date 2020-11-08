package com.cocoblue.securitytest.dao;

public class DepartmentDaoSqls {
    public static String SELECT_ALL = "SELECT * FROM department";
    public static String SELECT_DEPARTMENT_BY_NAME = "SELECT * FROM department WHERE name = :name";
}
