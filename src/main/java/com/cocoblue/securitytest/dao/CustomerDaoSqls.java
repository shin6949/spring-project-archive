package com.cocoblue.securitytest.dao;

public class CustomerDaoSqls {
    public static final String SELECT_ALL_BY_ID = "SELECT * FROM customer WHERE id = :id";
    public static final String USER_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM customer WHERE id = :id GROUP BY id";
    public static final String SELECT_CUSTOMER_BY_CNO = "SELECT * FROM customer WHERE cno = :cno";
}
