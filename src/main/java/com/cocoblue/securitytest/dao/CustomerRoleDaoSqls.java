package com.cocoblue.securitytest.dao;

public class CustomerRoleDaoSqls {
    public static final String SELECT_ALL_BY_ID = "SELECT cr.role_id, cr.cno, cr.role_name FROM customer_role cr JOIN customer c ON cr.cno = c.cno WHERE c.id = :id";
}