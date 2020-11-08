package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.Board;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);
    private final SimpleJdbcInsert insertAction;

    public BoardDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("board")
                .usingColumns("title", "content", "cno", "write_time")
                .usingGeneratedKeyColumns("post_id");
    }

    public List<Board> getPostsAll() {
        return jdbc.query(BoardDaoSqls.SELECT_ALL, new HashMap<>(), rowMapper);
    }

    public List<Board> getPostsByPage(int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", page * 4);
        map.put("end", page * 4 + 4);

        return jdbc.query(BoardDaoSqls.SELECT_POSTS_BY_PAGE, map, rowMapper);
    }

    public Board getPost(String postId) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);

        return jdbc.queryForObject(BoardDaoSqls.SELECT_BY_POST_ID, map, rowMapper);
    }

    public Boolean writePost(Board board) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(board);

        return insertAction.execute(params) > 0;
    }

    public void increaseViewNum(String postId) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);

        jdbc.update(BoardDaoSqls.UPDATE_VIEW_NUMBER, map);
    }

    public long getPostsCount() {
        return (long) jdbc.queryForMap(BoardDaoSqls.SELECT_POSTS_COUNT, new HashMap<>()).get("post_count");
    }

    public List<Board> getPostsByKeyword(String keyword, int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("start", page * 4);
        map.put("end", page * 4 + 4);

        return jdbc.query(BoardDaoSqls.SELECT_POSTS_BY_KEYWORD, map, rowMapper);
    }

    public long getPostsCountByKeyword(String keyword) {
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);

        try {
            return (long) jdbc.queryForMap(BoardDaoSqls.SELECT_COUNT_BY_KEYWORD, map).get("post_count");
        } catch (Exception e) {
            return 0;
        }
    }

    public Boolean deletePost(String postId) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);

        return jdbc.update(BoardDaoSqls.DELETE_BY_POST_ID, map) > 0;
    }

    public Boolean updatePost(Board board) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", board.getPostId());
        map.put("title", board.getTitle());
        map.put("content", board.getContent());

        return jdbc.update(BoardDaoSqls.UPDATE_BY_POST_ID, map) > 0;
    }
}
