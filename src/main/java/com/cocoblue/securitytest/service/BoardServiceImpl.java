package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dao.BoardDao;
import com.cocoblue.securitytest.dto.Board;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardDao boardDao;

    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    @Transactional
    public List<Board> getPostsAll() {
        return boardDao.getPostsAll();
    }

    @Override
    public Board getPost(String id) {
        return boardDao.getPost(id);
    }

    @Override
    public Boolean writePost(Board board) {
        return boardDao.writePost(board);
    }

    @Override
    public void increaseViewNum(String post_id) {
        boardDao.increaseViewNum(post_id);
    }

    @Override
    public List<Board> getPostsByPage(int page) {
        return boardDao.getPostsByPage(page);
    }

    @Override
    public long getPostsCount() {
        return boardDao.getPostsCount();
    }

    @Override
    public List<Board> getPostsByKeyword(String keyword, int page) {
        return boardDao.getPostsByKeyword("%" + keyword + "%", page);
    }

    @Override
    public long getPostsCountByKeyword(String keyword) {
        return boardDao.getPostsCountByKeyword("%" + keyword + "%");
    }

    @Override
    public Boolean deletePost(String postId) {
        return boardDao.deletePost(postId);
    }

    @Override
    public Boolean updatePost(Board board) {
        return boardDao.updatePost(board);
    }
}
