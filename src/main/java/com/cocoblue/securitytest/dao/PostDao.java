package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.MemberRole;
import com.cocoblue.securitytest.dto.Post;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Post> rowMapper = BeanPropertyRowMapper.newInstance(Post.class);
    private final SimpleJdbcInsert insertAction;

    public PostDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("post")
                .usingGeneratedKeyColumns("id");
    }

    public List<Post> getPostsAll(String boardName) {
        Map<String, Object> map = new HashMap<>();
        map.put("boardName", boardName);

        return jdbc.query(PostDaoSqls.SELECT_ALL_BY_BOARD_NAME, map, rowMapper);
    }

    public Post getPost(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        return jdbc.queryForObject(PostDaoSqls.SELECT_BY_POST_ID, map, rowMapper);
    }

    public Boolean writePost(Post post) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(post);

        if (insertAction.execute(params) > 0) {
            // 게시글이 정상적으로 올라갔다면 true
            return true;
        } else {
            return false;
        }
    }
}
