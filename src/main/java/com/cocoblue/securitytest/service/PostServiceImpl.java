package com.cocoblue.securitytest.service;

import java.util.List;
import com.cocoblue.securitytest.dto.Post;
import com.cocoblue.securitytest.dao.PostDao;

public class PostServiceImpl implements PostService {
    @Override
    public List<Post> listAll() {
        return PostDao.getInstance().selectAll();
    }

    @Override
    public void insert(Post post) {
        PostDao.getInstance().insert(post);
    }

    @Override
    public Post read(Long number) {
        // 조회 시 조회수 증가
        PostDao dao = PostDao.getInstance();
        dao.updateview_number(number);
        return dao.selectnumber(number);
    }

    @Override
    public void modify(Post post) {
        PostDao.getInstance().update(post);
    }

    @Override
    public void delete(Long number) {
        PostDao.getInstance().delete(number);
    }
}
