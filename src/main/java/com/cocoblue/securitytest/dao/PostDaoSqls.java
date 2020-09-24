package com.cocoblue.securitytest.dao;

public class PostDaoSqls {
    public static String SELECT_ALL_BY_BOARD_NAME = "SELECT p.id as id, p.title as title, p.content as content, p.writer_id as writer_id, p.write_time as write_time, p.board_id as board_id, p.view_number as view_number, m.name as writer_name, b.name as board_name\n" +
            "FROM post as p\n" +
            "JOIN board as b\n" +
            "ON p.board_id = b.id\n" +
            "JOIN member as m\n" +
            "ON p.writer_id = m.id\n" +
            "WHERE b.name = :boardName";
}
