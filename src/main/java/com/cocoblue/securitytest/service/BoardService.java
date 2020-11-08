package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.Board;

import java.util.List;

public interface BoardService {
    public List<Board> getPostsAll();
    public Board getPost(String id);
    public Boolean writePost(Board board);
    public void increaseViewNum(String postId);
    public List<Board> getPostsByPage(int page);
    public long getPostsCount();
    public List<Board> getPostsByKeyword(String keyword, int page);
    public long getPostsCountByKeyword(String keyword);
    public Boolean deletePost(String postId);
    public Boolean updatePost(Board board);
}